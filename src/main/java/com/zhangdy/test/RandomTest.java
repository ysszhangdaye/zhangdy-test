package com.zhangdy.test;

import com.zhangdy.util.IDS;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomTest {


    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("[1-8]");
        Matcher matcher = pattern.matcher("9");
        System.out.println(matcher.matches());

    }

}
