package com.hzk.thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPoolDemo {

    public static void main(String[] args) {
       // ExecutorService executorService = Executors.newWorkStealingPool();


        ExecutorService executorService = new ThreadPoolExecutor(1,3,1L, TimeUnit.SECONDS,new LinkedBlockingQueue<>(1)
                                            ,Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        ReentrantLock reentrantLock = new ReentrantLock();


        executorService.execute(()->{
            try {
                System.out.println("666");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            }catch (Exception e){

            }
        });
        executorService.execute(()->{
            try {
                System.out.println("666");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            }catch (Exception e){

            }
        });

        executorService.execute(()->{
            try {
                System.out.println("666");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            }catch (Exception e){

            }
        });

        executorService.execute(()->{
            try {
                System.out.println("666");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            }catch (Exception e){

            }
        });

        executorService.execute(()->{
            try {
                System.out.println("666");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            }catch (Exception e){

            }
        });

    }

}
