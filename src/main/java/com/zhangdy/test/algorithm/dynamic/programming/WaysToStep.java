package com.zhangdy.test.algorithm.dynamic.programming;

public class WaysToStep {


    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        int n = 3;
        int i = waysToStep(n);
        System.out.println("台阶为：" + n + "，上楼方式有：" + i + "种");

    }

    public static int waysToStep(int n) {
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            f[i] = f[i - 1];
            if (i >= 2)  f[i] = (f[i] + f[i - 2]) % MOD;
            if (i >= 3)  f[i] = (f[i] + f[i - 3]) % MOD;
        }
        return f[n];
    }

}
