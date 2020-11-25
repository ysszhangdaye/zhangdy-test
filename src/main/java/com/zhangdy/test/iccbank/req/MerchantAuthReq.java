package com.zhangdy.test.iccbank.req;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MerchantAuthReq {

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 经营业务
     */
    private String operatingScope;

    /**
     * 法人身份证
     */
    private String legalIdno;

    /**
     * 营业执照
     */
    private String bizLicenseNumber;

    /**
     * 法人身份证正面
     */
    private String legalPhotoFront;

    /**
     * 法人身份证反面
     */
    private String legalPhotoBack;

    /**
     * 营业执照照片
     */
    private String bizLicensePhoto;

    /**
     * 注册号
     */
    private String regNo;
    /**
     * 注册地
     */
    private String regAddress;

    /**
     * 法人姓名
     */
    private String legalName;
    
}
