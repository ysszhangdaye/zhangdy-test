package com.zhangdy.test.iccbank.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 转账请求对象
 */
@Setter
@Getter
@AllArgsConstructor
@Builder
public class TransferOrderPageReq {
    private int pageSize = 10;
    private int pageNo = 1;


    /**
     * 币种代码
     */
    private String currencyCode;

    /**
     * 账户id
     */
    private String accountId;

    /**
     * 从某个账户类型ID
     */
    private String fromAccountId;

    /**
     * 到某个账户类型ID
     */
    private String toAccountId;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

}
