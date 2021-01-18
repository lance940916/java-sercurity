package com.snailwu.security.SecureRandom;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author WuQinglong
 * @date 2021/1/18 15:40
 */
public class RandomMain {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        /*
            算法对应的实现类：
            1、DRBG // sun.security.provider.DRBG
            2、SHA1PRNG // java.security.SecureRandom
            3、NATIVEPRNGBLOCKING // sun.security.provider.NativePRNG.Blocking
            4、NATIVEPRNGNONBLOCKING // sun.security.provider.NativePRNG.NonBlocking
            5、NATIVEPRNG // sun.security.provider.NativePRNG
        */
        SecureRandom secureRandom = SecureRandom.getInstance("NATIVEPRNG");
        System.out.println("算法：" + secureRandom.getAlgorithm());
        System.out.println("提供商：" + secureRandom.getProvider().getName());
        System.out.println(secureRandom.nextInt());

    }

}
