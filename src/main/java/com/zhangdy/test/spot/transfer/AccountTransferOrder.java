package com.zhangdy.test.spot.transfer;

import com.zhangdy.test.annotation.AutoGenerateValue;
import com.zhangdy.test.annotation.Column;
import com.zhangdy.test.annotation.ID;
import com.zhangdy.test.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@TableName("icc_account.account_transfer_order")
public class AccountTransferOrder {
    @ID @Column(format = "%s")
    private Long id;
    @Column(format = "%s", value = "in_user_id")
    private Long inUserId;
    @Column(format = "%s", value = "out_user_id")
    private Long outUserId;
    @Column(format = "%s", value = "in_account_id")
    private Long inAccountId;
    @Column(format = "%s", value = "out_account_id")
    private Long outAccountId;
    @Column(format = "%s", value = "in_capital_detail_id")
    private Long inCapitalDetailId;
    @Column(format = "%s", value = "out_capital_detail_id")
    private Long outCapitalDetailId;
    @Column(format = "%s", value = "in_type")
    private Integer inType;
    @Column(format = "%s", value = "out_type")
    private Integer outType;
    @Column(format = "%s", value = "in_category")
    private Integer inCategory;
    @Column(format = "%s", value = "out_category")
    private Integer outCategory;
    @Column( value = "in_currency_pair")
    private String inCurrencyPair;
    @Column( value = "out_currency_pair")
    private String outCurrencyPair;
    @Column( value = "in_exchange")
    private String inExchange;
    @Column( value = "out_exchange")
    private String outExchange;
    @Column( value = "`status`", format = "%s")
    private Integer status;
    private String currency;
    @Column(format = "%s")
    private BigDecimal amount;
    private String description;
    @Column(value = "created_on", format = "'%tF %tT'")
    private Date createdOn;

    public static AccountTransferOrder create(long id, long userId, Long inAccountId, long outAccountId, Long inCapitalDetailId, long outCapitalDetailId,
                                              int inType, int outType, String currency, BigDecimal amount){
        return AccountTransferOrder.builder()
                .id(id)
                .inUserId(userId).outUserId(userId)
                .inAccountId(inAccountId)
                .outAccountId(outAccountId)
                .inCapitalDetailId(inCapitalDetailId)
                .outCapitalDetailId(outCapitalDetailId)
                .inType(inType)
                .outType(outType)
                .inCategory(10)
                .outCategory(10)
                .inCurrencyPair("BTC")
                .outCurrencyPair("BTC")
                .inExchange("ICC")
                .outExchange("ICC")
                .status(1)
                .currency(currency)
                .amount(amount)
                .createdOn(new Date())
                .build();
    }




}