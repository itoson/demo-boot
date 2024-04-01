package org.example.abc.pack_thread;

import java.util.concurrent.*;

public class Demo01 {
    public static void main(String[] args) {
        //1.继承Thread父类, 匿名内部类
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + ":正在运行");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //t1.start();  //--由创建的新线程执行
        //t1.run();  //--由main线程执行


        //2.1实现Runnable接口, 匿名内部类 (new接口, 边new边实现) +lambda表达式
        Runnable runnable = () -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + ":正在运行");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //2.2创建Thread对象时传入Runnable对象
        //new Thread(runnable).start();


        //3.线程池
        //3.1.jdk内置线程池Executors (不推荐使用)
        //ExecutorService pool = Executors.newCachedThreadPool();  //--线程池中可释放出无限条线程
        //ExecutorService pool = Executors.newFixedThreadPool(3);  //--有参数，线程池释放出指定数量的线程，任务数大于闲置线程数时暂时阻塞
        //ExecutorService pool = Executors.newSingleThreadExecutor();  //--单线程池
//        for (int i = 0; i < 10000; i++) {
//            pool.execute(runnable);
//        }


        //3.2ThreadPoolExecutor (重要)
        //--关键参数 :
        //*核心线程数
        //*最大线程数
        //闲置线程存活时间
        //闲置线程存活时间单位
        //*等待队列
        //*拒绝策略
        //--工作流程 :
        //面对大量任务,线程池会释放第一批次线程数量去应对任务
        //如果线程放完,仍然应对不了任务,则多出的任务进入等待队列暂时阻塞
        //如果等待队列也满了,则线程池释放第二批次线程数量去应对任务
        //如果第二批次线程也放完了,则触发拒绝策略,默认的拒绝策略是丢弃任务
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 6, 60000, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(2), (r, executor) -> System.out.println("暂时忽略任务" + r));
        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);

    }
}

