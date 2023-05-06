package com.jikao.nowcoder.test0;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * 未理解
 * 16，18
 */
public class NowCoderTest0_4 {

    public static void main(String[] args) throws Exception {
//        test16();
//        test17();
//        test18();
        test19();
//        test20();
    }

    /**
     * HJ16 购物单
     * 输入：
     * 1000 5
     * 800 2 0
     * 400 5 1
     * 300 5 1
     * 400 3 0
     * 500 2 0
     *
     * 输出：
     * 2200
     */
    public static void test16() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        String[] money_number = str.split(" ");
        int money = Integer.parseInt(money_number[0]);//钱数
        int count = Integer.parseInt(money_number[1]);//物品数
        int v[] = new int[count + 1];//物品的v p q和附件序号
        int p[] = new int[count + 1];
        int q[] = new int[count + 1];
        int sub1[] = new int[count + 1];
        int sub2[] = new int[count + 1];
        int dw = 100;// 单位
        boolean flag = true;
        for (int i = 1; i < count + 1; i++) {//第i件物品的属性
            String obj[] = br.readLine().split(" ");
            v[i] = Integer.parseInt(obj[0]);
            if (flag && v[i] % dw != 0) {
                dw = 10;
                flag = false;
                for (int m = 1; m < i; m++) {//出现不是整百的，按整十除
                    v[m] *= 10;
                    p[m] *= 10;
                }
            }
            v[i] = v[i] / dw;
            p[i] = Integer.parseInt(obj[1]) * v[i];//价值=价格*权重，需要的是p最大
            q[i] = Integer.parseInt(obj[2]);
            if (q[i] > 0) {//是附件
                if (sub1[q[i]] == 0)
                    sub1[q[i]] = i;//是附件1
                else
                    sub2[q[i]] = i;//是附件2
            }
        }
        money /= dw;
        int dp[][] = new int[count + 1][money + 1];//money为啥+1？
        for (int i = 1; i < count + 1; i++) {//两层for循环，动态规划二维表逐列逐行
            int p1 = 0, p2 = 0, p3 = 0;//根据附件数量，分4种情况v[i]、v1、v2、v3
            int v1 = -1, v2 = -1, v3 = -1;//
            if (sub1[i] != 0) {
                v1 = v[i] + v[sub1[i]];
                p1 = p[i] + p[sub1[i]];
            }
            if (sub2[i] != 0) {
                v2 = v[i] + v[sub2[i]];
                p2 = p[i] + p[sub2[i]];
            }
            if (sub1[i] != 0 && sub2[i] != 0) {
                v3 = v1 + v2 - v[i];
                p3 = p1 + p2 - p[i];
            }
            for (int j = 1; j < money + 1; j++) {
                dp[i][j] = dp[i - 1][j];//最大价值最少是这一件不放进去的大小
                if (q[i] == 0) {
                    if (j >= v[i]) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v[i]] + p[i]);
                    if (v1 != -1 && j >= v1) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v1] + p1);
                    if (v2 != -1 && j >= v2) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v2] + p2);
                    if (v3 != -1 && j >= v3) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v3] + p3);
                }
            }
        }
        System.out.println(dp[count][money] * dw);
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
     * HJ18 识别有效的IP地址和掩码并进行分类统计
     *
     * @throws Exception
     */
    public static void test18() throws Exception {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String str;
        int a=0,b=0,c=0,d=0,e=0,err=0,pri=0;
        while((str=buffer.readLine())!=null){
            int index=str.indexOf('~');
            long num1=strToInt(str.substring(0,index));
            long num2=strToInt(str.substring(index+1));
            long t=num1>>24;
            if(t==0||t==127) continue;
            if(num2<=0||num2>=0XFFFFFFFFL||(((num2 ^ 0XFFFFFFFFL)+1)|num2)!=num2){
                err++;
                continue;
            }
            if(t<=0)err++;
            else if(t>=1&&t<=126){
                a++;
                if(t==10) pri++;
            }else if(t>=128&&t<=191){
                b++;
                if(num1>>20==0xAC1)pri++;
            }else if(t>=192&&t<=223){
                c++;
                if(num1>>16==0xC0A8)pri++;
            }else if(t>=224&&t<=239)d++;
            else if(t>=240&&t<=255)e++;
        }
        System.out.println(a+" "+b+" "+c+" "+d+" "+e+" "+err+" "+pri);
    }

    public static long strToInt(String str){
        char[] cs=str.toCharArray();
        long res=0,tmp=0,flag=0;
        for(char c:cs){
            if(c=='.'){
                res=res<<8|tmp;
                tmp=0;
                flag++;
            }
            else if(c>='0'&&c<='9'){
                tmp=tmp*10+c-'0';
                flag=0;
            }else{
                return -1;
            }
            if(flag>=2) return -1;

        }
        res=res<<8|tmp;
        return res;
    }

    /**
     * HJ19 简单错误记录
     * @throws Exception
     */
    public static void test19() throws Exception {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String str;
        LinkedHashMap<String,Integer> data = new LinkedHashMap<>();
        while((str = buffer.readLine())!=null){
            int idx1 = str.lastIndexOf(" ");
            int idx2 = str.lastIndexOf("\\");
            String key = (idx1-idx2)>16?str.substring(idx1-16):str.substring(idx2+1);
            data.put(key,data.getOrDefault(key,0)+1);
        }
        int count=0;
        for (String key:data.keySet()){
            count++;
            if(count>(data.size()-8)){
                System.out.println(key+" "+data.get(key));
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
