package com.snailwu.security.KeyStore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Base64;
import java.util.Enumeration;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 使用如下命令创建证书
 *
 * @author WuQinglong
 * @date 2021/1/19 16:19
 */
public class KeyStoreMain {

    public static void main(String[] args) throws Exception {
        // keyStore 文件位置
        String keyStoreFilePath = "/Users/wu/key.keystore";
        String keyStorePassword = "123456";

        // 读取 KeyStore，参数为密钥库类型，如果是 JKS 密钥库的话则传入 JKS
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(new FileInputStream(keyStoreFilePath), keyStorePassword.toCharArray());


        // 密钥库的密钥数量
        System.out.println("密钥数量：" + keyStore.size());


        // 列出密钥库所有密钥别名
        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            // 别名
            String alias = aliases.nextElement();
            // 是否是 PrivateKeyEntry 或 SecretKeyEntry
            boolean keyEntry = keyStore.isKeyEntry(alias);
            // 是否是 TrustedCertificateEntry（trustedCertEntry）
            boolean certificateEntry = keyStore.isCertificateEntry(alias);
        }


        // 读取密钥库中的公钥私钥
        String keyAlias = "key-1";
        System.out.println("isKeyEntry: " + keyStore.isKeyEntry(keyAlias));
        System.out.println("isCertificateEntry: " + keyStore.isCertificateEntry(keyAlias));
        // 获取私钥
        if (keyStore.isKeyEntry(keyAlias)) {
            Key key = keyStore.getKey(keyAlias, keyStorePassword.toCharArray());
            byte[] privateKeyBytes = Base64.getEncoder().encode(key.getEncoded());
            String privateKey = new String(privateKeyBytes, UTF_8);
            System.out.println("私钥：" + privateKey);
        } else {
            System.out.println("没有私钥");
        }
        // 获取公钥
        Certificate certificate = keyStore.getCertificate(keyAlias);
        byte[] publicKeyBytes = certificate.getPublicKey().getEncoded();
        byte[] encodeBytes = Base64.getEncoder().encode(publicKeyBytes);
        String publicKey = new String(encodeBytes, UTF_8);
        System.out.println("公钥：" + publicKey);


        // 删除别名为 key-4 的密钥
        keyStore.deleteEntry("key-1");
        // 操作完后需要保存一下
        keyStore.store(new FileOutputStream(keyStoreFilePath), keyStorePassword.toCharArray());
    }

}
