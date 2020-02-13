package com.zhangdy.test.wallet;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.zhangdy.test.annotation.TableName;
import com.zhangdy.util.GenerateSqlUtil;
import com.zhangdy.util.IDS;
import com.zhangdy.util.RsaUtils;
import lombok.*;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

public class IccWalletConfig {


    static String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDfautNs3QvgeHEuBLDlkLVRIT5X/dCme+yFjrE8EZpjdo2mKBr8SweRc08WWFO1J7lgcCJyXkJ8fPHDLlvzz6+F2PGSAOIfBuIDs3yBB2u+G7agvxhmvPucHiwzKymUNAtvAVkdZiZEWVqyVPlh6rgvv8q8WlxYs8qjvIy75XWqQIDAQAB";
//
//    public static void main(String[] args) {
//        //2017-12-13
//        String str = "12-13";
//        //2017年每个月的天数
//        List<Integer> dayOfMon = Arrays.asList(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
//        int month = Integer.parseInt(str.split("-")[0]);
//        int day = Integer.parseInt(str.split("-")[1]);
//        int result = (month - 1) * dayOfMon.get(month - 1) + day;
//        System.out.println(str + " is the " + result + "th day of this year.");
//    }

//    static final Map<String,String> valuesStrMap = new HashMap() {
//        {
//            put(BigDecimal.class.getSimpleName(), "%s");
//            put(String.class.getSimpleName(), "'%s'");
//            put(Long.class.getSimpleName(),  "%s");
//            put(Integer.class.getSimpleName(),  "%s");
//        }
//    };


    public static int dayOfYear(String date) {

        List<Integer> leapYearDayOfMonth      = Arrays.asList(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        List<Integer> ordinaryYearDayOfMonth  = Arrays.asList(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

        String[] array = date.split("-");

        int year = Integer.parseInt(array[0]);
        int month =  Integer.parseInt(array[1]);
        int day =   Integer.parseInt(array[2]);
        if (isLeapYear(year)) {

            int idx = 1;
            for (Integer mon :leapYearDayOfMonth ) {
                if (idx++ < month) {
                    day += mon;
                }
            }


        } else {
            int idx = 1;
            for (Integer mon :ordinaryYearDayOfMonth ) {
                if (idx < month) {
                    System.out.println(mon);
                    day += mon;
                }
                idx++;
            }
            System.out.println(day);
        }

        return day;

    }

    public static boolean isLeapYear(int year){

        if ((year%4 == 0 && year%100!=0)   || year%400 == 0) {
            return true;
        }
        return false;

    }


    public static void main(String[] args) throws Exception{
//        int i = dayOfYear("1900-03-25");
//        System.out.println(i);
//        System.out.println(1900&4 );

        BtcConfig.genConfigSql();
        EtcConfig.genConfigSql();
        EthConfig.genConfigSql();
        OmniUsdtConfig.genConfigSql();
    }


    public static String encode(String str) throws Exception{
        byte[] bytes = RsaUtils.encryptByPublicKey(str.getBytes(), pubKey);
        return RsaUtils.toHexString(bytes);
    }



   @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
   @TableName("wallet_config")
   public static class Config {
        private Long id;
        private String url;
        private String user;
        private String password;
        private Integer type;
        private String default_from_address;
        private String currency;
        private String link_type;
        private Integer enabled;
        private String remark;
    }


}
