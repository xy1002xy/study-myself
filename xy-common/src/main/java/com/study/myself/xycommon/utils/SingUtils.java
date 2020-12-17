package com.study.myself.xycommon.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @program: xy-parent
 * @description: 签名
 * @author: wxy
 * @create: 2020-12-16 18:30
 **/
@Slf4j
public class SingUtils {

    public static String getSignature(String param, String secret) {

        StringBuilder hash = new StringBuilder();
        byte[] body = param.getBytes(StandardCharsets.UTF_8);
        String sign = "";
        try {
            Mac hmacSha256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
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
}
