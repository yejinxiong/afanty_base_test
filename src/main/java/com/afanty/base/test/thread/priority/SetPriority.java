package com.afanty.base.test.thread.priority;

/**
 * <p>
 * 测试多线程优先级：默认是5
 * <p>
 * 优先级高的不一定最先执行，只是先执行的概率高一些
 * </p>
 *
 * @author yejx
 * @date 2021/5/30
 */
public class SetPriority {

    public static void main(String[] args) {
        Priority priority = new Priority();
        Thread t1 = new Thread(priority, "t1");
        Thread t2 = new Thread(priority, "t2");
        Thread t3 = new Thread(priority, "t3");
        Thread t4 = new Thread(priority, "t4");
        Thread t5 = new Thread(priority, "t5");
        Thread t6 = new Thread(priority, "t6");

        // 先设置优先级，再启动线程
        t1.start();

        t2.setPriority(1);
        t2.start();

        t3.setPriority(4);
        t3.start();

        t4.setPriority(Thread.MAX_PRIORITY);
        t4.start();

        t5.setPriority(8);
        t5.start();

        t6.setPriority(7);
        t6.start();

    }

}

class Priority implements Runnable {

    @Override
    public void run() {
        System.out.println("线程：" + Thread.currentThread().getName() + " 优先级：" + Thread.currentThread().getPriority());
    }
}
