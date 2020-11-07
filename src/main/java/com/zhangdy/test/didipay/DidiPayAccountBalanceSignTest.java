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

/**
 * @Author kevin
 * @Description
 * @Date Created on 2020/8/14 17:16
 */
public class DidiPayAccountBalanceSignTest {

    private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKEECxqR1M+tnykV59sYc6oGe3VM2wWgN20oxvpbMxhZm+K6oKX5E6JtuIiVrlQkMLR0QDLm3H1RdxmkAMRsQRywozi8QDSf30PazbMSl4HkFRurwlvDpQqz2tagaAqoqbBcun9pKM/eIg0tw5YnV2MvNyJwh4tbXDJWy2crOM67AgMBAAECgYBCAtpahjbs/Y0m1ug7Foc0yXuL13pjkA+pHPKWaRVFglc6WLDL2pWGHd1tgY/6xuuAz7gSJjt21RMy293EqWz3o6SXgzzTMk6z2BY60hOujpHd0FCRUxXRRY/zt8R5u0F9CwqpSa8k7qp+torTjlFj5IGU4/rltPI9ufa/OnNdoQJBAM5kXUiiumGnus/zNfnJWvSjYy4B2GbRICjWQQ1ECHG/OnfSlriBwrx+k7ZqhGmHkxO1rRW+qEGUo9TZD52xVscCQQDHt5raXNDhZT/g30Name6pAj0Z4A3xKzpbIyNa4pf/i9MwGqvcDuudDS9KNVaPr9qR+46ILa2N8jeIktwFBkRtAkA9tAGzenrwQR33l4WWhN09cC9xjLmum2za6JsQJTvFDd+j/QB0bs7SXaE5kwS9K/wLk3AIpTRWTuLf+njRszf3AkAtMWD7NLGXr3+q9Rqar+aPnBATgN3myu9jnCnt8QSQMSXS5tYo779lRIyE8FjsHtEI47G3Iw745eynHQRopqhpAkEAlFeV5fcDc1wsxFQt8jgFf3VK/khikJaDRKt1Pa0OtcNhb0goxfEWu3FqL6v/OrbDtiIDFP0r9CCr+WUzsdHIIA==";
    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChBAsakdTPrZ8pFefbGHOqBnt1TNsFoDdtKMb6WzMYWZviuqCl+ROibbiIla5UJDC0dEAy5tx9UXcZpADEbEEcsKM4vEA0n99D2s2zEpeB5BUbq8Jbw6UKs9rWoGgKqKmwXLp/aSjP3iINLcOWJ1djLzcicIeLW1wyVstnKzjOuwIDAQAB";

    private static final String URL         = "jdbc:mysql://10.10.23.119:5001/didipay_account?characterEncoding=utf-8&useSSL=false";
    private static final String USER_NAME   = "root";
    private static final String PASSWORD    = "5uHyFAgDvmfL9esV";
    private static final String TEST_URL         = "jdbc:mysql://10.10.23.99:3306/didipay_account?characterEncoding=utf-8&useSSL=false";
    private static final String TEST_USER_NAME   = "dssj";
    private static final String TEST_PASSWORD    = "dssj@DSSJ123";


    public static void main(String[] args) {

//        sign();
//        verifySign();
        updateSign();

    }

