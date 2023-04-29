package com.jikao.enterprise.hw.exam;

import com.jikao.nowcoder.test0.NowCoderTest0_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ExamTest1 {

    public static void main(String[] args) throws Exception {
//        test1();
//        test2();
        test3();
    }

    /**
     * 1.汽水瓶
     */
    public static void test1(){
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) {
            int count = 0;
            int blank = in.nextInt();

            while (blank > 2) {
                int water = blank / 3;
                count += water;
                blank = blank % 3 + water;
                if (blank == 2) {
                    count++;
                }
            }
            if (count > 0) {
                System.out.println(count);
            }
        }
    }

    /**
     * 2.明明的随机数
     */
    public static void test2() throws Exception{
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int size = in.nextInt();
            int count = 0;
            boolean[] flagArray = new boolean[1000];
            while (in.hasNextInt()) {
                count++;
                flagArray[in.nextInt()] = true;
                if (count == size) {
                    break;
                }
            }
            // 遍历flagArray获取数值
            for (int i = 0; i < flagArray.length; i++) {
                if (flagArray[i]) {
                    System.out.println(i);
                }
            }
        }
    }

    /**
     * 3.进制转换
     * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
     *
     * 输入例子：
     * 0xAA
     * 输出例子：
     * 170
     * @throws Exception
     */
    public static void test3() throws Exception{
        NowCoderTest0_1.test5();
    }

}
