package com.study.myself.xycommon.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.ObjectUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.xml.crypto.dsig.SignatureMethod.HMAC_SHA256;

/**
 * @program: xy-parent
 * @description: 签名
 * @author: wxy
 * @create: 2020-12-16 18:30
 **/
@Slf4j
public class SignatureUtils {

    public static String getSignature(String param, String secret) {

        StringBuilder hash = new StringBuilder();
        byte[] body = param.getBytes(UTF_8);
        String sign = "";
        try {
            Mac hmacSha256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(UTF_8), "HmacSHA256");
            hmacSha256.init(secretKey);
            byte[] bytes = hmacSha256.doFinal(body);
            for (byte aByte : bytes) {
                hash.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            sign = hash.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("hash.toString()=========>{}", sign);
        return sign;
    }

    public static void main(String[] args) {
        String salt = "EE6Ox4hM2v&$r_$4M02QbK8@f02zv2Arp422K20@";
        String requestId = "0b0b56be16165907104373675e3891";
        calculateDigest(requestId, salt);
        try {
            String pwd = String.format("%s-%s", requestId, salt);
            HMACSHA256(pwd, salt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算签名
     *
     * @param requestId 请求id
     * @param salt      盐
     * @return 计算签名
     */
    public static String calculateDigest(String requestId, String salt) {
        String data = String.format("%s-%s", requestId, salt);
        byte[] message = data.getBytes();
        String sign = "";
        try {
            byte[] secretKeyBytes = salt.getBytes(UTF_8);
            Mac hmacSha256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, "HmacSHA256");
            hmacSha256.init(secretKeySpec);
            hmacSha256.update(message);
            byte[] digest = hmacSha256.doFinal();
            byte[] bytes = Base64.encodeBase64(digest);
            if (ObjectUtils.isEmpty(bytes)) {
                return null;
            }
            sign = new String(bytes, "UTF-8");
            System.out.println("sign------->" + sign);
            return sign;
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Invalid MAC algorithm: " + "SHA-256", e);
        } catch (InvalidKeyException e) {
            throw new IllegalStateException("Invalid MAC secret key", e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sign;
    }

    public static String HMACSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));

        // StringBuilder sb = new StringBuilder();
        // for (byte item : array) {
        //     sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        // }
        // return sb.toString().toUpperCase();

        String sign = new String(Base64.encodeBase64(array), "UTF-8");
        System.out.println("HMACSHA256---->" + sign);
        return sign;
    }

}

