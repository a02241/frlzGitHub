package com.frlz.util;

/**
 * @program: frlz
 * @description: 自定义业务异常类
 * @author: cz
 * @date: 2019-03-22 15:55
 **/
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
