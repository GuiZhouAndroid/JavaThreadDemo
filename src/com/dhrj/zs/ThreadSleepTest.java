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
 * 线程的调度模型：抢占式调度模型、均分式调度模型
 * 抢占式调度：线程优先级越高，抢夺CPU时间片/执行权越多，就会在运行状态的时间更多（setPriority()/getPriority()设置/获取线程优先级）
 * yield()暂停当前线程让位其它线程执行，不属于阻塞状态，直接调度该线程从运行状态回到就绪状态
 * 均分式调度：平均分配CPU时间片/执行权
 * join()合并线程：两个或多个线程合并为单线程，使用join的当前线程变为阻塞状态，追加队列按顺序依次执行，那个线程先主动调用join()就优先执行
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
