package com.snailwu.security.Signature;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author WuQinglong
 * @date 2021/1/18 17:42
 */
public class RSAMain {

    private static String src = "admin";

    private static RSAPublicKey rsaPublicKey;
    private static RSAPrivateKey rsaPrivateKey;

    static {
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            // 64的整数倍
            keyPairGenerator.initialize(512);
//            keyPairGenerator.initialize(512, SecureRandom.getInstance("DRBG"));
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            System.out.println("公钥：" + Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded()));
            System.out.println("私钥：" + Base64.getEncoder().encodeToString(rsaPrivateKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 私钥加密，公钥解密
     */
    private static void privateKeyEncode2PublicKeyDecode() throws NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 私钥加密
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(src.getBytes(StandardCharsets.UTF_8));
        System.out.println("私钥加密-公钥解密。加密：" + Base64.getEncoder().encodeToString(result));

        // 公钥解密
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
        keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        result = cipher.doFinal(result);
        System.out.println("私钥加密-公钥解密。解密：" + new String(result, StandardCharsets.UTF_8));
    }

    /**
     * 公钥加密，私钥解密
     */
    private static void publicKeyEncode2PrivateKeyDecode() throws NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 公钥加密
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(src.getBytes(StandardCharsets.UTF_8));
        System.out.println("公钥加密-私钥解密。加密：" + Base64.getEncoder().encodeToString(result));

        // 私钥解密
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
        keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        result = cipher.doFinal(result);
        System.out.println("公钥加密-私钥解密。解密：" + new String(result, StandardCharsets.UTF_8));
    }

    public static void main(String[] args) throws Exception {
        privateKeyEncode2PublicKeyDecode();
        publicKeyEncode2PrivateKeyDecode();
    }

}
