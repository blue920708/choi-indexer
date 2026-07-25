package com.example.indexer.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.example.indexer.common.ApiCode;
import com.example.indexer.common.ApiResult;

import io.sentry.Sentry;

@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResult<?> handleException(Exception e) {
        e.printStackTrace();
        Sentry.captureException(e);
        return new ApiResult<>(ApiCode.FAIL);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResourceFoundException.class)
    public void handleException(NoResourceFoundException e) throws NoResourceFoundException {
    }
}
