package com.snailwu.security;

import java.security.Provider;
import java.security.Security;

/**
 * Java加密算法的提供商
 *
 * @author WuQinglong
 * @date 2021/1/18 14:24
 */
public class SecurityProvider {

    public static void main(String[] args) {
        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            System.out.println("提供商名称：" + provider.getName() + "，版本：" + provider.getVersionStr());
            System.out.println(provider.getInfo());
        }
    }

}
