package com.hzk.aqs;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {

    public static void main(String[] args) throws Exception {
        // 异常组
        //exceptionApi();
        // 布尔值组
        booleanApi();
        // 阻塞
        //blockApi();
    }

    public static void exceptionApi(){
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        // 移除
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }


    public static void booleanApi() throws Exception{
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d",2,TimeUnit.SECONDS));
        // 偷窥
        System.out.println(blockingQueue.peek());
        // 获取
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    public static void blockApi() throws Exception{
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);
        blockingQueue.put("a");
        System.out.println("put a");
        blockingQueue.put("b");
        System.out.println("put b");

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        blockingQueue.put("c");
        System.out.println("put c");




    }

}
