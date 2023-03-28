package com.fran.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    //盐
    private static final String SALT = "aoruya*^&%";

    private static final String JWT_KEY = "passengerPhone";

    //生成token
    public static String generateToken(String passengerPhone){
        Map<String,String> map = new HashMap<>();
        map.put(JWT_KEY,passengerPhone);
        //过期时间 1天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        Date date = calendar.getTime();

        JWTCreator.Builder builder = JWT.create();
        //整合map
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        //整合过期时间
        builder.withExpiresAt(date);
        //生成token
        String token = builder.sign(Algorithm.HMAC256(SALT));

        return token;
    }
    //解析token
    public static String parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SALT)).build().verify(token);
        Claim claim = verify.getClaim(JWT_KEY);
        return claim.toString();
    }
    public static void main(String[] args) {
        String token = generateToken("123456");
        System.out.println(token);
        System.out.println(parseToken(token));
    }
}
