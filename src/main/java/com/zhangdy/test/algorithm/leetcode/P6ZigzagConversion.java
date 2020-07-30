package com.zhangdy.test.algorithm.leetcode;


/**
 *
 */
public class P6ZigzagConversion {

    public static void main(String[] args) {




    }


    public static String convert(String str, int numRows){
        if (str == null || str.trim().length() == 0 || numRows<=0) {
            return "";
        }
        if (numRows == 1) {
            return str;
        }
        StringBuilder result = new StringBuilder();
        int size = 2 * numRows - 2;

        for (int i = 0; i < size; i++) {

            for (int j = i; j < str.length(); j += size) {
                result.append(str.charAt(j));
                if(i != 0 && i != numRows - 1){
                    int temp = j + size - 2 * i;
                    if(temp < str.length()){
                        result.append(str.charAt(temp));
                    }
                }
            }
        }

        return result.toString();
    }

}
