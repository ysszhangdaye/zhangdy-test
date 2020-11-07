package com.zhangdy.test.algorithm.dynamic.programming;

public class LongestCommonSubsequence {


    public static void main(String[] args) {
        String text1 = "abc";
        String text2 = "absdc";
        int i = longestCommonSubsequence(text1, text2);
        System.out.println(i);
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();

        int[][] f = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                f[i][j] = Integer.max(f[i - 1][j], f[i][j - 1]);
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    f[i][j] = Integer.max(f[i][j], f[i - 1][j - 1] + 1);
            }
        }

        return f[n][m];
    }

}
