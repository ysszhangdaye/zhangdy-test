package com.zhangdy.test.design.pattern.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContainerSingleton {

    private ContainerSingleton(){}

    private static final Map<String, Object> INSTANCE_MAP = new ConcurrentHashMap<>();

    public static Object getInstance(String clazz){
        if (!INSTANCE_MAP.containsKey(clazz)) {
            try {
                synchronized (clazz.intern()) {
                    if (!INSTANCE_MAP.containsKey(clazz)) {
                        Object o = Class.forName(clazz).newInstance();
                        INSTANCE_MAP.put(clazz, o);
                        return o;
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return INSTANCE_MAP.get(clazz);
    }

    public static void main(String[] args) {
        INSTANCE_MAP.put("1", null);
    }

}
