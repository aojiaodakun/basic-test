package com.hzk.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模型-传统版
 */
public class ProduceConsumerDemo {

    public static void main(String[] args) throws Exception {
        MyData myData = new MyData();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                myData.add();
            },"AA").start();

            new Thread(()->{
                myData.reduce();
            },"BB").start();
        }

    }

}


class MyData{
    int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void add() {
        try {
            lock.lock();
            // 防止虚假唤醒
            while (number != 0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + " 生产了");
            condition.signalAll();
        }catch (Exception e){

        }finally
         {
            lock.unlock();
        }
    }

    public void reduce() {
        try {
            lock.lock();
            while (number == 0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + " 消费了");
            condition.signalAll();
        }catch (Exception e){

        }finally
         {
            lock.unlock();
        }
    }

}
