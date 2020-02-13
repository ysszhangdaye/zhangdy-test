package com.zhangdy.test.java;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class TestField {
    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setName("1");
        System.out.println(JSON.toJSONString(user));
        Field name = User.class.getDeclaredField("name");
        name.setAccessible(true);
        Object o = name.get(user);
        System.out.println(o);


        name.set(user, "sadasdsad");
        System.out.println(JSON.toJSONString(user));
//        Object[] a = {1, 2};
//
//        String format = MessageFormat.format("a{0}, {1}", a);
//        System.out.println(format);


//        List<User> u = Lists.newArrayList();
//        u.add(new User("a"));
//        u.add(new User("b"));
//        u.add(new User("c"));
//        u.add(new User("d"));
//        u.add(new User("e"));
//        u.add(new User("f"));
//        u.add(new User("g"));
//        convertList(u);
    }



    public static void test1(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }

    public static void convertList(Object obj){
        List<?> list = (List<?>) obj;
        list.parallelStream().forEach(o -> {
            Field[] fields=o.getClass().getDeclaredFields();
            ArrayList<Field> fieldList = Lists.newArrayList(fields);
            fieldList.parallelStream().forEach(field -> {
                I18nConvert annotation = field.getAnnotation(I18nConvert.class);
                if (annotation == null) {
                    return;
                }
                String name = field.getName();
                Object str = getFieldValueByName(name, o);
                if (str ==null) {
                    return;
                }
                setFieldValueByName(field.getName(), o, str + "222");
            });
        });
        System.out.println(JSON.toJSONString(obj));
    }

    public static void setFieldValueByName(String fieldName, Object o, String value) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "set" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, String.class);
            method.invoke(o, value);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

}
