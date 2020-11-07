package com.zhangdy.test.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomString {

    private static final String[] BASE_STRING = {"0", "1", "2", "3",
            "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e",
            "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o",
            "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y",
            "z", "A", "B", "C", "D",
            "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S",
            "T", "U", "V", "W", "X", "Y", "Z"};

    public static void main(String args[]) {

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; ; i++) {
            String randomString = getRandomString(16);
            Integer integer = map.get(randomString);
            if (integer == null) {
                map.put(randomString, 1);
            } else {
                System.out.println("重复字符串  :  " + randomString + "    ---> i:" + i);
                map.put(randomString, integer + 1);
            }
        }
    }

    public static String getRandomString(int len) {
        Random random = new Random();
        int length = BASE_STRING.length;
        String randomString = "";
        for (int i = 0; i < length; i++) {
            randomString += BASE_STRING[random.nextInt(length)];
        }
        random = new Random(System.currentTimeMillis());
        String resultStr = "";
        for (int i = 0; i < len; i++) {
            resultStr += randomString.charAt(random.nextInt(randomString.length() - 1));
        }
        return resultStr;
    }

}