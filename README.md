# 数据安全传输
SecurityProvider：列出了系统中算法的提供商和版本等信息。

JDKProvideAlgorithms：列出了每个serviceName（服务名？感觉怪怪的）对应的算法实现。

**基于 JDK11。**

# Base64编码
Base64可以编码一切数据，Base64编码后的长度比原字符串长三分之一倍。

注意编解码时字节转字符或字符转字节时的编码。

# 安全的随机数 SecureRandom
常用的有：DRBG、SHA1PRNG、NATIVEPRNG、NATIVEPRNGBLOCKING、NATIVEPRNGNONBLOCKING

# 消息摘要算法
常用的有：SHA、SHA3、MD5

# 公钥私钥生成
常用的有：RSA、DSA

# 密钥生成
常用的有：AES、DES、HMACMD5、HMACSHA1/HMACSHA224/HMACSHA256/HMACSHA384/HMACSHA512

# 加签验签
常用的有：SHA1WITHRSA/SHA512WITHRSA、SHA1WITHDSA/SHA512WITHECDSA、MD5WITHRSA

# 加密解密
常用的有：AES、RSA

