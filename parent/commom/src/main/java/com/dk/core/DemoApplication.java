package com.dk.core;

import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.Base64;

public class DemoApplication {

    public static void main(String[] args) throws Exception {
        System.out.println(System.currentTimeMillis());
        String a = org.apache.commons.codec.binary.Base64.encodeBase64String("1".getBytes());
      //  org.apache.commons.codec.binary.Base64.encodeBase64String()
        String b = org.apache.commons.codec.binary.Base64.encodeBase64String("2".getBytes());
        String c = org.apache.commons.codec.binary.Base64.encodeBase64String("3".getBytes());
        String d = org.apache.commons.codec.binary.Base64.encodeBase64String("4".getBytes());
        System.out.println(a+"|"+b+"|"+c+"|"+d);
       // eccTest();
    }

    private static void eccTest() throws Exception {
        KeyPair keyPair = ECCUtil.getKeyPair();
       // String publicKeyStr = ECCUtil.getPublicKey(keyPair);
        String privateKeyStr = ECCUtil.getPrivateKey(keyPair);
        String publicKeyStr = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEc3FiHd9izVZ-7ji7DR-SR8XZHbdhq3Tab7Aqjk31HX96LaczOUn3wVCWfHXTGlT5c5cd3I9crmXqMfPRkuzYTg==";

        ECPublicKey publicKey = ECCUtil.string2PublicKey(publicKeyStr);
        ECPrivateKey privateKey = ECCUtil.string2PrivateKey(privateKeyStr);

        System.out.println("私钥：" + privateKeyStr);

        System.out.println("公钥：" + publicKeyStr);
        String text = "DKCDPCA0000000001";
        byte[] bytes = text.getBytes("UTF-8");
        byte[] b = ECCUtil.publicEncrypt(bytes, publicKey);
        String str = Base64.getUrlEncoder().encodeToString(b);
        System.out.println("密文：" + str);
        System.out.println("原文长度：" + text.length() + " bytes:" + bytes.length + " 密文长度：" + b.length + " 密文base64长度：" + str.length());
        String outputStr = new String(ECCUtil.privateDecrypt(b, privateKey));
        System.out.println("原始文本：" + text);
        System.out.println("解密文本：" + outputStr);
    }

}
