package com.jikao.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class NowCoderTest2 {

    // TODO 6~9
    public static void main(String[] args) throws Exception {
//        test6();
//        test7();
//        test8();
//        test9();
        test10();
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
     * HJ10 字符个数统计
     *
     * 输入：
     * abc
     * 输出：
     * 3
     *
     * 输入：
     * aaa
     * 输出：
     * 1
     * @throws Exception
     */
    public static void test10() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = bufferedReader.readLine();
        int[] a = new int[128];
        int count=0;
        for(int i=0;i<line.length();i++){
            char b = line.charAt(i);
            if(a[b]==0){
                count++;
                a[b]=1;
            }
        }
        System.out.println(count);
    }

}
