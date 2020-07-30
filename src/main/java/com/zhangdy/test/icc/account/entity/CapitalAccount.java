package com.zhangdy.test.icc.account.entity;

import com.zhangdy.test.annotation.AutoGenerateValue;
import com.zhangdy.test.annotation.Column;
import com.zhangdy.test.annotation.ID;
import com.zhangdy.test.annotation.TableName;
import com.zhangdy.test.enums.GenerateTypeEnum;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@TableName("icc_account.capital_account")
public class CapitalAccount  {

    @ID
    @AutoGenerateValue(GenerateTypeEnum.IDS)
    @Column(format = "%s")
    private Long id;

    @Column(value = "user_id", format = "%s")
    private Long userId;

    @Column(format = "%s")
    private Integer category;

    @Column(value = "currency_pair")
    private String currencyPair;

    @Column(format = "%s")
    private Integer type;

    @Column(format = "%s")
    private Integer enabled;

    @Column(format = "%s")
    private Integer rank;

    private String exchange;

    @Column(value = "father_id", format = "%s")
    private Long fatherId;

    @Column(value = "created_on", format = "'%tF %tT'")
    private Date createdOn;

    @Column(format = "%s")
    private Integer version;

}