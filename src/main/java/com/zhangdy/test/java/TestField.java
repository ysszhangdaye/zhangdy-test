package com.zhangdy.test.java;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zhangdy.util.IDS;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;

public class TestField {
    public static void main(String[] args) throws Exception {
//        TestField testField = new TestField();
//        testField.testNoStream();
//        testField.testStream();
//        int x=20, y=5;
//        System.out.println(x+y +""+(x+y)+y);

        int a = Integer.parseInt("1024");
        int b  =Integer.valueOf("1024").intValue();
        System.out.println(a==b);

    }

    public  long testNoStream(){
        List<String> language = Lists.newArrayList();
        language.add("zh-cn");
        language.add("en-us");
        User zhangsan = new User("zhangsan");
        List<User> u = Lists.newArrayList();
        User a = new User("a");
        a.setLanguage(language);
        a.setUser(Lists.newArrayList(zhangsan));
        User b = new User("b");
        b.setLanguage(language);
        u.add(a);
        u.add(b);
        u.add(new User("c"));
        u.add(new User("d"));
        u.add(new User("e"));
        u.add(new User("f"));
        u.add(new User("g"));
        for (int i=0;i<10000;i++) {
            u.add(new User("g" + i));
            a.setLanguage(language);
        }

        long start = System.currentTimeMillis();
        convertList(u);
        long end = System.currentTimeMillis();
//        System.out.println("耗时（毫秒）：" + (end - start));
        return end - start;
    }


    public  long testStream(){
        List<String> language = Lists.newArrayList();
        language.add("zh-cn");
        language.add("en-us");
        User zhangsan = new User("zhangsan");
        List<User> u = Lists.newArrayList();
        User a = new User("a");
        a.setLanguage(language);
        a.setUser(Lists.newArrayList(zhangsan));
        User b = new User("b");
        b.setLanguage(language);
        u.add(a);
        u.add(b);
        u.add(new User("c"));
        u.add(new User("d"));
        u.add(new User("e"));
        u.add(new User("f"));
        u.add(new User("g"));
        for (int i=0;i<10000;i++) {
            u.add(new User("g" + i));
            a.setLanguage(language);
        }

        long start = System.currentTimeMillis();
        convertListStream(u);
        long end = System.currentTimeMillis();
//        System.out.println("耗时（毫秒）：" + (end - start));
        return end - start;
    }



    public  void convertList(Object obj){
        List<?> list = (List<?>) obj;
        for (Object o : list) {

            Field[] fields = o.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    I18nConvert annotation = field.getAnnotation(I18nConvert.class);
                    Object value = field.get(o);
                    if (annotation == null || value == null) {
                        continue;
                    }
                    System.out.println(field.getName());

                    Field name = o.getClass().getDeclaredField("name");
                    if (value instanceof List) {
                        translateStringList(field, value, o);
                        continue;
                    }
                    field.set(o,  value + " -> "+ IDS.uniqueID());
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public  void convertListStream(Object obj){
        List<?> list = (List<?>) obj;
        list.parallelStream().forEach(o -> {
            Field[] fields = o.getClass().getDeclaredFields();
            Lists.newArrayList(fields).parallelStream().forEach(field -> {
                try {
                    field.setAccessible(true);
                    I18nConvert annotation = field.getAnnotation(I18nConvert.class);
                    Object value = field.get(o);
                    if (annotation == null || value == null) {
                        return;
                    }
                    if (value instanceof List) {
                        translateStringList(field, value, o);
                        return;
                    }
                    field.set(o,  value + " -> "+ IDS.uniqueID());
                }catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
    }

    public  void translateStringList(Field field, Object value, Object obj) throws Exception{
        Type genericType = field.getGenericType();
        if (null == genericType) {
            return;
        }
        if (genericType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) genericType;
            Class<?> actualTypeArgument = (Class<?>)pt.getActualTypeArguments()[0];
            if (String.class.equals(actualTypeArgument)) {
                List<String> translateStringList = translateStringList((List<?>) value);
                field.set(obj,  translateStringList);
            } else {
                convertList(value);
            }
        }
    }

    public  List<String> translateStringList(List<?> list){
        List<String> result = Lists.newArrayList();
        list.parallelStream().forEach(value -> {
            {
                if (value != null) {
                    result.add(value.toString() + " language -> "+ IDS.uniqueID());
                }
            }
        });
        return result;
    }


}
