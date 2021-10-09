package com.afanty.base.test.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 可重入锁
 * </p>
 *
 * @author yejx
 * @date 2021/5/30
 */
public class Lock {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();

        new Thread(buyTicket, "张三").start();
        new Thread(buyTicket, "李四").start();
        new Thread(buyTicket, "王五").start();
    }
}

class BuyTicket implements Runnable {

    // 定义lock锁
    private final ReentrantLock LOCK = new ReentrantLock();
    private int tickets = 10;

    @Override
    public void run() {
        // 加锁
        LOCK.lock();
        try {
            while (tickets > 0) {
                try {
                    Thread.sleep(1000);
                    System.out.println("用户：" + Thread.currentThread().getName() + " 票号：" + tickets--);
                } catch (InterruptedException e) {
                    System.out.println("sleep异常");
                }
            }
        } catch (Exception e) {
            System.out.println("lock异常");
        } finally {
            LOCK.unlock();  // 解锁
        }
    }
}
