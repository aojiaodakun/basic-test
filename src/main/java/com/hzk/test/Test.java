package com.hzk.test;

import com.hzk.aqs.BlockingQueueDemo;
import com.hzk.net.util.HttpClientUtil;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static Map<String, ConfigRegistryService> CONFIGKEY_HOOK_MAP = new HashMap<>();

    static {
        CONFIGKEY_HOOK_MAP.put("myConfig", new configRegistryServiceImpl());
    }

    public static void main(String[] args) throws Exception{


        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo:threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
        }








//
//        C c = new C();
//        c.method();
//
//        B b = c;
//        b.method();
//
//        A a = c;
//        a.method();

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000 * 5);
                System.out.println("run");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("interrupt");
            }
        });
        thread.start();
        Thread.sleep(1000 * 2);
        thread.interrupt();

        Thread.sleep(1000 * 50);
    }

    private static void save(String key, String value){
        ConfigRegistryService configRegistryService = CONFIGKEY_HOOK_MAP.get(key);
        if (configRegistryService != null) {
            boolean flag = configRegistryService.beforeSave(key);
            if (!flag) {
                return;
            }
        }
        System.out.println("key:" + key + ",value:" + value);
        if (configRegistryService != null) {
            configRegistryService.afterSave(key);
        }

    }


    public static int compare(String s1, String s2){
        return s1.length() - s2.length();
    }

    static interface ConfigRegistryService {

        boolean beforeSave(String configKey);

        void afterSave(String configKey);

    }

    static class configRegistryServiceImpl implements ConfigRegistryService{

        @Override
        public boolean beforeSave(String configKey) {
            return true;
        }

        @Override
        public void afterSave(String configKey) {
            System.out.println("afterSave,configKey:" + configKey);
        }
    }

}


