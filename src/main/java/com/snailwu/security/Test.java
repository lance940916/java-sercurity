package com.snailwu.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author WuQinglong
 * @date 2021/1/18 15:37
 */
public class Test {

    public static void main(String[] args) throws Exception {
        String src = "1234567890";
        String key = "I6a0Eh6DM/gLYd5pIQs6Dw==";

        SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encodeBytes = cipher.doFinal(src.getBytes(UTF_8));
        System.out.println("16进制：" + new BigInteger(1, encodeBytes).toString(16).toUpperCase());
        // 91FB3F85C38DAFD782282A96E8B27047
        // AES 91FB3F85C38DAFD782282A96E8B27047

    }

}
