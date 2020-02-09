package com.zhangdy.util;

/**
 * @author 12933
 * @Title: CustomStringUtils
 * @ProjectName zhangdy-test
 * @Description: TODO
 * @date 2019/1/299:41
 */
public class CustomStringUtils {

    public static String replaceUnderlineAndfirstToUpper(String srcStr, String org, String ob) {
        String newString = "";
        int first = 0;
        while (srcStr.indexOf(org) != -1) {
            first = srcStr.indexOf(org);
            if (first != srcStr.length()) {
                newString = newString + srcStr.substring(0, first) + ob;
                srcStr = srcStr.substring(first + org.length());
                srcStr = firstCharacterToUpper(srcStr);
            }
        }
        newString = newString + srcStr;
        return newString;
    }

    public static String firstCharacterToUpper(String srcStr) {
        return srcStr.substring(0, 1).toUpperCase() + srcStr.substring(1);
    }

}
