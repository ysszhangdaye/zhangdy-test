package com.zhangdy.test.txt;


import com.google.common.collect.Lists;
import com.zhangdy.util.FileUtils;
import com.zhangdy.util.RsaUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class TestAddressSql {

    public static void main(String[] args) {

//        System.out.println(rsaPrivateKey.length());
//        String s = RsaUtils.signData("2N5Kn2LJCRY9gv3NG1Rv33iPCnrpTKXWxbw", rsaPrivateKey);
//        System.out.println(s);
//        testusdt("D:\\生产充币地址\\20191109-usdtomni", "USDT");
//        testbtc("D:\\生产充币地址\\20191109-btc", "BTC");
    }
    private static String rsaPrivateKey="XMIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALdHgRLS3ZaQkoKmidO+ICYJ8KM+IWU6lX2tY0DxdI3+q54ZjfSYNDD25WC+8acpMooIQHll56nF81tEeaUIW0NopZF+rxRzYT8GxlMCK56lHuegkk1oFW9uyum6enOaPmMh8iGR02+lGjf2I7RqgRcv7+yXFPioGC24JdSsPU7rAgMBAAECgYB2WS7ox88cnYht4zpWx4uc5IhF8SXBZz4G5IKV+hSoaE3LjnbS/cGd6KAfg80pgvnGliOEAKDCSTemtKXUQnwjCC+/0jwfyvCN+fVxdLe2pEwqDfo8xG322Kg4/A/2Q34Mmrn4Up3usQYZ1VRgZ6RgHJtYYovGDiSnQVjzph7XYQJBAOCPR8eY6fci3LhxdUpqkhTcCj3Yl6xxgV8gLR5yT2HSL1iZ4HqIOaMBxI0s6pG99Mtj8CDiuqoB0czBQn1NfZECQQDQ8KSl8wNrKhheUTdCKS963z+782Ko+U7XDqezkFn3MoJ1etypw4YCpbJVB8OusNSnRhe7idBqiOZmX6vOxja7AkEAzuLSk86Kx3ij9334PK5Ald0/xkj1PBd6xIQlbWaNQideHXfvO5Sj3U00o4hdOBbVIEmbowCgTx7MBqOKuVyaMQJAbUaulix3CUFG5W1MnUm1D2pbhw1P+FgLp+Z+RyekJelA2ficGB2upQvMLTSWX3MUhTffibIWUuGuvrQiw05fqQJAJr6/FvoR6Q1v/EfrXNoHtGnSj8knEK8655mXxxQ6/V+QF1q1W9OzoLTfEpv8XM4sQEhxqRqtQLrTuZDMe104lQ==";
    public  static void testusdt(String path, String currency){
        List<File> files = Lists.newArrayList();
        FileUtils.getDirectory(path, files);
        String SQL = "INSERT INTO `address_pool` (`address`, `currency`, `type`, `link_type`, `label_address`, `sign`, `created_on`) VALUES('%s','%s','1','%s',NULL,'%s',now());";
        try {
            List<String> sqlList = Lists.newArrayList();
            for (File file : files){
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String lineTxt = null;
                while ((lineTxt = br.readLine()) != null) {
                    if (lineTxt.contains("INSERT")) {
                        System.out.println(lineTxt);
                        String address = lineTxt.substring(lineTxt.indexOf("VALUES('") + 8, lineTxt.indexOf(currency)-3);
                        System.out.println(address);
                        String sign = RsaUtils.signData(address, rsaPrivateKey);
                        String saveSql = String.format(SQL, address, currency, "OMNI", sign);
                        System.out.println(saveSql);
                        sqlList.add(saveSql);
                    }
                }
                br.close();
            }

            FileUtils.writeToTxt(sqlList, "D:\\生产充币地址\\20191109-usdtomni\\20191111-usdtomni.sql");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    public  static void testbtc(String path, String currency){
        List<File> files = Lists.newArrayList();
        FileUtils.getDirectory(path, files);
        String SQL = "INSERT INTO `address_pool` (`address`, `currency`, `type`, `link_type`, `label_address`, `sign`, `created_on`) VALUES('%s','%s','1',null,NULL,'%s',now());";
        try {
            List<String> sqlList = Lists.newArrayList();
            for (File file : files){
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String lineTxt = null;
                while ((lineTxt = br.readLine()) != null) {
                    if (lineTxt.contains("INSERT")) {
                        System.out.println(file.getPath());

                        System.out.println(lineTxt);
                        String address = lineTxt.substring(lineTxt.indexOf("VALUES('") + 8, lineTxt.indexOf(currency)-3);
                        System.out.println(address);
                        String sign = RsaUtils.signData(address, rsaPrivateKey);
                        String saveSql = String.format(SQL, address, currency,sign);
                        System.out.println(saveSql);
                        sqlList.add(saveSql);
                    }
                }
                br.close();
            }

            FileUtils.writeToTxt(sqlList, "D:\\生产充币地址\\20191109-btc\\20191111-btc.sql");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
