package com.snailwu.security.SecureRandom;

import java.security.SecureRandom;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 用于生成安全场景下的随机数。
 * JDK11支持的有：
 * 1、DRBG（sun.security.provider.DRBG）
 * 2、SHAPRNG（java.security.SecureRandom）
 * 3、NATIVEPRNG（sun.security.provider.NativePRNG）
 * 4、NATIVEPRNGBLOCKING（sun.security.provider.NativePRNG.Blocking）
 * 5、NATIVEPRNGNONBLOCKING（sun.security.provider.NativePRNG.NonBlocking）
 *
 * @author WuQinglong
 * @date 2021/1/18 15:40
 */
public class RandomMain {

    public static void main(String[] args) throws Exception {
        // 默认情况下使用 new SecureRandom(); 即可，系统会自动选择算法。
//        SecureRandom secureRandom = new SecureRandom();
        // 使用 NATIVEPRNGNONBLOCKING 算法，想要使用其它算法，传入算法的字符串即可。
        SecureRandom secureRandom = SecureRandom.getInstance("NATIVEPRNGNONBLOCKING");
        // 种子相同随机数也相同
        secureRandom.setSeed("seed".getBytes(UTF_8));

        System.out.println("算法：" + secureRandom.getAlgorithm());
        System.out.println("提供商：" + secureRandom.getProvider().getName());
        System.out.println("随机数：" + secureRandom.nextLong());
    }

}
