package com.snailwu.security.KeyStore;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Base64;

/**
 * 从 CRT 证书中读取公钥
 *
 * @author WuQinglong
 * @date 2021/1/22 16:34
 */
public class CertificateFactoryMain {

    public static void main(String[] args) throws Exception {
        String certFilePath = "/Users/wu/key-1.crt";

        // 固定为 X.509 参考：https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#certificatefactory-types
        CertificateFactory factory = CertificateFactory.getInstance("X.509");
        try (FileInputStream fis = new FileInputStream(certFilePath)) {
            Certificate certificate = factory.generateCertificate(fis);
            PublicKey publicKey = certificate.getPublicKey();
            byte[] encodeBytes = Base64.getEncoder().encode(publicKey.getEncoded());
            System.out.println("公钥：" + new String(encodeBytes, StandardCharsets.UTF_8));
        }
    }

}
