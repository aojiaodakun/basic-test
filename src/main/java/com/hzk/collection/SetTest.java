package com.hzk.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetTest {

    public static void main(String[] args) {
        //Set<String> set = new HashSet<>();

        // 解决方案1
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());

        // 解决方案2，底层CopyOnWriteArrayList
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }).start();
        }



    }

}
