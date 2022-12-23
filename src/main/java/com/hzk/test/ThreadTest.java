package com.hzk.test;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThreadTest {

    private static ThreadLocal<Boolean> lastCachedResult = new ThreadLocal<>();

    public static void main(String[] args) throws Exception{
        String str1 = "456{{test}}123{{chart}}321";
        str1 = "456{test}123{chart}321";
        String reg = "\\{(.+?)\\}";
        List<String> regResult = getRegResult(reg, str1);


        String str = "{\"content\":\"亲爱的{name}，您好！感谢您加入{model.acompany.name}！有关入职相关事宜及待办事项已发送您的邮箱，请及时查看并登录入职服务平台完成您的待办事项，网址链接：{url} (邮件内容可能被误收进垃圾箱)\"}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println(jsonObject);
        String content = jsonObject.get("content").toString();
        JSONObject contentJSONObj = JSONObject.parseObject(content);
        System.out.println(contentJSONObj);
//        lastCachedResult.set(Boolean.TRUE);
        Class<ThreadTest> threadTestClass = ThreadTest.class;
        Field field = threadTestClass.getDeclaredField("lastCachedResult");
        field.setAccessible(true);
        field.set(new ThreadTest(), ThreadLocal.withInitial(() ->Boolean.FALSE));

//        ThreadLocal<Boolean> threadLocal = (ThreadLocal<Boolean>)field.get(new ThreadTest());
//        threadLocal.set(Boolean.FALSE);


        ThreadTest.lastCachedResult.get();
        boolean flag = ThreadTest.lastCachedResult.get();
        System.out.println(flag);
        ThreadTest.lastCachedResult.set(Boolean.TRUE);
        System.out.println("end");
    }


    public static List<String> getRegResult(String pattern, String str){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        List<String> tempList = new ArrayList<>();
        while (m.find()) {
            tempList.add(m.group());
        }
        return tempList;
    }

}
