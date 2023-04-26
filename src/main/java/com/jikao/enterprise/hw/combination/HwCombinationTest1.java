package com.jikao.enterprise.hw.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 5.排列组合（2题）
 * (1) *leetcode 面试题08.08.有重复字符串的排列组合
 * (2) leetcode 77.组合
 */
public class HwCombinationTest1 {

    List<String> list1 = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        new HwCombinationTest1().test1();


    }

    /**
     * (1) *leetcode 面试题08.08.有重复字符串的排列组合
     * 有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
     *  输入：S = "qqe"
     *  输出：["eqq","qeq","qqe"]
     *
     *  输入：S = "ab"
     *  输出：["ab", "ba"]
     *
     *  提示:
     * 字符都是英文字母。
     * 字符串长度在[1, 9]之间。
     * @throws Exception
     */
    private void test1() throws Exception{
        String str = "abc";
        String[] permutation = permutation(str);
        System.out.println(permutation);
    }


    public String[] permutation(String S) {
        dfs(S.toCharArray(), 0);
        return list1.toArray(new String[0]);
    }
    public void dfs(char[] c, int k){
        if(k == c.length){
            list1.add(new String(c));
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for(int i = k; i < c.length; i++){
            if(!set.contains(c[i])){
                set.add(c[i]);
                swap(c, i, k);
                dfs(c, k+1);
                swap(c, i, k);
            }
        }
    }


    private void swap(char[] chars, int x, int y) {
        char temp = chars[x];
        chars[x] = chars[y];
        chars[y] = temp;
    }


}
