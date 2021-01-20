package com.snailwu.security.KeyStore;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
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

    public static void main(String[] args) throws KeyStoreException, IOException, UnrecoverableKeyException,
            NoSuchAlgorithmException, CertificateException {
        // 获取证书的公钥和私钥
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(new FileInputStream("/Users/wu/wu.keystore"), "123456".toCharArray());
        Key key = keyStore.getKey("wu", "123456".toCharArray());
        String privateKey = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("私钥：" + privateKey);
        Certificate certificate = keyStore.getCertificate("wu");
        String publicKey = Base64.getEncoder().encodeToString(certificate.getPublicKey().getEncoded());
        System.out.println("公钥：" + publicKey);

    }

}
