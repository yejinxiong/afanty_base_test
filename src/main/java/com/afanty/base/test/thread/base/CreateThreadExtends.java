package com.afanty.base.test.thread.base;

/**
 * <p>
 * 创建线程方式一：继承Thread类，并重写run()方法
 * 调用start()方法开启多线程
 * <p>
 * 注意：线程开启，不一定最先执行，有cpu调度
 * </p>
 *
 * @author yejx
 * @date 2021/5/21
 */
public class CreateThreadExtends extends Thread {

    /**
     * main线程--主线程
     *
     * @param args
     */
    public static void main(String[] args) {
        // 创建一个线程对象
        CreateThreadExtends threadExtends = new CreateThreadExtends();

//        // 调用run()方法开启单线程（一步一步执行）
//        threadExtends.run();

        // 调用start()方法开启多线程（交替执行）
        threadExtends.start();

        for (int i = 1; i <= 200; i++) {
            System.out.println("main线程+++++++++++++++++++++" + i);
        }
    }

    /**
     * run方法线程体
     */
    @Override
    public void run() {
        for (int i = 1; i <= 200; i++) {
            System.out.println("run方法线程====================" + i);
        }
    }
}
