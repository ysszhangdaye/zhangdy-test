package com.zhangdy.test.iccbank.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NotNull
@Builder
public class ResetMerchantOpenApiReq {

    /**
     * ID
     */
    private Long id;

    /**
     * 绑定IP
     */
    private String bindIp;

    /**
     * 代收回调地址
     */
    private List<CallbackUrlReq> callbackUrl;

    /**
     * 备注
     */
    private String remark;

    /**
     * api权限
     */
    private List<Integer> apiPermission;

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

    @Getter
    @Setter
    public static class CallbackUrlReq {

        private int type;
        private String callbackUrl;

    }

}
