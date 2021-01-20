package com.snailwu.security.KeyPairGenerator;

import java.security.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * JDK11原生支持如下非对称算法：
 * 1、RSA
 * 2、DSA
 * 3、X25519
 * 4、DIFFIEHELLMAN
 * 5、RSASSA-PSS
 * 6、X448
 * 7、XDH
 * 8、EC
 *
 * @author WuQinglong
 * @date 2021/1/19 11:00
 */
public class KeyPairGeneratorMain {

    public static final String PUBLIC_KEY = "publicKey";
    public static final String PRIVATE_KEY = "privateKey";

    public static Map<String, String> generate(String algorithm, Integer keySize) throws NoSuchAlgorithmException {
        System.out.println("公钥私钥生成算法：" + algorithm);
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        if (keySize != null) {
            keyPairGenerator.initialize(keySize);
        }
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey keyPairPublic = keyPair.getPublic();
        PrivateKey keyPairPrivate = keyPair.getPrivate();
        String publicKey = Base64.getEncoder().encodeToString(keyPairPublic.getEncoded());
        String privateKey = Base64.getEncoder().encodeToString(keyPairPrivate.getEncoded());
        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);
        Map<String, String> keyMap = new HashMap<>();
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Set<String> messageDigest = Security.getAlgorithms("KeyPairGenerator");
        System.out.println("Java支持的非对称加密算法：" + messageDigest);

        // RSA 主流的长度有：1024、2048、3072、4096。。。
        // JDK11 默认的是 2048
        generate("RSA", null);

        // DSA 算法
        generate("DSA", null);

        // X25519
        generate("X25519", null);
    }

}
