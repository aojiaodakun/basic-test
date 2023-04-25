package com.jikao.nowcoder.test0;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Scanner;

public class NowCoderTest0_3 {

    // TODO 11,12,13,15
    public static void main(String[] args) throws Exception {
//        test11();
//        test12();
//        test13();
        test14();
//        test15();
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
     * (1) HJ8.合并表记录
     *数据表记录包含表索引index和数值value（int范围的正整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照index值升序进行输出。
     *
     * 提示:
     * 0 <= index <= 11111111
     * 1 <= value <= 100000
     *
     * 输入描述：
     * 先输入键值对的个数n（1 <= n <= 500）
     * 接下来n行每行输入成对的index和value值，以空格隔开
     *
     * 输出描述：
     * 输出合并后的键值对（多行）
     *
     * 输入：
     * 4
     * 0 1
     * 0 2
     * 1 2
     * 3 4
     *
     * 输出：
     * 0 3
     * 1 2
     * 3 4
     */
    public static void test8() throws Exception{
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        st.nextToken();      // 分隔符
        int n = (int) st.nval;   // nextValue
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            st.nextToken();
            int key = (int) st.nval;
            st.nextToken();
            int value = (int) st.nval;
            arr[key] = arr[key] + value;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length ; i++) {
            if(arr[i] != 0){
                sb.append(i).append(" ").append(arr[i]).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * (2) *HJ14.字符串排序
     * 描述
     * 给定 n 个字符串，请对 n 个字符串按照字典序排列。
     *
     * 数据范围： 1≤n≤1000  ，字符串长度满足1≤len≤100
     * 输入描述：
     * 输入第一行为一个正整数n(1≤n≤1000),下面n行为n个字符串(字符串长度≤100),字符串中只含有大小写字母。
     * 输出描述：
     * 数据输出n行，输出结果为按照字典序排列的字符串。
     *
     * 输入：
     * 9
     * cap
     * to
     * cat
     * card
     * two
     * too
     * up
     * boat
     * boot
     *
     * 输出：
     * boat
     * boot
     * cap
     * card
     * cat
     * to
     * too
     * two
     * up
     * @throws Exception
     */
    public static void test14() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        String[] data = new String[count];
        for(int i=0;i<data.length;i++){
            data[i] = br.readLine();
        }
        StringBuilder sb = new StringBuilder();
        Arrays.sort(data);
        for(String s:data){
            sb.append(s+"\n");
        }
        System.out.print(sb.toString());
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
