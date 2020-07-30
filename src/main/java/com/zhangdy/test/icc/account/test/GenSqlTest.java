package com.zhangdy.test.icc.account.test;

import com.zhangdy.test.icc.account.entity.CapitalAccount;
import com.zhangdy.util.GenerateSqlUtil;

import java.util.Date;

public class GenSqlTest {

    public static void main(String[] args) {

        String[] userIdArray = {"1012102489710567424","1012102494237655040","1012102594203262976","1012102598562578432","1012102603637686272","1012102607989940224","1012102617011888128","1012102621252329472","1012102625404690432"};
        Date date = new Date();
        for (String userId : userIdArray) {
            CapitalAccount capitalAccount = CapitalAccount.builder()
                    .userId(Long.valueOf(userId))
                    .category(10)
                    .currencyPair("BTC")
                    .type(1004)
                    .enabled(1)
                    .rank(0)
                    .exchange("ICC")
                    .createdOn(date)
                    .version(0)
                    .build();
            String sql = GenerateSqlUtil.generateInsertSql(capitalAccount);
            System.out.println(sql);
        }



    }

}
