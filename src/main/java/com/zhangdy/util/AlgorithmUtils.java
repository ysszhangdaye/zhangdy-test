package com.zhangdy.util;


import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.net.URLEncoder;

public class AlgorithmUtils {


    public static void main(String[] args) throws Exception {
        String key ="abcdefg123iqwkslcm3491ka";
        String data = "{\"name\": \"zhangsan\"}";
        System.out.println("key:" + key);
        System.out.println("明文：" + data);
        String s = encryptWith3DES(data, key);
        System.out.println("密文：" + URLEncoder.encode( s, "UTF-8" ));
        System.out.println("密文：" + s);


        System.out.println(decryptWith3DES("PlxpQx+Q79UUzc74jfsK4RIm4MwCeB/mlWUWqJg2wCZvxPJ7gwIahQ==",key ));
    }




    public static final String ALGORTHM_3DES_SECRETKEY = "DESede";
    public static final String ALGORTHM_3DES_TRANSFORMATION = "DESede/ECB/PKCS5Padding";
    public static final String UTF_8 = "utf-8";
    public static String encryptWith3DES(String plainData, String token) throws Exception {
        final DESedeKeySpec dks = new DESedeKeySpec(token.getBytes(UTF_8));
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORTHM_3DES_SECRETKEY);
        final SecretKey securekey = keyFactory.generateSecret(dks);
        final Cipher cipher = Cipher.getInstance(ALGORTHM_3DES_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, securekey);
        final byte[] b = cipher.doFinal(plainData.getBytes());
        return new String(Base64.encodeBase64(b), UTF_8);
    }

    /**
     * 3DESECB解密,key必须是长度大于等于 3*8 = 24 位哈
     */
    public static String decryptWith3DES(String encryptedData, String token) throws Exception {
        // --通过base64,将字符串转成byte数组
        // final BASE64Decoder decoder = new BASE64Decoder();
        final byte[] bytesrc = Base64.decodeBase64(encryptedData);

        // --解密的key
        final DESedeKeySpec dks = new DESedeKeySpec(token.getBytes(UTF_8));
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORTHM_3DES_SECRETKEY);
        final SecretKey securekey = keyFactory.generateSecret(dks);

        // --Chipher对象解密
        final Cipher cipher = Cipher.getInstance(ALGORTHM_3DES_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        final byte[] retByte = cipher.doFinal(bytesrc);

        return new String(retByte);
    }

}
