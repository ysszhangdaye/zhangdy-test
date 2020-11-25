package com.zhangdy.test.iccbank.req;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMerchantApiReq {

    /**
     * 绑定IP
     */
    private String bindIp;

    /**
     * 代收回调地址
     */
    private String callbackUrl;

    /**
     * 备注
     */
    private String remark;

    /**
     * api权限
     */
    private List<Long> apiPermission;

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
