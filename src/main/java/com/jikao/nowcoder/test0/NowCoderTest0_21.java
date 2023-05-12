package com.jikao.nowcoder.test0;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class NowCoderTest0_21 {

    public static void main(String[] args) throws Exception {
//        test101();
//        test102();
        test103();
//        test104();
//        test105();
    }

    /**
     * HJ101 输入整型数组和排序标识，对其元素按照升序或降序进行排序
     * @throws Exception
     */
    public static void test101() throws Exception{
        Scanner sc = new Scanner(System.in);

        //有多次输入，且存在输入数字组成数组的情况
        while(sc.hasNext()){
            //第一行输入数组元素个数
            int arrCount = sc.nextInt();
            //第二行输入待排序的数组，每个数用空格隔开
            Integer[] arr = new Integer[arrCount];
            //录入 arrCount 个 数字，组成数组
            for(int i=0;i<arrCount;i++){
                arr[i] = sc.nextInt();
            }
            //第三行输入一个整数0或1。0代表升序排序，1代表降序排序
            int flagSort = sc.nextInt();

            if(flagSort==0){
                Arrays.sort(arr,new Comparator<Integer>(){
                    public int compare(Integer o1 ,Integer o2){
                        return o1-o2;
                    }
                });
            } else if(flagSort==1){
                Arrays.sort(arr,new Comparator<Integer>(){
                    public int compare(Integer o1 ,Integer o2){
                        return o2-o1;
                    }
                });
            }

            for(Integer m : arr){
                System.out.print(m + " ");
            }
            break;
        }
        System.out.println();
    }


    /**
     * HJ102 字符统计
     * @throws Exception
     */
    public static void test102() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str=br.readLine())!=null){
            char[] chArr = str.toCharArray();
            int[] temp = new int[150];
            for(int i=0;i<chArr.length;i++){
                temp[chArr[i]]++;
            }
            int max = 0;
            for(int j=0;j<temp.length;j++){
                if(max<temp[j]){
                    max = temp[j];
                }
            }
            StringBuilder sb = new StringBuilder();
            while(max!=0){
                for(int j=0;j<temp.length;j++){
                    if(temp[j] == max){
                        sb.append((char)j);
                    }
                }
                max--;
            }
            System.out.println(sb.toString());
        }
    }


    /**
     * HJ103 Redraiment的走法 TODO
     * @throws Exception
     */
    public static void test103() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str=br.readLine())!=null){
            int n = Integer.parseInt(str);
            String line = br.readLine();
            int max = 0;
            String[] strArray = line.split(" ");
            for (int i = 0; i < strArray.length; i++) {
                int left = Integer.parseInt(strArray[i]);
                int count = 0;
                for (int j = i+1; j < strArray.length; j++) {
                    int right = Integer.parseInt(strArray[j]);
                    if (right > left) {
                        left = right;
                        count++;
                    }
                }
                if (count>0) {
                    max = Math.max(count, max);
                }
            }
            System.out.println(max);
        }

    }


    /**
     * HJ94 记票统计
     * @throws Exception
     */
    public static void test94() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = br.readLine()) != null){
            int n = Integer.parseInt(line);
            String[] name = br.readLine().split(" ");
            int[] num = new int[n+1];
            int tt = 0;
            int invalidData = 0;
            int voteData = Integer.parseInt(br.readLine());
            String[] vote = br.readLine().split(" ");
            for(int i = 0; i < vote.length; i++){
                for(int j = 0; j < n; j++){
                    if(vote[i].equals(name[j])){
                        num[j]++;
                        break;
                    }
                }
            }
            for(int m = 0; m < name.length; m++){
                tt += num[m];
                System.out.println(name[m] + " : " + num[m]);
            }
            invalidData = voteData - tt;
            System.out.println("Invalid : " + invalidData);
        }
    }

    /**
     * HJ95 人民币转换
     * @throws Exception
     */
    public static void test95() throws Exception {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = buffer.readLine()) != null) {
            String[] str1 = str.split("\\.");
            int number1 = Integer.parseInt(str1[0]);
            int number2 = Integer.parseInt(str1[1]);
            if (number2 == 0) {
                System.out.println("人民币" + cul1(number1) + "元整");
            } else if (number1 == 0) {
                System.out.println("人民币" + cul2(str1[1]));
            } else {
                System.out.println("人民币" + cul1(number1) + "元" + cul2(str1[1]));
            }
        }
    }

    public static String cul1(int num) {
        String[] flag = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾"};
        String result = null;
        if (0 <= num && num <= 10) {
            result = flag[num];
        } else if (10 < num && num < 20) {
            int x = num % 10;
            result = flag[10] + flag[x];
        } else if (20 <= num && num < 100) {
            int x = num % 10;
            int y = num / 10;
            if (x == 0) {
                result = flag[y] + "拾";
            } else {
                result = flag[y] + "拾" + flag[x];
            }
        } else if (100 <= num && num <= 1000) {
            int x = num % 100;
            int y = num / 100;
            if (x == 0) {
                result = flag[y] + "佰";
            } else if (x < 10) {
                result = flag[y] + "佰零" + cul1(x);
            } else {
                result = flag[y] + "佰" + cul1(x);
            }
        } else if (1000 <= num && num < 10000) {
            int x = num % 1000;
            int y = num / 1000;
            if (x == 0) {
                result = flag[y] + "仟";
            } else if (x < 100) {
                result = flag[y] + "仟零" + cul1(x);
            } else {
                result = flag[y] + "仟" + cul1(x);
            }
        } else if (10000 <= num && num < 100000000) {
            int x = num % 10000;
            int y = num / 10000;
            if (x == 0) {
                result = cul1(y) + "万";
            } else if (0 < x && x < 1000) {
                result = cul1(y) + "万零" + cul1(x);
            } else {
                result = cul1(y) + "万" + cul1(x);
            }

        } else if (100000000 <= num) {
            int x = num % 100000000;
            int y = num / 100000000;
            if (x == 0) {
                result = cul1(y) + "亿";
            } else if (0 < x && x < 10000000) {
                result = cul1(y) + "亿零" + cul1(x);
            } else {
                result = cul1(y) + "亿" + cul1(x);
            }
        }
        return result;
    }

    public static String cul2(String num) {
        String[] flag = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾"};
        char[] charArray = num.toCharArray();
        if (charArray[0] == '0') {
            int tmp = Integer.parseInt(String.valueOf(charArray[1]));
            return flag[tmp] + "分";
        } else if (charArray[1] == '0') {
            int tmp = Integer.parseInt(String.valueOf(charArray[0]));
            return flag[tmp] + "角";
        } else {
            int x = Integer.parseInt(String.valueOf(charArray[0]));
            int y = Integer.parseInt(String.valueOf(charArray[1]));
            return flag[x] + "角" + flag[y] + "分";
        }
    }





}
