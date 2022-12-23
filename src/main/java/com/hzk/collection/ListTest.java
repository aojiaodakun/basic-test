package com.hzk.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 不安全集合，ConcurrentModificationException
 * list
 * 解决方案：
 * 1、Vector
 * 2、java.util.Collections.synchronizedList()
 * 3、java.util.concurrent.CopyOnWriteArrayList.add()
 */
public class ListTest {

    public static void main(String[] args) {

        // 常规代码
        //List<String> list = new ArrayList<>();

        // 解决方案1
        //List<String> list = new Vector<>();

        // 解决方案2
        //List<String> list = Collections.synchronizedList(new ArrayList<>());

        // 解决方案3
        // 数据新增，删除时使用ReentrantLock加非公平锁,CAS算法
        List<String> list = new CopyOnWriteArrayList<>();

        ReentrantLock lock = new ReentrantLock();
        lock.lock();


        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }).start();
        }


    }

}
