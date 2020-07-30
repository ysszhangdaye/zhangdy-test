package com.zhangdy.test.wallet;

import com.zhangdy.test.annotation.Column;
import com.zhangdy.test.annotation.TableName;
import com.zhangdy.util.GenerateSqlUtil;
import com.zhangdy.util.IDS;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

public class ManualRechargeTest {


    public static void main(String[] args) {
        String          currency         = "USDT";
        String          linkType         = "OMNI";
        String          address          = "1EvjZzNHf1qvKoSHaMcub4b25S2KNkFAdQ";
        BigDecimal      amount           = new BigDecimal("129.72");
        String          txid             = "c55ac13de3c0fe09c4485aeedf25e7a6cf054b4302d81f7f35d45d88f78602f1";
        Integer         confirmations    = 147;
        Long            height           = 629148L;
        String          remark           = "手动添加充值记录-20200507";
        Long            userId           = 1006969729497473024L;
        Long            accountId        = 1006997049957519360L;
        Long            paymentAccountId = 1022200475681263616L;
        Date            createdOn        = new Date();

        WalletRecharge recharge = WalletRecharge.builder()
                .id(IDS.uniqueID())
                .currency(currency)
                .linkType(linkType)
                .address(address)
                .amount(amount)
                .txid(txid)
                .confirmations(confirmations)
                .blockHeight(height)
                .status(1)
                .remark(remark)
                .version(1)
                .createdOn(createdOn)
                .build();

        RechargeWithdrawal rechargeWithdrawal = RechargeWithdrawal.builder()
                .id(IDS.uniqueID())
                .userId(userId)
                .confirmations(confirmations)
                .txid(txid)
                .accountId(accountId)
                .paymentAccountId(paymentAccountId)
                .address(address)
                .currency(currency)
                .enabled(1)
                .bizType(0)
                .actualAmount(amount)
                .amount(amount)
                .fee(BigDecimal.ZERO)
                .actualMinerFee(BigDecimal.ZERO)
                .blockRecordId(recharge.getId())
                .state(1)
                .verifyState(1)
                .remark(remark)
                .linkType(linkType)
                .createdOn(createdOn)
                .build();

        VerifyRecord verifyRecord = VerifyRecord.builder()
                .id(IDS.uniqueID())
                .bizId(rechargeWithdrawal.getId())
                .category(0)
                .type(1)
                .status(1)
                .build();
        System.out.println("# 钱包库充值记录表");
        System.out.println(GenerateSqlUtil.genSql(recharge));
        System.out.println("# 资金账户库充值记录表");
        System.out.println(GenerateSqlUtil.genSql(rechargeWithdrawal));
        System.out.println("# 资金账户库充值审核记录表");
        System.out.println(GenerateSqlUtil.genSql(verifyRecord));

    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder @TableName("icc_account.verify_record")
    public static class VerifyRecord {
        private Long id;
        @Column(format = "%s", value = "biz_id")
        private Long bizId;
        @Column(format = "%s")
        private Integer category;
        @Column(format = "%s")
        private Integer type;
        @Column(format = "%s")
        private Integer status;
    }

    @Getter
    @Setter @AllArgsConstructor
    @NoArgsConstructor
    @Builder @TableName("icc_account.recharge_withdrawal")
    public static class RechargeWithdrawal {
        private Long id;
        @Column(format = "%s", value = "user_id")
        private Long userId;
        @Column(format = "%s")
        private Integer confirmations;

        private String txid;
        @Column(format = "%s", value = "account_id")
        private Long accountId;
        @Column(format = "%s", value = "payment_account_id")
        private Long paymentAccountId;

        private String address;

        private String currency;

        private Integer enabled;
        @Column(format = "%s", value = "biz_type")
        private Integer bizType;
        @Column(format = "%s", value = "actual_amount")
        private BigDecimal actualAmount;
        @Column(format = "%s")
        private BigDecimal amount;
        @Column(format = "%s")
        private BigDecimal fee;
        @Column(format = "%s", value = "actual_miner_fee")
        private BigDecimal actualMinerFee;

        @Column(format = "%s")
        private Integer state;
        @Column(format = "%s",value = "verify_state")
        private Integer verifyState;
        @Column(format = "%s",value = "block_record_id")
        private Long blockRecordId;

        private String remark;
        @Column(value = "link_type")
        private String linkType;

        @Column(value = "label_address")
        private String labelAddress;
        @Column(format = "%s")
        private Integer version;

        @Column(value = "created_on", format = "'%tF %tT'")
        private Date createdOn;

    }



    @Getter
    @Setter @AllArgsConstructor
    @NoArgsConstructor
    @Builder @TableName("icc_wallet.wallet_recharge_omni_usdt")
    public static class WalletRecharge{
        private Long id;
        private String currency;
        private String address;
        @Column(value = "link_type")
        private String linkType;
        @Column(value = "label_address")
        private String labelAddress;
        @Column(format = "%s")
        private BigDecimal amount;
        private String txid;
        private Integer confirmations;
        @Column(format = "%s", value = "block_height")
        private Long blockHeight;
        @Column(format = "%s")
        private Integer status;
        private String remark;
        @Column(format = "%s")
        private Integer version;
        @Column(value = "created_on", format = "'%tF %tT'")
        private Date createdOn;
    }
}
