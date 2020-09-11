package com.zhangdy.test.didipay;

import com.google.common.collect.Lists;
import com.infra.tools.DateUtil;
import com.infra.tools.RsaUtils;
import com.zhangdy.util.DbBase;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

public class DidiPayAccountBalanceSign {

    private static final String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKiUU+Zo/TwkuR4XMpZWM2f9y7/TNjUyENgv+sA0FuNqbDwVtMBSmCANHqdiM6VUD3PWDTDO9aAO+H6KekglQebPJIAkbiJDRdfRkhgayz5mA3Nkec7sDXvCybAuDZh2cqgpYM1zs2H6JppQbgtAO24BTaSruOKPeDNAeKVWK5bfAgMBAAECgYEAh/KOMorKZV0sGDMW7Zv4es12KWz4EzAb22vd6HBZYO5q+I18pXg2C+ropI2SHqrKh1zAaHMOLBTGmq2r7VB2co+zxJ2sPV1zR9r2weqrGeAD5BU/mn789RRk5W/shi9dYt8zTDBq/b2vRdylmOIL1zCY6Y94gstJj0pL4BE/dykCQQDWMhXN3JHTth8XVwjgLjVbIL4wb0OLMMvhm1I4DVRJUUdb+RmtAf8RAwdbMqn4mSoXOGr2fnSEVji5/r+0CUKNAkEAyXsbJkRR4Sj6Z0hLGKnApXyJdUxoK7Q0/tILfvquTfuv9P4hLgKrZR25lTTEAM8eJ6C4r7rs0QoMI1jMKfxaGwJBAKt0tBdxdBmwMvsP5D7vPi6P6y4CImMM0GbrWckcgleeBC0PHQpsCqynbAAIch5ZHGQx+Q0O9F3qVsYrsXB/6RECQQC4NNNXozLxFtQJRbFS9TBqnHfYRGkhO/sP1t4n0+004x22cfnH5Szqn8iiv8FLtBEvzePM1qvZWsRKNM9Eu7FtAkBrqTjfwMjvZAonAQhU4OmKfd0hBeLshi953r7fsXUUTDLGx5liD80sc8hyaAxjs15iJi+MdHdRZ1Qkun6Ld1C1";
    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQColFPmaP08JLkeFzKWVjNn/cu/0zY1MhDYL/rANBbjamw8FbTAUpggDR6nYjOlVA9z1g0wzvWgDvh+inpIJUHmzySAJG4iQ0XX0ZIYGss+ZgNzZHnO7A17wsmwLg2YdnKoKWDNc7Nh+iaaUG4LQDtuAU2kq7jij3gzQHilViuW3wIDAQAB";

    private static final String URL         = "jdbc:mysql://10.10.23.99:3306/didipay_account?characterEncoding=utf-8&useSSL=false";
    private static final String USER_NAME   = "dssj";
    private static final String PASSWORD    = "dssj@DSSJ123";


    public static void main(String[] args) {
//        sign();
//        verifySign();
        Long        userId      = 1066515908731383808L; // zhangdaye
//        updateSign(userId);

        initBalance(userId);
//        initBalance(userId, "XRP", new BigDecimal("200"));
    }

    public static void verifySign(){
        String data  = "1061344047803969536,1061104215746326528,1061344011399639040,13,eth,110021009.90076,0,20200814090045";
        String sign = "JxFLaD3RJ8j1MmhOS1Adxd7F6JhrNRF03uP77v3tx7xXy9dCcSyNL6OYTsnRtdgIBqLpO1Glnhv8qzp1cfunnndG8drGuES5ehvUtaGAvD9m/mIOwtFA9SRKAFI2BhH8mDCzWO6TdJ6pQjCi9vQucM12RhlVoYQ/58JJq3Ws/bA=";
        boolean b = RsaUtils.verifyData(data, sign, PUBLIC_KEY);
        System.out.println(b);
    }

    public static void sign(){
        String data  = "1061344262208401408,1061104215746326528,1061105054317326336,13,eth,89979932.29038,0,20200814090136";
        String s = RsaUtils.signData(data, PRIVATE_KEY);
        System.out.println(s);
    }

