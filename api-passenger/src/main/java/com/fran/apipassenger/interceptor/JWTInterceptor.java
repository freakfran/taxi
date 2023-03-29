package com.fran.apipassenger.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fran.dto.CommonResult;
import com.fran.util.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;
        String resultString = "";
        String token = request.getHeader("Authorization");
        try {
            JwtUtils.parseToken(token);
        }catch (SignatureVerificationException e){
            resultString = "signature error";
            result = false;
        }catch (TokenExpiredException e){
            resultString = "token expired";
            result = false;
        }catch (AlgorithmMismatchException e){
            resultString = "token AlgorithmMismatchException";
            result = false;
        }catch (Exception e){
            resultString = "token exception";
            result = false;
        }

        if(!result){
            response.getWriter().println(JSONObject.toJSONString(CommonResult.fail(resultString)));
        }
        return result;
    }
}
