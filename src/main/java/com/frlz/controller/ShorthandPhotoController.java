package com.frlz.controller;

import com.frlz.service.ShorthandPhotoService;
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
public class ShorthandPhotoController {

    private final ShorthandPhotoService shorthandPhotoService;
    @Autowired
    public ShorthandPhotoController(ShorthandPhotoService shorthandPhotoService){
        this.shorthandPhotoService = shorthandPhotoService;
    }
}
