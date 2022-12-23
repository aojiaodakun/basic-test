package com.hzk.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

    static int UNIT = 500;

    public static void main(String[] args) {

        int total = 18644;
        int jobSize = (int)Math.ceil((double)total/UNIT);
//        System.out.println(37*500 + 144);

        int sum = 0;
        for (int i = 0; i < jobSize; i++) {
            int currentHandleSize = UNIT;
            if(i == jobSize-1){
                currentHandleSize = total - (i*UNIT);
            }
            System.out.println("第" + i + "次：" + currentHandleSize);

            sum = sum + currentHandleSize;
        }


        System.out.println("总数：" + sum);


    }

}
