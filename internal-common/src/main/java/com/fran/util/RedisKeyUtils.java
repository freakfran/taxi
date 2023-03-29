package com.fran.util;

public class RedisKeyUtils {

    private static String verificationCodePrefix = "passenger-verification-code-";
    private static String tokenPrefix = "token-";
    public static String generateKeyByPhone(String passengerPhone){
        return verificationCodePrefix + passengerPhone;
    }
    public static String generateTokenKey(String phone,String identity,String tokenType){
        return tokenPrefix + phone + "-" + identity + "-" + tokenType;
    }
}
