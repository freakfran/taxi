package com.fran.util;

public class SsePrefixUtils {
    private static final String separator = "$";

    public static String generateSseKey(String userId,String identity){
        return userId + separator + identity;
    }
}
