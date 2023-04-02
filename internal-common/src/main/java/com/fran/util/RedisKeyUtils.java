package com.fran.util;

public class RedisKeyUtils {

    private static String verificationCodePrefix = "verification-code-";
    private static String tokenPrefix = "token-";
    public static String generateKeyByPhone(String phone,String identity){
        return verificationCodePrefix + identity + "-" + phone;
    }
    public static String generateTokenKey(String phone,String identity,String tokenType){
        return tokenPrefix + phone + "-" + identity + "-" + tokenType;
    }
}
