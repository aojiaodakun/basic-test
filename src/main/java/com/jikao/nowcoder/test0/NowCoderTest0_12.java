package com.jikao.nowcoder.test0;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class NowCoderTest0_12 {

    // TODO 56,57,58,59
    public static void main(String[] args) throws Exception {
//        test66();
//        test67();
//        test68();
//        test69();
        test60();
    }

    /**
     * hello nowcoder
     * <p>
     * 8
     */
    public static void test1() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.nextLine();
            String[] tempArray = str.split(" ");
            int length = tempArray[tempArray.length - 1].length();
            System.out.println(length);
        }
    }

    /**
     *HJ33 整数与IP地址间的转换
     * 输入描述：
     * 输入
     * 1 输入IP地址
     * 2 输入10进制型的IP地址
     *
     * 输出描述：
     * 输出
     * 1 输出转换成10进制的IP地址
     * 2 输出转换后的IP地址
     *
     * 输入：
     * 10.0.3.193
     * 167969729
     *
     * 输出：
     * 167773121
     * 10.3.3.193
     * @throws Exception
     */
    public static void test2() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        while ((str = br.readLine()) != null) {
            String[] ip = str.split("\\.");
            long num = Long.parseLong(br.readLine());
            //转10进制
            System.out.println(Long.parseLong(ip[0]) << 24 | Long.parseLong(ip[1]) << 16 |
                    Long.parseLong(ip[2]) << 8 | Long.parseLong(ip[3]));
            //转ip地址
            StringBuilder sb = new StringBuilder();
            sb.append(((num >> 24) & 255)).append(".").append(((num >> 16) & 255))
                    .append(".").append(((num >> 8) & 255)).append(".").append((num & 255));
            System.out.println(sb.toString());
        }
    }


    /**
     * (5) *HJ68.成绩排序
     *
     * 输入：
     * 3
     * 0
     * fang 90
     * yang 50
     * ning 70
     *
     * 输出：
     * fang 90
     * ning 70
     * yang 50
     *
     * @throws Exception
     */
    public static void test68() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numbers = Integer.parseInt(br.readLine());
        int sortType = Integer.parseInt(br.readLine());
        String[][] stringArray = new String[numbers][2];
        String str;
        int index = 0;// 0代表从高到低，1代表从低到高
        while ((str = br.readLine()) != null) {
            String[] tempArray = str.split(" ");
            stringArray[index][0] = tempArray[0];
            stringArray[index][1] = tempArray[1];
            if (index == numbers - 1) {
                break;
            }
            index++;
        }
        Arrays.sort(stringArray, new Comparator<String[]>() {
            @Override
            public int compare(String[] s1, String[] s2) {
                if (sortType == 1) {// 升序
                    return Integer.parseInt(s1[1]) - Integer.parseInt(s2[1]);
                } else {
                    // 降序
                    return Integer.parseInt(s2[1]) - Integer.parseInt(s1[1]);
                }

            }
        });
        System.out.println(str);
    }

    /**
     * 输入：
     * abc
     *
     * 输出：
     * abc00000
     * @throws Exception
     */
    public static void test4() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str = br.readLine())!=null){
            int len = str.length();
            int start = 0;
            while (len >= 8){
                System.out.println(str.substring(start, start + 8));
                start += 8;
                len -= 8;
            }
            if (len > 0) {
                char[] tmp = new char[8];
                for(int i = 0;i<8;i++){
                    tmp[i]='0';
                }
                for(int i = 0; start < str.length(); i++) {
                    tmp[i] = str.charAt(start++);
                }
                System.out.println(String.valueOf(tmp));
            }
        }
    }

    /**
     * (3) *HJ60.查找组成一个偶数最接近的两个素数
     * 描述
     * 任意一个偶数（大于2）都可以由2个素数组成，组成偶数的2个素数有很多种情况，本题目要求输出组成指定偶数的两个素数差值最小的素数对。
     *
     * 数据范围：输入的数据满足 4≤n≤1000
     * 输入描述：
     * 输入一个大于2的偶数
     *
     * 输出描述：
     * 从小到大输出两个素数
     *
     * 输入：
     * 20
     * 输出：
     * 7
     * 13
     *
     * 输入：
     * 4
     * 输出：
     * 2
     * 2
     * @throws Exception
     */
    public static void test60() throws Exception {
        List<Integer> zhisuList = new ArrayList<>(512);
        // 1、先找出1000内的所有质数
        for (int i = 2; i <= 1000; i++) {
            for (int j = 2; j <= i; j++) {
                if(i % j == 0 && j != i) {
                    break;
                }
                if (i == j) {
                    zhisuList.add(i);
                }
            }
        }
        // 2、组成指定偶数的两个素数差值最小的素数对
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while((line = br.readLine()) != null) {
            int max = 0;
            int num1 = 0;
            int num2 = 0;
            int target = Integer.parseInt(line);
            for (int i = 0; i < zhisuList.size(); i++) {
                int a = zhisuList.get(i);
                if (a > target) {
                    break;
                }
                for (int j = i; j < zhisuList.size(); j++) {
                    int b = zhisuList.get(j);
                    if (b > target) {
                        break;
                    }
                    int sum = a + b;
                    if (sum == target) {
                        num1 = a;
                        num2 = b;
                        max = Math.max(max, b-a);
                    }
                }
            }
            System.out.println(num1);
            System.out.println(num2);
        }

    }

}