    public static void initBalance(Long userId) {
        Connection  connection  = DbBase.getConnection(URL, USER_NAME, PASSWORD);
        String      querySql    = "SELECT * FROM account_balance WHERE user_id=?";
        String      signSql     = "update account_balance set sign=?,available_balance=? where id=?";
        PreparedStatement preparedStatement = null;
        try {
            List<Object> queryParam     = Lists.newArrayList(userId);
            List<AccountBalance> list   = DbBase.findList(querySql, connection, queryParam, AccountBalance.class);
            BigDecimal balance          = new BigDecimal("100000000");
            preparedStatement   = connection.prepareStatement(signSql);
            for (AccountBalance accountBalance : list) {
                String sign = accountBalance.signData(balance);
                preparedStatement.setString(1, sign);
                preparedStatement.setBigDecimal(2, balance);
                preparedStatement.setLong(3, accountBalance.id);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbBase.closeStatement(preparedStatement);
            DbBase.closeConn(connection);
        }
    }


    public static void initBalance(Long userId, String currency, BigDecimal amount) {
        Connection  connection  = DbBase.getConnection(URL, USER_NAME, PASSWORD);
        String      querySql    = "SELECT * FROM account_balance WHERE user_id=? and currency_code=?";
        String      signSql     = "update account_balance set sign=?,available_balance=? where id=?";
        PreparedStatement preparedStatement = null;
        try {
            List<Object> queryParam     = Lists.newArrayList(userId, currency);
            List<AccountBalance> list   = DbBase.findList(querySql, connection, queryParam, AccountBalance.class);
            preparedStatement   = connection.prepareStatement(signSql);
            for (AccountBalance accountBalance : list) {
                String sign = accountBalance.signData(amount);
                preparedStatement.setString(1, sign);
                preparedStatement.setBigDecimal(2, amount);
                preparedStatement.setLong(3, accountBalance.id);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbBase.closeStatement(preparedStatement);
            DbBase.closeConn(connection);
        }
    }


    public static void updateSign(Long userId) {
        Connection  connection  = DbBase.getConnection(URL, USER_NAME, PASSWORD);
//        Long        userId      = 1061104215746326528L; //zhangdaye
//        Long        userId      = 1062465418828107776L; // zhouzhiwei
        String      querySql    = "SELECT * FROM account_balance WHERE user_id=?";
        String      signSql     = "update account_balance set sign=? where id=?";
//        String      signSql     = "update account_balance set sign=?,available_balance=? where id=?";
        PreparedStatement preparedStatement = null;
        try {
            List<Object> queryParam     = Lists.newArrayList(userId);
            List<AccountBalance> list   = DbBase.findList(querySql, connection, queryParam, AccountBalance.class);
            preparedStatement   = connection.prepareStatement(signSql);
            boolean execute     = false;
            for (AccountBalance accountBalance : list) {
                execute = true;
                String sign = accountBalance.signData();
                preparedStatement.setString(1, sign);
                preparedStatement.setLong(2, accountBalance.id);
                preparedStatement.addBatch();
            }
            if (execute) {
                preparedStatement.executeBatch();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbBase.closeStatement(preparedStatement);
            DbBase.closeConn(connection);
        }

    }


    @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
    public static class AccountBalance {

        /**
         * 账户余额ID
         */
        private Long id;

        /**
         * 账户分类：0-商户账户，1-平台账户
         */
        private Integer category;

        /**
         * 用户ID
         */
        private Long userId;

        /**
         * 账户ID
         */
        private Long accountId;

        /**
         * 账户类型ID
         */
        private Long typeId;

        /**
         * 币种类型ID
         */
        private Long currencyId;

        /**
         * 币种代码
         */
        private String currencyCode;

        /**
         * 可用余额
         */
        private BigDecimal availableBalance;

        /**
         * 冻结余额
         */
        private BigDecimal frozenBalance;

        /**
         * 状态：1-正常，0-冻结
         */
        private Integer status;

        /**
         * 签名
         */
        private String sign;

        private Object attrs;

        private Integer version;

        private Boolean archived;

        private Date createdOn;

        private Long createdBy;

        private Date updatedOn;

        private Long updatedBy;

        private static final long serialVersionUID = 1L;

        public Date getCreatedOn() {
            String plainTxt = DateUtil.yyyymmddhhmmss.format(createdOn);
            return DateUtil.yyyymmddhhmmss.parse(plainTxt);
        }

        public void setCreatedOn(Date createdOn) {
            String plainTxt = DateUtil.yyyymmddhhmmss.format(createdOn);
            this.createdOn = DateUtil.yyyymmddhhmmss.parse(plainTxt);
        }

        public String getSignData(BigDecimal balance) {
            StringBuffer sb = new StringBuffer(300);
            sb.append(id).append(",")
                    .append(userId).append(",")
                    .append(accountId).append(",")
                    .append(currencyId).append(",")
                    .append(currencyCode).append(",")
                    .append(balance.stripTrailingZeros().toPlainString()).append(",")
                    .append(frozenBalance.stripTrailingZeros().toPlainString())
            ;
            String str = sb.toString().toLowerCase();
            return str;
        }

        public String getSignData() {
            StringBuffer sb = new StringBuffer(300);
            sb.append(id).append(",")
                    .append(userId).append(",")
                    .append(accountId).append(",")
                    .append(currencyId).append(",")
                    .append(currencyCode).append(",")
                    .append(availableBalance.stripTrailingZeros().toPlainString()).append(",")
                    .append(frozenBalance.stripTrailingZeros().toPlainString())
//                    .append(",")
//                    .append(DateUtil.yyyymmddhhmmss.format(createdOn))
            ;
            ;
            String str = sb.toString().toLowerCase();
            return str;
        }

        public String signData(BigDecimal balance) {
            String sign = RsaUtils.signData(getSignData(balance), PRIVATE_KEY);
            return sign;
        }

        public String signData() {
            String sign = RsaUtils.signData(getSignData(), PRIVATE_KEY);
            return sign;
        }

    }

}
