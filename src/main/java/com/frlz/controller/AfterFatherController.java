package com.frlz.controller;

import com.frlz.pojo.AfterTag;
import com.frlz.service.AfterFatherService;
import com.frlz.service.AfterTagService;
import com.frlz.service.UserService;
import com.frlz.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: frlz
 * @description: 盘前主表controller
 * @author: cz
 * @date: 2019-04-11 10:09
 **/
@RestControllerAdvice
@RestController
public class AfterFatherController {
    private final AfterFatherService afterFatherService;
    private final AfterTagService afterTagService;
    private final UserService userService;

    @Autowired
    public AfterFatherController(AfterFatherService afterFatherService, AfterTagService afterTagService, UserService userService) {
        this.afterFatherService = afterFatherService;
        this.afterTagService = afterTagService;
        this.userService = userService;
    }

    @PostMapping("addAfterDiscuss")
    public R addAfterDiscuss(String uid, AfterTag afterTag){
        afterFatherService.addAfterDiscuss(uid,afterTag);
        return R.isOk();
    }
}
