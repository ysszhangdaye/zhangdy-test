package com.zhangdy.test.wallet;

import com.zhangdy.test.annotation.AutoGenerateValue;
import com.zhangdy.test.annotation.Column;
import com.zhangdy.test.annotation.TableName;
import com.zhangdy.test.enums.GenerateTypeEnum;
import com.zhangdy.util.GenerateSqlUtil;
import com.zhangdy.util.IDS;
import lombok.*;

public class WalletConfigTest {


    public static void main(String[] args) throws Exception {
//        Config btcConfig    = convertConfig("http://18.141.24.172:8332", "dssj", "5qvwxj26", "BTC", null);
//        Config ltcConfig    = convertConfig("http://10.10.23.233:8888", "dssj", "dssj123", "LTC", null);
//        Config bchConfig    = convertConfig("http://10.10.23.234:18332", "dssj", "dssj123", "BCH", null);
//        Config ethConfig    = convertConfig("http://13.229.113.89:8545", "ETH", null);
//        Config etcConfig    = convertConfig("http://54.169.196.104:8546", "ETC", null);
//        Config erc20Config  = convertConfig("http://13.229.113.89:8545", "GETH", "ERC20");


//        System.out.println(GenerateSqlUtil.genSql(btcConfig));
//        System.out.println(GenerateSqlUtil.genSql(ltcConfig));
//        System.out.println(GenerateSqlUtil.genSql(bchConfig));
//        System.out.println(GenerateSqlUtil.genSql(ethConfig));
//        System.out.println(GenerateSqlUtil.genSql(etcConfig));
//        System.out.println(GenerateSqlUtil.genSql(erc20Config));
        Config usdtConfig    = convertConfig("http://10.10.23.232:18332", "dssj", "dssj123", "USDT", "OMNI");

        System.out.println(GenerateSqlUtil.genSql(usdtConfig));


    }


    public static Config convertConfig(String url, String user, String pwd, String currency, String linkType) throws Exception{
        return Config.builder()
                .id(IDS.uniqueID())
                .url(IccWalletConfig.encode(url))
                .user(IccWalletConfig.encode(user))
                .password(IccWalletConfig.encode(pwd))
                .type(0)
                .currency(currency)
                .linkType(linkType)
                .enabled(1)
                .remark(currency + " 充币钱包配置")
                .build();
    }
    public static Config convertConfig(String url, String currency, String linkType) throws Exception{
        return Config.builder()
                .id(IDS.uniqueID())
                .url(IccWalletConfig.encode(url))
                .type(0)
                .currency(currency)
                .linkType(linkType)
                .enabled(1)
                .remark(currency + " 充币钱包配置")
                .build();
    }





    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
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
