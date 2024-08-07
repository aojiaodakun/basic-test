package com.hzk.java.lock;

public class SynchronizedTest {

    public static void main(String[] args) throws Exception{


        Object object = new Object();

        new Thread(()->{
            try {
                Thread.currentThread().sleep(1000 * 5);
                synchronized (object){
                    System.out.println("notify before");
                    object.notify();
                    Thread.currentThread().sleep(1000 * 10);
                    System.out.println("notify after");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();

        synchronized (object){
            System.out.println("wait before");
//            Thread.currentThread().sleep(1000 * 60);
            object.wait();
            System.out.println("wait after");
        }




        System.in.read();

    }


}
