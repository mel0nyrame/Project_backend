package com.mel0ny.springboot.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataNoFoundException extends RuntimeException{
    public DataNoFoundException() {
    }

    public DataNoFoundException(String message) {
        super(message);
        log.error(message);
    }
}
