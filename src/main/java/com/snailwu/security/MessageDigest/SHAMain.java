package com.snailwu.security.MessageDigest;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA家族的五个算法：SHA-1、SHA-224、SHA-256、SHA-384、SHA-512。
 * 后四者有时并称为SHA-2（SHA-224、SHA-256、SHA-384、SHA-512）。
 * <p>
 * JDK11支持的有：
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

    /**
     * 计算下载的 apache-tomcat-9.0.41.zip 压缩包的完整性
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        // 官方下载页面提供的 sha512 的值
        String sha512 = "20029f00a64627a0fe9e6b86550d5244418e40cf933ba7ea985713e6cf638b8e13e16b0e3265a5fba1f0c7440e718e42ade4a10fc16d820bb801be1ac4a2aa5d";
        // 将文件读取成 byte 数组
        FileInputStream fis = new FileInputStream("/Users/wu/Downloads/apache-tomcat-9.0.41.zip");
        // JDK11 中的方法
        byte[] bytes = fis.readAllBytes();

        // 计算摘要，使用 SHA-512 算法
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        messageDigest.update(bytes);
        byte[] digestBytes = messageDigest.digest();
        // 转 16 进制表示
        String hexResult = new BigInteger(1, digestBytes).toString(16);
        if (sha512.equals(hexResult)) {
            System.out.println("文件数据完整");
        } else {
            System.out.println("文件被篡改过！！！");
        }
    }

}
