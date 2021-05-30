package com.afanty.base.test.thread.sync;

/**
 * <p>
 * 不安全的买票
 * </p>
 *
 * @author yejx
 * @date 2021/5/30
 */
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        Thread t1 = new Thread(buyTicket, "张三");
        Thread t2 = new Thread(buyTicket, "李四");
        Thread t3 = new Thread(buyTicket, "赵武");

        t1.start();
        t2.start();
        t3.start();
    }
}

class BuyTicket implements Runnable {

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

    private void buy() throws InterruptedException {
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
