package com.zhangdy.util;

import com.google.common.collect.Maps;

import java.awt.*;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.lang.management.ManagementFactory;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SystemUtil {
    /**
     * CUP 个数
     *
     * @return int CUP 个数
     */
    public static final int cupNum() {
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * 空闲内存量
     *
     * @return long 空闲内存量
     */
    public static final long freeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * 内存总量
     *
     * @return long 内存总量
     */
    public static final long totalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    /**
     * 最大内存量
     *
     * @return long 最大内存量
     */
    public static final long maxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    // check if Operating System is Windows
    public static boolean isWindows() {
        String os = System.getProperty("os.name");
        return os != null && (os.toLowerCase().indexOf("win") >= 0);
    }

    public static void checkTranslucencyMode() {

        SystemUtil
                .checkTranslucencyMode(WindowTranslucency.PERPIXEL_TRANSLUCENT);
        SystemUtil
                .checkTranslucencyMode(WindowTranslucency.PERPIXEL_TRANSPARENT);

    }

    public static void checkTranslucencyMode(WindowTranslucency arg) {
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        if (!gd.isWindowTranslucencySupported(arg)) {
            System.err.println("'" + arg
                    + "' translucency mode isn't supported.");
            System.exit(-1);
        }
    }

    public static Map<Object, Object> print() {
        HashMap<Object, Object> objectObjectHashMap = Maps.newHashMap();
        Map<String, String> envs = System.getenv();
        for (String key : envs.keySet()) {
            objectObjectHashMap.put(key, envs.get(key));
        }
        Properties properties = System.getProperties();

        for (Object key : properties.keySet()) {
            objectObjectHashMap.put(key, properties.get(key));
        }
        objectObjectHashMap.put("securityManager", System.getSecurityManager());
        return objectObjectHashMap;

    }

    /**
     * 获取PID
     *
     * @return
     */
    public static long getPid() {
        try {
            String jvmName = ManagementFactory.getRuntimeMXBean().getName();
            return Long.parseLong(jvmName.split("@")[0]);
        } catch (Throwable ex) {

        }
        return 0;
    }


    public static void main(String[] args) {

        System.out.println(SystemUtil.getPid());
    }

    public static boolean isLinux() {
        Properties props = System.getProperties();
        String osName = props.getProperty("os.name");
        if (osName.contains("Linux")) {
            return true;
        }
        return false;
    }

    public static String getMac() {
        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                StringBuffer stringBuffer = new StringBuffer();
                NetworkInterface networkInterface = enumeration.nextElement();
                if (networkInterface != null) {
                    byte[] bytes = networkInterface.getHardwareAddress();
                    if (bytes != null) {
                        for (int i = 0; i < bytes.length; i++) {
                            if (i != 0) {
                                stringBuffer.append("-");
                            }
                            int tmp = bytes[i] & 0xff; // 字节转换为整数
                            String str = Integer.toHexString(tmp);
                            if (str.length() == 1) {
                                stringBuffer.append("0" + str);
                            } else {
                                stringBuffer.append(str);
                            }
                        }
                        return stringBuffer.toString().toUpperCase();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }


}
