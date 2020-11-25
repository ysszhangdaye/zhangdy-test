package com.zhangdy.test.iccbank.req;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MerchantOpenApiDetailReq {

    /**
     * ID
     */
    private Long id;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证类型， mobile-手机 email-邮箱 google-谷歌认证
     */
    private String validCodeType;

    /**
     * 验证码
     */
    private String captcha;

}
