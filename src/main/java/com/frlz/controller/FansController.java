package com.frlz.controller;

import com.frlz.service.FansService;
import com.frlz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FansController {


    private final FansService fansService;
    private final UserService userService;
    @Autowired
    public FansController(FansService fansService,UserService userService){
        this.fansService = fansService;
        this.userService = userService;
    }

    @Transactional
    @RequestMapping("/interest")//关注
    public String interest(String uid,String fansUId){
        fansService.insertFans(uid,fansUId);
        userService.updateFansNumberAdd(uid);
        userService.updateInterestNumberAdd(fansUId);
        return "success";
    }

    @Transactional
    @RequestMapping("/notInterest")//取消关注
    public String notInterest(String uid,String fansUId){
        fansService.deleteFans(fansUId);
        userService.updateFansNumberReduce(uid);
        userService.updateInterestNumberReduce(fansUId);
        return "success";
    }
}
