package com.zhangdy.test.java;


import com.alibaba.fastjson.JSON;
import lombok.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @I18nConvert
    private String name;
    private int age;
    private BigDecimal a;



    public static void main(String[] args) throws Exception {

        User user = new User();
        user.setName("1");
        System.out.println(JSON.toJSONString(user));
        Field name = User.class.getDeclaredField("name");


        name.setAccessible(true);
        name.set(user, "sadasdsad");
        System.out.println(JSON.toJSONString(user));


//        Field[] fields = User.class.getDeclaredFields();

//        String print = "CapitalDetail.builder()";

//        for (Field field :fields) {
//            String print = "." + field.getName() + "(parentDetail." +  getFieldValueByName(field.getName()) + ")";
//            System.out.println(print);
//        }
//
    }

    private static String getFieldValueByName(String fieldName) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            return getter + "()";
        } catch (Exception e) {
            return null;
        }
    }



}
