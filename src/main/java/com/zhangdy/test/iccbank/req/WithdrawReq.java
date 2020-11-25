package com.zhangdy.test.iccbank.req;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class WithdrawReq {

    private String withdrawId;         //提现id

    private Long accountId;        //账户Id

    private String currencyCode;     //币种
    private Long addressBookId;    //地址簿Id
    private BigDecimal amount;       //数量
    private BigDecimal feeAmount;    //手续费
    private String password;         //钱包密码
    private String validCode;
    // 验证码类型
    private String validCodeType;
}
