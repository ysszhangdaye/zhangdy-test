package com.zhangdy.test.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/***
 * 数据库存储使用下标 从0开始   新增从后面加
 * 数据库存储使用下标 从0开始   新增从后面加
 * 数据库存储使用下标 从0开始   新增从后面加
 */
@Getter
@AllArgsConstructor
public enum Command {
    /**
     * 买,卖,撤
     */
    B(0, ""), S(1, ""), C(2, "");


    private int type;
    private String desc;
}
