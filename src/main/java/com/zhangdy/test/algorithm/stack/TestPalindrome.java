package com.zhangdy.test.algorithm.stack;

import java.util.Stack;

public class TestPalindrome {


    public static void main(String[] args) {

        int a = 1232134122;

        System.out.println( isPalindrome(a));

    }


    public static boolean isPalindrome(int x) {

        if (x < 0) {
            return false;
        }
        if (x<10) {
            return true;
        }

        Stack<Character> stack = new Stack<>();

        String str = String.valueOf(x);

        char[] chars = str.toCharArray();

        int length = chars.length/2;
        for (int i = 0; i < length; i++) {
            stack.push(chars[i]);
        }
        int i = length;
        if (length%2 != 0 && chars.length!=2) {
            i++;
        }

        for ( int j =i ; j < chars.length; j++) {
            Character pop = stack.pop();
            if (!pop.equals(chars[j])) {
                return false;
            }
        }
        return true;

    }

}
