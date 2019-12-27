package com.breeze.util;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;


/**
 * @author tx
 */
@Component
public class Md5TokenGenerator {

    public static String generate(String... strings) {
        long timestamp = System.currentTimeMillis();
        String tokenMeta = "";
        for (String s : strings) {
            tokenMeta +=   s;
        }
        tokenMeta += timestamp;
        return  DigestUtils.md5DigestAsHex(tokenMeta.getBytes());
    }
}
