package com.zhangdy.test.enums;

import com.alibaba.fastjson.JSON;
import lombok.*;

import java.math.BigDecimal;

public class TestEnum {

    enum AccountType
    {
        SAVING, FIXED, CURRENT;
        private AccountType()
        {
            System.out.println("It is a account type");
        }
    }
        public static void main(String[]args)
        {
            System.out.println(AccountType.FIXED);
        }



}
