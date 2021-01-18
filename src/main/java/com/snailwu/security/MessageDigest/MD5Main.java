package com.snailwu.security.MessageDigest;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 一种被广泛使用的密码散列函数，可以产生出一个128位（16字节）的散列值（hash value），用于确保信息传输完整一致。
 *
 * Java支持的有：
 * 1、MD2
 * 2、MD5
 *
 * @author WuQinglong
 * @date 2021/1/18 16:18
 */
public class MD5Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String source = "admin";

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(source.getBytes(StandardCharsets.UTF_8));
        byte[] result = messageDigest.digest();
        String encodeResult= new BigInteger(1, result).toString(16);
        System.out.println(encodeResult);

    }

}
