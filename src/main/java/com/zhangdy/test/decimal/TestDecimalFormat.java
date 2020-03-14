package com.zhangdy.test.decimal;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TestDecimalFormat {


    public static void main(String[] args) {
        String step = "0.0001";
        DecimalFormat numberInstance = (DecimalFormat )DecimalFormat.getNumberInstance();
        numberInstance.applyPattern("#.#");

        String s = step.split("\\.")[1];
        numberInstance.setMaximumFractionDigits(s.length());
//        DecimalFormat  numberInstance = (DecimalFormat )DecimalFormat.getNumberInstance();
//        numberInstance.applyPattern("#.#####");
//        numberInstance.setRoundingMode(RoundingMode.DOWN);
//        numberInstance.setMaximumFractionDigits(6);
//        System.out.println("保留4位小数 ---> " + numberInstance.format(new BigDecimal("1111111111007.1111711")));

        numberInstance.setRoundingMode(RoundingMode.DOWN);
        // 买
        BigDecimal bigDecimal1 = new BigDecimal(numberInstance.format(new BigDecimal("540.00054"))).setScale(6, BigDecimal.ROUND_DOWN);


        numberInstance.setRoundingMode(RoundingMode.UP);
        // 卖
        BigDecimal bigDecimal2 = new BigDecimal(numberInstance.format(new BigDecimal("540.00055"))).setScale(6, BigDecimal.ROUND_DOWN);
        System.out.println(bigDecimal1);
        System.out.println(bigDecimal2);

//        0.00053

//        0.000001
    }




}
