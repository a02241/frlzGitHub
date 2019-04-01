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
    /**
     * TODO 添加密保
     * @title addSecret
     * @create by: cz
     * @description: TODO 必填参数uid,secret
     * @create time: 2019/3/26 17:46
     * @Param: uid
     * @Param: secret
     * @throws
     * @return com.frlz.util.R
     * @version V1.0
     */

    public R addSecret(Secret secret){
        secretService.insertSecret(secret);
        return R.isOk();
    }

    @PostMapping("/getSecret")
    /**
     * 
     * @title getSecret
     * @create by: cz
     * @description: TODO
     * @create time: 2019/3/26 17:47
     * @Param: uid
     * @throws 
     * @return com.frlz.util.R<java.util.Map<java.lang.String,java.lang.String>>
     * @version V1.0
     */
    
    public R<Map<String,String>> getSecret(String uid){
        return R.isOk().data(secretService.selectFromSecret(uid));
    }

    @PostMapping("/checkSecret")
    /**
     * TODO 验证密保正确性
     * @title checkSecret
     * @create by: cz
     * @description: TODO 必填参数 answer1,answer2,answer3,uid
     * @create time: 2019/4/1 9:26
     * @Param: answer1
     * @Param: answer2
     * @Param: answer3
     * @Param: uid
     * @throws 
     * @return com.frlz.util.R<java.lang.Integer>
     * @version V1.0
     */
    //传入uid以及三个答案，返回int类型，个位为1则第一题正确，十位为1则第二题正确，百位为1则第三题正确
    public R<Integer> checkSecret(String answer1,String answer2,String answer3,String uid){
        return R.isOk().data(secretService.checkSecret(uid,answer1,answer2,answer3));
    }

    public R changeSecret(Secret secret){
        secretService.updateSecret(secret);
        return R.isOk();
    }

}
