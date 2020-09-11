package com.zhangdy.test.date;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TestDate {

    public static void main(String[] args) throws Exception{

//        Date date = new Date();
//
//
//        SimpleDateFormat sdf = new SimpleDateFormat("HH");
//
//        String format = sdf.format(date);
//        System.out.println(format);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date parse = simpleDateFormat.parse("2020-09-04T02:34:33.729Z");
        System.out.println(parse);

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
        String format = simpleDateFormat1.format(parse);
        System.out.println(format);
    }

}
