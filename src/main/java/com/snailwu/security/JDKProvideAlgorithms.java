package com.snailwu.security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * JDK 支持的加解密算法
 *
 * Java加密服务的名称:
 * SecureRandom: used to generate random or pseudo-random numbers.
 * MessageDigest: used to calculate the message digest (hash) of specified data.
 * Signature: initialized with keys, these are used to sign data and verify digital signatures.
 * Cipher: initialized with keys, these are used for encrypting/decrypting data. There are various types of algorithms: symmetric bulk encryption (e.g. AES), asymmetric encryption (e.g. RSA), and password-based encryption (e.g. PBE).
 * Message Authentication Codes (MAC): like MessageDigests, these also generate hash values, but are first initialized with keys to protect the integrity of messages.
 * KeyFactory: used to convert existing opaque cryptographic keys of type Key into key specifications (transparent representations of the underlying key material), and vice versa.
 * SecretKeyFactory: used to convert existing opaque cryptographic keys of type SecretKey into key specifications (transparent representations of the underlying key material), and vice versa. SecretKeyFactorys are specialized KeyFactorys that create secret (symmetric) keys only.
 * KeyPairGenerator: used to generate a new pair of public and private keys suitable for use with a specified algorithm.
 * KeyGenerator: used to generate new secret keys for use with a specified algorithm.
 * KeyAgreement: used by two or more parties to agree upon and establish a specific key to use for a particular cryptographic operation.
 * AlgorithmParameters: used to store the parameters for a particular algorithm, including parameter encoding and decoding.
 * AlgorithmParameterGenerator : used to generate a set of AlgorithmParameters suitable for a specified algorithm.
 * KeyStore: used to create and manage a keystore. A keystore is a database of keys. Private keys in a keystore have a certificate chain associated with them, which authenticates the corresponding public key. A keystore also contains certificates from trusted entities.
 * CertificateFactory: used to create public key certificates and Certificate Revocation Lists (CRLs).
 * CertPathBuilder: used to build certificate chains (also known as certification paths).
 * CertPathValidator: used to validate certificate chains.
 * CertStore: used to retrieve Certificates and CRLs from a repository.
 *
 * @author WuQinglong
 * @date 2021/1/18 15:16
 */
public class JDKProvideAlgorithms {

    public static void main(String[] args) {
        // 添加三方算法
//        Security.addProvider(new BouncyCastleProvider());

        List<String> cryptoServiceNames = Arrays.asList(
                // used to generate random or pseudo-random numbers.
                "SecureRandom"
                // used to calculate the message digest (hash) of specified data.
//                , "MessageDigest"
                // initialized with keys, these are used to sign data and verify digital signatures.
//                , "Signature"
                // initialized with keys, these are used for encrypting/decrypting data.
                // There are various types of algorithms: symmetric bulk encryption (e.g. AES),
                //  asymmetric encryption (e.g. RSA), and password-based encryption (e.g. PBE).
                , "Cipher"
//                , "MAC"
                // used to generate a new pair of public and private keys suitable for use with a specified algorithm.
//                , "KeyPairGenerator"
                // used to generate new secret keys for use with a specified algorithm.
//                , "KeyGenerator"
//                , "KeyAgreement"
//                , "AlgorithmParameters"
//                , "AlgorithmParameterGenerator"
//                , "KeyStore"
//                , "CertificateFactory"
//                , "CertPathBuilder"
//                , "CertPathValidator"
//                , "CertStore"
        );
        for (String serviceName : cryptoServiceNames) {
            System.out.println("加密服务名称：" + serviceName);
            Set<String> messageDigest = Security.getAlgorithms(serviceName);
            int index = 1;
            for (String item : messageDigest) {
                System.out.println("\t" + index + "、" + item);
                index++;
            }
        }

    }

}
