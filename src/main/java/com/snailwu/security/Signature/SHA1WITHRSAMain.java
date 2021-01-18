package com.snailwu.security.Signature;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author WuQinglong
 * @date 2021/1/18 19:17
 */
public class SHA1WITHRSAMain {

    private static String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKzprya/Eofm7WzaVgXUEwsSK9KbviZZ4BviNnUFi58SAeLPKBtTefMEgQZ2UORA/VUWSnaUNC5ZeN0NitUO/bsCAwEAAQ==";
    private static String privateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEArOmvJr8Sh+btbNpWBdQTCxIr0pu+JlngG+I2dQWLnxIB4s8oG1N58wSBBnZQ5ED9VRZKdpQ0Lll43Q2K1Q79uwIDAQABAkBpCulpi2fxgXs4xoa9sbED570Ri6V/XNgFeG4GtyCzt9gmDo3NUj0WPY8MWOtA7j3Oqt0HTN9nO5fj+Yzj9w8RAiEA008RBe1bNP2ebBsriMk1+OO3p4Bc30f5RlI7gZd4g+kCIQDRe7waPuS5zXUeqUgE8QAHsnGqQkPdoMbdi8uEUkyiAwIgRXG8pHgG+3lGMT0Up5WI2UztNG0pGHAs6gFocQR4pNECIQC8yi2o3ZuW09JEcr1ELsofJbOIhLXS7A2tJkqhEuabbwIgAM9/ewBM56nSQ5HqxhBnoP4f89Bev2tlbkiCKeapgM4=";

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidKeyException, SignatureException {
        String params = "a=1;b=2;c=3";

        // 获取 PrivateKey
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        // 加签
        Signature signature = Signature.getInstance("SHA1WITHRSA");
        signature.initSign(privateKey);
        signature.update(params.getBytes(UTF_8));
        byte[] signed = signature.sign();
        String signStr = new BigInteger(1, signed).toString(16);
        System.out.println("签名字符串：" + signStr);

        // 获取 publicKey
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
        keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        // 验签
        signature = Signature.getInstance("SHA1WITHRSA");
        signature.initVerify(publicKey);
        signature.update(params.getBytes(UTF_8));
        boolean verify = signature.verify(signStr.getBytes(UTF_8));
        System.out.println("验签结果：" + verify);

    }

}
