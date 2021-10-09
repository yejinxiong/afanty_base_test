package com.afanty.base.test.thread.sync;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 线程不安全的集合
 * </p>
 *
 * @author yejx
 * @date 2021/5/30
 */
public class SafeList {
    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        Thread.sleep(3000);
        System.out.println(list.size());
    }
}
