package com.jikao.nowcoder.test0;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class NowCoderTest0_9 {

    // TODO 42,43,44,45
    public static void main(String[] args) throws Exception {
        test41();
//        test32();
//        test33();
//        test34();
//        test35();
    }

    /**
     * HJ41 称砝码
     * 类型，重要，数量
     *
     *输入：
     * 2
     * 1 2
     * 2 1
     *
     * 输出：
     * 5
     * 复制
     * 说明：
     * 可以表示出0，1，2，3，4五种重量。
     */
    public static void test41() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //输入处理
        String str1 = br.readLine();
        String str2 = br.readLine();
        String str3 = br.readLine();
        int n = Integer.parseInt(str1);
        String[] strArr2 = str2.split(" ");
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(strArr2[i]);
        }
        String[] strArr3 = str3.split(" ");
        int[] counts = new int[n];
        for (int i = 0; i < n; i++) {
            counts[i] = Integer.parseInt(strArr3[i]);
        }
        //数据组,200000为最大重量
        boolean[] exist = new boolean[200000];  // 现有的可称量重量
        int[] arr = new int[20000];  // i个砝码可以称量多少种重量
        int count = 1;  // 现有的可称量重量计数

        //循环所有重量
        for (int i = 0; i < n; i++) {
            //每个重量循环个数次
            for (int j = 0; j < counts[i]; j++) {
                //对现有总重量数组进行遍历(新增的元素在下次j循环中加入)
                int index = count;
                for (int k = 0; k < index; k++) {
                    int sum = arr[k] + weights[i];
                    if (!exist[sum]) {
                        exist[sum] = true;
                        arr[count++] = sum;
                    }
                }
            }
        }
        System.out.println(count);
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
    public static void test33() throws Exception{
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
     * 输入：
     * aabcddd
     *
     * 输出：
     * aaddd
     * @throws Exception
     */
    public static void test23() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            int[] arr = new int[26];
            StringBuilder sb = new StringBuilder();
            int n = str.length();
            char[] chars = str.toCharArray();
            for(int i = 0; i < n; i++){
                arr[chars[i] - 'a']++;
            }
            int min = 20;
            for(int i: arr){
                if(i >0 && i<min){
                    min=i;
                }
            }
            for (int i = 0; i < n; i++) {
                char c = chars[i];
                if(arr[c - 'a'] != min) {
                    sb.append(chars[i]);
                }
            }
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
