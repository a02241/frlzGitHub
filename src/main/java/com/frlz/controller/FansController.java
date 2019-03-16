package com.frlz.controller;

import com.frlz.service.FansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FansController {


    private final FansService fansService;
    @Autowired
    public FansController(FansService fansService){
        this.fansService = fansService;
    }

    @RequestMapping("/fans")
    public String fans(String uid,String fansUId){
        fansService.insertFans(uid,fansUId);
        return "success";
    }
}
