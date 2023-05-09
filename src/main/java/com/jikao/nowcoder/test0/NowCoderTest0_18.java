package com.jikao.nowcoder.test0;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;


public class NowCoderTest0_18 {


    public static void main(String[] args) throws Exception {
        test86();
//        test87();
//        test88();
//        test89();
//        test90();
    }

    /**
     * HJ86 求最大连续bit数
     */
    public static void test86() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str ;
        while((str = br.readLine()) != null){
            int n = Integer.parseInt(str);
            int res = 0;
            int zere = 0;
            //一直取最后一位，
            while(n != 0){
                if(n % 2 == 1){
                    zere++;
                    if(zere > res){
                        res = zere;
                    }
                }else{
                    zere = 0;
                }
                n /= 2;
            }
            System.out.println(res);
        }
    }




}
