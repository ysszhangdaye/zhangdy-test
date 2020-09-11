package com.zhangdy.test.spot.transfer;

import com.zhangdy.util.GenerateSqlUtil;

import java.math.BigDecimal;
import java.util.Date;

public class TestTransfer {


    public static void main(String[] args) {

        long userId = 1006417678765957120L;

        long accountId = 1006421331775164416L;
        long inAccountId = 1006421331775164416L;

        long capitalDetailId = 1040757448906608640L;
        long inCapitalDetailId = 1040757448906608640L;

        long outAccountId = 1006417679475904512L;
        long outCapitalDetailId = 1006417679475904512L;


        int inAccountType = 1001;
        int outAccountType = 1002;

        String currency = "YQS";
        String feeCurrency = "YQS";
        BigDecimal outBeforeBalance = new BigDecimal("0");
        BigDecimal outAfterBalance= new BigDecimal("0");
        BigDecimal inBeforeBalance = new BigDecimal("0");
        BigDecimal inAfterBalance= new BigDecimal("0");
        BigDecimal amount = new BigDecimal("0.77540000000000000000");
        BigDecimal fee = new BigDecimal("0");

        String sourceId = "1042565714683072513";
        int cause = 10005;
        String description = "20200807-转账手动转入";
        Date date = new Date();

//        long transferOrderId = 1042404226267250689L;

//        AccountTransferOrder accountTransferOrder = AccountTransferOrder.create(transferOrderId, userId, inAccountId, outAccountId, inCapitalDetailId, outCapitalDetailId,
//                1001, 1004, currency, amount);
//        System.out.println("# 转账订单");
//        String sql = GenerateSqlUtil.generateInsertSql(accountTransferOrder);
//        System.out.println(sql);


        CapitalWater capitalWater = convertCapitalWater(userId, accountId, capitalDetailId, inAccountType,
                outAccountType, inAccountId, outAccountId, inCapitalDetailId,
                outCapitalDetailId, currency, feeCurrency, outBeforeBalance,
                outAfterBalance, inBeforeBalance, inAfterBalance,
                amount, fee, sourceId, cause, description, date);
        String insertWaterSql = GenerateSqlUtil.generateInsertSql(capitalWater);
        System.out.println("#insertWaterSql");
        System.out.println(insertWaterSql);
    }



    public static CapitalWater convertCapitalWater(long userId, long accountId, long capitalDetailId, int inAccountType,
                                                   int outAccountType, long inAccountId, long outAccountId, long inCapitalDetailId,
                                                   long outCapitalDetailId, String currency, String feeCurrency, BigDecimal outBeforeBalance,
                                                   BigDecimal outAfterBalance, BigDecimal inBeforeBalance, BigDecimal inAfterBalance,
                                                   BigDecimal amount, BigDecimal fee, String sourceId, int cause, String description, Date date){
        return CapitalWater.builder()
                .userId(userId)
                .accountId(accountId)
                .capitalDetailId(capitalDetailId)
                .inAccountType(inAccountType)
                .outAccountType(outAccountType)
                .inAccountCategory(10)
                .outAccountCategory(10)
                .inAccountId(inAccountId)
                .outAccountId(outAccountId)
                .inCapitalDetailId(inCapitalDetailId)
                .outCapitalDetailId(outCapitalDetailId)
                .currency(currency)
                .feeCurrency(feeCurrency)
                .outBeforeBalance(outBeforeBalance)
                .outAfterBalance(outAfterBalance)
                .inBeforeBalance(inBeforeBalance)
                .inAfterBalance(inAfterBalance)
                .amount(amount)
                .fee(fee)
                .sourceId(sourceId)
                .cause(cause)
                .description(description)
                .time(date)
                .checking(0)
                .createdOn(date)
                .version(0)
                .build();
    }

}




