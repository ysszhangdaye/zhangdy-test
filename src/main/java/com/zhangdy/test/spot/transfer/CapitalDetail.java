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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("capital_detail")
public class CapitalDetail {
    @ID @Column(format = "%s") @AutoGenerateValue
    private Long id;
    @Column(format = "%s", value = "user_id")
    private Long userId;
    @Column(format = "%s", value = "account_id")
    private Long accountId;
    @Column(format = "%s")
    private BigDecimal balance;
    @Column(format = "%s")
    private BigDecimal freeze;
    @Column(format = "%s", value = "trade_lock")
    private BigDecimal tradeLock;
    @Column(format = "%s", value = "transfer_lock")
    private BigDecimal transferLock;
    private String currency;
    @Column(format = "%s", value = "`enabled`")
    private Integer enabled;
    @Column(format = "%s")
    private Integer type;
    @Column(format = "%s")
    private Integer category;
    @Column( value ="currency_pair" )
    private String currencyPair;
    @Column(value = "created_on", format = "'%tF %tT'")
    private Date createdOn;
    @Column(format = "%s")
    private Integer version;
}