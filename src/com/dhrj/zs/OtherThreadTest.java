package com.dhrj.zs;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * created by on 2022/4/23
 * 描述：以后在开发中，项目都是运行在服务器当中，而服务器已经将线程的定义
 * 线程对象的创建，线程的启动等都已经实现完了，这些代码不需要编写。
 * 最重要的是编写的程序需要放到一个多线程的环境下运行，更需要关注的是这些数据在多线程并发的环境下是否安全。
 * 《多线程并发》出现安全三要素：(1)多线程并发、(2)有共享数据、(3)存在修改数据行为
 * 解决方案：线程排队执行--->线程同步机制--->牺牲程序执行效率--->保障数据安全
 * 线程同步：同步编程模型（排队执行 ） + 异步编程模型（各司其职，无须等待，多并发状态）
 * 总结：异步 = 并发， 同步 = 队列--->synchronized(共享数据/共享对象){线程同步代码块}，静态方法使用synchronized，共享对象类锁，只有一把--->类锁保证静态变量的安全。
 * synchronized后面小括号中传的这个"数据”是相当关键的。这个数据必须是多线程共享的数据。才能达到多线程排队
 * synchronized出现在实例方法上，对象锁只能是this，所以这种方式不灵活。缺点: synchronized出现在实例方法上，表示整个方法体都需要同步，扩大同步范围，导致程序的执行效率降低。
 * 在Java语言中，任何一个对象都有一把锁，这把锁就是标记。只是把它叫做锁。
 * 假设t1和t2线程并发，有先后顺序。假设t1先执行，遇到synchronized，此时自动寻找共享对象，将对象锁并占为己有，然后执行同步代码块中的程序
 * 在程序执行过程中一直都是占有这把锁的，直到同步代码块代码结束，这把锁才会释放。
 * 假设t1已经占有这把锁，此时t2也遇到synchronized关键字，t2只能在同步代码块外面等待t1结束，直到t1把同步代码块执行结束了
 * t1才归还这把锁，然后t2占有这把锁之后，进入同步代码块执行程序。
 * 局部变量+常量不会发生线程安全问题
 * 成员变量会发生线程安全问题，因为实例变量存储在堆内存，静态变量存储在方法区，JVM的堆内存和方法区数据共享。
 * 死锁(deadlock)：不出现异常和错误，停滞不动，难以调式。synchronized嵌套使用会导致死锁。
 * 开发中解决线程安全问题：
 * 1.使用局部变量代替实例变量和静态变量
 * 2.创建多个对象，不同的共享对象
 * 3. 1和2不行，再使用synchronized同步机制
 * <p>
 * Java语言线程分为：用户线程(--->main方法) 、守护线程（后台线程--->垃圾回收线程），线程对象.setDaemon(true);当用户线程结束，身为死循环的守护线程自动停止
 * 定时器：Timer(框架有SpringTask，底层是Timer)
 * <p>
 * 开启线程的第三种方式：实现Callable接口，可以获取线程的返回值(FutureTask对象.get()，阻塞线程状态)，前两种(new Thread() + 实现Runnable)无法返回，因为run返回的是void
 *
 * @author ZSAndroid
 * @create 2022-04-23-23:37
 */

public class OtherThreadTest {

    public static void main(String[] args) {
        //1.创建一个实现callable的实现类，实现call方法，将此线程需要执行的操作声明在call（）中
        //2.创建callable实现类的对象
        //3.将callable接口实现类的对象作为传递到FutureTask的构造器中，创建FutureTask的对象
        //4.将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start方法启动（通过FutureTask的对象调用方法get获取线程中的call的返回值）
        FutureTask futureTask = new FutureTask(new MyCallable());
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

