package com.frlz.controller;

import com.frlz.mapper.ShorthandTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: frlz
 * @description: 盘中速记股票标签信息controller
 * @author: cz
 * @date: 2019-03-11 15:21
 **/
@RestController
public class ShorthandTagController {
    @Autowired
    private ShorthandTagMapper shorthandTagMapper;
}
