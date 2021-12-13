package com.afanty.base.test.zookeeper.case2;

import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author yejx
 * @date 2021/12/9
 */
public class DistributeLockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributeLockTest.class);

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        final DistributeLock lock1 = new DistributeLock();
        final DistributeLock lock2 = new DistributeLock();

        new Thread(() -> {
            try {
                lock1.zkLock();
                System.out.println("线程1启动，获取到锁");
                Thread.sleep(5000);
                int i = 1 / 0;
            } catch (InterruptedException e) {
                LOGGER.error("线程1异常：{}", e.getCause());
            } finally {
                lock1.unZkLock();
                System.out.println("线程1释放锁");
            }
        }).start();

        new Thread(() -> {
            try {
                lock2.zkLock();
                System.out.println("线程2启动，获取到锁");
                Thread.sleep(5000);
                int i = 1 / 0;
            } catch (InterruptedException e) {
                LOGGER.error("线程2异常：{}", e.getCause());
            } finally {
                lock2.unZkLock();
                System.out.println("线程2释放锁");
            }
        }).start();

    }
}
