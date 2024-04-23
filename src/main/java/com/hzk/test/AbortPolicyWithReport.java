package com.hzk.test;

import java.net.SocketImpl;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

public class AbortPolicyWithReport extends ThreadPoolExecutor.AbortPolicy {

    private final String threadName = "AbortPolicyWithReport";

//    public AbortPolicyWithReport(String threadName) {
//        this.threadName = threadName;
//    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        String msg = String.format("Thread pool is EXHAUSTED!" +
                        " Thread Name: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d)," +
                        " Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s), in %s://%s:%d!" ,
                threadName, e.getPoolSize(), e.getActiveCount(), e.getCorePoolSize(), e.getMaximumPoolSize(), e.getLargestPoolSize(),
                e.getTaskCount(), e.getCompletedTaskCount(), e.isShutdown(), e.isTerminated(), e.isTerminating(),
                "dubbo", "myIP", 8080);
        System.err.println(msg);
        throw new RejectedExecutionException(msg);
    }



}
