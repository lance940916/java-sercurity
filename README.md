# 数据安全传输
注意区分编码和加解密，注意算法是否可逆。

Base64属于编码，不属于加密。

消息摘要算法属于不可逆的，计算摘要时会丢失大量信息。

摘要和加签是防止数据被篡改，加密是为了防止数据泄露。

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

加密算法：[查看](https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#securerandom-number-generation-algorithms)

# 消息摘要算法
依赖于 java.security.MessageDigest 类。

功能：对数据进行摘要计算，用于验证数据的完整性、一致性。

JDK11 提供的有：MD5、SHA-1、SHA-256和SHA3-256等等，具体查阅官方文档。

加密算法：[查看](https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#messagedigest-algorithms)

# 带密钥的消息摘要
依赖于 javax.crypto.Mac 类。

功能：弥补消息摘要算法的缺点，在摘要计算中加入密钥参与计算，防止数据和摘要同时被篡改。

JDK11 提供的有：HMACMD5、HMACSHA1、HMACSHA256、HMACSHA3-256等等，具体查阅官方文档。

加密算法：[查看](https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#mac-algorithms)

# 密钥生成
依赖于 javax.crypto.KeyGenerator 类。

功能：借助于 SecureRandom 随机数生成器，生成一个指定长度的密钥。

JDK11 提供的有：AES、DES、HmacSHA1、HmacSHA512等等，具体查阅官方文档。

加密算法：[查看](https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#keygenerator-algorithms)

# 公钥私钥生成
依赖于 java.security.KeyPairGenerator 类。

功能：生成公钥私钥。

JDK11 提供的有：RSA、DSA和X448等等，具体查阅官方文档。

加密算法：[查看](https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#keypairgenerator-algorithms)

# 加签验签
依赖于 java.security.Signature 类。

功能：使用公钥私钥。比如 SHA512withRSA 就是先用 SHA-512 进行消息摘要计算，然后使用 RSA 对摘要进行加密，注意：消息摘要计算是不可逆的。

JDK11 提供的有：SHA1WITHRSA、MD5withRSA和SHA3-512withRSA等等，具体查阅官方文档。

加密算法：[查看](https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#signature-algorithms)

# 加密解密
依赖于 javax.crypto.Cipher 类。

分为**对称加密**和**非对称加密**。

功能：加密数据，防止数据泄露。

JDK11 提供的有：AES、RSA和RC4等等，具体查阅官方文档。

加密算法：[查看](https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#cipher-algorithm-names)

注意：AES 还有**加密模式**和**填充方式**两个参数。

加密模式：[查看](https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#cipher-algorithm-modes)

填充方式：[查看](https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#cipher-algorithm-paddings)

# TODO 数字证书