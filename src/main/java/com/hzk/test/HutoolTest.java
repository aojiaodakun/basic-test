package com.hzk.test;

import cn.hutool.core.date.DateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class HutoolTest {

    public static void main(String[] args) {



        // 客户端
        Date today = DateUtil.parse(DateUtil.today());
        System.out.println(today);

        // 服务端
        Date date = new Date();
        System.out.println(date);

        TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
        int differentDays = differentDays(date, today, timeZone.toZoneId());
        System.out.println(differentDays);
    }


    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2, ZoneId zoneId) {
        LocalDateTime localDatetime1 = LocalDateTime.ofInstant(date1.toInstant(), zoneId);
        LocalDate localDate1 = localDatetime1.toLocalDate();
        LocalDateTime localDatetime2 = LocalDateTime.ofInstant(date2.toInstant(), zoneId);
        LocalDate localDate2 = localDatetime2.toLocalDate();
        long day1 = localDate1.toEpochDay();
        long day2 = localDate2.toEpochDay();
        return (int) (day2 - day1);
    }

}
