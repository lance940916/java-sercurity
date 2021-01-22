package com.snailwu.security.MessageAuthenticationCodes;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 与MessageDigests一样，它们也会生成散列值，但是首先使用密钥初始化以保护消息的完整性。
 * <p>
 * Java原生支持的算法如下：
 * 1、HMACMD5
 * 2、HMACSHA1
 * 3、HMACSHA224
 * 4、HMACSHA256
 * 5、HMACSHA384
 * 6、HMACSHA512
 * 7、HMACSHA512/224
 * 8、HMACSHA512/256
 * 9、HMACSHA3-224
 * 10、HMACSHA3-256
 * 11、HMACSHA3-384
 * 12、HMACSHA3-512
 * 13、PBEWITH{MAC} 如 PBEWITHHMACMD5、PBEWITHHMACSHA512 等等，与上面都匹配一遍。
 *
 * @author WuQinglong
 * @date 2021/1/19 17:35
 */
public class MACMain {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        // 数据
        String data = "admin";

        // 密钥，不能泄露
        String key = "0123456789";
        SecretKey secretKey = new SecretKeySpec(key.getBytes(UTF_8), "HMACMD5");

        // 计算摘要
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        mac.update(data.getBytes(UTF_8));
        byte[] resultBytes = mac.doFinal();
        // 转 16 进制显示
        String hexResult = new BigInteger(1, resultBytes).toString(16);
        System.out.println("摘要结果：" + hexResult);
    }

}
