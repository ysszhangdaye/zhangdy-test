package com.zhangdy.test.wallet;

import com.google.common.collect.Lists;
import com.zhangdy.test.annotation.AutoGenerateValue;
import com.zhangdy.test.annotation.Column;
import com.zhangdy.test.annotation.TableName;
import com.zhangdy.test.enums.GenerateTypeEnum;
import com.zhangdy.util.RsaUtils;
import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class IccWalletConfig {

    static String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDfautNs3QvgeHEuBLDlkLVRIT5X/dCme+yFjrE8EZpjdo2mKBr8SweRc08WWFO1J7lgcCJyXkJ8fPHDLlvzz6+F2PGSAOIfBuIDs3yBB2u+G7agvxhmvPucHiwzKymUNAtvAVkdZiZEWVqyVPlh6rgvv8q8WlxYs8qjvIy75XWqQIDAQAB";

    static void f(double  s) {
        System.out.println(1);
    }
    static void f(Integer i){
        System.out.println(2);

    }
    <String, T, Alibaba> String get(String string, T t) { return string; }

    public static void main(String[] args) throws Exception{
//        BtcConfig.genConfigSql();
//        EtcConfig.genConfigSql();
//        EthConfig.genConfigSql();
//        OmniUsdtConfig.genConfigSql();
//        LtcConfig.genConfigSql();

        List<Integer> a = Lists.newArrayList();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        List<String> strings = a.stream().map(x -> x + "").collect(Collectors.toList());
        String join = String.join(",", strings);
        System.out.println(join);

        List<String> strings1 = Arrays.asList(join.split(","));


        String url  = IccWalletConfig.encode("http://172.31.14.122:8332");
        String user = IccWalletConfig.encode("dssj");
        String pwd  = IccWalletConfig.encode("dssj123");

        System.out.println("url :   "  + url);
        System.out.println("user:   "  + user);
        System.out.println("pwd :   "  + pwd);
    }


    public static String encode(String str) throws Exception{
        byte[] bytes = RsaUtils.encryptByPublicKey(str.getBytes(), pubKey);
        return RsaUtils.toHexString(bytes);
    }



   @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
   @TableName("wallet_config")
   public static class Config {
        @AutoGenerateValue(GenerateTypeEnum.IDS)
        @Column(format = "%s")
        private Long id;
        private String url;
        private String user;
        private String password;
        private Integer type;
        @Column("default_from_address")
        private String defaultFromAddress;
        private String currency;
        @Column("link_type")
        private String linkType;
        @Column(value = "`enabled`", format = "%s")
        private Integer enabled;
        private String remark;
        private String extra;
    }


}
