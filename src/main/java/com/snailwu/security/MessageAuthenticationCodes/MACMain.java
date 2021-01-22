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
 * 1、HmacMD5
 * 2、HmacSHA1
 * 3、HmacSHA224
 * 4、HmacSHA256
 * 5、HmacSHA384
 * 6、HmacSHA512
 * 7、HmacSHA512/224
 * 8、HmacSHA512/256
 * 9、HmacSHA3-224
 * 10、HmacSHA3-256
 * 11、HmacSHA3-384
 * 12、HmacSHA3-512
 * 13、PBEWith{MAC} 如 PBEWithHmacMD5、PBEWithHmacSHA512 等等，与上面都匹配一遍。
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
        SecretKey secretKey = new SecretKeySpec(key.getBytes(UTF_8), "HmacMD5");

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
