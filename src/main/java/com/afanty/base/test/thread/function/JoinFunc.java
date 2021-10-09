package com.afanty.base.test.thread.function;

/**
 * <p>
 * 测试join方法：插队
 * <p>
 * 会让线程阻塞，不建议使用
 * </p>
 *
 * @author yejx
 * @date 2021/5/29
 */
public class JoinFunc implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        JoinFunc joinFunc = new JoinFunc();
        Thread thread = new Thread(joinFunc);
        thread.start();

        for (int i = 1; i <= 200; i++) {
            if (i == 51) {
                thread.join(); // 插队
            }
            System.out.println("main执行........" + i);
        }
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println("线程插队-->" + i);
        }
    }
}
