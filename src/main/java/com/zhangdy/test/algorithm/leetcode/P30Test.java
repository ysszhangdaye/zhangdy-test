package com.zhangdy.test.algorithm.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class P30Test {

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = {"foo","bar"};
        List<Integer> substring = findSubstring(s, words);
        System.out.println(JSON.toJSONString(substring));
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words == null ) {
            return result;
        }
        int wordLength = words[0].length();





        return null;
    }

}
