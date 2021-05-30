package com.afanty.base.test.thread.function;

/**
 * <p>
 * 测试线程停止状态
 * <p>
 * 1.建议线程正常停止-->利用次数，不建议死循环
 * 2.建议使用标志位-->设置一个标志位
 * 3.不要使用stop或者destroy等过时或者官方不建议使用的方法
 * </p>
 *
 * @author yejx
 * @date 2021/5/29
 */
public class StopFunc implements Runnable {

    /**
     * 设置一个停止标志位
     */
    private boolean flag = true;

    @Override
    public void run() {
        int i = 1;
        while (flag) {
            System.out.println("run---------------" + i++);
        }
    }

    /**
     * 设置一个公开的方法停止线程，转换标志位
     */
    public void customStop() {
        this.flag = false;
    }

    public static void main(String[] args) {
        StopFunc stopFunc = new StopFunc();
        new Thread(stopFunc).start();

        for (int i = 1; i <= 200; i++) {
            System.out.println("main......" + i);
            if (i == 50) {
                // 调用stop方法，切换标志位，让线程停止
                stopFunc.customStop();
                System.out.println("main......线程该停止了");
            }
        }


    }
}
