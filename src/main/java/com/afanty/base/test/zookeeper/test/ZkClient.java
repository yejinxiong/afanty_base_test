package com.afanty.base.test.zookeeper.test;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * zookeeper客户端测试
 * </p>
 *
 * @author yejx
 * @date 2021/11/30
 */
public class ZkClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZkClient.class);

    private static String cluster = "192.168.30.101:2181,192.168.30.102:2181,192.168.30.103:2181";

    private static int timeout = 4000;

    private static ZooKeeper zooKeeper;

    @BeforeAll
    public static void init() {
        try {
            LOGGER.info("zookeeper连接信息：{}", cluster);
            zooKeeper = new ZooKeeper(cluster, timeout, watchedEvent -> {
                LOGGER.info("WatchedEvent：{}", watchedEvent);
                // 该代码用于getChildren()测试方法
                System.out.println("--------------------- 监听开始 ---------------------");
                try {
                    List<String> children = zooKeeper.getChildren("/", true);
                    for (String child : children) {
                        System.out.println("节点：" + child);
                    }
                    System.out.println("--------------------- 监听结束 ---------------------");
                } catch (KeeperException e) {
                    LOGGER.error("监听节点异常KeeperException：{}", e.getMessage());
                } catch (InterruptedException e) {
                    LOGGER.error("监听节点异常InterruptedException：{}", e.getMessage());
                }
            });
        } catch (IOException e) {
            LOGGER.error("zooKeeper初始化异常：{}", e.getMessage());
        }
    }

    @Test
    public void create() {
        try {
            String nodeCreated = zooKeeper.create("/afanty", "jianghanlu".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            LOGGER.error("zooKeeper创建节点异常KeeperException：{}", e.getMessage());
        } catch (InterruptedException e) {
            LOGGER.error("zooKeeper创建节点异常InterruptedException：{}", e.getMessage());
        }

    }

    @Test
    public void getChildren() {
        try {
            List<String> children = zooKeeper.getChildren("/", true);
            for (String child : children) {
                System.out.println("节点：" + child);
            }
            Thread.sleep(Long.MAX_VALUE);
        } catch (KeeperException e) {
            LOGGER.error("监听节点异常KeeperException：{}", e.getMessage());
        } catch (InterruptedException e) {
            LOGGER.error("监听节点异常InterruptedException：{}", e.getMessage());
        }
    }

    @Test
    public void isExist() {
        try {
            Stat exists = zooKeeper.exists("/afanty", false);
            System.out.println(null == exists ? "节点afanty不存在" : "节点afanty存在");
        } catch (KeeperException e) {
            LOGGER.error("判断节点是否存在异常KeeperException：{}", e.getMessage());
        } catch (InterruptedException e) {
            LOGGER.error("判断节点是否存在异常InterruptedException：{}", e.getMessage());
        }
    }
}
