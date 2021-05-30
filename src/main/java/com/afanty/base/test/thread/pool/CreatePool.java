package com.afanty.base.test.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * 创建线程池
 * </p>
 *
 * @author yejx
 * @date 2021/5/31
 */
public class CreatePool {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();

        // 1.创建服务，创建线程池
        // newFixedThreadPool：参数为线程池大小
        ExecutorService service = Executors.newFixedThreadPool(10);

        // 2.执行
        service.execute(myThread);
        service.execute(myThread);
        service.execute(myThread);
        service.execute(myThread);

        // 3.关闭连接池
        service.shutdown();
    }
}

class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
