package com.afanty.base.test.thread.sync;

/**
 * <p>
 * 安全的买票
 * </p>
 *
 * @author yejx
 * @date 2021/5/30
 */
public class SafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket2 buyTicket2 = new BuyTicket2();
        Thread t1 = new Thread(buyTicket2, "张三");
        Thread t2 = new Thread(buyTicket2, "李四");
        Thread t3 = new Thread(buyTicket2, "赵武");

        t1.start();
        t2.start();
        t3.start();
    }
}

class BuyTicket2 implements Runnable {

    // 总票数
    private int ticketNums = 10;
    // 标志位
    private boolean flag = true;

    @Override
    public void run() {
        // 买票
        while (flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解决线程不安全
     * synchronized：锁方法，此处锁的是this，即BuyTicket
     */
    private synchronized void buy() throws InterruptedException {
        // 判断是否有票
        if (ticketNums <= 0) {
            flag = false;
            return;
        }
        Thread.sleep(100);
        // 买票
        System.out.println("用户：" + Thread.currentThread().getName() + " 票号：" + ticketNums--);
    }
}
