/**
 * @Project： cloud.tools
 * @Package：com.cloud.tools
 * @Title：ThreadUtil.java.java
 * @Version：V1.0.0
 */
package com.zhangdy.util;

import java.util.concurrent.TimeUnit;

public class ThreadUtil {


    private ThreadUtil() {

    }

    /**
     * 休息N天
     *
     * @param N 天
     */
    public static void SET_DAYS(int N) {
        try {
            TimeUnit.DAYS.sleep(N);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 休息N小时
     *
     * @param N 小时
     */
    public static void SET_HOURS(int N) {
        try {
            TimeUnit.HOURS.sleep(N);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 休息N毫秒
     *
     * @param N 毫秒
     */
    public static void SET_MILLISECONDS(int N) {
        try {
            TimeUnit.MILLISECONDS.sleep(N);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 休息N微秒
     *
     * @param N 微秒
     */
    public static void SET_MICROSECONDS(int N) {
        try {
            TimeUnit.MICROSECONDS.sleep(N);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 休息N纳秒
     *
     * @param N 纳秒
     */
    public static void SET_NANOSECONDS(int N) {
        try {
            TimeUnit.NANOSECONDS.sleep(N);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 休息N分钟
     *
     * @param N 分钟
     */
    public static void SET_MINUTES(int N) {
        try {
            TimeUnit.MINUTES.sleep(N);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 休息N秒
     *
     * @param N 秒
     */
    public static void SET_SECONDS(int N) {
        try {
            TimeUnit.SECONDS.sleep(N);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
