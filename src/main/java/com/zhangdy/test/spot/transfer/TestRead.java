package com.zhangdy.test.spot.transfer;

import com.zhangdy.util.GenerateSqlUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Date;

public class TestRead {


    public static void main(String[] args) {

        genCapitalDetail(972291200135979008L, 973097701844987904L, "GOD", 1001);

//        genAccountTransfer();
//        genAccountTransferOrder();
    }

    public static void genAccountTransferOrder(){
        try {
            File file = new File("E:\\icc\\20200617转账数据修复\\account_transfer_other_water");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                String[] array = lineTxt.split("\t");
                long id = Long.parseLong(array[15]);
                long userId = Long.parseLong(array[1]);
                Long inAccountId = null;
                long outAccountId = Long.parseLong(array[2]);
                Long inCapitalDetailId = null;
                long outCapitalDetailId = Long.parseLong(array[3]);
                int inType = Integer.parseInt(array[4]);
                int outType = Integer.parseInt(array[5]);
                String currency = array[12];
                BigDecimal amount = new BigDecimal(array[14]);

                AccountTransferOrder accountTransferOrder = AccountTransferOrder.create(id, userId, inAccountId, outAccountId, inCapitalDetailId, outCapitalDetailId,
                        inType, outType, currency, amount);
                String sql = GenerateSqlUtil.generateInsertSql(accountTransferOrder);
                System.out.println(sql);

//                System.out.println(id);

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    public static void genCapitalDetail(long userId, long accountId, String currency, int type){
        CapitalDetail capitalDetail = CapitalDetail.builder()
                .userId(userId)
                .accountId(accountId)
                .balance(BigDecimal.ZERO)
                .freeze(BigDecimal.ZERO)
                .tradeLock(BigDecimal.ZERO)
                .transferLock(BigDecimal.ZERO)
                .currency(currency)
                .enabled(1)
                .type(type)
                .category(10)
                .currencyPair("BTC")
                .createdOn(new Date())
                .version(0)
                .build();
        String sql = GenerateSqlUtil.generateInsertSql(capitalDetail);
        System.out.println("#表名表名表名");
        System.out.println(sql);
    }

    public static void genAccountTransfer(){
        try {
            File file = new File("E:\\icc\\20200617转账数据修复\\transfer_in_null");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String lineTxt = null;
            Date date = new Date();
            String description = "20200617-转账手动转入";
            String sql = "update icc_account.account_transfer_order set status=1,updated_on=now() where id=%s;";
            while ((lineTxt = br.readLine()) != null) {
                String[] array = lineTxt.split("\t");
                long userId = Long.parseLong(array[1]);
                long accountId = 3L;
                long capitalDetailId = 1L;
                int inAccountType = Integer.parseInt(array[7]);
                int outAccountType = Integer.parseInt(array[8]);
                long inAccountId = 1L;
                long outAccountId = Long.parseLong(array[4]);
                long inCapitalDetailId = 1L;
                long outCapitalDetailId = Long.parseLong(array[6]);
                String currency = array[16];
                String feeCurrency = array[16];
                BigDecimal outBeforeBalance = new BigDecimal("0");
                BigDecimal outAfterBalance= new BigDecimal("0");
                BigDecimal inBeforeBalance = new BigDecimal("0");
                BigDecimal inAfterBalance= new BigDecimal("0");
                BigDecimal amount = new BigDecimal(array[17]);
                BigDecimal fee = new BigDecimal("0");
                String sourceId = array[0];
                int cause = 15002;
                if (inAccountType == 1004) {
                    cause = 13002;
                } else if (inAccountType == 1001) {
                    cause = 10005;
                } else if (inAccountType == 1003) {
                    cause = 12002;
                }
                CapitalWater capitalWater = TestTransfer.convertCapitalWater(userId, accountId, capitalDetailId, inAccountType,
                        outAccountType, inAccountId, outAccountId, inCapitalDetailId,
                        outCapitalDetailId, currency, feeCurrency, outBeforeBalance,
                        outAfterBalance, inBeforeBalance, inAfterBalance,
                        amount, fee, sourceId, cause, description, date);
                String insertWaterSql = GenerateSqlUtil.generateInsertSql(capitalWater);
                System.out.println("# " +userId + " 币种 " + currency + " insertWaterSql");
                System.out.println(insertWaterSql);
                System.out.println(String.format(sql, sourceId));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
