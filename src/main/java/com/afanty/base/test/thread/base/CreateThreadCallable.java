package com.afanty.base.test.thread.base;

import java.util.concurrent.*;

/**
 * <p>
 * 创建线程方式三：实现Callable接口，并重写call()方法
 * <p>
 * callable好处：
 * 1.可以定义返回值
 * 2.可以抛出异常
 * </p>
 *
 * @author yejx
 * @date 2021/5/25
 */
public class CreateThreadCallable implements Callable<Boolean> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CreateThreadCallable t1 = new CreateThreadCallable();
        CreateThreadCallable t2 = new CreateThreadCallable();
        // 创建并执行服务
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // 提交执行
        Future<Boolean> submit1 = executorService.submit(t1);
        Future<Boolean> submit2 = executorService.submit(t2);
        // 获取结果
        Boolean aBoolean1 = submit1.get();
        Boolean aBoolean2 = submit2.get();
        // 关闭服务
        executorService.shutdownNow();

    }

    @Override
    public Boolean call() {
        for (int i = 1; i <= 100; i++) {
            System.out.println("call方法线程====================" + i);
        }
        return true;
    }
}
