package com.afanty.base.test.thread.sync;

import lombok.Data;

/**
 * <p>
 * 不安全取钱
 * </p>
 *
 * @author yejx
 * @date 2021/5/30
 */
public class UnsafeBank {
    public static void main(String[] args) {
        Account account = new Account(100, "9550");
        Drawing you = new Drawing("你", account, 50);
        Drawing girl = new Drawing("女朋友", account, 100);

        you.start();
        girl.start();
    }
}

/**
 * 账户
 */
@Data
class Account {

    // 余额
    private int money;
    // 卡号
    private String cardNo;

    public Account(int money, String cardNo) {
        this.money = money;
        this.cardNo = cardNo;
    }
}

/**
 * 银行：模拟取款
 */
class Drawing extends Thread {
    // 账户
    private Account account;
    // 取了多少钱
    private int drawingMoney;
    // 现在手里有多少钱
    private int nowMoney;

    public Drawing(String name, Account account, int drawingMoney) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    /**
     * 取钱
     */
    @Override
    public void run() {
        // 判断有没有钱
        if (account.getMoney() - drawingMoney < 0) {
            System.out.println("余额不足：取款人（" + Thread.currentThread().getName() + "） 取款金额（" + drawingMoney + "） 账户余额（" + account.getMoney() + "）");
            return;
        }
        // 模拟网络延时
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 卡内余额 = 卡内余额 - 你取的钱
        account.setMoney(account.getMoney() - drawingMoney);
        // 你手里的钱
        nowMoney = nowMoney + drawingMoney;

        System.out.println("账户：" + account.getCardNo() + " 余额：" + account.getMoney());
        System.out.println("取款人：" + this.getName() + " 手里的钱：" + nowMoney);
    }
}
