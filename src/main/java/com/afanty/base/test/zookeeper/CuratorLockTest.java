package com.afanty.base.test.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 使用分布式锁框架
 * </p>
 *
 * @author yejx
 * @date 2021/12/13
 */
public class CuratorLockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CuratorLockTest.class);

    private static String cluster = "192.168.30.101:2181,192.168.30.102:2181,192.168.30.103:2181";

    private static int connectionTimeoutMs = 4000;

    private static int sessionTimeoutMs = 4000;

    /**
     * 根节点路径
     */
    private static String rootPath = "/locks";

    public static void main(String[] args) {
        // 创建分布式锁1
        InterProcessMutex lock1 = new InterProcessMutex(getCuratorFramework(), rootPath);
        // 创建分布式锁2
        InterProcessMutex lock2 = new InterProcessMutex(getCuratorFramework(), rootPath);

        new Thread(() -> {
            try {
                lock1.acquire();
                System.out.println("线程1：获取到锁");
                // 测试锁重入
                lock1.acquire();
                System.out.println("线程1：再次获取到锁");
                Thread.sleep(5000);
            } catch (Exception e) {
                LOGGER.error("线程1分布式锁异常：{}", e.getMessage());
            } finally {
                try {
                    lock1.release();
                    System.out.println("线程1：释放锁");
                    lock1.release();
                    System.out.println("线程1：再次释放锁");
                } catch (Exception e) {
                    LOGGER.error("线程1释放锁异常：{}", e.getMessage());
                }
            }
        }).start();

        new Thread(() -> {
            try {
                lock2.acquire();
                System.out.println("线程2：获取到锁");
                lock2.acquire();
                System.out.println("线程2：再次获取到锁");
                Thread.sleep(5000);
            } catch (Exception e) {
                LOGGER.error("线程2分布式锁异常：{}", e.getMessage());
            } finally {
                try {
                    lock2.release();
                    System.out.println("线程2：释放锁");
                    lock2.release();
                    System.out.println("线程2：再次释放锁");
                } catch (Exception e) {
                    LOGGER.error("线程2释放锁异常：{}", e.getMessage());
                }
            }
        }).start();
    }

    private static CuratorFramework getCuratorFramework() {
        // 每3秒钟重试3次
        ExponentialBackoffRetry policy = new ExponentialBackoffRetry(3000, 3);

        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(cluster)
                .connectionTimeoutMs(connectionTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs)
                .retryPolicy(policy).build();
        // 启动客户端
        client.start();
        LOGGER.info("zookeeper客户端启动成功");
        return client;
    }
}
