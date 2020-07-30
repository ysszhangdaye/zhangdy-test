package com.zhangdy.test.mkpay;

import com.alibaba.fastjson.JSONObject;
import com.zhangdy.util.IDS;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;

public class Test {

    public static void main(String[] args){
        String accessSecurt = "226aa940f30740f9ad414949cee4ecf3";
        //LinkedHashMap<String, Object> json = JSON.parseObject(message,LinkedHashMap.class, Feature.OrderedField);
        //JSONObject jsonObject=new JSONObject(true);
        //jsonObject.putAll(json);
        long l = System.currentTimeMillis() + 300000;
        JSONObject trans = new JSONObject(true);
        trans.put("amount",0.1);
        trans.put("orderNo", IDS.uniqueID());
        trans.put("orderRemark","");
        trans.put("notifyUrl","http://zhangdy.51vip.biz/account/buyNotify");
        trans.put("accessKey","96525ad2b2904f50aa2a21894aa32de7");
        trans.put("timestamp", l);
        String transStr = trans.toString();
        System.out.println(transStr);
        Base64 base64 = new Base64();
        String transBase64Str = null;
        try{
            transBase64Str = base64.encodeToString(transStr.toString().getBytes("UTF-8"));
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        String sign = encodeMd5(transStr + accessSecurt);
        //System.out.println("transBase64Str=" + transBase64Str + ",sign =" + sign);
        System.out.println("URL = " + "http://pay.fanuy.vip/payment?channel=ALIPAY_QRCODE&trans=" + transBase64Str + "&sign=" + sign);
    }

    public static String encodeMd5(String srcStr) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(srcStr.getBytes());
            return new BigInteger(1, md5.digest()).toString(16);
        }catch (Exception err){
            return "";
        }
    }

}