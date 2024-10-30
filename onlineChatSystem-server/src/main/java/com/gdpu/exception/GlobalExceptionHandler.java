package com.gdpu.exception;

import com.gdpu.interceptors.LoginInterceptor;
import com.gdpu.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//全局异常处理
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler
{
    @Autowired
    private LoginInterceptor loginInterceptor;
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e)
    {
        log.info("全局异常捕获：{}",e.getMessage());
        return Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失败");
    }
}