    public static void verifySign(){
//        String data  = "1059935586032295936,1059935586007130113,1059935586007130112,130,ionc,99.9977,0";
        //String sign = "MhxmKSwyQri/2ylvillIkDE6Jc8SyXf2Bup+fTFHFu/pj2iUd6nWw7atIk3czGB9H/Pd8w0hEowFIx7f2kdEL94cHPAOQRP2ThE95EGjeZRCWyXW+iNI905f5daELsu3Hwcxeho18HWI7sti/xfG3yiY/Ys80zruUl9GMWvdDVs=";
//        String sign = "UMD3BgDQHpEm6CStwMwEsKrc35nVpM3iBMZyLl0qO0F4LUxBqeX2T07VS0XQG5nSdo2wmbMvupBP3oa/tShMn4QQo6ABK0jz4jQQz0dot0Jydo5RNGdhuaFabKz7G1PLWqGeCbgY0F4F+DQdsqIhNY6QPnirHZ0em3FPlV62yEI=";
//        String sign = "IhApbyyimjb+a3AJjrj/wHrcVMpfhwN672PqYKtzdC0W2hlYDb5rzB9mD7fs72En2Akta9I+qvuhZkEy/+hhXQWakDXDa6x5n8JTxLQmsb8YmZkgAN1uis9w4pSFhVXXdeQq1AtvHFvz+gNkQWmNrpW/v+tvAeQBYgi6eqR5NG4=";



//        String sign = "OXWGRTg+xp6LIUpF49cHZ+mj7CYgvVFGeuh2UHSjiBUQjgUj0dIg0rlQuA/CEJYhm4DN3U9eYZhN8H+yjf6bajU4RYuN/KrxLXKXUHWfExjb/BPOhxp74kpDCvsb5YDfM+sJVE+luoHA8xTFKdr8pcdQlqxN6Q3IHDj4BLiBnTY=";
//        String sign = "S+ihOzKV83MDY2NocUM0PNWmLkGPwfoDVZPlDkn3JpTPt82uQfaCS0jWNAfnHf5r0pOHB/caJYW0JJCZR+haLUhC80KlFzGGXksuqVvqDVf7ggIMFN9wB774KJsoxqt0fbDQqinSD+RVCLPhoCJm9hH76qr6nID5tBS5KhLrb9s=";



        //BglDmCD50BlF6y4EZEFkcF89bl5NA3AkEBPAnAcffSpXPEZOvyGeSAfL1Bdi6cmbG/EOgHGuLy8GNVIv2tCzYK1cD3Tw+2sPq+HB2igMQybyF1HhgA39BGC/gn+GdZHx3ixQi5CVcGx+sOwiaYxpmvxA1ZY8fHzzeiD9564Jxbw=
//        String data  = "1059935586032295936,1059935586007130113,1059935586007130112,130,ionc,99.9945,0";
//        String sign = "guyhzdfrgHlPpquY5EKJmW9WiqanAVa2DKQ6sCw6lr8lFb6S/rzwEZwluC1V/L6hMmYhYX6GYclln7zWP3TzkAOUhk4CvCauSyVGy2YZt98PUJbbTIUx8LoU3VjxnX3Xtw5hKWollzpQ5Alw0j9eypp9RUJrSaGzTplAyyEy6zU=";

        String data  = "1059935586032295936,1059935586007130113,1059935586007130112,130,ionc,99.99189,0";
        String sign = "Z8yb0MCtlw7j81xOxgSUNpkswYNyyWIhwt0SKZVXtg/fgki+eFjlJaGAehxlOkszM/SiLsy1mb51omFzSCA+KQLds6MFy9t4lGbAj0CzRnhYjvq6VvZLYNXtCFPy5wZTAlSegobZZE0aNAhh3QdALGFi2hE+p7RskXMI2eLmWGM=";




        boolean b = RsaUtils.verifyData(data, sign, PUBLIC_KEY);
        System.out.println(b);
    }

    public static void sign(){
        //
//        String data  = "1062459736505905152,3,1062459736497516544,130,ionc,-0.00857,0";
//        bd4dAy5ZDEE253jeIPl6ZehwIdcEFVOYnpZFWDoaI6wHriJ9jq9LY6Z5DnUQSYeH5TuKhyHUPTD1m0IuiB/MYxoeqk7bPYu/6o4mwl7y1Afgsi4aYpbysanEaIkS2fN6tyRab6SH7J95vBa/k+v4WBVd0V3etlY3B6k5NguI9wo=


        String data  = "1062451212317028352,1061436547545624576,1062445672780853249,130,ionc,99.99782,0";
//        ZyOeyK4lKzloMfb/dcY/PiQF5QhnB93U+4gsrcjikZp4mKN2dl3rH7Jtii2CbIGdD8erUp9HCUN7/oenICF9me2a5Kmo+nNrJS+nVIEyAmnexi9fnwx0CRLpEwnxdJQ6fEdK9+DB8EgBM4MRCLGZIBuNCQ9/gmjuT4Qym0iHfuM=
//        ZyOeyK4lKzloMfb/dcY/PiQF5QhnB93U+4gsrcjikZp4mKN2dl3rH7Jtii2CbIGdD8erUp9HCUN7/oenICF9me2a5Kmo+nNrJS+nVIEyAmnexi9fnwx0CRLpEwnxdJQ6fEdK9+DB8EgBM4MRCLGZIBuNCQ9/gmjuT4Qym0iHfuM=


        String s = RsaUtils.signData(data, PRIVATE_KEY);
        System.out.println(s);
    }

    public static void updateSign() {
//        Connection  connection  = DbBase.getConnection(URL, USER_NAME, PASSWORD);
        Connection  connection  = DbBase.getConnection(TEST_URL, TEST_USER_NAME, TEST_PASSWORD);
        Long        userId      = 1091904311150161920L;
        String      querySql    = "SELECT * FROM account_balance WHERE user_id=? and id<>1091908936521789441";
        String      signSql     = "update account_balance set sign=? , available_balance=? where id=?";

        PreparedStatement preparedStatement = null;
        try {
            List<Object> queryParam     = Lists.newArrayList(userId);
            List<AccountBalance> list   = DbBase.findList(querySql, connection, queryParam, AccountBalance.class);
            BigDecimal balance          = new BigDecimal("0");

            preparedStatement   = connection.prepareStatement(signSql);
            boolean execute     = false;
            for (AccountBalance accountBalance : list) {
                accountBalance.setAvailableBalance(balance);
                execute = true;
                String sign = accountBalance.signData();
                preparedStatement.setString(1, sign);
                preparedStatement.setBigDecimal(2, balance);
                preparedStatement.setLong(3, accountBalance.id);
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
                    .append(frozenBalance.stripTrailingZeros().toPlainString()).append(",")
                    .append(DateUtil.yyyymmddhhmmss.format(createdOn))
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
