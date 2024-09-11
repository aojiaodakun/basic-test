package com.hzk.java.thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPoolDemo {

    public static void main(String[] args) throws Exception{
       // ExecutorService executorService = Executors.newWorkStealingPool();


        ExecutorService executorService = new ThreadPoolExecutor(1,3,1L, TimeUnit.SECONDS,new SynchronousQueue<>()
                                            ,Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        ReentrantLock reentrantLock = new ReentrantLock();


        executorService.execute(()->{
            try {
                System.out.println("111");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            }catch (Exception e){

            }
        });
        TimeUnit.SECONDS.sleep(2);

        executorService.execute(()->{
            try {
                System.out.println("222");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            }catch (Exception e){

            }
        });
        TimeUnit.SECONDS.sleep(2);
        executorService.execute(()->{
            try {
                System.out.println("333");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            }catch (Exception e){

            }
        });
        TimeUnit.SECONDS.sleep(2);
        executorService.execute(()->{
            try {
                System.out.println("444");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            }catch (Exception e){

            }
        });
        TimeUnit.SECONDS.sleep(2);
        executorService.execute(()->{
            try {
                System.out.println("555");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            }catch (Exception e){

            }
        });

    }

}
