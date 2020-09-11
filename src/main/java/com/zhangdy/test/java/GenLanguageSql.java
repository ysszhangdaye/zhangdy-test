package com.zhangdy.test.java;

import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class GenLanguageSql {

    public static void main1(String[] args) {
//        test("C:\\Users\\it\\Desktop\\account-er.txt");
//        test("D:\\a.txt");


//        genevent();

//        System.out.println(IDS.uniqueID());
//        System.out.println(IDS.uniqueID());

//        MathContext mathContext = new MathContext(8, RoundingMode.DOWN);
//
//        System.out.println(BigDecimal.TEN.pow(-0));
//        System.out.println(BigDecimal.TEN.pow(-1, mathContext));
//        System.out.println(BigDecimal.TEN.pow(-2, mathContext));
//        System.out.println(BigDecimal.TEN.pow(-3, mathContext));
//        System.out.println(BigDecimal.TEN.pow(-4, mathContext));
//        System.out.println(BigDecimal.TEN.pow(-5, mathContext));
//        System.out.println(BigDecimal.TEN.pow(-6, mathContext));
//        System.out.println(BigDecimal.TEN.pow(-7, mathContext));
//        System.out.println(BigDecimal.TEN.pow(-8, mathContext));
//        System.out.println(BigDecimal.TEN.pow(-9, mathContext));


        genTopicScript();



    }

    public static void genTopicScript(){
//        String str = "4,5,1005543133638856704,1005543133643051008,1005543133643051009,1005543133643051010,1020049153929527296,1020050747702132736,1020050980691525632,1020051132277866496,1020051284703068160,1020051403229904896,1020051519672172544,1020051654154141696,1020051954311118848,1020052074083663872,1020052183609524224,1020052292640456704,1021987471801073664,1021989810779865088,1023052973419937792,1029468892262580224,1030244857070764032,1032088362294460416,1032096217949028352,1032099517826088960,1032105403000832000,1032120311851532288,1032120510053367808,1032120656421994496,1032360325067849728,1032360709761024000,1034595991675748352,1034652993634451456";
//        String[] array = str.split(",");
//        String topicPrefix = "MATCH_EACH_COMPLETE_TOPIC_";
//        String script = "sh mqadmin updateTopic -n 172.31.22.101:9876 -t %s -c DefaultCluster -r 4 -w 4;";
//        for (String instrumentId : array) {
//            System.out.println(String.format(script, topicPrefix + instrumentId));
//        }
//        System.out.println(array[-1]);
//


    }





    public static  void genevent(){
        String str = "4,5,1005543133638856704,1005543133643051008,1005543133643051009,1005543133643051010,1020049153929527296,1020050747702132736,1020051284703068160,1020051403229904896,1020051519672172544,1020051954311118848,1020052074083663872,1020052183609524224";
        String[] array = str.split(",");

//        String sql = "CREATE EVENT transfer_match_history_event_%s\n" +
//                "ON SCHEDULE EVERY 1 hour\n" +
//                "STARTS '2020-06-10 09:10:00'\n" +
//                "DO CALL transfer_match_history (%s, 2000);";
        String sql =  "CALL transfer_match_history (%s, 20000);";


        for (String instrument : array) {
            System.out.println(String.format(sql, instrument, instrument));
        }



    }

    public static void test(String path){
        try {
            String sql = "update icc_trade_cfd.cfd_capital_detail set balance=balance+%s ,trade_lock=trade_lock+%s, transfer_lock=transfer_lock+%s where user_id=%s and currency='BTC';";

            File file = new File(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                String[] array = lineTxt.split("\t");
                String userId = String.valueOf(array[0]);
                String amount = new BigDecimal(array[1]).stripTrailingZeros().toPlainString();
                String format = String.format(sql, amount, amount, amount, userId);
                System.out.println(format);

            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    zh-cn 中文简体
//    zh-hk 中文繁体
//    en-us 英文
//    ja-jp 日文
//    km-kh 高棉语/柬埔寨
//    ko-kr 韩语
//    ms-my 马来语
//    th-th 泰语
//    vi-vn 越南语
//    id-id 印尼语
//    ru-ru 俄语

    public static void genMetaLanguageSql(String path) {
        List<File> files = Lists.newArrayList();
        String SQL = "INSERT INTO `i18n_meta_language` (`code`, `enabled`, `language`, `message` ) VALUES('%s','1','%s','%s');";
        try {
            List<String> sqlList = Lists.newArrayList();
            File file = new File(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String lineTxt = null;
            String language  = "en-us";
            while ((lineTxt = br.readLine()) != null) {
                String[] array = lineTxt.split("\t");
//                System.out.println(array[0] + "  " + array[3]);
                String code = array[0];
                String msg = array[2];
                String format = String.format(SQL, code, language, msg);
                System.out.println(format);
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        genMetaLanguageSql("d:\\decenter-wallet.txt");
    }
}
