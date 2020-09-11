package com.zhangdy.test.sign;


import com.infra.tools.RsaUtils;
import com.infra.tools.ThreeDES;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.UUID;

/**
 * @author java小工匠
 */
public class JdkSignatureDsaUtils {
    public static final String DSA = "DSA";
    public static final String MD5withDSA = "SHAwithDSA";

    // 初始化密钥对
    public static KeyPair initKey() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(DSA);
            // 512 -65536 && 64 的倍数
            generator.initialize(1024);
            return generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // 获取公钥
    public static byte[] getPublicKey(KeyPair keyPair) {
        byte[] bytes = keyPair.getPublic().getEncoded();
        return bytes;
    }

    // 获取公钥
    public static String getPublicKeyStr(KeyPair keyPair) {
        byte[] bytes = keyPair.getPublic().getEncoded();
        return encodeHex(bytes);
    }

    // 获取私钥
    public static byte[] getPrivateKey(KeyPair keyPair) {
        byte[] bytes = keyPair.getPrivate().getEncoded();
        return bytes;
    }

    // 获取私钥
    public static String getPrivateKeyStr(KeyPair keyPair) {
        byte[] bytes = keyPair.getPrivate().getEncoded();
        return encodeHex(bytes);
    }

    // 签名
    public static byte[] sign(byte[] data, byte[] privateKey, String type) {
        try {
            // 还原使用
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey);
            KeyFactory keyFactory = KeyFactory.getInstance(DSA);
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            // 1、实例化Signature
            Signature signature = Signature.getInstance(type);
            // 2、初始化Signature
            signature.initSign(priKey);
            // 3、更新数据
            signature.update(data);
            // 4、签名
            return signature.sign();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 验证
    public static boolean verify(byte[] data, byte[] publicKey, byte[] sign, String type) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
            KeyFactory keyFactory = KeyFactory.getInstance(DSA);
            PublicKey pubKey = keyFactory.generatePublic(keySpec);
            // 1、实例化Signature
            Signature signature = Signature.getInstance(type);
            // 2、初始化Signature
            signature.initVerify(pubKey);
            // 3、更新数据
            signature.update(data);
            // 4、签名
            return signature.verify(sign);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 数据准16进制编码
    public static String encodeHex(final byte[] data) {
        return encodeHex(data, true);
    }

    // 数据转16进制编码
    public static String encodeHex(final byte[] data, final boolean toLowerCase) {
        final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        final char[] toDigits = toLowerCase ? DIGITS_LOWER : DIGITS_UPPER;
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return new String(out);
    }
    private static final String THREE_DES_KEY = "S2#%@^ADOWFM@$#$@!";

    public static void main(String[] args) throws Exception{
        String s = UUID.randomUUID().toString().replaceAll("-", "");


        String str = s + "&icncde";
        byte[] data = str.getBytes();
        // 初始化密钥度
        KeyPair keyPair = initKey();

        byte[] publicKeyArray = getPublicKey(keyPair);
        byte[] privateKeyArray = getPrivateKey(keyPair);

        String publicKey = RsaUtils.toHexString(publicKeyArray);
        String privateKey = RsaUtils.toHexString(privateKeyArray);

        // 签名
        String type = MD5withDSA;
        byte[] sign = sign(str.getBytes(), RsaUtils.toByteArray(privateKey), type);
        // 验证
        System.out.println( "签名:"+encodeHex(sign));

        String remark = str + "&" + RsaUtils.toHexString(sign);
        System.out.println("data -> " +remark);

        String s1 = ThreeDES.encryptDESCBC(remark, THREE_DES_KEY);
        System.out.println("3des -> " + s1 + " #### length -> " + s1.getBytes("utf-8").length);
        boolean b = verify(data, RsaUtils.toByteArray(publicKey), sign, type);
        System.out.println("验证:" + b);
    }
}