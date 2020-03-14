package com.zhangdy.test.string;

import com.alibaba.fastjson.JSON;
import lombok.*;

import java.io.Serializable;

/**
 * 启用新加密币对
 */
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
        EnabledInstrumentMessage test = EnabledInstrumentMessage.builder()
                .instrumentId(6L)
                .code("test")
                .build();
        System.out.println(JSON.toJSONString(test));

    }

}
