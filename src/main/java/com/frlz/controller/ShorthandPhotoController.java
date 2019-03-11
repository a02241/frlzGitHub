package com.frlz.controller;

import com.frlz.service.ShorthandPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: frlz
 * @description: 股票图片位置信息controller
 * @author: cz
 * @date: 2019-03-11 15:16
 **/
@RestController
public class ShorthandPhotoController {
    @Autowired
    private ShorthandPhotoService shorthandPhotoService;
}
