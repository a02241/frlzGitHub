package com.frlz.controller;

import com.frlz.service.ShorthandTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: frlz
 * @description: TODO 盘中速记股票标签信息controller
 * @author: cz
 * @date: 2019-03-11 15:21
 **/
@RestControllerAdvice
@RestController
public class ShorthandTagController {
    @Autowired
    private ShorthandTagService shorthandTagService;
}
