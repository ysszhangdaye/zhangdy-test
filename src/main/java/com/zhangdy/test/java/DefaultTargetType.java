package com.zhangdy.test.java;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;

public class DefaultTargetType<T> {

    private Type type;
    private T obj;

    @SuppressWarnings("unchecked")
    public DefaultTargetType() {
//        Type superClass = getClass().getGenericSuperclass();
//        this.type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
//        if (this.type instanceof ParameterizedType) {
//            this.classType = (Class<T>) ((ParameterizedType) this.type).getRawType();
//        } else {
//            this.classType = (Class<T>) this.type;
//        }
    }

    public static void main(String[] args) {

        Date date = new Date(1573176985000L);
        System.out.println(date);

//        DefaultTargetType<String> stringDefaultTargetType = new DefaultTargetType<String>();
//        Type genericSuperclass = stringDefaultTargetType.getClass().getClass().getGenericSuperclass();
//
//        if (genericSuperclass instanceof ParameterizedType) {
//            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass)
//                    .getActualTypeArguments();
//            if (actualTypeArguments != null && actualTypeArguments.length > 0) {
//                Class<?> actualTypeArgument = (Class<?>) actualTypeArguments[0];
//                System.out.println(actualTypeArgument);
//            }
//        }
//        String typeName = genericSuperclass.getTypeName();
//        System.out.println(typeName);

    }

}
