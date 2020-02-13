package com.zhangdy.test.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {

    public static void main(String[] args) {

        Date date = new Date();


        SimpleDateFormat sdf = new SimpleDateFormat("HH");

        String format = sdf.format(date);
        System.out.println(format);


    }

}
