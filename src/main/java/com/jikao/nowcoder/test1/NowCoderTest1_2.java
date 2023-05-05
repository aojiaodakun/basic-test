package com.jikao.nowcoder.test1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class NowCoderTest1_2 {

    // TODO 107,109,110
    public static void main(String[] args) throws Exception {
//        test106();
//        test2();
        test108();
//        test4();
//        test5();
    }

    /**
     * (6) *HJ106.字符串逆序
     *
     * 将一个字符串str的内容颠倒过来，并输出。
     *
     * 输入：
     * I am a student
     *
     * 输出：
     * tneduts a ma I
     *
     * @throws Exception
     */
    public static void test106() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputCount;
        while ((inputCount = br.readLine()) != null) {
            StringBuilder sb = new StringBuilder();
            char[] charArray = inputCount.toCharArray();
            for (int i = charArray.length-1; i >= 0; i--) {
                sb.append(charArray[i]);
            }
            System.out.println(sb);
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
     * (1) *HJ108.求最小公倍数
     * 正整数A和正整数B 的最小公倍数是指 能被A和B整除的最小的正整数值，设计一个算法，求输入A和B的最小公倍数。
     * 数据范围：1≤a,b≤100000
     * 输入：
     * 5 7
     *
     * 输出：
     * 35
     *
     * 输入：
     * 2 4
     *
     * 输出：
     * 4
     */
    public static void test108() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            String[] strArray = str.split(" ");
            int a = Integer.parseInt(strArray[0]);
            int b = Integer.parseInt(strArray[1]);
            int min = Math.min(a, b);
            int max = Math.max(a, b);
            for (int i = 1; i <= min; i++) {
                int tempNum = max * i;
                if (tempNum % min == 0) {
                    System.out.println(tempNum);
                    break;
                }
            }
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
