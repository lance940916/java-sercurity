package com.snailwu.security.MessageAuthenticationCodes;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Java原生支持的算法如下：
 * 1、PBEWITHHMACSHA512
 * 2、PBEWITHHMACSHA224
 * 3、HMACSHA384
 * 4、PBEWITHHMACSHA256
 * 5、HMACSHA256
 * 6、PBEWITHHMACSHA384
 * 7、HMACPBESHA1
 * 8、HMACSHA512/256
 * 9、HMACSHA224
 * 10、HMACMD5
 * 11、HMACSHA512/224
 * 12、PBEWITHHMACSHA1
 * 13、SSLMACSHA1
 * 14、HMACSHA512
 * 15、SSLMACMD5
 * 16、HMACSHA1
 *
 * @author WuQinglong
 * @date 2021/1/19 17:35
 */
public class MACMain {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        // 消息
        String str = "admin";
        // 密钥
        String key = "wu";
        SecretKey secretKey = new SecretKeySpec(key.getBytes(UTF_8), "HMACMD5");

        Mac hmacmd5 = Mac.getInstance("HMACMD5");
        hmacmd5.init(secretKey);
        hmacmd5.update(str.getBytes(UTF_8));
        byte[] resultBytes = hmacmd5.doFinal();
        String result = new BigInteger(1, resultBytes).toString(16);
        System.out.println(result);

    }

}
