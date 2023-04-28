package com.jikao.enterprise.hw.other;

import com.jikao.nowcoder.test0.NowCoderTest0_1;
import com.jikao.nowcoder.test0.NowCoderTest0_12;
import com.jikao.nowcoder.test0.NowCoderTest0_6;
import com.jikao.nowcoder.test1.NowCoderTest1_2;

import java.util.Arrays;

/**
 * 9.其他（6题）
 * (1) *HJ108.求最小公倍数
 * (2) *HJ28.素数伴侣
 * (3) *HJ60.查找组成一个偶数最接近的两个素数
 * (4) *leetcode 994.腐烂的橘子
 * (5) leetcode 204.计数质数
 * (6) HJ25. 数据分类处理
 */
public class HwOtherTest1 {

    public static void main(String[] args) throws Exception {
        HwOtherTest1 my = new HwOtherTest1();
//        test1();
//        test2();
//        test3();
        my.test4();
//        test5();
//        test6();
    }


    /**
     * (1) *HJ108.求最小公倍数
     */
    private static void test1() throws Exception{
        NowCoderTest1_2.test108();
    }

    /**
     * (2) *HJ28.素数伴侣
     */
    private static void test2() throws Exception{
        NowCoderTest0_6.test28();
    }

    /**
     * (3) *HJ60.查找组成一个偶数最接近的两个素数
     */
    private static void test3() throws Exception{
        NowCoderTest0_12.test60();
    }

    /**
     * (4) *leetcode 994.腐烂的橘子
     *
     * 提示：
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 10
     * grid[i][j] 仅为 0、1 或 2
     *
     * 输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
     * 输出：4
     */
    private void test4() throws Exception{
        int[][] inputArray = new int[][] {
                {2,1,1},
                {1,1,0},
                {0,1,1}
        };
        int result1 = orangesRotting(inputArray);
        System.out.println(result1);
    }

    public int orangesRotting(int[][] grid) {
        int[][] num2Array = new int[grid.length][2];
        for (int i = 0; i < num2Array.length; i++) {
            for (int j = 0; j < num2Array[0].length; j++) {
                num2Array[i][j] = -1;
            }
        }
        int num2_index = 0;
        // 1、拿到所有2的坐标位
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int tempNum = grid[i][j];
                if (tempNum == 2) {
                    num2Array[num2_index][0] = i;
                    num2Array[num2_index][1] = j;
                    num2_index++;
                }
            }
        }
        // 没有找到2的坐标
        if (num2_index == 0) {
            return -1;
        }

        // 2、感染2的坐标位
        // 分钟数
        int minute = 0;
        int count = 0;
        // 蔓延成功标志
        while (true) {
            boolean flag = false;
            count++;
            int new_num2_index = 0;
            int[][] newNum2Array = new int[grid.length][2];
            for (int i = 0; i < newNum2Array.length; i++) {
                for (int j = 0; j < newNum2Array[0].length; j++) {
                    newNum2Array[i][j] = -1;
                }
            }


            for (int i = 0; i < num2Array.length; i++) {
                int x = num2Array[i][0];
                int y = num2Array[i][1];
                if (x == -1 && y == -1) {
                    break;
                }

                // 上
                if (x-1>0 && grid[x-1][y] == 1) {
                    grid[x-1][y] = 2;
                    newNum2Array[new_num2_index][0] = x-1;
                    newNum2Array[new_num2_index][1] = y;
                    new_num2_index++;
                    flag = true;
                }
                // 下
                if (x+1<grid.length && grid[x+1][y] == 1) {
                    grid[x+1][y] = 2;
                    newNum2Array[new_num2_index][0] = x+1;
                    newNum2Array[new_num2_index][1] = y;
                    new_num2_index++;
                    flag = true;
                }
                // 左
                if (y-1>0 && grid[x][y-1] == 1) {
                    grid[x][y-1] = 2;
                    newNum2Array[new_num2_index][0] = x;
                    newNum2Array[new_num2_index][1] = y-1;
                    new_num2_index++;
                    flag = true;
                }
                // 右
                if (y+1<grid[0].length && grid[x][y+1] == 1) {
                    grid[x][y+1] = 2;
                    newNum2Array[new_num2_index][0] = x;
                    newNum2Array[new_num2_index][1] = y+1;
                    new_num2_index++;
                    flag = true;
                }
            }
            if (flag) {
                minute++;
                num2Array = newNum2Array;
            }
            if (count != minute) {
                break;
            }
        }
        return minute;
    }

}
