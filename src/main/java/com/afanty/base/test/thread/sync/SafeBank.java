package com.afanty.base.test.thread.sync;

import lombok.Data;

/**
 * <p>
 * 安全取钱
 * </p>
 *
 * @author yejx
 * @date 2021/5/30
 */
public class SafeBank {
    public static void main(String[] args) {
        Account2 account2 = new Account2(100, "9550");
        Drawing2 you = new Drawing2("你", account2, 50);
        Drawing2 girl = new Drawing2("女朋友", account2, 100);

        you.start();
        girl.start();
    }
}

/**
 * 账户
 */
@Data
class Account2 {

    // 余额
    private int money;
    // 卡号
    private String cardNo;

    public Account2(int money, String cardNo) {
        this.money = money;
        this.cardNo = cardNo;
    }
}

/**
 * 银行：模拟取款
 */
class Drawing2 extends Thread {
    // 账户
    private Account2 account2;
    // 取了多少钱
    private int drawingMoney;
    // 现在手里有多少钱
    private int nowMoney;

    public Drawing2(String name, Account2 account2, int drawingMoney) {
        super(name);
        this.account2 = account2;
        this.drawingMoney = drawingMoney;
    }

    /**
     * 取钱
     * synchronized：锁代码块，默认锁的是this，此处锁的是account2
     * 锁的对象是变化的量，即需要增删改的对象
     */
    @Override
    public void run() {
        // 锁的对象是变化的量，即需要增删改的对象
        synchronized (account2) {
            // 判断有没有钱
            if (account2.getMoney() - drawingMoney < 0) {
                System.out.println("余额不足：取款人（" + Thread.currentThread().getName() + "） 取款金额（" + drawingMoney + "） 账户余额（" + account2.getMoney() + "）");
                return;
            }
            // 模拟网络延时
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 卡内余额 = 卡内余额 - 你取的钱
            account2.setMoney(account2.getMoney() - drawingMoney);
            // 你手里的钱
            nowMoney = nowMoney + drawingMoney;

            System.out.println("账户：" + account2.getCardNo() + " 余额：" + account2.getMoney());
            System.out.println("取款人：" + this.getName() + " 手里的钱：" + nowMoney);
        }
    }
}
