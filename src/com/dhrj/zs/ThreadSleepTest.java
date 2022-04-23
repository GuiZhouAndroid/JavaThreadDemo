package com.dhrj.zs;

/**
 * created by on 2022/4/23
 * 描述：sleep()是静态方法：让当前线程进入休眠，进入“阻塞状态”，放弃占有的CPU时间片，让给其它线程使用。
 * 间隔特定的时间，去执行一段特定的代码，每隔多久执行一次--->例如计时器
 * 不中断线程，将sleep()睡眠的线程唤醒：使用 线程实例对象.interrupted(); --->原理是使用java异常处理机制，打印日志，结束跳出run方法。
 * 强制中断线程：线程实例对象.stop(); --->不推荐使用，线程没有保存数据，存在损坏数据/丢失数据。
 * 合理的中断线程方式：线程类中定义一个布尔标记，在外部调用时，通过线程对象.布尔标记修改true或false。
 * 在run方法体中if判断布尔值，true执行继续业务代码，false就return;
 * 在return;之前可以做相关的数据保存操作
 *
 * @author ZSAndroid
 * @create 2022-04-23-21:38
 */
public class ThreadSleepTest {
    public static void main(String[] args) {
        //主线程睡眠3秒
        System.out.println("我睡了，晚安！");
        try {
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("睡了3秒，我醒了！");
    }
}
