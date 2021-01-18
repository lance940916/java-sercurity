package com.snailwu.security.MessageDigest;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA家族的五个算法：SHA-1、SHA-224、SHA-256、SHA-384、SHA-512。
 * 由美国国家安全局（NSA）所设计，并由美国国家标准与技术研究院（NIST）发布；是美国的政府标准。
 * 后四者有时并称为SHA-2（SHA-224、SHA-256、SHA-384、SHA-512）。
 *
 * Java支持的有：
 * 1、SHA
 * 2、SHA-224
 * 3、SHA-256
 * 4、SHA-384
 * 5、SHA-512
 * 6、SHA-512/224
 * 7、SHA-512/256
 * 8、SHA3-224
 * 9、SHA3-256
 * 10、SHA3-384
 * 11、SHA3-512
 *
 * @author WuQinglong
 * @date 2021/1/18 16:10
 */
public class SHAMain {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String source = "admin";

        MessageDigest messageDigest = MessageDigest.getInstance("SHA3-256");
        messageDigest.update(source.getBytes(StandardCharsets.UTF_8));
        byte[] result = messageDigest.digest();
        String encodeResult= new BigInteger(1, result).toString(16);
        System.out.println(encodeResult);

        // SHA d033e22ae348aeb5660fc2140aec35850c4da997
        // SHA-256 8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918
        // SHA3-256 fb001dfcffd1c899f3297871406242f097aecf1a5342ccf3ebcd116146188e4b
        // SHA-512 c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec

    }

}
