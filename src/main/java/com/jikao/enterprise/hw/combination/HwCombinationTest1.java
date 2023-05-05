package com.jikao.enterprise.hw.combination;

import java.util.*;

/**
 * 5.排列组合（2题）
 * (1) *leetcode 面试题08.08.有重复字符串的排列组合
 * (2) leetcode 77.组合
 */
public class HwCombinationTest1 {

    List<String> list0 = new ArrayList<>();
    List<String> list1 = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        HwCombinationTest1 my = new HwCombinationTest1();
        my.test0();
//        my.test1();
//        my.test2();


    }

    /**
     * 面试题 08.07. 无重复字符串的排列组合
     *
     *  输入：S = "qwe"
     *  输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
     *
     *  输入：S = "ab"
     *  输出：["ab", "ba"]
     *
     * @throws Exception
     */
    private void test0() throws Exception{
        String[] result = permutation0("qwe");
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    public String[] permutation0(String str) {
        permutate0(str.toCharArray(), 0);
        String[] res = new String[list0.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list0.get(i);
        }
        return res;
    }

    public void permutate0(char[] arr, int first) {
        if (first == arr.length - 1) {
            list0.add(new String(arr));
            return;
        }
        for (int i = first; i < arr.length; i++) {
            swap(arr, first, i);
            permutate0(arr, first + 1);
            swap(arr, first, i);
        }
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

    /**
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     *
     * 你可以按 任何顺序 返回答案。
     * @throws Exception
     */
    private void test2() throws Exception{
        int n = 5;
        int k = 3;
        List<List<Integer>> res = combine(n, k);
        System.out.println(res);
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, res);
        return res;
    }

    private void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i <= n; i++) {
            path.addLast(i);
            System.out.println("递归之前 => " + path);
            dfs(n, k, i + 1, path, res);
            path.removeLast();
            System.out.println("递归之后 => " + path);
        }
    }



}
