package com.zhangdy.test.testdate;

import java.util.Calendar;
import java.util.Date;

public class Test {


    public static void main(String[] args) {
//        1582992000000
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(day);
    }

}
