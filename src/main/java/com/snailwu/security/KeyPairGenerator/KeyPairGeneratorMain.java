package com.snailwu.security.KeyPairGenerator;

import java.security.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * JDK11原生支持如下非对称算法：
 * 1、DiffieHellman
 * 2、DSA
 * 3、RSA
 * 4、RSASSA-PSS
 * 5、EC
 * 6、XDH
 * 7、X25519
 * 8、X448
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
        // 通常使用 Base64 编码一下
        Base64.Encoder base64Encoder = Base64.getEncoder();
        byte[] publicKeyBytes = base64Encoder.encode(keyPairPublic.getEncoded());
        byte[] privateKeyBytes = base64Encoder.encode(keyPairPrivate.getEncoded());
        String publicKey = new String(publicKeyBytes, UTF_8);
        String privateKey = new String(privateKeyBytes, UTF_8);
        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);

        // 封装公钥私钥
        Map<String, String> keyMap = new HashMap<>();
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // RSA 主流的长度有：1024、2048、3072、4096。。。
        // JDK11 默认的是 2048
        generate("RSA", null);

        // DSA 算法
        generate("DSA", null);

        // X25519
        generate("X25519", null);
    }

}
