package com.mel0ny.springboot.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataErrorException extends RuntimeException{

    public DataErrorException(String message) {
        super(message);
        log.error(message);
    }
}
