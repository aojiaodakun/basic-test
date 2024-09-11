package com.hzk.tool.hutool;

import cn.hutool.core.date.DateUtil;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;

/**
 * hutoolDate序列化问题
 */
public class HutoolDateTest {

    public static void main(String[] args) {
        Date today = DateUtil.parse(DateUtil.today());
        System.out.println(today);
        // 序列化/反序列化
        byte[] bytes = serialize(today);
        Date newToday = deserialize(bytes);
        System.out.println(newToday);
    }

    private static byte[] serialize(Date date){
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Hessian2Output hessian2Output = new Hessian2Output(byteArrayOutputStream);
            hessian2Output.writeObject(date);
            hessian2Output.getBytesOutputStream().flush();
            hessian2Output.completeMessage();
            hessian2Output.close();

            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Date deserialize(byte[] bytes){
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            Hessian2Input input = new Hessian2Input(is);
            Date result = (Date)input.readObject();
            input.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
