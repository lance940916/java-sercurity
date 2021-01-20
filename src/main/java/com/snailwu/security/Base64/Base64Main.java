package com.snailwu.security.Base64;

import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Base64只是编码和解码，并不是加密解密
 *
 * @author WuQinglong
 * @date 2021/1/19 10:13
 */
public class Base64Main {

    public static void main(String[] args) {
        System.out.println(Base64.getEncoder().encodeToString("吴".getBytes(UTF_8)));
    }

}
