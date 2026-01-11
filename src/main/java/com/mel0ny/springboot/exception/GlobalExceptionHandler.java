package com.mel0ny.springboot.exception;

import com.mel0ny.springboot.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception exception) {
        log.error("发生错误,请查看日志",exception);
        return Result.error();
    }
}
