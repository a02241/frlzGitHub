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
    /**
     * 密保
     * @title addSecret
     * @create by: cz
     * @description: TODO
     * @create time: 2019/3/25 10:25
     * @Param: uid
     * @Param: secret
     * @throws
     * @return com.frlz.util.R
     * @version V1.0
     */

    public R addSecret(String uid, Secret secret){
        secretService.insertSecret(secret,uid);
        return R.isOk();
    }

    @PostMapping("/getSecret")
    /**！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     * 根据uid
     * @title getSecret
     * @create by: cz
     * @description: TODO uid
     * @create time: 2019/3/25 10:28
     * @Param: uid
     * @throws
     * @return com.frlz.util.R
     * @version V1.0
     */

    public R getSecret(String uid){

        return R.isOk().data(secretService.selectFromSecret(uid));
    }

}
