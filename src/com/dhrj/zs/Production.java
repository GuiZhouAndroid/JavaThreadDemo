package com.dhrj.zs;

import java.util.List;

/**
 * created by on 2022/4/26
 * 描述：生产者
 *
 * @author ZSAndroid
 * @create 2022-04-26-11:59
 */
public class Production implements Runnable {
    private List warehouseList;//共享仓库

    public Production(List warehouseList) {
        this.warehouseList = warehouseList;
    }

    @Override
    public void run() {
        //死循环模拟一直生产
        while (true) {
            //对共享仓库加同步锁
            synchronized (warehouseList) {
                if (warehouseList.size() > 0) { //大于0 证明仓库一个元素
                    try {
                        warehouseList.wait();//生产线程进入等待状态，并Production释放之前占有的list集合的锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //程序执行到这，说明仓库中没有元素，进行生产
                Object o = new Object();
                warehouseList.add(o);
                System.out.println(Thread.currentThread().getName() + "生产数量" + o);
                //唤醒消费者消费，不释放锁
                warehouseList.notifyAll();
            }
        }
    }
}