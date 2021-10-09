package com.afanty.base.test.thread.function;

import java.text.SimpleDateFormat;

/**
 * <p>
 * 测试线程休眠状态
 * <p>
 * 1.模拟延时：放大问题的发生性
 * 2.模拟倒计时
 * </p>
 *
 * @author yejx
 * @date 2021/5/29
 */
public class SleepFunc implements Runnable {

    private int ticketNum = 10;

    public static void main(String[] args) throws Exception {
        /*
          1.模拟延时
         */
        SleepFunc ticket = new SleepFunc();
        new Thread(ticket, "张三").start();
        new Thread(ticket, "李四").start();
        new Thread(ticket, "赵武").start();

        /*
         * 2.模拟倒计时
         */
        int i = 10;
        while (i > 0) {
            System.out.println(i);
            Thread.sleep(1000);
            i--;
        }

        /*
         * 3.打印当前时间
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("开始时间：" + currentTimeMillis);
        long deadLine = sdf.parse("2021-05-29 10:58:00").getTime();
        System.out.println("结束时间：" + deadLine);
        while (currentTimeMillis < deadLine) {
            System.out.println("北京时间：" + sdf.format(currentTimeMillis));
            Thread.sleep(1000);
            currentTimeMillis = System.currentTimeMillis();
        }
    }

    @Override
    public void run() {
        while (ticketNum > 0) {
            // 模拟延时
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-->买到第" + ticketNum-- + "张票");
        }
    }
}
