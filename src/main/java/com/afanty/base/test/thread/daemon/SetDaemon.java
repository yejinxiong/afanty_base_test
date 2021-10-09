package com.afanty.base.test.thread.daemon;

/**
 * <p>
 * 守护线程
 * </p>
 *
 * @author yejx
 * @date 2021/5/30
 */
public class SetDaemon {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread t1 = new Thread(god);
        t1.setDaemon(true); // 默认是false表示是用户线程，正常的线程都是用户线程
        t1.start(); // 上帝守护线程启动

        Thread t2 = new Thread(you);
        t2.start();

    }
}

/**
 * 上帝
 */
class God implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("上帝保佑着你");
        }
    }
}

/**
 * 你
 */
class You implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 36500; i++) {
            System.out.println("你一生都开心的活着");
        }
        System.out.println("========Goodbye World!=======");
    }
}
