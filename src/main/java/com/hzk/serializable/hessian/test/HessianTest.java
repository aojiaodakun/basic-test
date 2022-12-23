package com.hzk.serializable.hessian.test;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.hzk.serializable.hessian.dto.MserviceRequestDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class HessianTest {

    private static final String FILE_PATH = "../dto.txt";


    public static void main(String[] args) {
        MserviceRequestDTO dto = getDTO();
        serialize(dto);
        deserialize();
    }

    private static void serialize(MserviceRequestDTO dto){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Hessian2Output hessian2Output = new Hessian2Output(byteArrayOutputStream);
            hessian2Output.writeObject(dto);
            hessian2Output.getBytesOutputStream().flush();
            hessian2Output.completeMessage();
            hessian2Output.close();

            byte[] bytes = byteArrayOutputStream.toByteArray();
            System.out.println("hessian序列化后字节数:" + bytes.length);
            String s = new String(bytes);

            // fastjson
            String string = JSON.toJSONString(dto);
            System.out.println("fastjson.bytes:" + string.length());



            // 写入文件
            fileOutputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deserialize(){
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            byte[] data = new byte[1024];
            int len = fileInputStream.read(data);
            System.out.println("byte length:" + len);

            ByteArrayInputStream is = new ByteArrayInputStream(data);
            Hessian2Input input = new Hessian2Input(is);
            Object result = input.readObject();
            System.out.println(result);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    private static MserviceRequestDTO getDTO(){
        MserviceRequestDTO requestDTO = new MserviceRequestDTO();
        requestDTO.setAge(18);
        requestDTO.setMoney(999l);
        requestDTO.setName("hzk");
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("a", "1");
        paramMap.put("b", "2");
        requestDTO.setParamMap(paramMap);

        return requestDTO;
    }

}
