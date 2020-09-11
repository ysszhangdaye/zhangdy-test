//package com.zhangdy.test.sign;
//
//import org.apache.commons.codec.binary.Hex;
//import org.apache.flink.table.expressions.E;
//
//import java.security.KeyFactory;
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.PrivateKey;
//import java.security.interfaces.DSAPrivateKey;
//import java.security.interfaces.DSAPublicKey;
//import java.security.spec.PKCS8EncodedKeySpec;
//
//public class ImoocDSAUtil {
//
//    private static final S
//
//    public static void main(String[] args) {
//        genKey();
//    }
//
//    public static void genKey(){
//        //1.初始化密钥
//        try {
//            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
//            keyPairGenerator.initialize(512);
//            KeyPair keyPair = keyPairGenerator.generateKeyPair();
//            DSAPublicKey dsaPublicKey = (DSAPublicKey) keyPair.getPublic();
//            DSAPrivateKey dsaPrivateKey = (DSAPrivateKey)keyPair.getPrivate();
//
//
//            System.out.println(Hex.encodeHexString(dsaPublicKey.getEncoded()));
//            System.out.println(Hex.encodeHexString(dsaPrivateKey.getEncoded()));
//
//
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//
//    }
//
//}
