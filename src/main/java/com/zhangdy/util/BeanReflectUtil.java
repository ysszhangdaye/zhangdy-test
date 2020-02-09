package com.zhangdy.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 类描述：封装反射方法，主要利用反射调用对象的get方法 <br>
 */
public class BeanReflectUtil {
    /**
     * 组装类中某个属性的get方法名称 StringUtil.upperFirst(String str)是自己封装的，将首字母变大写的小方法；
     *
     * @param c         所反射方法所在的类的Class
     * @param filedName ：字段名称
     * @return 返回该字段的get方法
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    public static Method assembleGetMethod(Class<?> c, String filedName)
            throws SecurityException, NoSuchMethodException {
        String methodName = "get" + BeanReflectUtil.upperFirst(filedName);// 组装方法名
        Class<?>[] nullClasses = null;// getter方法所需要的参数类型的Class
        return c.getDeclaredMethod(methodName, nullClasses);
    }

    /**
     * 组装类中某个属性的set方法名称
     *
     * @param c             :所反射方法所在的类的Class
     * @param filedName     ：字段名称
     * @param classArgsType :setter方法所需的参数类型
     * @return ：返回该字段的setter方法
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    public static Method assembleSetMethod(Class<?> c, String filedName,
                                           Class<?>[] classArgsType) throws SecurityException,
            NoSuchMethodException {
        String methodName = "set" + BeanReflectUtil.upperFirst(filedName);
        return c.getDeclaredMethod(methodName, classArgsType);
    }

    /**
     * 调用组装类中某个属性的getter方法
     *
     * @param c         ：所反射方法所在的类的Class
     * @param filedName ：字段名称
     * @param obj       ：所反射方法所在的类的实体对象
     * @return ：getter的返回值
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object invokeGetMethod(Class<?> c, String filedName,
                                         Object obj) throws SecurityException, NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        Method method = assembleGetMethod(c, filedName); // 得到属性的getter
        Object[] nullObjects = null; // 定义方法所要的参数值
        return method.invoke(obj, nullObjects);
    }

    /**
     * 调用组装类中某个属性的setter方法
     *
     * @param c         ：所反射方法所在的类的Class
     * @param filedName ：字段名称
     * @param obj       ：所反射方法所在的类的实体对象
     * @param argsType  :setter方法所需要的参数类型
     * @param argsValue ：setter方法所需要的参数值
     * @return setter方法的返回值
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void invokeSetMethod(Class<?> c, String filedName,
                                       Object obj, Class<?>[] argsType, Object[] argsValue)
            throws SecurityException, NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        Method method = assembleSetMethod(c, filedName, argsType);
        method.invoke(obj, argsValue);
    }

    /**
     * 将首字母转化为大写的小方法
     *
     * @param str :需要转化的字符
     * @return 转化后的结果
     */
    public static String upperFirst(String str) {
        String first = str.substring(0, 1);
        String last = str.substring(1);
        return first.toUpperCase() + last;
    }

    public static String converToColunm(String colunmStr) {
        if (colunmStr != null && !"".equals(colunmStr)) {
            if (colunmStr.indexOf("_") > 0) {
                String[] splitStr = colunmStr.toLowerCase().split("_");
                String newStr = "";
                for (int i = 1; i < splitStr.length; i++) {
                    newStr += splitStr[i].substring(0, 1).toUpperCase()
                            + splitStr[i].substring(1);
                }
                return splitStr[0] + newStr;
            } else {

                return colunmStr;
            }

        }
        return "";
    }

    /**
     * 根据类对象得到该类的所有属性
     *
     * @param entityClass
     * @return
     */
    public static String[] getPropertyNameByClass(Class<?> entityClass) {
        Field[] fields = entityClass.getDeclaredFields();
        int length = fields.length;
        String[] name = new String[length];
        for (int j = 0; j < length; j++) {
            fields[j].setAccessible(true);
            try {
                name[j] = fields[j].getName();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return name;
    }
}