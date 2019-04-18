package com.frlz.controller;

import com.frlz.pojo.Secret;
import com.frlz.service.SecretService;
import com.frlz.util.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
@RestController
@Api(value="密保controller",tags={"密保信息操作接口"})
public class SecretController {

    private final SecretService secretService;

    @Autowired
    public SecretController(SecretService secretService){
        this.secretService = secretService;
    }

    
    /**
     *  添加密保
     * @title addSecret
     * @create by: cz
     * @description  必填参数uid,secret
     * @create time: 2019/3/26 17:46
     * @param secret(uid,secret)
     * @return com.frlz.util.R
     * @version V1.0
     */
    @PostMapping("/addSecret")
    @ApiOperation(value="添加密保", notes="根据url的信息来添加密保")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R addSecret( @ApiParam(name="用户对象",value="必填参数uid,secret",required=true)Secret secret){
        secretService.insertSecret(secret);
        return R.isOk();
    }

    
    /**
     * 
     * @title getSecret
     * @create by: cz
     * @description 
     * @create time: 2019/3/26 17:47
     * @param uid
     * @return com.frlz.util.R<java.util.Map<java.lang.String,java.lang.String>>
     * @version V1.0
     */
    @PostMapping("/getSecret")
    @ApiOperation(value="获取密保", notes="根据url的信息来获取密保")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<Map<String,String>> getSecret(String uid){
        return R.isOk().data(secretService.selectFromSecret(uid));
    }


    /**
     *  验证密保正确性
     * @title checkSecret
     * @create by: cz
     * @description  必填参数 answer1,answer2,answer3,uid
     * @create time: 2019/4/1 9:26
     * @param answer1
     * @param answer2
     * @param answer3
     * @param uid
     * @return com.frlz.util.R<java.lang.Integer>
     * @version V1.0
     */
    //传入uid以及三个答案，返回int类型，个位为1则第一题正确，十位为1则第二题正确，百位为1则第三题正确
    @PostMapping("/checkSecret")
    @ApiOperation(value="验证密保正确性", notes="根据url的信息来验证密保正确性")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "answer1", value = "答案1", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "answer2", value = "答案2", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "answer3", value = "答案3", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "uid", value = "用户识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<Integer> checkSecret(String answer1,String answer2,String answer3,String uid){
        return R.isOk().data(secretService.checkSecret(uid,answer1,answer2,answer3));
    }

    /**
     *
     * @title changeSecret
     * @create by: cz
     * @description 必填参数uid选填问题和答案
     * @create time: 2019/4/18 17:45
     * @param secret(必填参数uid选填问题和答案)
     * @return com.frlz.util.R
     * @version V1.0
     */
    @PostMapping("changeSecret")
    @ApiOperation(value="验证密保正确性", notes="根据url的信息来验证密保正确性")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R changeSecret( @ApiParam(name="密保对象",value="必填参数uid，选填问题和答案",required=true)Secret secret){
        secretService.updateSecret(secret);
        return R.isOk();
    }

}
