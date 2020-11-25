package com.zhangdy.test.iccbank;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.infra.tools.MD5Util;
import com.zhangdy.test.iccbank.req.*;
import com.zhangdy.util.HttpUtil;
import com.zhangdy.util.IDS;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.ws.rs.HEAD;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Slf4j
public class TestMerchantWeb {

    private static final String END_POINT = "http://localhost:41038";
    private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDk0NDI5OTE0MDk1MzQxNTY5IiwiYXVkIjoibnVsbCIsImhlYWRJbWciOm51bGwsIm1lcmNoYW50SWQiOiIxMDk0MzU0MDI0NDQ3ODMyMDY0IiwiaXNzIjoiMTA5NDQyOTkxNDA5NTM0MTU2OSIsInVzZXJUeXBlIjowLCJ1c2VyTmFtZSI6InpoYW5nc2FuIiwidGVtcGxhdGVJZCI6IjEwOTQ0NTc4MDUxNzY5MjYyMDgiLCJleHAiOjIyMjIyMjM4MjgwNzEwNDMsImlhdCI6MTYwNTg0ODgyMSwiZW1haWwiOiJ6aGFuZ3NAYXNkLmNvbSIsImp0aSI6IjY0MzVlMjdlLTJmNjMtNDlkMC1iNzMxLWQ4YTA0MjY4YWMyNyJ9.vQbDEnKr3ZqJbXneixshYs3070EXC6oO6m3RCTczFUCZMo7HJHK_LsUmo2pxuyzPCOCWnfATuoosbmnhscYybw";
    private static final Map<String, String> HEADERS = Maps.newHashMap();

    static {
        HEADERS.put("Authorization", TOKEN);
    }

    @Test
    public void testMerchantOpenedBizList() throws Exception{
        String url = END_POINT + "/v1/mch/business/info";
        String s = HttpUtil.reqGetJson(url, HEADERS);
        log.info("{}", s);
    }

    @Test
    public void testMerchantOpenBiz() throws Exception{
        String url = END_POINT + "/v1/mch/business/open";
        String content = "{\"openList\":[0,1,2,3]}";
        String s = HttpUtil.reqPostJson(url, content, HEADERS);
        log.info("{}", s);
    }

    @Test
    public void testApplyOpenApi() throws Exception{
        List<Long> apiPermissionList = Lists.newArrayList();
        apiPermissionList.add(0L);
        apiPermissionList.add(1L);
        apiPermissionList.add(2L);

        CreateMerchantApiReq req = CreateMerchantApiReq.builder()
//                .bindIp("127.0.0.1")
                .remark("s")
                .password(getFundPassword())
                .validCodeType("email")
                .captcha("111111")
                .apiPermission(apiPermissionList)
                .build();
        String url = END_POINT + "/v1/mch/userapi/apply";
        String content = JSON.toJSONString(req);
        String s = HttpUtil.reqPostJson(url, content, HEADERS);
        log.info("{}", s);
    }

    @Test
    public void testMerchantOpenApiPage() throws Exception{
        String url = END_POINT + "/v1/mch/userapi/list";
        String s = HttpUtil.reqPostJson(url, "{}", HEADERS);
        log.info("{}", s);
    }

    @Test
    public void testRemoveMerchantOpenApi() throws Exception{
        RemoveMerchantOpenApiReq req = RemoveMerchantOpenApiReq.builder()
                .id(1098692038290231296L)
                .password(MD5Util.getMD5String("12345678"))
                .validCodeType("email")
                .captcha("111111")
                .build();
        String url = END_POINT + "/v1/mch/userapi/delete";
        String content = JSON.toJSONString(req);
        String s = HttpUtil.reqPostJson(url, content, HEADERS);
        log.info("{}", s);
    }

