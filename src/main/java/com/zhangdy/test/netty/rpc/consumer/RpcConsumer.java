package com.zhangdy.test.netty.rpc.consumer;


import com.zhangdy.test.netty.rpc.api.CalcApiService;
import com.zhangdy.test.netty.rpc.protocol.proxy.RpcProxy;

public class RpcConsumer {
	
    public static void main(String [] args){

        CalcApiService service = RpcProxy.create(CalcApiService.class);
        
        System.out.println("8 + 2 = " + service.plus(8, 2));
        System.out.println("8 - 2 = " + service.sub(8, 2));
        System.out.println("8 * 2 = " + service.mul(8, 2));
        System.out.println("8 / 2 = " + service.div(8, 2));
    }
    
}
