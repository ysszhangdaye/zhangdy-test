package com.zhangdy.test.netty.rpc.provider;

import com.zhangdy.test.netty.rpc.api.CalcApiService;


public class CalcApiServiceImpl implements CalcApiService {

    @Override
    public int plus(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int mul(int a, int b) {
        return  a * b;
    }

    @Override
    public int div(int a, int b) {
        return a / b;
    }
}
