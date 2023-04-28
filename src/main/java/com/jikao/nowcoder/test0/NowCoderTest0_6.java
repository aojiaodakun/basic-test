package com.jikao.nowcoder.test0;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class NowCoderTest0_6 {

    // TODO 26,29,30
    public static void main(String[] args) throws Exception {
//        test26();
//        test27();
        test28();
//        test29();
//        test30();
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
     * (3) HJ27.查找兄弟单词
     *
     * 输入：
     * 3 abc bca cab abc 1
     *
     * 输出：
     * 2
     * bca
     *
     * ----
     * 输入：
     * 6 cab ad abcd cba abc bca abc 1
     *
     * 输出：
     * 3
     * bca
     *
     * 说明：
     * abc的兄弟单词有cab cba bca，所以输出3
     * 经字典序排列后，变为bca cab cba，所以第1个字典序兄弟单词为bca
     * @throws Exception
     */
    public static void test27() throws Exception{
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s=bf.readLine())!=null){
            // 将输入的字符串分割成字符串数组
            String[] words=s.split(" ");
            // 待查找单词
            String str=words[words.length-2];
            // 兄弟单词表里的第k个兄弟单词
            int k=Integer.parseInt(words[words.length-1]);
            // 存放兄弟单词表
            ArrayList<String> broWords=new ArrayList<>();
            // 遍历输入的单词
            for (int i = 1; i < words.length-2; i++) {
                // 不相等且长度相同
                if((!words[i].equals(str)) && words[i].length()==str.length()) {
                    char[] chStr=str.toCharArray();
                    char[] word=words[i].toCharArray();
                    int temp=0;
                    for (int j = 0; j < chStr.length; j++) {
                        for (int j2 = 0; j2 < word.length; j2++) {
                            if (word[j]==chStr[j2]) {
                                chStr[j2]='0';
                                temp++;
                                break;
                            }
                        }
                    }
                    if (temp==chStr.length) {
                        broWords.add(words[i]);
                    }
                }
            }
            System.out.println(broWords.size());
            if(k>0 && k<=broWords.size()) {
                Collections.sort(broWords);
                System.out.println(broWords.get(k-1));
            }
        }
    }


    /**
     * HJ28 素数伴侣
     *
     * 输入:
     * 有一个正偶数 n ，表示待挑选的自然数的个数。后面给出 n 个具体的数字。
     *
     * 输出:
     * 输出一个整数 K ，表示你求得的“最佳方案”组成“素数伴侣”的对数。
     *
     * 数据范围： 1≤n≤100  ，输入的数据大小满足 2≤val≤30000
     *
     * 输入：
     * 4
     * 2 5 6 13
     *
     * 输出：
     * 2
     * @throws Exception
     */
    public static void test28() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while((line = br.readLine()) != null) {
            int count = Integer.parseInt(line);
            String[] elements = br.readLine().split(" ");
            int[] nums = new int[count];
            int oddCount = 0;
            int index = 0;
            for(String ele : elements) {
                nums[index] = Integer.parseInt(ele);
                if(nums[index] % 2 == 1) {
                    oddCount++;    //记录奇数个数
                }
                index++;
            }

            int[] oddNums = new int[oddCount];
            int[] evenNums = new int[count - oddCount];
            int oddIndex = 0;
            int evenIndex = 0;
            //奇偶分离
            for(int num : nums) {
                if(num % 2 == 0) {
                    evenNums[evenIndex++] = num;
                } else {
                    oddNums[oddIndex++] = num;
                }
            }

            int pairCount = 0;
            int[] evenPair = new int[evenIndex];
            for(int i = 0; i < oddIndex; i++) {
                boolean[] used = new boolean[evenIndex];
                if(findPair(i, oddNums, evenNums, evenPair, used)){
                    pairCount++;
                }
            }
            System.out.println(pairCount);
        }
    }
    /**
     偶数+奇数才可能是素数
     */
    private static boolean findPair(int oddIndex, int[] oddNums, int[] evenNums, int[] evenPair, boolean[] used) {
        for(int i = 0;i < evenNums.length; i++) {
            if(!used[i] && isP(oddNums[oddIndex] + evenNums[i])) {
                used[i] = true;
                if(evenPair[i] == 0 || findPair(evenPair[i] - 1,oddNums,evenNums,evenPair,used)) {
                    evenPair[i] = oddIndex + 1;
                    return true;
                }
            }
        }
        return false;
    }

    //见过比较经典的思路
    private static boolean isP(int num) {
        if(num <= 3) {
            return num > 1;    // 2,3都是素数
        }
        //6*n+2;6*n+3;6*n+4;6*n等都不是素数;可过滤掉2/3的判断
        if(num % 6 != 1 && num % 6 !=5) {
            return false;
        }
        double sqrt = Math.sqrt(num);
        for (int i = 5; i < sqrt; i += 6) {
            //只保留2类数据num % 6 == 1 num % 6 == 5
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
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
