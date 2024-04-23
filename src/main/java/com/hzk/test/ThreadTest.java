package com.hzk.test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.NamedThreadFactory;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThreadTest {

    static ExecutorService executorService;
    static ScheduledExecutorService scheduledExecutorService;

    static {
        int queues = 0;
        executorService = new ThreadPoolExecutor(0, 2, 60000, TimeUnit.MILLISECONDS,
                queues == 0 ? new SynchronousQueue<Runnable>() :
                        (queues < 0 ? new LinkedBlockingQueue<Runnable>()
                                : new LinkedBlockingQueue<Runnable>(queues)),
                new NamedThreadFactory("hzk_test", true), new AbortPolicyWithReport());


        scheduledExecutorService = Executors.newScheduledThreadPool(1, new java.util.concurrent.ThreadFactory() {
            private final AtomicInteger atomicInteger = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "ServiceCatalog-" + atomicInteger.incrementAndGet());
            }
        });
    }



    public static void main(String[] args) throws Exception{
        // test1
//        executorService.execute(() -> method());
//        executorService.execute(() -> method());
//        executorService.execute(() -> method());
//        executorService.execute(() -> method());

        // test2
        for (int i = 0; i < 10; i++) {
            scheduledExecutorService.scheduleWithFixedDelay(new MyRunnable(i),0, 2,
                    TimeUnit.SECONDS);
        }


        System.in.read();
    }

    private static class MyRunnable implements Runnable{

        int index;

        public MyRunnable(int index){
            this.index = index;
        }

        @Override
        public void run() {
            LocalDateTime localDateTime = DateUtil.toLocalDateTime(new Date());

            String name = Thread.currentThread().getName();
            System.out.println(name + " start,index=" + index + ",time=" + localDateTime);
            try {
                Thread.currentThread().sleep(1000 * 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " end,index=" + index);
        }



    }


    private static void method(){
        String name = Thread.currentThread().getName();
        System.out.println(name + " start...");
        try {
            Thread.currentThread().sleep(1000 * 60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " end...");
    }

}
