package com.zhangdy.test.openapi;

import cn.hutool.core.date.DateTime;
import com.zhangdy.test.annotation.Column;
import com.zhangdy.test.annotation.AutoGenerateValue;
import com.zhangdy.test.annotation.ID;
import com.zhangdy.test.annotation.TableName;
import com.zhangdy.test.enums.GenerateTypeEnum;
import com.zhangdy.util.GenerateSqlUtil;
import com.zhangdy.util.HmacSHA256Base64Utils;
import lombok.*;

import java.util.Date;


public class GenerateApiKey {

    public static void main(String[] args) throws Exception{
//        b17756566660@163.com apiKey 113c7a36760c47c2af9781cc3e3e636e 口令  makun

//        gen("makun", 966439767067648000L);
        gen("zhangsan", 959621091381919744L);



    }

    public static void gen(String pass, Long userId) throws Exception{
        UserApiInfo userApiInfo = UserApiInfo.builder()
                .userId(userId)
                .accessKey(HmacSHA256Base64Utils.sign(pass))
                .enable(1)
                .createdOn(new Date())
                .build();
        System.out.println(GenerateSqlUtil.genSql(userApiInfo));
    }

    public static void genUpd(String pass, Long userId) throws Exception{
        UserApiInfo userApiInfo = UserApiInfo.builder()
                .userId(userId)
                .id(222L)
                .accessKey(HmacSHA256Base64Utils.sign(pass))
                .enable(1)
                .createdOn(new Date())
                .build();
        System.out.println(GenerateSqlUtil.generateUpdateSql(userApiInfo));
    }




    @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
    @TableName("user_api_info")
    public static class UserApiInfo{

        @AutoGenerateValue(GenerateTypeEnum.IDS)
        @Column(format = "%s")
        @ID
        private Long id;

        @Column(value = "user_id", format = "%s")
        private Long userId;

        @Column("api_key")
        @AutoGenerateValue(GenerateTypeEnum.UUID)
        private String apiKey;

        @Column("secret_key")
        @AutoGenerateValue(GenerateTypeEnum.UUID)
        private String secretKey;

        @Column("access_key")
        private String accessKey;

        @Column(value = "`enable`", format = "%s")
        private Integer enable;

        @Column(value = "created_on", format = "'%tF %tT'")
        private Date createdOn;
    }

}
