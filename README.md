# 数据安全传输
SecurityProvider：列出了系统中算法的提供商和版本等信息。

JDKProvideAlgorithms：列出了每个serviceName（服务名？感觉怪怪的）对应的算法实现。

**基于 JDK11。**

# Base64编码
依赖于 java.util.Base64 类。

功能：可以将任意数据编码为ASCII内的字符，便于数据传输。

Base64可以编码一切数据，Base64编码后的长度比原字符串长三分之一倍。

注意编解码时字节转字符或字符转字节时的编码。

# 数字生成算法
依赖于 java.security.SecureRandom 类。

功能：生成安全的随机数。

JDK11 提供的有：DRBG、SHA1PRNG、NATIVEPRNG、NATIVEPRNGBLOCKING、NATIVEPRNGNONBLOCKING。

官方文档：[查看](https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#securerandom-number-generation-algorithms)

# 消息摘要算法
依赖于 java.security.MessageDigest 类。

功能：对数据进行摘要计算，用于验证数据的完整性、一致性。

JDK11 提供的有：MD5、SHA-1、SHA-256和SHA3-256等等，具体查阅官方文档。

官方文档：[查看](https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#messagedigest-algorithms)

# 带密钥的消息摘要
依赖于：javax.crypto.Mac 类。

功能：弥补消息摘要算法的缺点，在摘要计算中加入密钥参与计算，防止数据和摘要同时被篡改。

JDK11 提供的有：HMACMD5、HMACSHA1、HMACSHA256、HMACSHA3-256等等，具体查阅官方文档。

官方文档：[查看](https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#mac-algorithms)

# 公钥私钥生成
常用的有：RSA、DSA

# 密钥生成
常用的有：AES、DES、HMACMD5、HMACSHA1/HMACSHA224/HMACSHA256/HMACSHA384/HMACSHA512

# 加签验签
常用的有：SHA1WITHRSA/SHA512WITHRSA、SHA1WITHDSA/SHA512WITHECDSA、MD5WITHRSA

# 加密解密
常用的有：AES、RSA

