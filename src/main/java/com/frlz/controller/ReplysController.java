package com.frlz.controller;

import com.frlz.pojo.Replys;
import com.frlz.service.ReplysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: frlz
 * @description: TODO 博客回复controller
 * @author: cz
 * @date: 2019-03-04 13:56
 **/
@RestController
public class ReplysController {
    @Autowired
    ReplysService replysService;

    @RequestMapping("addReplys")
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

    public String addReplys(Replys replys){
        replysService.addReplys(replys);
        return "success";
    }
}
