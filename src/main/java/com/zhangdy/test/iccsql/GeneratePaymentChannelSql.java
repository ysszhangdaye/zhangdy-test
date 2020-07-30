package com.zhangdy.test.iccsql;

import com.zhangdy.test.annotation.AutoGenerateValue;
import com.zhangdy.test.annotation.Column;
import com.zhangdy.test.annotation.TableName;
import com.zhangdy.test.enums.GenerateTypeEnum;
import com.zhangdy.util.GenerateSqlUtil;
import lombok.*;

public class GeneratePaymentChannelSql {

    public static void main(String[] args) {

        PaymentChannel usdtPaymentChannel = PaymentChannel.builder()
                .currency("USDT")
                .remark("usdt mk 购买渠道")
                .enabled(1)
                .bizType(2)
                .name("usdt mk 购买渠道")
                .rank(0)
                .build();
        System.out.println(GenerateSqlUtil.genSql(usdtPaymentChannel));
        PaymentChannel btcPaymentChannel = PaymentChannel.builder()
                .currency("BTC")
                .remark("BTC mk 购买渠道")
                .enabled(1)
                .bizType(2)
                .name("BTC mk 购买渠道")
                .rank(0)
                .build();
        System.out.println(GenerateSqlUtil.genSql(btcPaymentChannel));
        PaymentChannel ethPaymentChannel = PaymentChannel.builder()
                .currency("ETH")
                .remark("ETH mk 购买渠道")
                .enabled(1)
                .bizType(2)
                .name("ETH mk 购买渠道")
                .rank(0)
                .build();
        System.out.println(GenerateSqlUtil.genSql(ethPaymentChannel));


    }


    @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
    @TableName("payment_channel")
    public static class PaymentChannel{
//        CODE, currency,remark,enabled,biz_type,NAME, rank,link_type)
        @Column(format = "%s")
        @AutoGenerateValue(GenerateTypeEnum.IDS)
        private Long id;
        private String currency;
        private String remark;
        @Column(format = "%s")
        private Integer enabled;
        @Column(value = "biz_type", format = "%s")
        private Integer bizType;
        private String name;
        @Column(format = "%s")
        private Integer rank;
    }
}
