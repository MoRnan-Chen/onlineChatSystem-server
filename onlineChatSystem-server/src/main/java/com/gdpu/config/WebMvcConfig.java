package com.gdpu.config;

import com.gdpu.interceptors.LoginInterceptor;
import com.gdpu.json.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//配置类，注册web层相关组件
@Configuration
@Slf4j
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

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters)
    {
        log.info("扩展消息转换器……");
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //需要为消息转换器设置一个对象转换器，对象转换器可以将java对象序列化为json数据
        converter.setObjectMapper(new JacksonObjectMapper());
        //将自己的消息转换器加入容器中
        converters.add(0,converter);

    }

}
