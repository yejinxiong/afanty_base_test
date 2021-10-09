package com.afanty.base.test.thread.base;

/**
 * <p>
 * 创建线程方式二：实现Runable接口，并重写run()方法
 * 调用start()方法开启多线程
 *
 * </p>
 *
 * @author yejx
 * @date 2021/5/24
 */
public class CreateThreadImplements implements Runnable {

    public static void main(String[] args) {
        // 创建Runable接口的实现类对象
        CreateThreadImplements createThreadImplements = new CreateThreadImplements();
        // 船舰线程对象，通过线程对象开启线程
        Thread thread = new Thread(createThreadImplements);
        thread.start();

        for (int i = 1; i <= 200; i++) {
            System.out.println("main线程+++++++++++++++++++++" + i);
        }
    }

    @Override
    public void run() {
        for (int i = 1; i <= 200; i++) {
            System.out.println("run方法线程====================" + i);
        }
    }
}
