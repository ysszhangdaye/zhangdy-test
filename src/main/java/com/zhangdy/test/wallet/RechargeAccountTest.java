package com.zhangdy.test.wallet;

import com.google.common.collect.Maps;
import com.zhangdy.test.annotation.Column;
import com.zhangdy.test.annotation.TableName;
import com.zhangdy.util.*;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class RechargeAccountTest {


    public static void main(String[] args) {


        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(1019324548360286208L);
        stringBuffer.append(",").append("ETH");
        stringBuffer.append(",").append("0x414f454daf75f1851e5852eb88f9c97631fef6f0");
//        if (StringUtils.isNotBlank(newAddress.getLinkType())) {
//            stringBuffer.append(",").append(newAddress.getLinkType());
//        }
        PaymentAccount paymentAccount = new PaymentAccount();
        paymentAccount.setId(IDS.uniqueID());
        paymentAccount.setUserId(1019324492762951680L);
        paymentAccount.setAccountId(1019324548360286208L);
        paymentAccount.setAccountDetailId(1019324574411108352L);
        paymentAccount.setAddress("0x414f454daf75f1851e5852eb88f9c97631fef6f0");
        paymentAccount.setCurrency("ETH");
//        paymentAccount.setLinkType(param.getLinkType());
        paymentAccount.setBizType(0);
        paymentAccount.setChannel(1004108226846978048L);
        paymentAccount.setEnabled(1);
        paymentAccount.setCreatedOn(new Date());
        paymentAccount.setType(0);
        paymentAccount.setUnKey(stringBuffer.toString());
        paymentAccount.setSign(paymentAccount.signData("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJOafXnRcdBWGuNPN3w6/5u+A+NiBo9yAnd8Ojf3y3zR6Jaxwu6bS0eGq56EQHZaEByljuTdRGHWnMLnBHPwnecZLu9g9L238YXhL9xSD2nrV0A35VLTdGK2wVGC4290ddE+k18HjJeXTHmPPGQtGheRZr/Zc9sOUdBl6KnfF8JBAgMBAAECgYAGnDmYVgfp06FaoraSarX9zJGyAjbW/70aisa0sPU8/s2Syh03wF6rsl1HORcF9dyY29U9bKdeVlUo6V2qr5DysiXDRveR7WTWLuzxZpysOpHpYFV0SsrGFhXvrn2p+rIYopphdfxlzI8iqAnGSPZ1rYbBusnxkh56NxT5fjspXQJBAML5uZK9aDeVPAJRYDmZITDNWodh81vqm3KeHH8qvTq9AcMXKNs1roxPkoF2oJ2jKHjCIMiwg9SkdT5iOwGFj98CQQDBzR2rFBrj/W03eg/6RsLoOzoDTYBqcu+//hIAM9B15jmSbGssQ71vYzJP7/Okrp0bkhuSlGIfazY10Cx4gnHfAkBUQluCILyDGMkgL06oVZbUYkp3K0tiM5cOB2pFQpEYa0nxp3aOlTpjhJfJvxxWQ4Zf+XT1heLS6GeKmCTr2W59AkB3GSmZYDKiV7TNugqloOTYuMUPEIrRYC7sSyQe0bjTK5fH3y8BteNt14Us0OZnFxPfxKuDKgMfEdDvwy4BJRD7AkEAugy0u3w1+ouSj7+sgpWUJ4i7Qfx6SByEtuNIDnH76MktFIYukaq1I3SZVLNBPhD4RbjtOKV/uQjybLeHmabvCA=="));
//        paymentAccount.setLabelAddress(newAddress.getLabelAddress());
        System.out.println(GenerateSqlUtil.genSql(paymentAccount));

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @TableName("icc_account.payment_account")
    public static class PaymentAccount {
        private Long id;

        private String currency;
        @Column(format = "%s", value = "user_id")
        private Long userId;
        @Column(format = "%s", value = "account_id")
        private Long accountId;

        @Column(format = "%s", value = "account_detail_id")
        private Long accountDetailId;

        private String address;

        @Column(format = "%s", value = "channel")
        private Long channel;

        private String remark;

        @Column(format = "%s", value = "enabled")
        private Integer enabled;

        private String currencyName;
        @Column(format = "%s", value = "orders")

        private Integer orders;

        private String sign;

        private Date createdOn;

        private Date updatedOn;

        private Long createdBy;

        private Long updatedBy;

        private String linkType;

        private String labelAddress;

        private String unKey;

        private Integer bizType;

        private Integer type;

        private Boolean archived;

        private Integer version;

        private Object attrs;

        public String getSignData(){
            StringBuffer str = new StringBuffer();
            str.append(id).append(",")
                    .append(currency).append(",")
                    .append(channel).append(",")
                    .append(accountId).append(",")
                    .append(accountDetailId).append(",")
                    .append(address).append(",")
                    .append(type).append(",")
                    .append(new DateFormatImpl("yyyyMMddHHmmss").format(this.createdOn));
            System.out.println(new DateFormatImpl("yyyyMMddHHmmss").format(this.createdOn));
            return str.toString().toLowerCase();
        }

        public String signData(String privateKey){
            return RsaUtils.signData(this.getSignData(), privateKey);
        }

    }

}
