package com.hzk.java.clazz.load;

import com.hzk.java.clazz.Test1;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 步骤一：加载
 * 1、3种类加载器
 * 2、各个类加载器的加载范围
 */
public class LoadTest {

    public static void main(String[] args) {
        // 系统类加载器
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器:" + appClassLoader);
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println("系统类加载器:" + contextClassLoader);
        System.out.println(appClassLoader == contextClassLoader);
        URLClassLoader urlClassLoader1 = (URLClassLoader)contextClassLoader;
        URL[] urLs1 = urlClassLoader1.getURLs();
        for (int i = 0; i < urLs1.length; i++) {
            System.out.println(urLs1[i]);
        }
        // 拓展类加载器
        ClassLoader extClassLoader = appClassLoader.getParent();
        // 拓展类加载器的加载范围
        System.out.println("-----------拓展类加载器--------------");
        URLClassLoader urlClassLoader2 = (URLClassLoader)extClassLoader;
        URL[] urLs2 = urlClassLoader2.getURLs();
        for (int i = 0; i < urLs2.length; i++) {
            System.out.println(urLs2[i]);
        }
        System.out.println(extClassLoader);
        // 引导类加载器
        ClassLoader bootstapClassLoad = extClassLoader.getParent();
        System.out.println("引导类加载器为C语言编写，没有java类的概念，为空:" + bootstapClassLoad);



        // 加载类，参数为类的全限定名（全类名）
        try {
            String className = "com.hzk.java.clazz.Test1";
            // 无link
            Class clazz = appClassLoader.loadClass("com.hzk.java.clazz.Test1");
            // link
            //Class clazz = Class.forName(className);


            // 反射获取系统类加载器的loadClass(String name, boolean resolve)
            Method method = appClassLoader.getClass().getDeclaredMethod("loadClass",String.class,boolean.class);
            method.setAccessible(true);
            System.out.println(method);


            Object object = method.invoke(appClassLoader,className,true);
            System.out.println("返回值：" + object);

            System.out.println(Test1.class.getClassLoader());


            System.out.println("加载成功");
//            System.out.println(clazz);

            System.out.println();
            // 实例化
//            clazz.newInstance();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
