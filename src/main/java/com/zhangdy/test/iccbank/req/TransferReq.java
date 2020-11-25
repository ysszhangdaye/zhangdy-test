package com.zhangdy.test.iccbank.req;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class TransferReq {

    /**
     * 业务ID
     */
    private Long bizId;

    /**
     * 币种代码
     */
    private String currencyCode;

    /**
     * 从某个账户类型ID
     */
    private Long fromAccountId;

    /**
     * 到某个账户类型ID
     */
    private Long toAccountId;

    /**
     * 金额
     */
    private BigDecimal amount;
    
}
