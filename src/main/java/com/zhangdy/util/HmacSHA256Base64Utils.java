package com.zhangdy.util;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.management.RuntimeErrorException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HmacSHA256Base64Utils {



    public static final String SALT_ACCESS_PASSPHRASE = "b35517ba6a5d4fb5b8ae86d84cffbb64ab5ab48f8f774099bd89837a7298721c";

    public static String sign(String content) throws CloneNotSupportedException, InvalidKeyException, UnsupportedEncodingException {
        byte[] secretKeyBytes = SALT_ACCESS_PASSPHRASE.getBytes("UTF-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, "HmacSHA256");
        Mac mac = (Mac) MAC.clone();
        mac.init(secretKeySpec);
        return Base64.getEncoder().encodeToString(mac.doFinal(content.getBytes("UTF-8")));
    }





    public static Mac MAC;

    static {
        try {
            MAC = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeErrorException(new Error("Can't get Mac's instance."));
        }
    }
}
