package com.hzk.safeurl;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;


public class SafeUrlTest {





    public static void main(String[] args) throws Exception{
        String NL = System.getProperty("line.separator");
        String url = "http://ssmc.kingdee.com/tenant/cloud_auth/get_access_token.do?from=2&redirect_url=https://feature.kingdee.com:1026/patchhr/&service_id=EK_12850105&service_key=b077f76e9b594c57b78c79700d38c188&tenantid=tenant_patchhr_test&accountId=1163738190729709568&appId=shr_charge&appSecuret=Kingdee@shrCharge.123&user=administrator";
        HttpClient httpClient = new DefaultHttpClient();

        while (true) {
            HttpGet httpGet = new HttpGet();
            httpGet.setURI(new URI(url));
            HttpResponse httpResponse = httpClient.execute(httpGet);
            BufferedReader in = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            while((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            String content = sb.toString();
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(dateFormat.format(date) + "。响应:" + content);
            Thread.currentThread().sleep(1000 * 60 * 8);
        }

//        String fixLengthString = getFixLengthString();
//        String randomString = getRandomString();
//        String shortUri = fixLengthString + randomString;
//        boolean flag = isShortUri(shortUri);
    }

    private static String getRandomNumber(){
        while (true) {
            // 3~6
            int randomInt = (int)(Math.random() * 4 + 3);
            String randomString = RandomStringUtils.random(randomInt, "0123456789");
            int total = 0;
            // 012345
            for (int i = 0; i < randomString.length(); i++) {
                int temp = Integer.parseInt(randomString.charAt(i)+"");
                total += temp;
            }
            if (total%10 == 0){
                return randomString;
            }
        }
    }

    private static boolean isShortUri(String uri){
        String fixLengthString = uri.substring(0, 8);
        int total = 0;
        // 012345
        for (int i = 0; i < fixLengthString.length(); i++) {
            char c = fixLengthString.charAt(i);
            if (c >= 48 && c <= 57 ) {
                int temp = Integer.parseInt(fixLengthString.charAt(i)+"");
                total += temp;
            }
        }
        if (total % 5 == 0){
            return true;
        }
        return false;
    }


    private static String getFixLengthString(){
        while (true) {
            String randomString = RandomStringUtils.randomAlphanumeric(8);
            int total = 0;
            // 012345
            for (int i = 0; i < randomString.length(); i++) {
                char c = randomString.charAt(i);
                if (c >= 48 && c <= 57 ) {
                    int temp = Integer.parseInt(randomString.charAt(i)+"");
                    total += temp;
                }
            }
            if (total % 5 == 0){
                return randomString;
            }
        }
    }

    private static String getRandomString(){
        // 2~4
        int randomInt = (int)(Math.random() * 3 + 2);
        return RandomStringUtils.randomAlphanumeric(randomInt);
    }



}
