package com.dhrj.zs;

import java.util.concurrent.Callable;

/**
 * created by on 2022/4/26
 * 描述：Callable方法实现类
 *
 * @author ZSAndroid
 * @create 2022-04-26-10:02
 */
public class MyCallable implements Callable {
    private int sum = 0;

    @Override
    public Object call() throws Exception {
        for (int i = 0; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + "---" + i);
            sum += i;
        }
        return sum;
    }
}