    @Test
    public void testRestMerchantOpenApi()  throws Exception{
        ResetMerchantOpenApiReq req = ResetMerchantOpenApiReq.builder()
                .id(2L)
                .bindIp("1")
                .remark("test-reset")
                .validCodeType("email")
                .captcha("111111")
                .build();
        String url = END_POINT + "/v1/mch/userapi/reset";
        String content = JSON.toJSONString(req);
        String s = HttpUtil.reqPostJson(url, content, HEADERS);
        log.info("{}", s);
    }

    @Test
    public void testGetMerchantOpenApiDetail() throws Exception{
        MerchantOpenApiDetailReq req = MerchantOpenApiDetailReq.builder()
                .id(1098692038290231296L)
                .password(this.getFundPassword())
                .validCodeType("email")
                .captcha("111111")
                .build();
        String url = END_POINT + "/v1/mch/userapi/getInfoIncludeAppSecret";
        String content = JSON.toJSONString(req);
        String s = HttpUtil.reqPostJson(url, content, HEADERS);
        log.info("{}", s);
    }


    @Test
    public void testGetAllCallback() throws Exception{
        String url = END_POINT + "/v1/mch/userapi/allcallbacktype";
        String s = HttpUtil.reqGetJson(url,  HEADERS);
        log.info("{}", s);
    }

    @Test
    public void testGetAllPermission() throws Exception{
        String url = END_POINT + "/v1/mch/userapi/permissions";
        String s = HttpUtil.reqGetJson(url,  HEADERS);
        log.info("{}", s);
    }

    @Test
    public void testMerchantAuth() throws Exception{
        MerchantAuthReq req = MerchantAuthReq.builder()
                .orgName("八号当铺")
                .operatingScope("收集灵魂")
                .legalIdno("2535489856544556")
                .bizLicenseNumber("没有")
                .legalPhotoFront("不给")
                .legalPhotoBack("也不给")
                .bizLicensePhoto("还是不给")
                .regNo("zhangsan")
                .regAddress("地狱十八层")
                .legalName("斗战胜佛")
                .build();
        String url = END_POINT + "/v1/mch/authenticate";
        String content = JSON.toJSONString(req);
        String s = HttpUtil.reqPostJson(url,  content, HEADERS);
        log.info("{}", s);
    }


    @Test
    public void testFindTransferOrderPage() throws Exception{

        TransferOrderPageReq req = TransferOrderPageReq.builder()
                .pageNo(1)
                .pageSize(10)
                .currencyCode("BTC")
//                .startTime("2020-11-10 11:11:11")
                .endTime("2020-11-20 11:11:11")
                .build();
        String url = END_POINT + "/v1/mch/assets/transferList";
        String content = JSON.toJSONString(req);
        String s = HttpUtil.reqPostJson(url,  content, HEADERS);
        log.info("{}", s);

    }

    @Test
    public void testTransfer()  throws Exception{
        TransferReq req = TransferReq.builder()
                .bizId(IDS.uniqueID())
                .currencyCode("BTC")
                .fromAccountId(1L)
                .toAccountId(1L)
                .amount(new BigDecimal("0.1"))
                .build();
        String url = END_POINT + "/v1/mch/assets/transfer";
        String content = JSON.toJSONString(req);
        String s = HttpUtil.reqPostJson(url,  content, HEADERS);
        log.info("{}", s);
    }

    @Test
    public void testMerchantWithdraw() throws Exception{

        WithdrawReq req = WithdrawReq.builder()
                .withdrawId(IDS.uniqueID() + "")
                .accountId(1L)
                .addressBookId(1L)
                .amount(new BigDecimal("1"))
                .feeAmount(new BigDecimal("0.02"))
                .currencyCode("BTC")
                .password(getFundPassword())
                .validCode("111111")
                .validCodeType("email")
                .build();
        String url = END_POINT + "/v1/mch/withdrawWallet/withdraw";
        String content = JSON.toJSONString(req);
        String s = HttpUtil.reqPostJson(url,  content, HEADERS);
        log.info("{}", s);
    }

    private String getFundPassword(){
        return MD5Util.getMD5String("12345678");
    }

}
