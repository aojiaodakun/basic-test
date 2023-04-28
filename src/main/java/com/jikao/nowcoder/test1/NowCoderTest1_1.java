package com.jikao.nowcoder.test1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class NowCoderTest1_1 {

    // TODO 102~105
    public static void main(String[] args) throws Exception {
        test101();
//        test2();
//        test3();
//        test4();
//        test5();
    }

    /**
     * (5) HJ101.输入整型数组和排序标识
     *输入整型数组和排序标识，对其元素按照升序或降序进行排序
     *
     * 数据范围： 1≤n≤1000  ，元素大小满足0≤val≤100000
     * 输入描述：
     * 第一行输入数组元素个数
     * 第二行输入待排序的数组，每个数用空格隔开
     * 第三行输入一个整数0或1。0代表升序排序，1代表降序排序
     *
     * 输出描述：
     * 输出排好序的数字
     *
     * 示例1
     * 输入：
     * 8
     * 1 2 4 9 3 55 64 25
     * 0
     *
     * 输出：
     * 1 2 3 4 9 25 55 64
     */
    public static void test101() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputCount;

        while ((inputCount = br.readLine()) != null) {
            int count = Integer.parseInt(inputCount);
            String[] input = br.readLine().split(" ");
            int flag = Integer.parseInt(br.readLine());
            int[] num = new int[input.length];
            for (int i = 0; i < input.length; i++) {
                num[i] = Integer.parseInt(input[i]);
            }
            quickSort(num,0,num.length - 1);
            StringBuilder sb = new StringBuilder();
            if(flag == 0){
                for (int j = 0; j < num.length; j++) {
                    sb.append(num[j]).append(" ");
                }
            }else{
                for (int k = num.length - 1; k >= 0; k--) {
                    sb.append(num[k]).append(" ");
                }
            }
            System.out.println(sb.substring(0,sb.length()-1));
        }

    }

    public static void quickSort(int[] num, int L, int R) {
        if (L >= R) {
            return;
        }
        int p = partition(num, L, R);
        quickSort(num, L, p - 1);
        quickSort(num, p+1, R);
    }

    public static int partition(int[] num, int L, int R) {
        int key = num[L];
        int pivot = L;

        for (int i = L + 1; i <= R; i++) {
            if (num[i] < key) {
                int temp = num[++pivot];
                num[pivot] = num[i];
                num[i] = temp;
            }
        }
        int tt = num[pivot];
        num[pivot] = num [L];
        num[L] = tt;
        return pivot;
    }

    /**
     * 输入：
     * ABCabc
     * A
     * 输出：
     * 2
     */
    public static void test2() {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine().toLowerCase();
        String s2 = in.nextLine().toLowerCase();
        int lenBefore = s1.length();
        int lenAfter = s1.replaceAll(s2, "").length();
        System.out.print(lenBefore - lenAfter);
    }

    /**
     * 输入：
     * 3
     * 2
     * 2
     * 1
     *
     * 输出：
     * 1
     * 2
     */
    public static void test3() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            boolean[] flagArrays = new boolean[1001];
            StringBuilder sb = new StringBuilder();
            int n = Integer.parseInt(str);
            for (int i = 0; i < n; i++){
                flagArrays[Integer.parseInt(bf.readLine())] = true;
            }

            for (int i = 0; i < 1001; i++){
                if (flagArrays[i]) {
                    sb.append(i).append("\n");
                }
            }

            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
        }
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
     * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
     *
     * 输入：
     * 0xAA
     *
     * 输出：
     * 170
     * @throws Exception
     */
    public static void test5() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while((input = bf.readLine())!=null){
            String temp = input.substring(0, 2);
            int sum = 0;
            int length = temp.length();
            for(int i= length-1;i>=0;i--){
                char c = temp.charAt(i);
                int tempNum = c;
                if(tempNum>=65){
                    tempNum = tempNum - 65 + 10;
                }else{
                    tempNum = tempNum - 48;
                }
                sum = sum + (int) Math.pow(16, length-i-1)*tempNum;
            }
            System.out.println(sum);
        }
    }

}
