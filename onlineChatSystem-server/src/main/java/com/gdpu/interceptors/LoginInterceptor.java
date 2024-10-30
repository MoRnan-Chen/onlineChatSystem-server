package com.gdpu.interceptors;

import com.gdpu.utils.JwtUtil;
import com.gdpu.utils.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    {
        //令牌验证
        String token = request.getHeader("Authorization");

        //验证token
        try
        {
            log.info("验证token");
            Map<String, Object> claims = JwtUtil.parseToken(token);

            UserContext.setUser(claims);
            //放行
            return true;
        }
        catch (Exception e)
        {
            //http响应状态码为401
            response.setStatus(401);

            //解析失败，则不放行
            return false;
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
        UserContext.removeUser();
    }
}
