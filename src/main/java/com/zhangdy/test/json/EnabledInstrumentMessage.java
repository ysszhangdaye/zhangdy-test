package com.zhangdy.test.json;

import com.alibaba.fastjson.JSON;
import com.icc.framework.api.constant.CommonConstant;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnabledInstrumentMessage implements Serializable {
    private Long instrumentId;
    private String code;
    /** ENCRY */
    private String exType;
    /** ENCRY */
    private String type;
    /** COINBASE */
    private String exchange;

    public static void main(String[] args) {

        EnabledInstrumentMessage build = EnabledInstrumentMessage.builder()
                .instrumentId(1005543133643051008L)
                .code("ETH_BTC_ICNCDE_ENCRY")
                .exType("ENCRY")
                .type("ENCRY")
                .exchange("ICNCDE")
                .build();

        System.out.println(JSON.toJSONString(build));

    }

}

