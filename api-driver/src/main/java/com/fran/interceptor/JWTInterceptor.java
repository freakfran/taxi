package com.fran.interceptor;


import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fran.constant.TokenTypeConstants;
import com.fran.dto.CommonResult;
import com.fran.dto.TokenResult;
import com.fran.util.JwtUtils;
import com.fran.util.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;
        String resultString = "";

        //解析token
        String token = request.getHeader("Authorization");
        TokenResult tokenResult = JwtUtils.checkToken(token);

        //校验token
        if(tokenResult!=null){
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();
            String tokenKey = RedisKeyUtils.generateTokenKey(phone,identity, TokenTypeConstants.ACCESS_TOKEN_TYPE);
            String tokenRedis = stringRedisTemplate.opsForValue().get(tokenKey);
            if(StringUtils.isBlank(tokenRedis)||(!token.trim().equals(tokenRedis.trim()))){
                resultString = "token invalid";
                response.getWriter().println(JSONObject.toJSONString(CommonResult.fail(resultString)));
                result = false;
            }
        }else{
            resultString = "token invalid";
            result = false;
            response.getWriter().println(JSONObject.toJSONString(CommonResult.fail(resultString)));
        }
        return result;
    }
}
