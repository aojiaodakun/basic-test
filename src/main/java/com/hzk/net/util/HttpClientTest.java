package com.hzk.net.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultClientConnectionReuseStrategy;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpClientTest {

    private static HttpClient staticHttpClient;

    public static void main(String[] args) throws Exception {
        initHttpClient();

        String uri = "http://localhost:3658/test";
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 20; j++) {
                    // method1
//                    String result = "";
//                    try {
//                        result = HttpClientUtil.get(uri);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }

                    // method2
                    String result = null;
                    try {
                        result = get(staticHttpClient, uri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(result);
                }
            },"thread_" + i).start();
        }
        Thread.currentThread().sleep(1000* 10);
        new Thread(()->{
            while (true) {
                try {
                    Thread.currentThread().sleep(1000* 1);
                    String result = null;
                    try {
                        result = get(staticHttpClient, uri);
                    } catch (Exception e) {
                        e.printStackTrace();
                        initHttpClient();
                    }
                    System.out.println(result);
                } catch (Exception e){

                }
            }
        },"testConn").start();

        System.in.read();
    }

    private static void initHttpClient() {
        // TODO，10次业务调度，执行一次ping-pong逻辑
        staticHttpClient = HttpClientBuilder.create()
                //DefaultConnectionKeepAliveStrategy是默认的判断超时时间策略，读取的是Keep-Alive:timeout=超时时间
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setMaxConnTotal(1)// TODO，待调整
                .useSystemProperties()
                .setDefaultRequestConfig(
                        RequestConfig.custom()
                                .setConnectionRequestTimeout(5000)
                                .setConnectTimeout(5000)
                                .setSocketTimeout(5000).build()
                ).build();
    }

    public static String get(HttpClient client, String url) throws IOException, URISyntaxException {
        BufferedReader in = null;

        String content = null;
        try {
            // 实例化HTTP方法
            HttpGet request = new HttpGet();
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);

            in = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent()));
            StringBuilder sb = new StringBuilder();
            String line;
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line).append(NL);
            }
            content = sb.toString();
        } catch (URISyntaxException | IOException e) {
            throw e;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    //ignore
                }
            }
        }
        return content;
    }


}
