package com.zhangdy.test.fn;

import com.alibaba.fastjson.JSON;
import com.zhangdy.util.IDS;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;
import java.util.function.Function;

public class TestFn {




    public static void main(String[] args) {


        for (int i=0;i<100;i++) {
            System.out.println(IDS.uniqueID());

        }

//        testFn("zhangsan", new TestFn()::convert);
//
//        WalletConfigExtraDTO walletConfigExtraDTO = new WalletConfigExtraDTO();
//        walletConfigExtraDTO.setIp("169.254.56.245");
//        walletConfigExtraDTO.setJobType(1);
//        walletConfigExtraDTO.setNrtid(1L);
//        walletConfigExtraDTO.setNrtd(1L);
//        walletConfigExtraDTO.setRctid(1L);
//        walletConfigExtraDTO.setRctd(1L);
//        System.out.println(JSON.toJSONString(walletConfigExtraDTO));

    }

    public static void testFn(String name, Consumer<String> fn){
      fn.accept(name);
    }

    public  void convert(String name){
        System.out.println(name);
    }


    @Getter
    @Setter
    public static class WalletConfigExtraDTO {
        private String ip;
        /** 0-上层业务触发 1-定时任务线程池 */
        private Integer jobType;
        /** 定时任务首次延迟时间 单位：分钟 */
        private Long nrtid;
        /** 定时任务执行间隔 单位：分钟*/
        private Long nrtd;
        /** 定时任务首次延迟时间 单位：分钟 */
        private Long rctid;
        /** 定时任务执行间隔 单位：分钟*/
        private Long rctd;

    }


}
