package com.afanty.base.test.zookeeper.case2;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * 分布式锁
 * 业务逻辑：
 * 1. 接收到请求后，在/locks节点下创建一个临时有序节点
 * 2. 判断自己是不是当前节点下面最小的节点：是，则获取到锁，反之，对前一个节点进行监听
 * 3. 获取到锁，处理完业务后，delete节点释放锁，然后下面的节点将收到通知，重复第二步的判断
 * </p>
 *
 * @author yejx
 * @date 2021/12/6
 */
public class DistributeLock {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributeLock.class);

    private String cluster = "192.168.30.101:2181,192.168.30.102:2181,192.168.30.103:2181";

    private int timeout = 4000;

    private ZooKeeper zooKeeper;

    /**
     * 根节点路径
     */
    private String rootPath = "/locks";

    /**
     * 子节点前缀
     */
    private String subPrefix = "/seq-";

    private CountDownLatch connectLatch = new CountDownLatch(1);

    private CountDownLatch waitLatch = new CountDownLatch(1);

    /**
     * 当前节点绝对路径
     */
    private String currentAbsoutePath;

    /**
     * 前一节点绝对路径
     */
    private String preAbsoutePath;

    public DistributeLock() throws IOException, InterruptedException, KeeperException {
        // 获取连接
        zooKeeper = new ZooKeeper(cluster, timeout, watchedEvent -> {
            // 该代码用于getChildren()测试方法
            System.out.println("--------------------- 监听开始 ---------------------");
            try {
                List<String> children = zooKeeper.getChildren(rootPath, true);
                for (String child : children) {
                    System.out.println("节点：" + child);
                }
                System.out.println("--------------------- 监听结束 ---------------------");
            } catch (KeeperException e) {
                LOGGER.error("监听节点异常KeeperException：{}", e.getMessage());
            } catch (InterruptedException e) {
                LOGGER.error("监听节点异常InterruptedException：{}", e.getMessage());
            }
            // connectLatvh 如果连接上zookeeper，需要释放
            // 监听器的状态为“已连接”
            if (watchedEvent.getState() == Watcher.Event.KeeperState.SyncConnected) {
                connectLatch.countDown();
            }

            // waitLatch 需要释放
            // 监听的类型是节点删除 && 监听节点是前一个节点
            // 发生了 preAbsoutePath 的删除事件
            if (watchedEvent.getType() == Watcher.Event.EventType.NodeDeleted && watchedEvent.getPath().equals(preAbsoutePath)) {
                waitLatch.countDown();
            }
        });

        // 等待zk正常连接后，程序继续往下执行
        connectLatch.await();

        // 判断根节点/locks是否存在，不存在，则创建一个永久节点
        Stat stat = zooKeeper.exists(rootPath, false);
        if (null == stat) {
            zooKeeper.create(rootPath, "locks".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

    }

    /**
     * 对zookeeper加锁：加一个子节点，下一个线程进来的时候会查询之前的线程是否释放锁了（前一个节点是否删除：监听前一节点的删除事件）
     */
    public void zkLock() {
        try {
            // 创建临时的有序节点
            currentAbsoutePath = zooKeeper.create(rootPath + subPrefix, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            // 判断创建的节点是否是最小的有序节点，如果是，则获取到锁，反之，监听他序号前一个节点
            List<String> children = zooKeeper.getChildren(rootPath, false);
            // 如果children只有一个值，那就直接获取锁；如果有多个节点，需要判断谁最小
            if (null == children) {
                LOGGER.error("对zookeeper加锁：没有子节点");
            } else if (children.size() == 1) {
                LOGGER.info("对zookeeper加锁：只有一个节点，直接获取锁");
            } else {
                Collections.sort(children);
                // 获取节点名称：seq-0000000
                String currentRelativePath = currentAbsoutePath.substring((rootPath + "/").length());
                // 通过seq-0000000获取到在集合children当中的位置
                int index = children.indexOf(currentRelativePath);
                if (index == -1) {
                    LOGGER.error("对zookeeper加锁：数据异常");
                } else if (index == 0) {
                    LOGGER.info("对zookeeper加锁：{}下只有一个节点，可以获取锁", rootPath);
                } else {
                    // 需要监听currentAbsoutePath前一个节点的变化
                    preAbsoutePath = rootPath + "/" + children.get(index - 1);
                    // 在preAbsoutePath上注册监听器, 当preAbsoutePath被删除时，zookeeper会回调监听器的process方法
                    zooKeeper.getData(preAbsoutePath, true, null);
                    // 等待监听
                    waitLatch.await();
                }
            }
        } catch (KeeperException e) {
            LOGGER.error("zookeeper加锁异常KeeperException：{}", e.getMessage());
        } catch (InterruptedException e) {
            LOGGER.error("zookeeper加锁异常InterruptedException：{}", e.getMessage());
        }
    }

    /**
     * 解锁
     */
    public void unZkLock() {
        // 删除节点
        try {
            zooKeeper.delete(currentAbsoutePath, -1);
        } catch (InterruptedException e) {
            LOGGER.error("zookeeper解锁异常InterruptedException：{}", e.getMessage());
        } catch (KeeperException e) {
            LOGGER.error("zookeeper解锁异常KeeperException：{}", e.getMessage());
        }
    }

}
