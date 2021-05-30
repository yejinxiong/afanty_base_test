package com.afanty.base.test.thread.base;

/**
 * 多线程同时操作同一份资源：抢火车票
 *
 * 发现问题：多线程在操作同一个资源的情况下，线程不安全，数据紊乱（一张票被多个人买到）
 *
 * @author yejx
 * @date 2021/5/24
 */
public class MultiThreadTickets implements Runnable {

    private int ticketNum = 10;

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

    public static void main(String[] args) {
        MultiThreadTickets ticket = new MultiThreadTickets();
        new Thread(ticket, "张三").start();
        new Thread(ticket, "李四").start();
        new Thread(ticket, "赵武").start();
    }
}
