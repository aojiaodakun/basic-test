package com.hzk.test;

import com.hzk.aqs.BlockingQueueDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static Map<String, ConfigRegistryService> CONFIGKEY_HOOK_MAP = new HashMap<>();

    static {
        CONFIGKEY_HOOK_MAP.put("myConfig", new configRegistryServiceImpl());
    }

    public static void main(String[] args) throws Exception{

//        String regex = "[a-zA-Z0-9_.,\\-()/=+?!*;@#:%\\[\\]‘\\\\${}^|~\\n\\r\\t ]{1,35}";
//        String input = ".,\\-()/=+?!*[\\]‘\\ 0.0${}^|~\n\r\t";

        String regex = "^[0-9;]*$";
        Pattern pattern = Pattern.compile(regex);
        String input = "15767";
        Matcher matcher = pattern.matcher(input);
        System.out.println(matcher.matches());




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


