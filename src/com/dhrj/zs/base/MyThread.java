package com.dhrj.zs.base;

/**
 * created by on 2022/4/23
 * 描述：在JVM虚拟机中，创建分支栈，在当中开启子线程
 *
 * @author ZSAndroid
 * @create 2022-04-23-17:53
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i <= 1000; i++) {
            System.out.println("子线程----->" + i);
        }
    }
}
