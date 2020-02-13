package com.zhangdy.test.wallet;

import com.zhangdy.util.GenerateSqlUtil;
import com.zhangdy.util.IDS;

public class EtcConfig {


    public static void genConfigSql()  throws Exception{
        IccWalletConfig.Config rechargeConfig = IccWalletConfig.Config.builder()
                .id(IDS.uniqueID())
                .url(IccWalletConfig.encode("http://10.10.23.236:8545/wallet/recharge"))
                .type(0)
                .default_from_address(null)
                .currency("ETC")
                .link_type(null)
                .enabled(1)
                .remark("ETC 充币钱包配置")
                .build();
        String sql = GenerateSqlUtil.genSql(rechargeConfig);
        System.out.println(sql);
        IccWalletConfig.Config withdrawConfig = IccWalletConfig.Config.builder()
                .id(IDS.uniqueID())
                .url(IccWalletConfig.encode("http://10.10.23.236:8545/wallet/recharge"))
                .type(1)
                .default_from_address(null)
                .currency("ETC")
                .link_type(null)
                .enabled(1)
                .remark("ETC 提币钱包配置")
                .build();
        sql = GenerateSqlUtil.genSql(withdrawConfig);
        System.out.println(sql);
    }

}
