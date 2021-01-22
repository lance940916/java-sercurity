package com.snailwu.security.KeyGenerator;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 对称加密算法
 * 加密和解密用到的密钥是相同的，这种加密解密速度非常快，适合经常发送数据的场合。缺点是密钥的传输比较麻烦。
 * <p>
 * Java原生支持的算法如下：
 * 1、AES
 * 2、ARDFOUR（RC4）
 * 3、Blowfish
 * 4、ChaCha20
 * 5、DES
 * 6、DESede
 * 7、HmacMD5
 * 8、HmacSHA1
 * 9、HmacSHA224
 * 10、HmacSHA256
 * 11、HmacSHA384
 * 12、HmacSHA512
 * 13、RC2
 *
 * @author WuQinglong
 * @date 2021/1/19 11:10
 */
public class KeyGeneratorMain {

    public static String generate(String algorithm, Integer keySize) throws Exception {
        System.out.println("对称密钥生成算法：" + algorithm);
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        if (keySize != null) {
            keyGenerator.init(keySize);
        }
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] encode = Base64.getEncoder().encode(secretKey.getEncoded());
        String key = new String(encode, StandardCharsets.UTF_8);
        System.out.println("密钥：" + key);
        return key;
    }

    public static void main(String[] args) throws Exception {
        // 对于 AES 来说，keySize 可选值：128位，192位，256位。
        // 对于 AES 来说,，JDK 默认的 keySize 是 128。
        generate("AES", null);
        generate("AES", 128);
        generate("AES", 256);

        // 对于其它算法，有各自的密钥长度规定。
    }

}
