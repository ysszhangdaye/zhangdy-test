package com.zhangdy.test.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import lombok.AllArgsConstructor;

public class BaseDubbo {

    public  <T> T getApi(Class<T> clazz, ServerNode serverNode) {
        String url = "dubbo://".concat(serverNode.node).concat("/").concat(clazz.getName());
        ApplicationConfig application = new ApplicationConfig();
        application.setName("icc-test-dubbo-app");
        ReferenceConfig<T> reference = new ReferenceConfig();
        reference.setApplication(application);
        reference.setUrl(url);
        reference.setGroup(serverNode.group);
        reference.setCheck(false);
        reference.setRetries(0);
        reference.setInterface(clazz);
        reference.setVersion(serverNode.version);
        reference.setTimeout(10000);
        return  reference.get();
    }



    @AllArgsConstructor
    enum ServerNode {

        INSTRUMENT_NODE_TEST_A("instrument", "1.0.0", "10.10.23.202:20638"),
        INSTRUMENT_NODE_TEST_B("instrument", "1.0.0", "10.10.23.203:20638"),
        INSTRUMENT_NODE_DEV("instrument", "1.0.0", "10.10.23.207:20638"),

        MARKET_COIN_NODE_TEST_A("market-coin", "1.0.0", "10.10.23.202:22317"),
        MARKET_COIN_NODE_TEST_B("market-coin", "1.0.0", "10.10.23.203:22317"),
        MARKET_COIN_NODE_DEV("market-coin", "1.0.0", "10.10.23.207:22317"),
        MARKET_COIN_NODE_LOCAL("market-coin", "1.0.0", "192.168.21.199:22317"),
        MARKET_CORE_NODE_183("market-coin", "1.0.0", "10.10.23.183:20788"),

        TRADE_COIN_NODE_TEST_A("coin", "1.0.0", "10.10.23.202:23807"),
        TRADE_COIN_NODE_TEST_B("coin", "1.0.0", "10.10.23.203:23807"),
        TRADE_COIN_NODE_LOCAL("coin", "1.1.0", "localhost:23807"),

        MATCH_NODE_TEST_A("match", "1.0.0", "10.10.23.202:22837"),
        MATCH_NODE_TEST_B("match", "1.0.0", "10.10.23.203:22837"),
        MATCH_NODE_DEV("match", "1.0.0", "10.10.23.207:22837"),

        ACCOUNT_ADMIN_DEV("account-admin", "1.0.1", "10.10.23.207:21807"),
        ACCOUNT_DEV_TEST_A("account", "1.0.1", "10.10.23.202:21807"),
        ACCOUNT_DEV_TEST_B("account", "1.0.1", "10.10.23.203:21807"),
        ACCOUNT_DEV_ADMIN_LOCAL("account-admin", "1.0.1", "192.168.21.199:21807"),



        DECENTER_WALLET_LOCAL("decenter-wallet", "1.0.0", "localhost:22022"),
        DECENTER_WALLET_LOCAL2("decenter-wallet", "1.0.0", "localhost:22023"),
        DECENTER_WALLET_DEV("decenter-wallet", "1.0.0", "10.10.23.207:22022"),

        ;

        String group;
        String version;
        String node;
    }

}
