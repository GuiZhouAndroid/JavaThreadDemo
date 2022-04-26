package com.dhrj.zs;

import java.util.ArrayList;
import java.util.List;

/**
 * created by on 2022/4/26
 * 描述：使用wait方法和notify方法实现生产者和消费者模式
 * 生产者和消费者模式：生产线程负责生产，消费线程负责消费，生产线程和消费线程要达到均衡，在这种特殊的情况下需要使用wait方法和notify方法
 * wait和notify方法不是线程对象的方法，是普通Java对象都有的方法，建立在线程同步的基础之上。因为多线程要同时操作一个仓库，存在线程安全问题。
 * wait方法作用； xxx.wait()让正在xxx对象上活动的线程进入等待状态，并且释放掉线程之前占有的xxx对象的锁
 * notify方法作用：xxx.notify()让正在xxx对象上等待的线程唤醒，只是通知，不会释放xxx对象上之前占有的锁
 * 案例需求：仓库我们采用List集合，List集合中假设只能存储i个元素。1个元素就表示仓库满了。
 * 如果List集合中元素个数是0，就表示仓库空了。保证List集合中永远都是最多存储1个元素。
 * 案例效果：生产一个消费一个
 *
 * @author ZSAndroid
 * @create 2022-04-26-11:28
 */
public class ProductionConsumptionTest {

    public static void main(String[] args) {

        //创建共享仓库List
        List warehouseList = new ArrayList();

        //创建生成者线程
        Thread productionThread = new Thread(new Production(warehouseList));
        //创建消费者线程
        Thread consumptionThread = new Thread(new Consumption(warehouseList));

        //设置线程名
        productionThread.setName("生产者线程");
        consumptionThread.setName("消费者线程");

        //启动线程
        productionThread.start();
        consumptionThread.start();
    }
}
