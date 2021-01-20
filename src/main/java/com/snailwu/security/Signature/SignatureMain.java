package com.snailwu.security.Signature;

import com.snailwu.security.KeyPairGenerator.KeyPairGeneratorMain;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * JDK11 原生支持的加签验签算法：
 * 1、SHA384WITHECDSA
 * 2、SHA256WITHECDSAINP1363FORMAT
 * 3、SHA224WITHDSAINP1363FORMAT
 * 4、SHA512/224WITHRSA
 * 5、SHA1WITHRSA
 * 6、SHA512WITHRSA
 * 7、SHA224WITHECDSAINP1363FORMAT
 * 8、NONEWITHECDSAINP1363FORMAT
 * 9、SHA384WITHECDSAINP1363FORMAT
 * 10、SHA256WITHECDSA
 * 11、SHA224WITHECDSA
 * 12、SHA512/256WITHRSA
 * 13、SHA512WITHECDSA
 * 14、SHA1WITHDSA
 * 15、NONEWITHDSA
 * 16、SHA224WITHDSA
 * 17、SHA256WITHRSA
 * 18、SHA1WITHDSAINP1363FORMAT
 * 19、MD5WITHRSA
 * 20、SHA256WITHDSAINP1363FORMAT
 * 21、SHA1WITHECDSAINP1363FORMAT
 * 22、NONEWITHDSAINP1363FORMAT
 * 23、MD2WITHRSA
 * 24、SHA256WITHDSA
 * 25、SHA1WITHECDSA
 * 26、MD5ANDSHA1WITHRSA
 * 27、SHA224WITHRSA
 * 28、NONEWITHECDSA
 * 29、RSASSA-PSS
 * 30、SHA384WITHRSA
 * 31、SHA512WITHECDSAINP1363FORMAT
 *
 * @author WuQinglong
 * @date 2021/1/18 19:17
 */
public class SignatureMain {
    private static final String KEY_ALGORITHM = "RSA";
    private static final String SIGN_ALGORITHM = "SHA1WITHRSA";

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidKeyException, SignatureException {
        String source = "admin";

        // 使用 RSA 进行加解密
        Map<String, String> rsaMap = KeyPairGeneratorMain.generate(KEY_ALGORITHM, null);
        String publicKeyStr = rsaMap.get(KeyPairGeneratorMain.PUBLIC_KEY);
        String privateKeyStr = rsaMap.get(KeyPairGeneratorMain.PRIVATE_KEY);

        // 获取 PrivateKey
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyStr));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        // 加签
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(source.getBytes(UTF_8));
        byte[] signed = signature.sign();
        // 得到签名
        String signStr = Base64.getEncoder().encodeToString(signed);
        System.out.println("签名字符串：" + signStr);

        // 获取 publicKey
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyStr));
        keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        // 验签
        signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(source.getBytes(UTF_8));
        // 验证签名
        boolean verify = signature.verify(Base64.getDecoder().decode(signStr));
        System.out.println("验签结果：" + verify);

    }

}
