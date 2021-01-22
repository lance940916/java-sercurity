package com.snailwu.security.Cipher;

import com.snailwu.security.KeyGenerator.KeyGeneratorMain;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * AES 对称加密。加速速度快，适合对接口的参数进行加解密，缺点就是密钥的传输比较麻烦。
 *
 * @author WuQinglong
 * @date 2021/1/19 14:25
 */
public class AESMain {

    public static void main(String[] args) throws Exception {
        // 数据
        String data = "1234567890";
        // 密钥算法
        String keyAlgorithm = "AES";
        // 加密算法
        String cipherAlgorithm = "AES/ECB/PKCS5PADDING";

        // 获取密钥，封装为 SecretKey
        String key = KeyGeneratorMain.generate(keyAlgorithm, null);
        byte[] keyBytes = Base64.getDecoder().decode(key.getBytes(UTF_8));
        SecretKey secretKey = new SecretKeySpec(keyBytes, keyAlgorithm);

        // 加密。对于 AES 来说，默认使用的加密模式是ECB，填充方式为PKCS5Padding
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        // 加密模式
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptBytes = cipher.doFinal(data.getBytes(UTF_8));
        // Base64 编码输出
        byte[] encodeBytes = Base64.getEncoder().encode(encryptBytes);
        String encodeStr = new String(encodeBytes, UTF_8);
        System.out.println("私钥=>公钥：加密：" + encodeStr);

        // 解密。必须与加密使用的算法对应！
        encodeBytes = Base64.getDecoder().decode(encodeStr.getBytes(UTF_8));
        cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptBytes = cipher.doFinal(encodeBytes);
        String decryptStr = new String(decryptBytes, UTF_8);
        System.out.println("私钥=>公钥：解密：" + decryptStr);
    }

}
