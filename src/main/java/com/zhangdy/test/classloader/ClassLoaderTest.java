package com.zhangdy.test.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class ClassLoaderTest {

    public static void main(String[] args) throws MalformedURLException {
        String path = "jar:file:///test-jar-1.0.0-SNAPSHOT.jar!/";
        MyClassLoader myClassLoader = new MyClassLoader(path, Thread.currentThread().getContextClassLoader().getParent());
//        Thread.currentThread().setContextClassLoader(myClassLoader);
        try {


            Class<?> clazz = myClassLoader.loadClass("com.zhangdy.test.http.TestClassLoader");
//            Class<?> clazz = Thread.currentThread()
//                    .getContextClassLoader()
//                    .loadClass();
            Object obj = clazz.newInstance();
            Method test1 = clazz.getMethod("test1");
            Object invoke = test1.invoke(obj);
            System.out.println(invoke);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
