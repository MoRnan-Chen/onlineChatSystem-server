package com.gdpu.config;

import com.gdpu.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//配置类，注册web层相关组件
@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
    @Autowired
    private LoginInterceptor loginInterceptor;

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        //登录接口和注册接口以及knife4j不拦截
        registry.addInterceptor(loginInterceptor).excludePathPatterns(
                "/user/login",
                "/user/register",
                "/v3/api-docs"
                , "/api/**"
                ,"/doc.html"
                , "/webjars/**"
                , "/img.icons/**"
                , "/swagger-resources/**"
                , "/v2/api-docs");
    }

}
