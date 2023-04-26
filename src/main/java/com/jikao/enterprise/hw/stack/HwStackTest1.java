package com.jikao.enterprise.hw.stack;

import com.jikao.nowcoder.test0.NowCoderTest0_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

/**
 * 4.栈（2题）
 * (1) NC60.括号序列
 * (2) *leetcode 1614.括号的最大嵌套深度
 */
public class HwStackTest1 {

    public static void main(String[] args) throws Exception {
        test1();

//        test2();
    }

    /**
     * TODO
     * (1) NC60.括号序列
     * https://leetcode.cn/problems/permutation-sequence/solution/di-kge-pai-lie-by-leetcode-solution/
     * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
     * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * 给定 n 和 k，返回第 k 个排列。
     *
     * 示例 1：
     *
     * 输入：n = 3, k = 3
     * 输出："213"
     *
     * 示例 2：
     *
     * 输入：n = 4, k = 9
     * 输出："2314"
     *
     * 提示：
     * 1 <= n <= 9
     * 1 <= k <= n!
     */
    private static void test1() throws Exception{
        String permutation = getPermutation(3, 3);
        System.out.println(permutation);
//        String permutation1 = getPermutation(4, 9);
//        System.out.println(permutation1);
    }

    private static String getPermutation(int n, int k) {
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; ++i) {
            factorial[i] = factorial[i - 1] * i;
        }

        --k;
        StringBuffer ans = new StringBuffer();
        int[] valid = new int[n + 1];
        Arrays.fill(valid, 1);
        for (int i = 1; i <= n; ++i) {
            int order = k / factorial[n - i] + 1;
            for (int j = 1; j <= n; ++j) {
                order -= valid[j];
                if (order == 0) {
                    ans.append(j);
                    valid[j] = 0;
                    break;
                }
            }
            k %= factorial[n - i];
        }
        return ans.toString();
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
        Stack<Character> stack = new Stack<>();
        int maxDepth = 0;
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char tempChar = charArray[i];
            if (tempChar == '(') {
                stack.push(tempChar);
            } else if(tempChar == ')'){
                maxDepth = Math.max(maxDepth, stack.size());
                stack.pop();
            }
        }
        return maxDepth;
    }

}
