package com.jikao.enterprise.hw.stack;

import com.jikao.nowcoder.test0.NowCoderTest0_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 4.栈（2题）
 * (1) NC52.括号序列
 * (2) *leetcode 1614.括号的最大嵌套深度
 */
public class HwStackTest1 {

    public static void main(String[] args) throws Exception {

        test2();
    }

    /**
     * (1) NC60.括号序列
     */
    private static void test1() throws Exception{

    }

    /**
     * (2) *leetcode 1614.括号的最大嵌套深度
     *
     * 输入：s = "(1)+((2))+(((3)))"
     * 输出：3
     */
    private static void test2() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            String[] strArray = str.split("=");
            String inputString = strArray[1].trim();
            int maxDepth = maxDepth(inputString);
            System.out.println(maxDepth);
        }
    }

    public static int maxDepth(String str) {
        int maxDepth = 0;
        int depth = 0;
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char tempChar = charArray[i];
            if (tempChar == '(') {
                depth++;
                maxDepth = Math.max(maxDepth, depth);
            } else if(tempChar == ')'){
                depth--;
            }
        }
        return maxDepth;
    }

}
