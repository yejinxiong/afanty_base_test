package com.afanty.base.test.thread.productorandconsumer;

/**
 * <p>
 * 生产者-消费者问题2：信号灯法-用标志位解决
 * <p>
 *
 * <p>
 * 案例：
 * 看电视：演员-标志位-观众
 * 1.标志位：是否有电视节目
 * 2.演员：如果有节目，不需要演员表演（标志位为false），则演员等待；如果没有节目，需要演员表演（标志位true），则表演节目，并通知观众观看
 * 3.观众：如果有节目，不需要演员表演（标志位为false），则观众观看，并通知演员表演下一个节目；如果没有节目，需要演员表演（标志位true），则观众等待
 * </p>
 *
 * @author yejx
 * @date 2021/5/30
 */
public class Flag {
    public static void main(String[] args) {
        TV tv = new TV();

        Actor actor = new Actor(tv);
        Audience audience = new Audience(tv);

        new Thread(actor).start();
        new Thread(audience).start();
    }
}

/**
 * 生产者：演员
 */
class Actor implements Runnable {

    private TV tv;

    public Actor(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) {
                try {
                    this.tv.perform("初入职场的我们");
                } catch (InterruptedException e) {
                    System.out.println("节目出错");
                }
            } else {
                try {
                    this.tv.perform("向往的生活");
                } catch (InterruptedException e) {
                    System.out.println("节目出错");
                }
            }
        }
    }
}

/**
 * 消费者：观众
 */
class Audience implements Runnable {

    private TV tv;

    public Audience(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            try {
                this.tv.watch();
            } catch (InterruptedException e) {
                System.out.println("观看出错");
            }
        }
    }
}

/**
 * 产品：节目
 */
class TV {

    // 没有节目：演员表演，观众等待：flag=false
    // 有节目：观众观看，演员等待：flag=true
    private String program; // 节目
    private boolean flag = true; // 标志位

    /**
     * 表演
     *
     * @param program
     */
    public synchronized void perform(String program) throws InterruptedException {
        // 有节目，演员等待观众观看
        if (!flag) {
            this.wait();
        }
        // 没有节目，表演节目，并通知观众观看
        System.out.println("表演节目：" + program);
        this.notifyAll();

        this.program = program;
        this.flag = !this.flag;
    }

    /**
     * 观看
     */
    public synchronized void watch() throws InterruptedException {
        // 没有节目，等待演员表演
        if (flag) {
            this.wait();
        }
        // 有节目，观看节目，并通知演员表演
        System.out.println("观看节目：" + program);
        this.notifyAll();
        this.flag = !this.flag;
    }

}

