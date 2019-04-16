package com.frlz.controller;

import com.frlz.service.ShorthandPhotoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: frlz
 * @description: 股票图片位置信息controller
 * @author: cz
 * @date: 2019-03-11 15:16
 **/
@RestControllerAdvice
@RestController
@Api(value="盘中图片信息controller",tags={"盘中图片信息操作接口"})
public class ShorthandPhotoController {

    private final ShorthandPhotoService shorthandPhotoService;
    @Autowired
    public ShorthandPhotoController(ShorthandPhotoService shorthandPhotoService){
        this.shorthandPhotoService = shorthandPhotoService;
    }
}
