package com.dhrj.zs.base;

/**
 * created by on 2022/4/23
 * 描述：
 *
 * @author ZSAndroid
 * @create 2022-04-23-18:44
 */

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 1000; i++) {
            System.out.println("子线程----->" + i);
        }
    }
}
