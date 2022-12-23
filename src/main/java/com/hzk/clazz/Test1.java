package com.hzk.clazz;

import java.net.URL;
import java.net.URLClassLoader;

public class Test1 {

    public int age = 18;

    static {
        int i =1;
        System.out.println("com.hzk.clazz.Test1静态代码块执行");
        int j=2;
        System.out.println("j=" + j);
    }

    public static int abc=1;


    public static void main(String[] args) {
        int a=1;
        int b=2;
        int x=3;
        int c=a+b;
        System.out.println(c);

        URLClassLoader classLoader = (URLClassLoader)ClassLoader.getSystemClassLoader().getParent();
        for (URL url : classLoader.getURLs()) {
            System.out.println(url);
        }
        System.out.println(System.getProperty("java.ext.dirs"));

    }

}
