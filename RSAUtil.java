package com.cmos.csr.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSAUtil {
    private static final String pubKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDNo/tCZwpr/8nnh+5RVQtHEy/smmJ2WFCJSExMSDxYUJ2QqSawRg5Iy5te8FQYs0M+KUoSNZYLyb0nkv2ThOT8z8LJ+5Hdm3vZ/TSk3guY096vcazQiBhK+Yf/XLDl8WBYbaGGNZo6ckFeqKnrxNVgo+slOHXEDgBEYIgRml7ASQIDAQAB";
    private static final String priKeyStr = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM2j+0JnCmv/yeeH7lFVC0cTL+yaYnZYUIlITExIPFhQnZCpJrBGDkjLm17wVBizQz4pShI1lgvJvSeS/ZOE5PzPwsn7kd2be9n9NKTeC5jT3q9xrNCIGEr5h/9csOXxYFhtoYY1mjpyQV6oqevE1WCj6yU4dcQOAERgiBGaXsBJAgMBAAECgYEAvtYl75qWNmGbaq1gAcRtgdkRiX5AT9lna+9XNxd2QPMXNk9zovRwfyfMmU9oi7+YcJB6t+lYxLnEO3ySrJsQsM2HOVj894+ZGUbnWCkhw4HiDqR5W5afLVjDAZVLdODQvaat7v+AYhkkNVK13Pt6q1y7wVoHbgVPnMMksfruu30CQQD5EGbgsDOMSQ154OHQ2kb6tEElebKfJCZZ5sSqBFK+ItRCB7sKBDFB0cj26gOs4ZmFds9zvtETVYtK7FmIdRpnAkEA014CgtoV3QFJVTrBNcle3VsR/dubaXHuZpfwJ+AOoC40ltYb2KvjOV/812Vidll4A3kPxjwu3nEDr26VSEwBzwJAEf6kYDF61/TjO14LEqrim/RwDIQZarQS7UXmCZ6cV85uZYLneZbJ9EZy7ZwEvfXiwKjP3j34Hxx8HuKqqVhEmwJAIxb4U8Dg4E/WXM/kQ4mqthToZ7yNkHPZoC6ZMAWCaBVDpcbl2JKwplXJI/kw33T695ihhcd4AHXCpt0060uzAQJAD9KNjn7x7Dksz/TZcCyYCqHAWA1IKjNQiqw6YJEuH7Fuxf56ciWNVXfa4y60/ScRxfpjU1dQZiC0MQNKnA+Bow==";
    private static final RSAPublicKey PUBLIC_KEY = RSA.getPublicKey(pubKeyStr);
    private static final RSAPrivateKey PRIVATE_KEY = RSA.getPrivateKey(priKeyStr);

    /**
     * 使用指定的公钥加密数据。
     *
     * @param publicKey 给定的公钥。
     * @param data      要加密的数据。
     * @return 加密后的数据。
     */
    private static byte[] encrypt(PublicKey publicKey, byte[] data) throws Exception {
        Cipher ci = Cipher.getInstance("RSA");
        ci.init(Cipher.ENCRYPT_MODE, publicKey);
        return ci.doFinal(data);
    }

    /**
     * 使用指定的私钥解密数据。
     *
     * @param privateKey 给定的私钥。
     * @param data       要解密的数据。
     * @return 原数据。
     */
    private static byte[] decrypt(PrivateKey privateKey, byte[] data) throws Exception {
        Cipher ci = Cipher.getInstance("RSA");
        ci.init(Cipher.DECRYPT_MODE, privateKey);
        return ci.doFinal(data);
    }


    public static String encryptString(PublicKey publicKey, String plaintext) throws Exception {
        if (publicKey == null || plaintext == null) {
            return null;
        }
        byte[] data = plaintext.getBytes();
        byte[] en_data = encrypt(publicKey, data);
        return Base64.encodeBase64String(en_data);
    }

    public static String decryptString(PrivateKey privateKey, String encrypttext) {
        if (privateKey == null || StringUtils.isBlank(encrypttext)) {
            return null;
        }
        try {
            byte[] en_data = Base64.decodeBase64(encrypttext);
            byte[] data = decrypt(privateKey, en_data);
            return new String(data);
        } catch (Exception ex) {
            return null;
        }

    }

    /**
     * 使用默认的的公钥加密数据。
     *
     * @param plaintext 要加密的字符串。
     * @return 加密后数据。
     */
    public static String encryptByDefaultPublicKey(String plaintext) throws Exception {
        return encryptString(PUBLIC_KEY, plaintext);
    }

    /**
     * 使用默认的的私钥解密数据。
     *
     * @param encrypttext 要解密的字符串。
     * @return 原数据。
     */
    public static String decryptByDefaultPrivateKey(String encrypttext) {
        return decryptString(PRIVATE_KEY, encrypttext);
    }

}
