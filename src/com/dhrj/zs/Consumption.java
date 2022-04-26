package com.dhrj.zs;

import java.util.List;

/**
 * created by on 2022/4/26
 * 描述：消费者
 *
 * @author ZSAndroid
 * @create 2022-04-26-11:59
 */
public class Consumption implements Runnable {
    private List warehouseList;//共享仓库

    public Consumption(List warehouseList) {
        this.warehouseList = warehouseList;
    }

    @Override
    public void run() {
        //死循环模拟一直消费
        while (true) {
            //对共享仓库加同步锁
            synchronized (warehouseList) {
                if (warehouseList.size() == 0) { //等于0 证明仓库没有元素了，消费完了
                    try {
                        warehouseList.wait();//消费者线程进入等待状态，并Consumption释放之前占有的list集合的锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //程序执行到这，说明仓库中有元素，进行消费
                System.out.println(Thread.currentThread().getName() + "消费数量" + warehouseList.remove(0));
                //唤醒生产者生产，不释放锁
                warehouseList.notifyAll();
            }
        }
    }
}