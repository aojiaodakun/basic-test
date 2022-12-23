package com.hzk.collection;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {

    public static void main(String[] args) {
        //Map<String,String> map = new HashMap<>();

        // 解决方案1
        //Map<String,String> map = Collections.synchronizedMap(new HashMap<>());

        // 解决方案2
        Map<String,String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }).start();
        }


    }

}
