package com.frlz.controller;

import com.frlz.pojo.Secret;
import com.frlz.service.SecretService;
import com.frlz.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    public R getSecret(String uid){

        return R.isOk().data(secretService.selectFromSecret(uid));
    }

}
