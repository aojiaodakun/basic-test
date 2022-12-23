package com.hzk.lock;

import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockTest {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        MyData myData = new MyData();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                setDataName(myData,lock);
            }).start();
        }
    }

    public static void setDataName(MyData dataObj,Lock lock){
        try {
            lock.lock();
            dataObj.setName(UUID.randomUUID().toString().substring(0,8));
            System.out.println(Thread.currentThread().getName() + ",name=" + dataObj.getName());
        }finally {
            lock.unlock();
        }
    }

}


class MyData{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
