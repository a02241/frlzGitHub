package com.frlz.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: frlz
 * @description: 统一异常处理类
 * @author: cz
 * @date: 2019-03-22 15:56
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BusinessException.class)
    public R businessExceptionHandler(BusinessException exception) {
        return R.isFail(exception);
    }

    @ExceptionHandler(value = Exception.class)
    public R exceptionHandler(Exception exception) {
        return R.isFail(exception);
    }
}
