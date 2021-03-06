package com.frlz.controller;

import com.frlz.service.ShorthandTagService;
import io.swagger.annotations.Api;
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
@Api(value="盘中标签controller",tags={"盘中标签信息操作接口"})
public class ShorthandTagController {

    private final ShorthandTagService shorthandTagService;
    @Autowired
    public ShorthandTagController(ShorthandTagService shorthandTagService){
        this.shorthandTagService = shorthandTagService;
    }

}
