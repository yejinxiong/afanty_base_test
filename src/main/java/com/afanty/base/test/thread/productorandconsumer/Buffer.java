package com.afanty.base.test.thread.productorandconsumer;

import lombok.Data;

/**
 * <p>
 * 生产者-消费者问题1：管程法-用缓冲区解决
 * <p>
 * 生产者、消费者、产品、缓冲区
 * <p>
 * 案例：
 * 肯德基：厨师-前台-顾客
 * 1.前台预存10只鸡
 * 2.后厨做好鸡，给前台：如果前台放不下了，则后厨等待；如果前台没满，则给前台，并通知顾客可以买了
 * 3.客户去前台买鸡：如果前台有鸡，则卖给顾客，并通知后厨送新鸡过来；如果没有，则等待后厨送鸡过来
 * </p>
 *
 * @author yejx
 * @date 2021/5/30
 */
public class Buffer {
    public static void main(String[] args) {
        FrontDesk frontDesk = new FrontDesk();

        Chef chef = new Chef(frontDesk);
        Customer customer = new Customer(frontDesk);

        new Thread(chef).start();
        new Thread(customer).start();

    }
}

/**
 * 生产者：厨师
 */
class Chef implements Runnable {

    private FrontDesk frontDesk;

    public Chef(FrontDesk frontDesk) {
        this.frontDesk = frontDesk;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 30; i++) {
            String type = (i % 2 == 0) ? "脆皮炸鸡" : "蜜汁扒鸡";
            Chicken chicken = new Chicken(i, type);
            try {
                frontDesk.push(chicken);
                System.out.println("生产者+：产品编号：" + i + " 类型：" + type);
            } catch (InterruptedException e) {
                System.out.println("生产异常");
            }
        }
    }
}

/**
 * 消费者：顾客
 */
class Customer implements Runnable {

    private FrontDesk frontDesk;

    public Customer(FrontDesk frontDesk) {
        this.frontDesk = frontDesk;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 30; i++) {
            try {
                Chicken chicken = frontDesk.pop();
                System.out.println("消费者-：产品编号：" + chicken.getChickenId() + " 类型：" + chicken.getType());
            } catch (InterruptedException e) {
                System.out.println("消费异常");
            }
        }
    }
}

/**
 * 产品-鸡
 */
@Data
class Chicken {

    private int chickenId; // 产品编号
    private String type; // 产品类型：蜜汁扒鸡、脆皮炸鸡

    public Chicken(int chickenId, String type) {
        this.chickenId = chickenId;
        this.type = type;
    }
}

/**
 * 缓冲区：前台
 */
class FrontDesk {

    // 需要一个容器大小
    private Chicken[] chickens = new Chicken[10];
    // 容器内产品数量
    private int count = 0;

    /**
     * 生产者放入产品
     *
     * @param chicken
     */
    public synchronized void push(Chicken chicken) throws InterruptedException {
        // 如果容器满了，就等待消费者消费
        if (count == chickens.length) {
            // 通知消费者消费，生产者等待
            this.wait();
        }

        // 如果没有满，就放入产品
        chickens[count] = chicken;
        count++;

        // 通知消费者可以消费了
        this.notifyAll();
    }

    /**
     * 消费者消费产品
     *
     * @return
     */
    public synchronized Chicken pop() throws InterruptedException {
        // 如果没有鸡，不能消费
        if (count == 0) {
            // 消费者等待生产者生产
            this.wait();
        }

        // 如果有鸡，可以消费：取出鸡
        count--;
        Chicken chicken = chickens[count];

        // 吃完了，通知生产者再生产
        this.notifyAll();
        return chicken;
    }

}


