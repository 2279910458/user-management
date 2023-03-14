package com.example.user.management.controller.exception;

import com.example.user.management.util.DataResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public DataResult handle(Exception e) {
        return DataResult.fail(e.getMessage());
    }

}
