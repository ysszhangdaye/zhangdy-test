package com.zhangdy.test.java;

import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

public class GenLanguageSql {

    public static void main(String[] args) {
//        test("C:\\Users\\it\\Desktop\\account-er.txt");


    }

    public static void test(String path) {
        List<File> files = Lists.newArrayList();
        String SQL = "INSERT INTO `meta_language` (`code`, `enabled`, `language`, `message` ) VALUES('%s','1','%s','%s');";
        try {
            List<String> sqlList = Lists.newArrayList();
            File file = new File(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                String s = lineTxt.split("\t")[0];
                String s1 = lineTxt.split("\t")[1];

                String format = String.format(SQL, s, "zh-cn", s1);
                String format1 = String.format(SQL, s, "en-us", s1 + "-en");
                System.out.println(format);
                System.out.println(format1);
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
