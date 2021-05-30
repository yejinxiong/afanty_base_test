package com.afanty.base.test.thread.deadlock;

/**
 * <p>
 *
 * </p>
 *
 * @author yejx
 * @date 2021/5/30
 */
public class DeadLock {
    public static void main(String[] args) {
        Makeup xiaoHong = new Makeup(0, "小红");
        Makeup xiaoBai = new Makeup(1, "小白");

        new Thread(xiaoHong).start();
        new Thread(xiaoBai).start();
    }
}

/**
 * 口红
 */
class Lipstick {

}

/**
 * 镜子
 */
class Mirror {

}

class Makeup implements Runnable {

    // 需要的资源只有一份，用static来保证只有一份
    private static final Lipstick lipstick = new Lipstick();
    private static final Mirror mirror = new Mirror();

    private int choice; // 选择
    private String userName;    // 使用此化妆品的人

    public Makeup(int choice, String userName) {
        this.choice = choice;
        this.userName = userName;
    }

    @Override
    public void run() {
        // 化妆
        try {
            makeup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makeup() throws Exception {
        switch (choice) {
            case 0:
                synchronized (lipstick) {
                    System.out.println("\"" + this.userName + "\"获得口红的锁");
                    Thread.sleep(1000);
                    /**
                     * 一秒钟后想获得镜子
                     * 死锁：别人拿不到镜子
                     */
//                    synchronized (mirror) {
//                        System.out.println("\"" + this.userName + "\"获得镜子的锁");
//                    }
                }
                synchronized (mirror) {
                    System.out.println("\"" + this.userName + "\"获得镜子的锁");
                }
                break;
            case 1:
                synchronized (mirror) {
                    System.out.println("\"" + this.userName + "\"获得镜子的锁");
                    Thread.sleep(1000);
                    /**
                     * 一秒钟后想获得口红
                     * 死锁：别人拿不到口红
                     */
//                    synchronized (lipstick) {
//                        System.out.println("\"" + this.userName + "\"获得口红的锁");
//                    }
                }
                synchronized (lipstick) {
                    System.out.println("\"" + this.userName + "\"获得口红的锁");
                }
                break;
        }
    }
}
