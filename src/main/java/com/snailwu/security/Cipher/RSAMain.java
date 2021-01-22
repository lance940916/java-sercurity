package com.snailwu.security.Cipher;

import com.snailwu.security.KeyPairGenerator.KeyPairGeneratorMain;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author WuQinglong
 * @date 2021/1/19 12:07
 */
public class RSAMain {

    public static void main(String[] args) throws Exception {
        String data = "admin";
        String algorithm = "RSA";

        // 使用 RSA 进行加解密
        Map<String, String> rsaMap = KeyPairGeneratorMain.generate(algorithm, null);
        String publicKey1 = rsaMap.get(KeyPairGeneratorMain.PUBLIC_KEY);
        String privateKey1 = rsaMap.get(KeyPairGeneratorMain.PRIVATE_KEY);

//        privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCduMenrmfmADpZlhZSUG9O79VETl1ziaka6Ps5EepQZiCsFRk1OoCF4ajn9nmTJ1M1EHtFxF5URScw9inf+R41Zoxy6HwoghFJa3PReD3vFAqSFZ6+RZGXUAxnqHUY4WjF2XMOKixfDHRPVyxckb6clcMDHWQHSaA8HyR+XVKa/hRK8EMoEAJJwo7NStPpcF9QGGVknaEfBb+9S4ARQNeagY1SmSKIpX3yte9D7nSji4CKaNwp7RjqrLwZg6B+gho8Y3wXgO1qX80XpXqoLA8amyhHq4knKnLVFSFHUzWTJO1wHqkHpR1VxUnQNK3vdzcku/kyDy0g1IlK6jsvSJCFAgMBAAECggEATzEaPl1Zmah/o7+yUX5FhTLyuCtIzNxP5k0Y5tzR87BzszOcxpcrNQ3LCz08aeCS7M/rtWoVmuZl+TWAbQ7eVh25Y3DL9mLoXx7utjxTjJlZfWQRoqO3qGvu7pUsxmNXgg/erzwJr2fHdpbklU4wkNq5YfT95pLXO5i/cBtXgK5KlMMzzJ4s8ReNMEWkl8gfmDQYd/PNqJMWl/4zOpANJZhrWI14pN7hEUUao8i1TbQljrvr77uS9SbWOH4TEJ/bP6mj2cCiW4Hkujtj/lAkIf/2lj9GNh5IZZAndRokN+SjQiLUzGd5qMcSkrtsue6BwK/MTW1gjqvLy9ZAQIxqOQKBgQDnzRgll9EPSd9U1uFBBr5hW3AXGO0gFua57iQMpZn1tWjNLx5KMIwaEsSJCRfod+1tGhud8d1n0mYIfLNK+diArudqIXAKW0oPYby6AjQhWQFP3OGud8KDN4kljIkW+VVFKBIK8ZcNGTJZLMkhxxqIUAIeQzV+4DBqoXf3NFKKEwKBgQCuL+iSslJDDwnns3QZzmJ087XyPtXRlIYoR9PDNBwz/UuHgpar80cKx7Mxee/SvrSYou+xvDYyM6Ge2ItYx79uRRxSKg/b8ngXNStQb4pXA9Y+ILX/LUc91YaHkYNsLhoRwMpDwPk+jiGMMBvfJC65A1x5oU3s9PEGz+S4Z9dOBwKBgQDhdZsG9aNiO3f+DQMLPIPwLf/Yd2Ys0BmavwZRRsF5O/mzmSDdCFs9AQQoRa/7bKqCc+JovdecJAkaODArvNfYUkbD88IGUMS4MVhfk8UgqnGcPDtGdzW6a0n6O7cboWhO26qw+krxcpCGLAl10TL9YtSsmrJttaFiFauZxkqCAQKBgB50zAKSYWXORFoZ+4vjlT2Du2V+7R33DToPzQwNmXzRphoskDHCmMJexos5GeDMbYFew/Bcy1Edy/2dOUgtrOAX/2qyZhF8JNlR95/Z0OOEDTrRt2DFxvYd1qbvn34T1YUTI4qTPEFe0qjTZJwwlGbPGqU9ugVbkqUxRnmj1naTAoGANAaq9+oJlx6yzJZWIFI1uf9VTxFDjymX4W3B5N3Yo5n46fjP7rTmVpOtfdecUAx8gV5/EHUagZAtywzYfX8C3Q7QdPk6iqtT/v9VCi0GNk8+Wbj0WliSvgp2NV0OB+fjVNl3C9V2lSlQbR5hOrmsRX7iPgB+v407eRsiLmGeJ4o=";
//        publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnbjHp65n5gA6WZYWUlBvTu/VRE5dc4mpGuj7ORHqUGYgrBUZNTqAheGo5/Z5kydTNRB7RcReVEUnMPYp3/keNWaMcuh8KIIRSWtz0Xg97xQKkhWevkWRl1AMZ6h1GOFoxdlzDiosXwx0T1csXJG+nJXDAx1kB0mgPB8kfl1Smv4USvBDKBACScKOzUrT6XBfUBhlZJ2hHwW/vUuAEUDXmoGNUpkiiKV98rXvQ+50o4uAimjcKe0Y6qy8GYOgfoIaPGN8F4Dtal/NF6V6qCwPGpsoR6uJJypy1RUhR1M1kyTtcB6pB6UdVcVJ0DSt73c3JLv5Mg8tINSJSuo7L0iQhQIDAQAB";

        // 私钥加密，公钥解密
        // 获取 PrivateKey 实例，先 Base64 解码。
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKey1.getBytes(UTF_8));
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes, algorithm);
        KeyFactory keyFactory = KeyFactory.getInstance(pkcs8EncodedKeySpec.getAlgorithm());
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        // 加密
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encryptBytes = cipher.doFinal(data.getBytes(UTF_8));
        byte[] encodeBytes = Base64.getEncoder().encode(encryptBytes);
        String encodeStr = new String(encodeBytes, UTF_8);
        System.out.println("私钥=>公钥：加密：" + encodeStr);

        // 获取 PublicKey 实例，先 Base64 解码。
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey1.getBytes(UTF_8));
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes, algorithm);
        keyFactory = KeyFactory.getInstance(x509EncodedKeySpec.getAlgorithm());
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        // 解密
        cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decodeBytes = Base64.getDecoder().decode(encodeStr.getBytes(UTF_8));
        byte[] decryptBytes = cipher.doFinal(decodeBytes);
        System.out.println("私钥=>公钥：解密：" + new String(decryptBytes, UTF_8));
    }

}
