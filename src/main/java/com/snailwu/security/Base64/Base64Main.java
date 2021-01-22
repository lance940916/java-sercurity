package com.snailwu.security.Base64;

import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Base64只是编码和解码，并不是加密解密。
 * 编码原理参考：https://www.cnblogs.com/wuqinglong/p/14302285.html
 *
 * @author WuQinglong
 * @date 2021/1/19 10:13
 */
public class Base64Main {

    public static void main(String[] args) {
        String str = "张三丰";

        // 编码，明确指定统一使用 UTF-8 编码。
        byte[] encodeBytes = Base64.getEncoder().encode(str.getBytes(UTF_8));
        String encodeStr = new String(encodeBytes, UTF_8);
        System.out.println("编码结果：" + encodeStr);

        // 解码，明确指定统一使用 UTF-8 编码。
        byte[] decodeBytes = Base64.getDecoder().decode(encodeStr.getBytes(UTF_8));
        String decodeStr = new String(decodeBytes, UTF_8);
        System.out.println("解码结果：" + decodeStr);
    }

}
