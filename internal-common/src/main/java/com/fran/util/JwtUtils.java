package com.fran.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fran.constant.IdentityConstants;
import com.fran.dto.TokenResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    //盐
    private static final String SALT = "aoruya*^&%";

    private static final String JWT_KEY_PHONE = "passengerPhone";
    private static final String JWT_KEY_IDENTITY = "identity";
    private static final String JWT_KEY_TOKEN_TYPE = "tokenType";
    private static final String JWT_KEY_TOKEN_TIME = "tokenType";

    //生成token
    public static String generateToken(String passengerPhone,String identity,String tokenType){
        Map<String,String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE,passengerPhone);
        map.put(JWT_KEY_IDENTITY,identity);
        map.put(JWT_KEY_TOKEN_TYPE,tokenType);
        //为了防止每一次生成的token都是一样的
        map.put(JWT_KEY_TOKEN_TIME,new Date().toString());
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
        //builder.withExpiresAt(date);
        //生成token
        String token = builder.sign(Algorithm.HMAC256(SALT));

        return token;
    }
    //解析token
    public static TokenResult parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SALT)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).asString();
        TokenResult tokenResult = new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);
        return tokenResult;
    }

    //校验token
    public static TokenResult checkToken(String token){
        TokenResult tokenResult = null;
        boolean result = true;
        try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (Exception e){
            result = false;
        }
        return tokenResult;
    }
    public static void main(String[] args) {
//        String token = generateToken("17314742659", IdentityConstants.IDENTITY_DRIVER);
//        System.out.println(token);
//        TokenResult tokenResult = parseToken(token);
//        System.out.println(tokenResult.getPhone());
    }
}
