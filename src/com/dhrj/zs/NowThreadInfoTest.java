package com.dhrj.zs;


import com.dhrj.zs.base.MyThread;

/**
 * created by on 2022/4/23
 * 描述：获取线程对象 + 获取线程对象的名字 + 修改线程对象的名字
 *
 * @author ZSAndroid
 * @create 2022-04-23-21:18
 */
public class NowThreadInfoTest {
    public static void main(String[] args) {

        System.out.println("当前线程对象===" + Thread.currentThread());
        System.out.println("当前线程对象的名称===" + Thread.currentThread().getName());

        MyThread thread1 = new MyThread();
        System.out.println("thread1默认线程名==" + thread1.getName());

        thread1.setName("thread1");
        System.out.println("thread1修改后线程名==" + thread1.getName());

    }
}
