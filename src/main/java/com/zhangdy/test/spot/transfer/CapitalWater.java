package com.zhangdy.test.spot.transfer;

import com.zhangdy.test.annotation.AutoGenerateValue;
import com.zhangdy.test.annotation.Column;
import com.zhangdy.test.annotation.ID;
import com.zhangdy.test.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("icc_account.account_capital_water")
public class CapitalWater {
    @Column(format = "%s") @ID @AutoGenerateValue
    private Long id;
    @Column(format = "%s", value = "user_id")
    private Long userId;
    @Column(format = "%s", value = "account_id")
    private Long accountId;
    @Column(format = "%s", value = "capital_detail_id")
    private Long capitalDetailId;
    @Column(format = "%s", value = "in_account_type")
    private Integer inAccountType;
    @Column(format = "%s", value = "out_account_type")
    private Integer outAccountType;
    @Column(format = "%s", value = "in_account_category")
    private Integer inAccountCategory;
    @Column(format = "%s", value = "out_account_category")
    private Integer outAccountCategory;
    @Column(format = "%s", value = "in_account_id")
    private Long inAccountId;
    @Column(format = "%s", value = "out_account_id")
    private Long outAccountId;
    @Column(format = "%s", value = "in_capital_detail_id")
    private Long inCapitalDetailId;
    @Column(format = "%s", value = "out_capital_detail_id")
    private Long outCapitalDetailId;
    private String currency;
    @Column(value = "fee_currency")
    private String feeCurrency;
    @Column(format = "%s", value = "out_before_balance")
    private BigDecimal outBeforeBalance;
    @Column(format = "%s", value = "out_after_balance")
    private BigDecimal outAfterBalance;
    @Column(format = "%s", value = "in_before_balance")
    private BigDecimal inBeforeBalance;
    @Column(format = "%s", value = "in_after_balance")
    private BigDecimal inAfterBalance;
    @Column(format = "%s", value = "amount")
    private BigDecimal amount;
    @Column(format = "%s", value = "fee")
    private BigDecimal fee;
    @Column(value = "source_id")
    private String sourceId;
    @Column(format = "%s")
    private Integer cause;
    private String description;
    @Column(value = "`time`", format = "'%tF %tT'")
    private Date time;
    @Column(format = "%s")
    private Integer checking;
    @Column(value = "created_on", format = "'%tF %tT'")
    private Date createdOn;
    @Column(format = "%s", value = "`version`")
    private Integer version;

}