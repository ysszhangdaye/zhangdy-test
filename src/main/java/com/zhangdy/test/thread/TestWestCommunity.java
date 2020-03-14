package com.zhangdy.test.thread;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.zhangdy.util.DateUtil;
import com.zhangdy.util.HttpUtil;
import com.zhangdy.util.ThreadUtil;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestWestCommunity extends Thread{

    int periods;

    ExecutorService executorService;

    public static void main(String[] args) throws Exception {

//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//
////        login();
//        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
//        TestWestCommunity testWestCommunity = new TestWestCommunity(5874, executorService);
//        testWestCommunity.run();
//        pool.scheduleWithFixedDelay(testWestCommunity, 5, 5, TimeUnit.MINUTES);

        String s = HttpUtil.reqGetJson("http://www.icctoro.net/api/user/binary/getUserInfoById?userId=1", null);
        System.out.println(s);
    }


    public TestWestCommunity(int periods, ExecutorService executorService){
        this.periods = periods;
        this.executorService = executorService;
    }


    @Override
    public void run() {

        int p = periods;

        Thread thread = new Thread(() -> {
            try {
                place(1, p);
            } catch (Exception e) {

            }
        });
        executorService.submit(thread);


        Thread thread1 = new Thread(() -> {
            try {
                place(4, p);
            } catch (Exception e) {

            }
        });
        executorService.submit(thread1);

        periods ++;



    }

    public static void place(int prizeResult, int periods) throws Exception{
//        06071c7b7b284c2abf52c78864490a15
        //        {"languageCode":localStorage.languageCode,"coinType":coinCode,
//                "amount":amount,"playItemCode":"lottery_luckyNumber",
//                "prizeResult":prize,"periods":periods,"luckyUserId":luckyUserId}
//        prizeResult 1 大 2小 3单 4双
//        {"status":200,"msg":"调用服务接口成功！",
//        "data":{"accessToken":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5NTA1NjI2MDQ4NzA2NTYwMDAiLCJhdWQiOiJudWxsIiwiaXNzIjoiOTUwNTYyNjA0ODcwNjU2MDAwIiwiZXhwIjoyMTg0MjE5Mjc3LCJpYXQiOjE1Nzk0MTkyNzcsImp0aSI6IjExYmQ1NjhmLTM4MmQtNDU1OC1iZDIyLTUxMzUyYzM1MWM1NiJ9.Vfh9SHhjIktJSz-TATGF0gqcRo75yBLOQxmyWxKp46Dy8ui00cTgtdaAiGy0PxXnjM_pAQu6K9YD924mlPcheg",
//        "luckyUserId":"06071c7b7b284c2abf52c78864490a15","userSecretKey":"af53f43ff9c3f5b60ef5d0054994c1f5"}}
        Map<String, String> headers = Maps.newConcurrentMap();
        headers.put("Origin", "https://static.icoinlife.com");
        headers.put("Referer", "https://static.icoinlife.com/luckynumber/zh-cn/index.html");
        headers.put("Sec-Fetch-Mode", "cors");
        headers.put("accessToken", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5NTA1NjI2MDQ4NzA2NTYwMDAiLCJhdWQiOiJudWxsIiwiaXNzIjoiOTUwNTYyNjA0ODcwNjU2MDAwIiwiZXhwIjoyMTg0MjE5Mjc3LCJpYXQiOjE1Nzk0MTkyNzcsImp0aSI6IjExYmQ1NjhmLTM4MmQtNDU1OC1iZDIyLTUxMzUyYzM1MWM1NiJ9.Vfh9SHhjIktJSz-TATGF0gqcRo75yBLOQxmyWxKp46Dy8ui00cTgtdaAiGy0PxXnjM_pAQu6K9YD924mlPcheg");
        headers.put("userSecretKey", "af53f43ff9c3f5b60ef5d0054994c1f5");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36");
//&languageCode=zh-cn&luckyUserId=06071c7b7b284c2abf52c78864490a15&coinType=BTC&amount=3000&playItemCode=lottery_luckyNumber&prizeResult=1&periods=282
        String url = "https://api.icoinlife.com/api/luckyNumber/betting";
        Request request = Request.builder()
                .languageCode("zh-cn")
                .coinType("BTC")
                .amount(new BigDecimal("3000"))
                .playItemCode("lottery_luckyNumber")
                .prizeResult(prizeResult)
                .periods(periods)
                .luckyUserId("06071c7b7b284c2abf52c78864490a15")
                .build();
        String hHmmss = DateUtil.getDateFormat(new Date(), "HH时mm分ss.sss秒");
        String s = HttpUtil.reqPostString(url, request.getReqStr(), headers);
        System.out.println(hHmmss + "  " + periods + "   ----->   " + s);
    }

    //某欧某方法
    public static void total() throws Exception{
        String url = "https://api.icoinlife.com/api/luckyNumber/home/totalScroll";
        String s = HttpUtil.reqPostJson(url, "{'languageCode':'zh-cn'}");
        System.out.println(s);
    }

    public static void login()  throws Exception{
        String url = "https://api.icoinlife.com/api/luckyNumber/saveUser";
//        data:{"languageCode":localStorage.languageCode,"userId":userId,"userName":userName,
//                "headImg":headImg,"nickName":nickName,"accessToken":accessToken},

        Map<String, String> headers = Maps.newConcurrentMap();
        headers.put("Origin", "https://static.icoinlife.com");
        headers.put("Referer", "https://static.icoinlife.com/luckynumber/zh-cn/index.html");
        headers.put("Sec-Fetch-Mode", "cors");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36");
//        {"accessToken":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5NTA1NjI2MDQ4NzA2NTYwMDAiLCJhdWQiOiJudWxsIiwiaXNzIjoiOTUwNTYyNjA0ODcwNjU2MDAwIiwiZXhwIjoyMTg0MjE5Mjc3LCJpYXQiOjE1Nzk0MTkyNzcsImp0aSI6IjExYmQ1NjhmLTM4MmQtNDU1OC1iZDIyLTUxMzUyYzM1MWM1NiJ9.Vfh9SHhjIktJSz-TATGF0gqcRo75yBLOQxmyWxKp46Dy8ui00cTgtdaAiGy0PxXnjM_pAQu6K9YD924mlPcheg",
//                "headImg":"http://iccimg.oss-ap-southeast-1.aliyuncs.com/images/2020-01-13/fd921d785bbc40368839936e384e0980.png",
//                "nickName":"Miles","userId":950562604870656000,"userName":"18269776176"}
        LoginRequest request = LoginRequest.builder()
                .languageCode("zh-cn")
                .userId("950562604870656000")
                .userName("18269776176")
                .headImg("http://iccimg.oss-ap-southeast-1.aliyuncs.com/images/2020-01-13/fd921d785bbc40368839936e384e0980.png")
                .nickName("18269776176")
                .accessToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5NTA1NjI2MDQ4NzA2NTYwMDAiLCJhdWQiOiJudWxsIiwiaXNzIjoiOTUwNTYyNjA0ODcwNjU2MDAwIiwiZXhwIjoyMTg0MjE5Mjc3LCJpYXQiOjE1Nzk0MTkyNzcsImp0aSI6IjExYmQ1NjhmLTM4MmQtNDU1OC1iZDIyLTUxMzUyYzM1MWM1NiJ9.Vfh9SHhjIktJSz-TATGF0gqcRo75yBLOQxmyWxKp46Dy8ui00cTgtdaAiGy0PxXnjM_pAQu6K9YD924mlPcheg")
                .build();


        String s = HttpUtil.reqPostString(url, request.getReqStr(), headers);
        System.out.println(s);
    }
    @Getter @Setter @AllArgsConstructor @Builder @NoArgsConstructor
    public static class LoginRequest{
        private String languageCode;
        private String userId;
        private String userName;
        private String headImg;
        private String nickName;
        private String accessToken;

        public String getReqStr(){

            String result = "&languageCode=" + languageCode;
            result += "&userId=" + userId;
            result += "&userName=" + userName;
            result += "&headImg="+headImg;
            result += "&nickName="+nickName;
            result += "&accessToken="+accessToken;



            return result;
        }

    }


    @Getter @Setter @AllArgsConstructor @Builder @NoArgsConstructor
    public static class Request {
        private String languageCode;
        private String coinType;
        private BigDecimal amount;
        private String playItemCode;
        private Integer prizeResult;
        private Integer periods;
        private String luckyUserId;


        public String getReqStr(){
            String result = "&languageCode=" + languageCode;
            result += "&luckyUserId=" + luckyUserId;
            result += "&coinType=" + coinType;
            result += "&amount="+amount;
            result += "&playItemCode="+playItemCode;
            result += "&prizeResult="+prizeResult;
            result += "&periods="+periods;

            return result;
        }

    }

}
