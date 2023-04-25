package com.jikao.nowcoder.test0;

import scala.Int;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class NowCoderTest0_14 {

    // TODO 66,67,69,70
    public static void main(String[] args) throws Exception {
//        test66();
//        test67();
        test68();
//        test69();
//        test70();
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
     * (2) HJ20.密码验证合格程序
     *
     * 密码要求:
     * 1.长度超过8位
     * 2.包括大小写字母.数字.其它符号,以上四种至少三种
     * 3.不能有长度大于2的包含公共元素的子串重复 （注：其他符号不含空格或换行）
     *
     * 数据范围：输入的字符串长度满足
     * 1≤n≤100
     *
     * 输入：
     * 021Abc9000
     * 021Abc9Abc1
     * 021ABC9000
     * 021$bc9000
     *
     * 输出：
     * OK
     * NG
     * NG
     * OK
     * @throws Exception
     */
    public static void test20() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        StringBuffer sb = new StringBuffer();
        while (null != (input = reader.readLine())) {
            //设置四种类型数据初始为空即false，有数据了就更改为true
            boolean[] flag = new boolean[4];
            char[] chars = input.toCharArray();

            // 第一个条件
            if (chars.length < 9) {
                sb.append("NG").append("\n");
                continue;
            }

            // 第二个条件
            for (int i = 0; i < chars.length; i++) {
                if ('A' <= chars[i] && chars[i] <= 'Z') {
                    flag[0] = true;
                } else if ('a' <= chars[i] && chars[i] <= 'z') {
                    flag[1] = true;
                } else if ('0' <= chars[i] && chars[i] <= '9') {
                    flag[2] = true;
                } else {
                    flag[3] = true;
                }
            }
            int count = 0;
            for (int i = 0; i < 4; i++) {
                if (flag[i]) {
                    count++;
                }
            }

            // 第三个条件
            //不存在两个大于2的子串相同，即！（i=i+3,i+1=i+4,i+2=i+5）
            boolean sign = true;
            for (int i = 0; i < chars.length - 5; i++) {
                for (int j = i + 3; j < chars.length - 2; j++) {
                    if (chars[i] == chars[j] &&
                            chars[i + 1] == chars[j + 1] &&
                            chars[i + 2] == chars[j + 2]) {
                        sign = false;
                    }
                }
            }

            if (count >= 3 && sign) {
                sb.append("OK").append("\n");
            } else {
                sb.append("NG").append("\n");
            }
        }
        System.out.println(sb);

    }

}
