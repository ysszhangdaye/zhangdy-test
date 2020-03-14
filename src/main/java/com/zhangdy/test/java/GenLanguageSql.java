package com.zhangdy.test.java;

import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

public class GenLanguageSql {

    public static void main(String[] args) {
//        test("C:\\Users\\it\\Desktop\\account-er.txt");
        test("D:\\a.sql");

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
                String msg = lineTxt.split("\\|")[0];
                String code = lineTxt.split("\\|")[1];

                String format = String.format(SQL, code, "zh-cn", msg);
                String format1 = String.format(SQL, code, "en-us", msg + "-en");
                System.out.println(format);
                System.out.println(format1);
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
