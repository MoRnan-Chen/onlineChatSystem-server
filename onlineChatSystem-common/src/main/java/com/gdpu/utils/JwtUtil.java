package com.gdpu.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

//封装jwt工具类
public class JwtUtil
{
    //密钥
    public static final String KEY = "MoRan";

    //生成token
    public static String getToken(Map<String,Object> claims)
    {
        return JWT.create().withClaim("claims",claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))
                .sign(Algorithm.HMAC256(KEY));
    }

    //解析token
    public static Map<String,Object> parseToken(String token)
    {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}
