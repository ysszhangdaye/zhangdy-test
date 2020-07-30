package com.zhangdy.test.decimal;


import com.zhangdy.util.ThreadUtil;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestDecimalFormat {


    public static void main(String[] args) {

        ScheduledExecutorService  executorService = new ScheduledThreadPoolExecutor(100, new BasicThreadFactory.Builder().namingPattern("Execute-Pool-%d--").build());


        executorService.scheduleAtFixedRate(()->{
            int i = 0;
            while(true){
                System.out.println(i++);
                ThreadUtil.SET_MILLISECONDS(100);
                if (i==100) {
                    break;
                }
            }

                },
                1000L, 100, TimeUnit.MILLISECONDS);





//        String a= "1301720737078373550000000";
//        String b = "5144361246800";
//
//        BigDecimal divide = new BigDecimal(a).divide(BigDecimal.TEN.pow(16)).add(new BigDecimal(b).divide(BigDecimal.TEN.pow(8)));
////        130223517.320305355
////        127200833.62994235500000000000
//        System.out.println(divide);

//        String step = "0.0001";
//        DecimalFormat numberInstance = (DecimalFormat )DecimalFormat.getNumberInstance();
//        numberInstance.applyPattern("#.#");
//
//        String s = step.split("\\.")[1];
//        numberInstance.setMaximumFractionDigits(s.length());
////        DecimalFormat  numberInstance = (DecimalFormat )DecimalFormat.getNumberInstance();
////        numberInstance.applyPattern("#.#####");
////        numberInstance.setRoundingMode(RoundingMode.DOWN);
////        numberInstance.setMaximumFractionDigits(6);
////        System.out.println("保留4位小数 ---> " + numberInstance.format(new BigDecimal("1111111111007.1111711")));
//
//        numberInstance.setRoundingMode(RoundingMode.DOWN);
//        // 买
//        BigDecimal bigDecimal1 = new BigDecimal(numberInstance.format(new BigDecimal("540.00054"))).setScale(6, BigDecimal.ROUND_DOWN);
//
//
//        numberInstance.setRoundingMode(RoundingMode.UP);
//        // 卖
//        BigDecimal bigDecimal2 = new BigDecimal(numberInstance.format(new BigDecimal("540.00055"))).setScale(6, BigDecimal.ROUND_DOWN);
//        System.out.println(bigDecimal1);
//        System.out.println(bigDecimal2);

//        0.00053

//        0.000001
    }




}
