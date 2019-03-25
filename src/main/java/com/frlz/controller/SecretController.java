package com.frlz.controller;

import com.frlz.pojo.Secret;
import com.frlz.service.SecretService;
import com.frlz.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
@RestController
public class SecretController {

    private SecretService secretService;

    @Autowired
    public SecretController(SecretService secretService){
        this.secretService = secretService;
    }

    @PostMapping("/addSecret")
    public R addSecret(String uid, Secret secret){
        secretService.insertSecret(secret,uid);
        return R.isOk();
    }

    @PostMapping("/getSecret")
    public R<Map<String,String>> getSecret(String uid){
        return R.isOk().data(secretService.selectFromSecret(uid));
    }

    @PostMapping("/checkSecret")//传入uid以及三个答案，返回int类型，个位为1则第一题正确，十位为1则第二题正确，百位为1则第三题正确
    public R<Integer> checkSecret(String answer1,String answer2,String answer3,String uid){
        return R.isOk().data(secretService.checkSecret(uid,answer1,answer2,answer3));
    }

}
