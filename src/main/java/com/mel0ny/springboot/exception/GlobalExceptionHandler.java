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
        return Result.error(exception.getMessage(),null);
    }

    @ExceptionHandler(DataNoFoundException.class)
    public Result exceptionHandler(DataNoFoundException dataNoFoundException) {
        log.error("数据未找到",dataNoFoundException);
        return Result.error("数据未找到",null);
    }

    @ExceptionHandler(OperationFailureException.class)
    public Result exceptionHandler(OperationFailureException operationFailureException) {
        log.error("操作失败",operationFailureException);
        return Result.error("操作失败", null);
    }
}
