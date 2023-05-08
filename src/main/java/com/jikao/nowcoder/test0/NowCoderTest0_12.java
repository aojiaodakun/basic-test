package com.jikao.nowcoder.test0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class NowCoderTest0_12 {

    // TODO 58,59
    public static void main(String[] args) throws Exception {
//        test56();
        test57();
//        test58();
//        test59();
//        test60();
    }

    /**
     * HJ56 完全数计算
     */
    public static void test56() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int inputInt = in.nextInt();
            System.out.println(count(inputInt));
        }
    }

    public static int conut2(int n){
        if(n<6){
            return 0;
        } else if(n<28){
            return 1;
        }else if(n<496){
            return 2;
        } else if(n<8128){
            return 3;
        } else if(n<33550336){
            return 4;
        } else {
            return -1;
        }
    }

    public static int count(int n){
        int result = 0;
        for(int i =1;i<n;i++){
            int sum = 0;
            for(int j=1;j<=i/2;j++){
                if(i%j==0){
                    sum += j;
                }
            }
            if(sum == i){
                result ++;
            }
        }
        return result;
    }

    /**
     * HJ57 高精度整数加法
     * @throws Exception
     */
    public static void test57() throws Exception{
        Scanner in = new Scanner(System.in);
        String str1 = in.nextLine();
        char[] charArray1 = str1.toCharArray();
        int length1 = charArray1.length;
        String str2 = in.nextLine();
        char[] charArray2 = str2.toCharArray();
        int length2 = charArray2.length;

        if (length1 < 8 && length2 < 8) {
            System.out.println(Long.parseLong(str1) + Long.parseLong(str2));
            return;
        }

        List<String> list = new ArrayList<>();



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
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        while((str = bf.readLine()) != null){
            int num = Integer.parseInt(str.trim());
            for(int m = num/2;num >= 2; m--){
                if(isZhiShu(m) && isZhiShu(num - m)){
                    System.out.println(m);
                    System.out.println(num - m);
                    break;
                }
            }
        }

    }

    private static void my60() throws IOException {
        List<Integer> zhisuList = new ArrayList<>(512);
        // 1、先找出1000内的所有质数
        for (int i = 2; i <= 1000; i++) {
            if (isZhiShu(i)) {
                zhisuList.add(i);
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

    private static boolean isZhiShu(int num){
        for(int n = 2; n < num;n++){
            if(num % n == 0){
                return false;
            }
        }
        return true;
    }


}
