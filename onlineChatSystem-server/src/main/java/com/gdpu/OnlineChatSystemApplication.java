package com.gdpu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication


public class OnlineChatSystemApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(OnlineChatSystemApplication.class, args);
    }
}
