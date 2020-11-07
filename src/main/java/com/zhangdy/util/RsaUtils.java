package com.zhangdy.util;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import scala.math.Ordering;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 经典的数字签名算法RSA 数字签名
 */
public class RsaUtils {

    public static final String KEY_ALGORTHM = "RSA";//
    public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";

    public static final String PUBLIC_KEY = "RSAPublicKey";//公钥
    public static final String PRIVATE_KEY = "RSAPrivateKey";//私钥

    /**
     * 初始化密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws Exception {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORTHM);
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);

        return keyMap;
    }


    /**
     * 取得公钥，并转化为String类型
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * 取得私钥，并转化为String类型
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * 用私钥加密
     *
     * @param data 加密数据
     * @param key  密钥
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
        //解密密钥
        byte[] keyBytes = Base64.decodeBase64(key);
        //取私钥
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        //对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 用私钥解密
     *
     * @param data 加密数据
     * @param key  密钥
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {
        //对私钥解密
        byte[] keyBytes = Base64.decodeBase64(key);

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        //对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 用公钥加密
     *
     * @param data 加密数据
     * @param key  密钥
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
        //对公钥解密
        byte[] keyBytes = Base64.decodeBase64(key);
        //取公钥
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
        Key publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        //对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * 用公钥解密
     *
     * @param data 加密数据
     * @param key  密钥
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {
        //对私钥解密
        byte[] keyBytes = Base64.decodeBase64(key);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
        Key publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        //对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data       //加密数据
     * @param privateKey //私钥
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        //解密私钥
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        //构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        //指定加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
        //取私钥匙对象
        PrivateKey privateKey2 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        //用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey2);
        signature.update(data);

        return Base64.encodeBase64String(signature.sign());
    }

    /**
     * 校验数字签名
     *
     * @param data      加密数据
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        //解密公钥
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        //构造X509EncodedKeySpec对象
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        //指定加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
        //取公钥匙对象
        PublicKey publicKey2 = keyFactory.generatePublic(x509EncodedKeySpec);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicKey2);
        signature.update(data);
        //验证签名是否正常
        return signature.verify(Base64.decodeBase64(sign));
    }


    public static boolean verifyData(String requestXml, String sign, String publicKeyString) {
        try {
            return verify(requestXml.getBytes(), publicKeyString, sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String signData(String xml, String privateKeyString) {
        try {
            return sign(xml.getBytes(), privateKeyString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encode(String xml, String privateKeyString) {
        try {
//			return utils.Base64Util.ENCODER.encode(RSACoder.encryptByPrivateKey(xml.getBytes(), privateKeyString));
            return Base64.encodeBase64String(encryptByPrivateKey(xml.getBytes(), privateKeyString));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decode(String xml, String publicKeyString) {
        try {
//			return utils.Base64Util.ENCODER.encode(RSACoder.decryptByPublicKey(xml.getBytes(), publicKeyString));
            return Base64.encodeBase64String(decryptByPublicKey(xml.getBytes(), publicKeyString));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void test() {
        try {
            Map<String, Object> keyMap = RsaUtils.initKey();
            System.out.println("公钥:" + RsaUtils.getPublicKey(keyMap));     //公钥
            System.out.println("私钥:" + RsaUtils.getPrivateKey(keyMap));    //私钥

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static byte[] toByteArray(String hexString) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(hexString))
            throw new IllegalArgumentException("this hexString must not be empty");

        hexString = new String(hexString.getBytes(), "utf8").toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }


    public static String toHexString(byte[] byteArray) {
        if (byteArray == null || byteArray.length < 1)
            throw new IllegalArgumentException("this byteArray must not be null or empty");

        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) < 0x10)//0~F前面不零
                hexString.append("0");
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString().toLowerCase();
    }
    public static void genkey(){
        try {
            Map<String, Object> keyMap = RsaUtils.initKey();
            System.out.println("公钥:"+ RsaUtils.getPublicKey(keyMap));     //公钥
            System.out.println("私钥:"+ RsaUtils.getPrivateKey(keyMap));    //私钥

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        15616285817
//        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfC7SocuEOgIEeTUhl+vwAAyiLvcHQRd97y6qMukoqbaYFfLep+vN/Q5WxgVOUlWpOejnb7kXnVgtagWOgekuvyj42+y8AvFKdkQcnmzHCwiGETIgIcDn1+BtEtUkzpxCIdyUHM00v3kRmld/7XiHwUlT0Ql3FzOUuxqaiQYWh4wIDAQAB";
//        String data = "2913ebbab66e543d168f22512a82520364d16a14ea1184df7d21e1d64e4a4bad3855e405b2ffafa74b537c660d30207602c501e9098f5ce0961497b9c123bc8eee694cbca785190eb4b973f03ae1d879bedc13f3f938f995c362623b40dfe89888222b3dc5f6daf20dbaace993fe6a81723d12dfd202d23d84c1ad6f760ce946";
//        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ8LtKhy4Q6AgR5NSGX6/AADKIu9wdBF33vLqoy6SiptpgV8t6n6839DlbGBU5SVak56OdvuRedWC1qBY6B6S6/KPjb7LwC8Up2RByebMcLCIYRMiAhwOfX4G0S1STOnEIh3JQczTS/eRGaV3/teIfBSVPRCXcXM5S7GpqJBhaHjAgMBAAECgYAcxHNC3LSUeO3h2zyzJXibT/bvf70kvN61d5s7pR5xGjAjYfGej0Ony0OyPaAuifAWjckVXL3MICYhkrHAfx5dPc2v8yWVZRmCbi/PILOfzAKDFAcloCHTuBps11NXUP65lf2TdEq6t283o0IvBd7CKLgY25AjyJIhWWxpB35dAQJBAOiYeRON1OIvjYkmIVOaod6rKJ6vvKSKyDeaZ/DIU0t/JkZIg5hQ1ZuEELKKl33brn7g1jve5hc185A5RSa8eaMCQQCvDKGCcL29kUGJGtSY/ckWNNr0aoJR2dsVaoL5+K14VT9czlDbgsm8d+EK/OIgKQ8ABbosIzvz7SCdqWQalTrBAkEAmXycUUID3CqDHKDz1wawkI6j5GuVcYM/cinLM0IObUB/kluhsy6Mdu5kUl1QcLY55PIbjTCe52wryN1x+4jePwJBAJUeiHPLQQe9NvvqHFOcVnIRYri2BwBdOxH6Y0s4+eW5kTmpFB57QwnHgbFPjf5hm8KkHl29QjRgu9kzVPCEUcECQDG5L067rbCw0jFCX02wb70ldWmDqzRR02WuQ/+T+cT0/zEgqc+g+TbvomX1lqY+cbGs1WVNiWTZemA/an+Tw1o=";
//
//        byte[] bytes1 = encryptByPublicKey ("zhangsan".getBytes(), publicKey);
//        System.out.println(toHexString(bytes1));
//
//        byte[] bytes = decryptByPrivateKey(toByteArray(toHexString(bytes1)), privateKey);
//        System.out.println(new String(bytes));
//
//
////        genkey();
//
//
//        String publicKey1 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCkjfpZAp8dn1WZmInnJhaF1myPwsf1PbKB4TNYIk9tqUzEtwbR5CZJE0vH09/ITLc8925l8XvTijaghxTaBlfXh/rvbHlWhb2ZO421F9c9SnVSjIJ61fLQazTArGmwqbtEeM4Mg4F4Y2xcX/G0WUNnJD0qjZ4fhdIOYNZAGEZ0+wIDAQAB";
//        String privateKey1 ="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKSN+lkCnx2fVZmYiecmFoXWbI/Cx/U9soHhM1giT22pTMS3BtHkJkkTS8fT38hMtzz3bmXxe9OKNqCHFNoGV9eH+u9seVaFvZk7jbUX1z1KdVKMgnrV8tBrNMCsabCpu0R4zgyDgXhjbFxf8bRZQ2ckPSqNnh+F0g5g1kAYRnT7AgMBAAECgYBl5VzunUb2SuHwuJo8PkOYIM0cwFqdq9TLxC3NF4HjwAA/l65rbkGe4on8dWCPYaHd6MG98qjgtydYhA0PbPobA9bq1Nlqkn839avx4P9ueH3PJ8DIf8STh8BIl08HzZyTo9P4iQtbG/ouzAPbSTM4u378hVM7XS+zfNdhMykk0QJBAPg4oEvs8w4sZk6Nzri2Z/2cZW40MEyyhLPHj60ggtqTn/0fzhAD43cmDabsdeqTvpPw6FtciDykB8U8krpfGr8CQQCptiFHzyVOc5kQEupqX7RIkZHng1d26aaKRgPlYv9tWZIJDNoulI4m8gzTbCxSNSp7rzWKQeOwNiJI61sRASDFAkEAiA3xvuwn5XLsuM39qz1ADsC00A0HFGMdXbVayVhyoQAdx46xUhHvkhVnkkE1+Nf2s/xuP/WeLy7xm2iyPDl43QJAPc1fjUD4OvuJz/qzdw1nH84zLfX8kgdAaLZakNxeXDn0HmuZwMDEi5Y16YBJG57U27nQa4rXGcJ89cUPor+gaQJBAJ8Y0t0qBS9MLyCRA5C0gk9zLdl9pN4sRkC1J3sfcbbJHwpJM92S0LHPrSaAgZVsHZzxb7yIpVkQ+FI1Nk83VE0=";
//        String data1 = "{\"appDeviceId\":\"ffffffff-c369-b973-ffff-ffffca01fdf4\",\"appNotifyId\":\"ffffffff-c369-b973-ffff-ffffca01fdf4\"}";
//
//        byte[] bytes12 = encryptByPublicKey (data1.getBytes(), publicKey1);
//        System.out.println("{\"body\":\"" + toHexString(bytes12) + "\"}");
////
//        String body = "8cc5201d6c2b632b54f9af98c269513f6b8b6c3b63ca2d2da3f9d796fc81b10ad0137fab18c69119a52304b5cbf4e70bac84216afdada7c7c2cd89d6fa369afefee7fbafbc2b5e5cce37879723539bcd5af1d84083d3e1a6c4fea71e0e655e7b9698d4595b71df5684ec2f0e72bdb9e04601097af9e556e54bf5fc3872997000";
//
//
//        byte[] bytes2 = decryptByPrivateKey(toByteArray(body), privateKey1);
//        System.out.println(new String(bytes2));
//
//        byte[] bytes3 = decryptByPrivateKey(toByteArray(toHexString(bytes12)), privateKey1);
//        System.out.println(new String(bytes3));



        WebSocketReqBody webSocketReqBody = new WebSocketReqBody();
        webSocketReqBody.channel = "collection";
        webSocketReqBody.deviceId = "zhangdaye";
        webSocketReqBody.walletId = "zhangdaye";
        System.out.println(JSON.toJSONString(webSocketReqBody));
//        byte[] bytes12 = encryptByPublicKey (JSON.toJSONString(webSocketReqBody).getBytes(), publicKey1);
//        System.out.println("{\"body\":\"" + toHexString(bytes12) + "\"}");
//
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCyuDgvdj8DmRG4PvGp/pNjv5byOU5UzAMHg4Q6f1f+gJX7FvlaErCC4LUqA1B368UnkXJvdz0V9dgK091+6xTygECuomazrJIiZBZCP+OQEc12K5AWKHDRnbC4wqCb9O/FO+2GVE+lrfaA4xuZB9CF9T5NkgSu+NqNRFHfRW6YcwIDAQAB";
        String privateKey  = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALK4OC92PwOZEbg+8an+k2O/lvI5TlTMAweDhDp/V/6AlfsW+VoSsILgtSoDUHfrxSeRcm93PRX12ArT3X7rFPKAQK6iZrOskiJkFkI/45ARzXYrkBYocNGdsLjCoJv078U77YZUT6Wt9oDjG5kH0IX1Pk2SBK742o1EUd9FbphzAgMBAAECgYAtmz0bP32NGX6gGc/ppYFPLxEB9wW6NeWAAVu44c4+r+mWDaLMDYDlpRoWAoqCT7QcCM2/DBqWhAoBmVcokCzj18o3gMhnGDzOGXxY42LgeYBCjEBYW7GWBhHYca50S5Abl3jApL1N4wkbJNOG9teV5wT9BoXaZL6sVm8hupWuwQJBAOX4MXfs/ZCXFQoI4k2tKEPmM6Bg1Z96japGtvLLQ19SoGVDSoSuw8gubl61n5GCJihINFVods3cJGYN1K5TGesCQQDG8vZaqhWeCIxOcUPI4uQIbWmpU41BO40As+8FjGQ/lJWw0eiT1rlbyTPL35RGYsCQe9KlkxULMwzif60gwpGZAkATzggUl+hOZ3R8ZL94iQ0Kq+yOR0ieb2N74pMm3bJzpx4BGoZ0XsilmLoQlfukAF1g4rb/E/wHRoor78z9JlL5AkEAua+rH5P/+TAhRFWS3rxl+TelltTU0j2yrSVDwfn21yDnVdGVMecHuhNgtLhIvCkPccsgIEnXKkzLiduAy0LOgQJBAI2Fg5jzT7I+Lwcyck+prvkabn5MB5DkZshqqU4ZybDwzffLXOgPWo1hVrNCFOy0+SQGkoo03oBCLEx72ifYWP8=";

        Map<String, String> map = new HashMap<>();
        map.put("appDeviceId", "ffffffff-c369-b973-ffff-ffffca01fdf4");
        map.put("appNotifyId", "ffffffff-c369-b973-ffff-ffffca01fdf4");

        String data = "{\"appDeviceId\":\"ffffffff-c369-b973-ffff-ffffca01fdf4\",\"appNotifyId\":\"ffffffff-c369-b973-ffff-ffffca01fdf4\"}";

        System.out.println(data);
        System.out.println(JSON.toJSONString(map));

        byte[] bytes1 = encryptByPublicKey (data.getBytes("utf8"), publicKey);
        System.out.println(toHexString(bytes1));

//
//        bytes1 = encryptByPublicKey (JSON.toJSONString(map).getBytes("UTF-8"), publicKey);
//        System.out.println(toHexString(bytes1));

        String e = "a81f947946d09fab2c0ca0212ae8587330d23f7b4bd65e5653eac4b6eb62b1b8d7c557e1aeb016938faa89da0a5be2ebb7065ed497ed24eb8d530f1e5dc90013b6ea4bf245ba58a8e1ff01bfe55135802563451e7246ebcb8a120fddaad485396595091701dd683e31e73d5dded43b9f2b58d3259070a1c6155538a9fdce1cb8";

        byte[] bytes = decryptByPrivateKey(toByteArray(e), privateKey);
        System.out.println(new String(bytes));



    }

    @Getter
    @Setter
    public static class WebSocketReqBody{
        private String channel;
        private String deviceId;
        private String walletId;
        private String code;
    }

}
