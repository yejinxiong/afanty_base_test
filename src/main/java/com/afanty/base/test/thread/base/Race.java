package com.afanty.base.test.thread.base;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * 龟兔赛跑
 * 1.定义赛道距离，离终点越来越近
 * 2.判断比赛是否结束
 * 3.打印出胜利者
 * 4.兔子中途睡觉
 * </p>
 *
 * @author yejx
 * @date 2021/5/24
 */
public class Race implements Runnable {

    /**
     * 胜利者
     */
    private String winner;

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 1; i <= 100; i++) {
                // 模拟兔子睡觉：me每20米睡0.2秒
                if ("兔子".equals(Thread.currentThread().getName()) && i % 20 == 0) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 游戏是否结束
                boolean gameOver = this.gameOver(i);
                if (gameOver) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "--> " + i + " 米");
            }
        }

    }

    /**
     * 游戏是否结束
     *
     * @param distance 已跑距离
     * @return
     */
    public boolean gameOver(int distance) {
        if (StringUtils.isNotEmpty(winner)) {
            return true;
        } else {
            if (distance >= 100) {
                winner = Thread.currentThread().getName();
                System.out.println("终点==================");
                System.out.println("冠军：" + winner);
                return true;
            }
        }
        return false;
    }
}
