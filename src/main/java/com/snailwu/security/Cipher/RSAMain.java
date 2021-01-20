package com.snailwu.security.Cipher;

import com.snailwu.security.KeyPairGenerator.KeyPairGeneratorMain;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
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
    private static final String SOURCE = "admin";
    private static String publicKey;
    private static String privateKey;
    private static final String ALGORITHM = "RSA";

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 使用 RSA 进行加解密
        Map<String, String> rsaMap = KeyPairGeneratorMain.generate(ALGORITHM, null);
        publicKey = rsaMap.get(KeyPairGeneratorMain.PUBLIC_KEY);
        privateKey = rsaMap.get(KeyPairGeneratorMain.PRIVATE_KEY);

//        privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCduMenrmfmADpZlhZSUG9O79VETl1ziaka6Ps5EepQZiCsFRk1OoCF4ajn9nmTJ1M1EHtFxF5URScw9inf+R41Zoxy6HwoghFJa3PReD3vFAqSFZ6+RZGXUAxnqHUY4WjF2XMOKixfDHRPVyxckb6clcMDHWQHSaA8HyR+XVKa/hRK8EMoEAJJwo7NStPpcF9QGGVknaEfBb+9S4ARQNeagY1SmSKIpX3yte9D7nSji4CKaNwp7RjqrLwZg6B+gho8Y3wXgO1qX80XpXqoLA8amyhHq4knKnLVFSFHUzWTJO1wHqkHpR1VxUnQNK3vdzcku/kyDy0g1IlK6jsvSJCFAgMBAAECggEATzEaPl1Zmah/o7+yUX5FhTLyuCtIzNxP5k0Y5tzR87BzszOcxpcrNQ3LCz08aeCS7M/rtWoVmuZl+TWAbQ7eVh25Y3DL9mLoXx7utjxTjJlZfWQRoqO3qGvu7pUsxmNXgg/erzwJr2fHdpbklU4wkNq5YfT95pLXO5i/cBtXgK5KlMMzzJ4s8ReNMEWkl8gfmDQYd/PNqJMWl/4zOpANJZhrWI14pN7hEUUao8i1TbQljrvr77uS9SbWOH4TEJ/bP6mj2cCiW4Hkujtj/lAkIf/2lj9GNh5IZZAndRokN+SjQiLUzGd5qMcSkrtsue6BwK/MTW1gjqvLy9ZAQIxqOQKBgQDnzRgll9EPSd9U1uFBBr5hW3AXGO0gFua57iQMpZn1tWjNLx5KMIwaEsSJCRfod+1tGhud8d1n0mYIfLNK+diArudqIXAKW0oPYby6AjQhWQFP3OGud8KDN4kljIkW+VVFKBIK8ZcNGTJZLMkhxxqIUAIeQzV+4DBqoXf3NFKKEwKBgQCuL+iSslJDDwnns3QZzmJ087XyPtXRlIYoR9PDNBwz/UuHgpar80cKx7Mxee/SvrSYou+xvDYyM6Ge2ItYx79uRRxSKg/b8ngXNStQb4pXA9Y+ILX/LUc91YaHkYNsLhoRwMpDwPk+jiGMMBvfJC65A1x5oU3s9PEGz+S4Z9dOBwKBgQDhdZsG9aNiO3f+DQMLPIPwLf/Yd2Ys0BmavwZRRsF5O/mzmSDdCFs9AQQoRa/7bKqCc+JovdecJAkaODArvNfYUkbD88IGUMS4MVhfk8UgqnGcPDtGdzW6a0n6O7cboWhO26qw+krxcpCGLAl10TL9YtSsmrJttaFiFauZxkqCAQKBgB50zAKSYWXORFoZ+4vjlT2Du2V+7R33DToPzQwNmXzRphoskDHCmMJexos5GeDMbYFew/Bcy1Edy/2dOUgtrOAX/2qyZhF8JNlR95/Z0OOEDTrRt2DFxvYd1qbvn34T1YUTI4qTPEFe0qjTZJwwlGbPGqU9ugVbkqUxRnmj1naTAoGANAaq9+oJlx6yzJZWIFI1uf9VTxFDjymX4W3B5N3Yo5n46fjP7rTmVpOtfdecUAx8gV5/EHUagZAtywzYfX8C3Q7QdPk6iqtT/v9VCi0GNk8+Wbj0WliSvgp2NV0OB+fjVNl3C9V2lSlQbR5hOrmsRX7iPgB+v407eRsiLmGeJ4o=";
//        publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnbjHp65n5gA6WZYWUlBvTu/VRE5dc4mpGuj7ORHqUGYgrBUZNTqAheGo5/Z5kydTNRB7RcReVEUnMPYp3/keNWaMcuh8KIIRSWtz0Xg97xQKkhWevkWRl1AMZ6h1GOFoxdlzDiosXwx0T1csXJG+nJXDAx1kB0mgPB8kfl1Smv4USvBDKBACScKOzUrT6XBfUBhlZJ2hHwW/vUuAEUDXmoGNUpkiiKV98rXvQ+50o4uAimjcKe0Y6qy8GYOgfoIaPGN8F4Dtal/NF6V6qCwPGpsoR6uJJypy1RUhR1M1kyTtcB6pB6UdVcVJ0DSt73c3JLv5Mg8tINSJSuo7L0iQhQIDAQAB";

        // 私钥加密，公钥解密
        privateKeyEncodePublicKeyDecode();
        publicKeyEncodePrivateKeyDecode();

    }

    /**
     * 私钥加密，公钥解密
     */
    private static void privateKeyEncodePublicKeyDecode() throws NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 私钥加密
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encodeBytes = cipher.doFinal(SOURCE.getBytes(UTF_8));
        String encodeStr = Base64.getEncoder().encodeToString(encodeBytes);
        System.out.println("私钥=>公钥：加密：" + encodeStr);

        // 公钥解密
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
        keyFactory = KeyFactory.getInstance(ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decodeBytes = cipher.doFinal(Base64.getDecoder().decode(encodeStr));
        System.out.println("私钥=>公钥：解密：" + new String(decodeBytes, UTF_8));
    }

    /**
     * 公钥加密，私钥解密
     */
    private static void publicKeyEncodePrivateKeyDecode() throws NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 公钥加密
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encodeBytes = cipher.doFinal(SOURCE.getBytes(UTF_8));
        String encodeStr = Base64.getEncoder().encodeToString(encodeBytes);
        System.out.println("公钥=>私钥：加密：" + encodeStr);

        // 私钥解密
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
        keyFactory = KeyFactory.getInstance(ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decodeBytes = cipher.doFinal(Base64.getDecoder().decode(encodeStr));
        System.out.println("公钥=>私钥：解密：" + new String(decodeBytes, UTF_8));
    }

}
