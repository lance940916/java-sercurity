package com.snailwu.security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Provider;
import java.security.Security;

/**
 * Java加密算法的提供商
 *
 * https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html
 *
 * @author WuQinglong
 * @date 2021/1/18 14:24
 */
public class SecurityProvider {

    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());

        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            System.out.println("提供商名称：" + provider.getName() + "，版本：" + provider.getVersion());
            System.out.println(provider.getInfo());
            System.out.println();
        }
    }

}
