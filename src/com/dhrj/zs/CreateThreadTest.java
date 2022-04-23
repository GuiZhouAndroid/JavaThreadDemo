package com.dhrj.zs;

/**
 * created by on 2022/4/23
 * 描述：线程测试类
 *
 * @author ZSAndroid
 * @create 2022-04-23-17:52
 */
public class CreateThreadTest {
    public static void main(String[] args) {
        //子线程开启后，在当前线程或主线程中调用run方法，等同于调用普通的Java类中的方法--->某种意义上来说属于单线程
        //new MyThread().run();

        //start调用结束后，主线程继续执行，同时在JVM中分配新的栈内存空间，并开启子线程调用run方法进行压栈
        //run结束后执行弹栈，停止子线程--->(在主子线程同时进行，互不干扰，相互独立，因此是并发运行的)

        //显式对象调用，在栈内存中有引用变量myThread指向堆内存new MyThread()
//        MyThread myThread = new MyThread();
//        myThread.start();

        //new MyThread().start();//匿名对象调用（匿名对象优点：没有引用指向，节省资源，优先被GC回收。缺点：只能用一次）

        //使用Thread匿名对象调用run方法
//        new Thread(){
//            @Override
//            public void run() {
//                for (int i = 0; i <= 1000; i++) {
//                    System.out.println("子线程----->" + i);
//                }
//            }
//        }.start();

        //通过匿名Thread对象去调用匿名Runnable接口实现的run方法--->推荐使用实现的方式
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 1000; i++) {
                    System.out.println("子线程----->" + i);
                }
            }
        }).start();

        //以下代码执行在主线程中
        for (int i = 0; i <= 1000; i++) {
            System.out.println("主线程----->" + i);
        }
        //综上所述可得并发效果：主线程与子线程打印的结果为互相嵌套，证明两个线程都在同时运行
    }
}

