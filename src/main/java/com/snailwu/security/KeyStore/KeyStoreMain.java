package com.snailwu.security.KeyStore;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Base64;

/**
 * 使用如下命令创建证书
 * keytool -genkey -alias "wu" -keyalg "RSA" -keystore "/Users/wu/wu.keystore" -validity 180 -dname "CN=吴大大, OU=蜗牛小队, O=蜗牛小队, L=北京, ST=北京, C=CN" -storepass 123456
 * keytool -genkey -alias "wu-new" -keyalg "RSA" -keystore "/Users/wu/wu-new.keystore" -validity 180 -dname "CN=吴大大, OU=蜗牛小队, O=蜗牛小队, L=北京, ST=北京, C=CN" -storepass 123456
 * 导出证书
 * keytool -export -alias "wu-new" -keystore "/Users/wu/wu-new.keystore" -file "/Users/wu/wu-new.crt" -storepass 123456
 * 导入证书，将 wu-new 导入到 wu 中
 * keytool -import -alias "wu-new" -keystore "/Users/wu/wu.keystore" -file /Users/wu/wu-new.crt -storepass 123456
 *
 * @author WuQinglong
 * @date 2021/1/19 16:19
 */
public class KeyStoreMain {

    public static void main(String[] args) throws Exception {
        readKeyStore();
    }

    /**
     * 读取 keyStore 中的公钥和私钥
     */
    private static void readKeyStore() throws Exception {
        // keyStore 文件位置
        String keyStoreFilePath = "/Users/wu/key.keystore";
        String keyStorePassword = "123456";
        String keyAlias = "key-1";

        // 读取 KeyStore
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(new FileInputStream(keyStoreFilePath), keyStorePassword.toCharArray());

        // 获取私钥
        Key key = keyStore.getKey(keyAlias, keyStorePassword.toCharArray());
        byte[] privateKeyBytes = Base64.getEncoder().encode(key.getEncoded());
        String privateKey = new String(privateKeyBytes, StandardCharsets.UTF_8);
        System.out.println("私钥：" + privateKey);

        // 获取公钥
        Certificate certificate = keyStore.getCertificate(keyAlias);
        byte[] publicKeyBytes = certificate.getPublicKey().getEncoded();
        byte[] encodeBytes = Base64.getEncoder().encode(publicKeyBytes);
        String publicKey = new String(encodeBytes, StandardCharsets.UTF_8);
        System.out.println("公钥：" + publicKey);
    }

}
