package com.jikao.nowcoder.test0;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class NowCoderTest0_5 {

    // TODO 21,22,24
    public static void main(String[] args) throws Exception {
//        test21();
//        test22();
//        test23();
//        test24();
        test25();
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
     * (1) HJ17.坐标移动
     * A10;S20;W10;D30;X;A1A;B10A11;;A10;
     * 处理过程：
     * 起点（0,0）
     * +A10=  （-10,0）
     * +S20=  (-10,-20)
     * +W10=  (-10,-10)
     * +D30=  (20,-10)
     * +x =  无效
     * +A1A=  无效
     * +B10A11 =  无效
     * +一个空 不影响
     * + A10  =  (10,-10)
     * 结果 （10， -10）
     *
     *
     * A10;S20;W10;D30;X;A1A;B10A11;;A10;
     * 10,-10
     *
     * ABC;AKL;DA1;
     * 0,0
     */
    public static void test17() throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = bufferedReader.readLine();
        String move1 = move(line);
        System.out.println(move1);
    }

    /**
     * 输入：
     * A10;S20;W10;D30;X;A1A;B10A11;;A10;
     * 输出：
     * 10,-10
     * @param string
     * @return
     */
    private static String move(String string) {
        int x = 0;
        int y = 0;
        String[] tempArray = string.split(";");
        for(String tempString : tempArray) {
            if (!tempString.equals("") && tempString.length() == 3) {
                String position = tempString.substring(0, 1);
                int number;
                try {
                    number = Integer.parseInt(tempString.substring(1, 3));
                } catch (NumberFormatException e) {
                    continue;
                }
                switch (position) {
                    case "A":
                        x = x - number;
                        break;
                    case "S":
                        y = y - number;
                        break;
                    case "W":
                        y = y + number;
                        break;
                    case "D":
                        x = x + number;
                        break;
                    default:
                        break;
                }
            }
        }
        return x + "," + y;
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
     * (6) HJ25. 数据分类处理
     *
     * @throws Exception
     */
    public static void test25() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        while ((str = br.readLine()) != null) {
            if (str.equals("")) continue;
            String[] I = str.split(" ");
            String[] temp = br.readLine().split(" ");
            long[] R = new long[Integer.parseInt(temp[0])];
            for (int i = 0; i < R.length; i++) R[i] = Long.parseLong(temp[i+1]);
            Arrays.sort(R);
            StringBuilder res = new StringBuilder();
            int count = 0;
            for (int i = 0; i < R.length; i++) {
                if (i > 0 && R[i] == R[i-1]) continue;
                String pattern = R[i] + "";
                int num = 0;
                StringBuilder index = new StringBuilder();
                for (int j = 1; j < I.length; j++) {
                    if (I[j].contains(pattern)) {
                        num++;
                        index.append(" ").append(j-1).append(" ").append(I[j]);
                    }
                }
                if (num > 0){
                    res.append(" ").append(R[i]).append(" ").append(num).append(index);
                    count+=num*2+2;
                }
            }
            System.out.println(count + res.toString());
        }
    }

}
