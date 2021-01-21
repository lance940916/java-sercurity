package com.snailwu.security.Cipher;

import com.snailwu.security.KeyGenerator.KeyGeneratorMain;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author WuQinglong
 * @date 2021/1/19 14:25
 */
public class AESMain {

    private static final String SOURCE = "1234567890";
    private static final String ALGORITHM = "AES";

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException,
            IOException, InvalidAlgorithmParameterException, InvalidParameterSpecException {
        // 获取密钥
        String key = KeyGeneratorMain.generate("AES", null);

        SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), ALGORITHM);

        // 加密，默认使用的模式是ECB，填充方式为PKCS5Padding
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encodeBytes = cipher.doFinal(SOURCE.getBytes(UTF_8));
        System.out.println(new BigInteger(1, encodeBytes).toString(16));
        String encodeStr = Base64.getEncoder().encodeToString(encodeBytes);
        System.out.println("私钥=>公钥：加密：" + encodeStr);

        // 解密
        cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodeBytes = cipher.doFinal(Base64.getDecoder().decode(encodeStr));
        String decodeStr = new String(decodeBytes, UTF_8);
        System.out.println("私钥=>公钥：解密：" + decodeStr);

    }

}
