package com.zhangdy.test.algorithm.leetcode;

public class P26Test {

    public static void main(String[] args) {

//        18:00	info
//        解答失败:
//        测试用例:"mississippi"
//        "issip"
//        测试结果:-1
//        期望结果:4
//        stdout:

        int i = strStr("mississippi", "issip");
        System.out.println(i);
    }


    public static int strStr(String haystack, String needle) {

        if ("".equalsIgnoreCase(haystack) && !"".equalsIgnoreCase(needle)) {
            return -1;
        }
        if ( "".equalsIgnoreCase(needle) ) {
            return 0;
        }

        char[] haystackArray = haystack.toCharArray();
        char[] needleArray = needle.toCharArray();


        int j = 0;
        int result = -1;
        for (int i = 0; i < haystackArray.length; i++) {
            if (haystackArray[i] == needleArray[j]) {
                if (j == 0) {
                    result = i;
                }
                if (j++ == needleArray.length) {
                    break;
                }

            } else {
                if (j > 0){
                    i = i - j;
                }
                j = 0;
            }
        }

        if (j != needleArray.length) {
            return -1;
        }
        return result;

    }
}
