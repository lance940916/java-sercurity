package com.snailwu.security.MessageDigest;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 一种被广泛使用的密码散列函数，可以产生出一个128位（16字节）的散列值（hash value），用于确保信息传输完整一致。
 * <p>
 * JDK11支持的有：
 * 1、MD2
 * 2、MD5
 *
 * @author WuQinglong
 * @date 2021/1/18 16:18
 */
public class MD5Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 数据
        String data = "admin";

        // 计算过程是不可逆的，因为计算中会丢失大量的数据。
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(data.getBytes(UTF_8));
        byte[] result = messageDigest.digest();

        // 通常转为 16 进制进行显示。
        String hexResult = new BigInteger(1, result).toString(16);
        System.out.println("摘要结果：" + hexResult);
    }

}
