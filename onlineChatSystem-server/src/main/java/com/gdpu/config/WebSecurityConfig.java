package com.gdpu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭跨域校验
                .csrf().disable()
                //关闭security默认session验证
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //授权
                .authorizeRequests()
                //放行想要放行的资源
                .antMatchers(
                        //下面是knief4j和swagger放行的内容，包含了好几个版本的knief4j，所以直接全部复制就行了
                        "/v3/api-docs"
                        , "/api/**"
                        ,"/doc.html"
                        , "/webjars/**"
                        , "/img.icons/**"
                        , "/swagger-resources/**"
                        , "/**"
                        , "/v2/api-docs"
                ).permitAll() // 允许匿名访问
                // 任何请求都需要认证
                .anyRequest().authenticated();

    }


}

