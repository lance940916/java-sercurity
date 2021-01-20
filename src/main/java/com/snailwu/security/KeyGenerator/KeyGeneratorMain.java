package com.snailwu.security.KeyGenerator;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 对称加密算法
 * 加密和解密用到的密钥是相同的，这种加密解密速度非常快，适合经常发送数据的场合。缺点是密钥的传输比较麻烦。
 *
 * Java原生支持的算法如下：
 * 1、AES
 * 2、DES
 * 3、HMACMD5
 * 4、HMACSHA1
 * 5、HMACSHA224
 * 6、HMACSHA256
 * 7、HMACSHA384
 * 8、HMACSHA512
 * 9、DESEDE
 * 10、RC2
 * 11、CHACHA20
 * 12、BLOWFISH
 * 13、SUNTLSPRF
 * 14、SUNTLSRSAPREMASTERSECRET
 * 15、SUNTLSKEYMATERIAL
 * 16、SUNTLSMASTERSECRET
 * 17、SUNTLS12PRF
 * 18、ARCFOUR
 *
 * @author WuQinglong
 * @date 2021/1/19 11:10
 */
public class KeyGeneratorMain {

    public static String generate(String algorithm, Integer keySize) throws NoSuchAlgorithmException {
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

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 128位，192位，256位。AES128 性能高，安全性低；AES256 性能低，安全性高，成正比。
        // AES 密钥，填充，模式
        generate("AES", 256);
//        generate("DES", null);
    }

}
