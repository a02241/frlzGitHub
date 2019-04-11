package com.frlz.controller;

import com.frlz.service.AfterFatherService;
import com.frlz.service.AfterTagService;
import com.frlz.service.UserService;
import com.frlz.util.R;
import com.frlz.utilPojo.UtilAfterFather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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

    @PostMapping("searchAfterTag")
    public R searchAfterTag(String uid , String time){
        if (afterFatherService.checkAfterFatherByUid(uid) !=0){
            List<UtilAfterFather> utilAfterFathers = afterFatherService.selectAfterByTimeUid(uid,time);
            return R.isOk().data(utilAfterFathers);
        }else {
            return R.isFail().msg("参数错误");
        }
    }
}
