/**
 * 与MessageDigests一样，它们也会生成散列值，但是首先使用密钥初始化以保护消息的完整性。
 * HMACSHA384
 *
 * Java原生支持的算法如下：
 * 1、PBEWITHHMACSHA512
 * 2、PBEWITHHMACSHA224
 * 3、HMACSHA384
 * 4、PBEWITHHMACSHA256
 * 5、HMACSHA256
 * 6、PBEWITHHMACSHA384
 * 7、HMACPBESHA1
 * 8、HMACSHA512/256
 * 9、HMACSHA224
 * 10、HMACMD5
 * 11、HMACSHA512/224
 * 12、PBEWITHHMACSHA1
 * 13、SSLMACSHA1
 * 14、HMACSHA512
 * 15、SSLMACMD5
 * 16、HMACSHA1
 *
 * @author WuQinglong
 * @date 2021/1/19 17:34
 */
package com.snailwu.security.MessageAuthenticationCodes;