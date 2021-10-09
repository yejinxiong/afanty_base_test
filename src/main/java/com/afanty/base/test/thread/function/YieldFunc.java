package com.afanty.base.test.thread.function;

/**
 * <p>
 * 测试线程礼让方法
 * 礼让不一定成功，线程重新金正资源，看cpu心情
 * </p>
 *
 * @author yejx
 * @date 2021/5/29
 */
public class YieldFunc implements Runnable {
    /**
     * 礼让成功：A-->开始执行，B-->开始执行   或者  B-->开始执行，A-->开始执行
     * 礼让失败：A-->开始执行，A-->结束执行   或者  B-->开始执行，B-->结束执行
     *
     * @param args
     */
    public static void main(String[] args) {
        YieldFunc t = new YieldFunc();
        new Thread(t, "A").start();
        new Thread(t, "B").start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-->开始执行");
        Thread.yield(); // 礼让
        System.out.println(Thread.currentThread().getName() + "-->停止执行");
    }
}
