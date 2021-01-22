package com.snailwu.security.Signature;

import com.snailwu.security.KeyPairGenerator.KeyPairGeneratorMain;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * JDK11 原生支持的加签验签算法：
 * NONEwithRSA
 * ---
 * MD2withRSA
 * MD5withRSA
 * ---
 * SHA1withRSA
 * SHA224withRSA
 * SHA256withRSA
 * SHA384withRSA
 * SHA512withRSA
 * SHA512/224withRSA
 * SHA512/256withRSA
 * SHA3-224withRSA
 * SHA3-256withRSA
 * SHA3-384withRSA
 * SHA3-512withRSA
 * ---
 * RSASSA-PSS
 * ---
 * NONEwithDSA
 * ---
 * SHA1withDSA
 * SHA224withDSA
 * SHA256withDSA
 * SHA384withDSA
 * SHA512withDSA
 * SHA3-224withDSA
 * SHA3-256withDSA
 * SHA3-384withDSA
 * SHA3-512withDSA
 * ---
 * NONEwithECDSA
 * SHA1withECDSA
 * SHA224withECDSA
 * SHA256withECDSA
 * SHA384withECDSA
 * SHA512withECDSA
 * SHA3-224withECDSA
 * SHA3-256withECDSA
 * SHA3-384withECDSA
 * SHA3-512withECDSA
 * ---
 * NONEwithDSAinP1363Format
 * SHA1withDSAinP1363Format
 * SHA224withDSAinP1363Format
 * SHA256withDSAinP1363Format
 * ---
 * NONEwithECDSAinP1363Format
 * SHA1withECDSAinP1363Format
 * SHA224withECDSAinP1363Format
 * SHA256withECDSAinP1363Format
 * SHA384withECDSAinP1363Format
 * SHA512withECDSAinP1363Format
 *
 * @author WuQinglong
 * @date 2021/1/18 19:17
 */
public class SignatureMain {
    private static final String KEY_ALGORITHM = "RSA";
    private static final String SIGN_ALGORITHM = "SHA1WITHRSA";

    public static void main(String[] args) throws Exception {
        // 数据
        String data = "admin";

        // 获取公钥私钥
        Map<String, String> rsaMap = KeyPairGeneratorMain.generate(KEY_ALGORITHM, null);
        String publicKeyStr = rsaMap.get(KeyPairGeneratorMain.PUBLIC_KEY);
        String privateKeyStr = rsaMap.get(KeyPairGeneratorMain.PRIVATE_KEY);

        // 先要Base64解码，然后获取 PrivateKey 实例
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyStr.getBytes(UTF_8));
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        // 私钥加签
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data.getBytes(UTF_8));
        byte[] signedBytes = signature.sign();
        // Base64 编码签名
        byte[] encodeBytes = Base64.getEncoder().encode(signedBytes);
        String signStr = new String(encodeBytes, UTF_8);
        System.out.println("签名：" + signStr);

        // 先要Base64解码，然后获取 PublicKey 实例
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyStr.getBytes(UTF_8));
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        // 公钥验签
        signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(data.getBytes(UTF_8));
        // 验证签名
        signedBytes = Base64.getDecoder().decode(signStr.getBytes(UTF_8));
        boolean verify = signature.verify(signedBytes);
        System.out.println("验签结果：" + verify);

    }

}
