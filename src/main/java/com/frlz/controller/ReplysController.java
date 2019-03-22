package com.frlz.controller;

import com.frlz.pojo.Replys;
import com.frlz.service.ReplysService;
import com.frlz.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: frlz
 * @description: TODO 博客回复controller
 * @author: cz
 * @date: 2019-03-04 13:56
 **/
@RestControllerAdvice
@RestController
public class ReplysController {

    private final ReplysService replysService;

    @Autowired
    public ReplysController(ReplysService replysService){
        this.replysService = replysService;
    }

    @PostMapping("addReplys")
    /**
     * 添加评论回复
     * @title addReplys
     * @author cz
     * @date 2019/3/4 14:44
     * @param replys
     * @Description: TODO 回复评论,必填字段cId(评论的主键),username,content 成功则返回success
     * @return java.lang.String
     * @throws
     */

    public R<String> addReplys(Replys replys){
        replysService.addReplys(replys);
        return R.isOk().msg("success");
    }
}
