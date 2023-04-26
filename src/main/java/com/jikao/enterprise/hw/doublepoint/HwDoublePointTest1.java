package com.jikao.enterprise.hw.doublepoint;

import java.util.ArrayList;
import java.util.List;

/**
 * 6.双指针（3题）
 * (1) *leetcode 674.最长连续递增序列
 * (2) NC5.最长回文子串
 * (3) NC28.最小覆盖子串7.深搜（1题）
 * (1) HJ41.称砝码
 */
public class HwDoublePointTest1 {

    private List<String> list2 = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        HwDoublePointTest1 my = new HwDoublePointTest1();
//        test1();
        my.test2();
//        test3();
//        test4();
    }

    /**
     * (1) *leetcode 674.最长连续递增序列
     * 输入：nums = [1,3,5,4,7]
     * 输出：3
     *
     * 输入：nums = [2,2,2,2,2]
     * 输出：1
     * @throws Exception
     */
    private static void test1() throws Exception{
        int[] nums = new int[]{1,3,5,4,7};
        int length = findLengthOfLCIS(nums);
        System.out.println(length);
    }

    private static int findLengthOfLCIS(int[] nums) {
        int maxLength = 0;
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] <= nums[i - 1]) {
                start = i;
            }
            maxLength = Math.max(maxLength, i - start + 1);
        }
        return maxLength;
    }

    /**
     * (2) NC5.最长回文子串
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     *
     * 输入：s = "cbbd"
     * 输出："bb"
     * @throws Exception
     */
    private void test2() throws Exception{
        String str1 = "babad";
        String result1 = longestPalindrome(str1);
        System.out.println(result1);
        list2.clear();
        System.out.println("----------");
        String str2 = "cbbd";
        String result2 = longestPalindrome(str2);
        System.out.println(result2);
    }

    public String longestPalindrome(String str) {
        if (str.length() < 2) {
            return str;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            int index = charArray.length;
            while (index > i+1) {
                String tempString = str.substring(i, index);
                isValid(tempString);
                index--;
            }
        }
        if (list2.size()>0) {
            return list2.get(0);
        }
        return null;
    }

    private boolean isValid(String str) {
        boolean flag = true;
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int index = 0;
        for (int i = length-1; i >= 0; i--) {
            if (charArray[i] != charArray[index]) {
                flag = false;
                break;
            }
            index++;
        }
        if (flag) {
            list2.add(str);
        }
        return flag;
    }

}
