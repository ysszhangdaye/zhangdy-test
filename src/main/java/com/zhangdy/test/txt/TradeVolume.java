package com.zhangdy.test.txt;

import com.alibaba.fastjson.JSON;
import com.zhangdy.util.RsaUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class TradeVolume {
    public static void main(String[] args) throws Exception{
        File file = new File("D:\\a.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String lineTxt = null;
        BigDecimal amount = BigDecimal.ZERO;
        while ((lineTxt = br.readLine()) != null) {
            String[] split = lineTxt.split(",");
            if (split.length<=1) {
                continue;
            }
            for (String s : split) {
                if (s.trim().indexOf("price") == 0){
                    System.out.println(s);
                    BigDecimal price = new BigDecimal(s.split("=")[1]).divide(BigDecimal.TEN.pow(10));
                    break;
                }
            }
        }
        br.close();
        System.out.println(amount);//68.0
    }

}
