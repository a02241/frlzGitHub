package com.frlz.controller;

import com.frlz.pojo.Replys;
import com.frlz.pojo.User;
import com.frlz.service.ReplysService;
import com.frlz.service.UserService;
import com.frlz.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

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
    private final UserService userService;

    @Autowired
    public ReplysController(ReplysService replysService, UserService userService){
        this.replysService = replysService;
        this.userService = userService;
    }


    @PostMapping("showAllRelysByCId")
    /**
     * 根据cId获取该评论的所有回复
     * @title showAllRelysByCId
     * @create by: cz
     * @description: TODO 必填参数cId
     * @create time: 2019/3/25 10:23
     * @Param: cId
     * @throws
     * @return com.frlz.util.R<java.util.HashMap<java.lang.String,java.lang.Object>>
     * @version V1.0
     */

    public R<HashMap<String,Object>> showAllRelysByCId(String cId){
        HashMap<String,Object> map = new HashMap<>();
        map.put("replys",replysService.selectRelysAllByCId(cId));
        return R.isOk().data(map);
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
        User user = userService.selectUserByUsername(replys.getUsername());
        if (user != null) {
            if (user.getExperience() > 0) {
                replysService.addReplys(replys);
            }else {
                return R.isFail().msg("等级为0,答题后回复评论");
            }
        }else {
            return R.isFail().msg("username错误");
        }
        return R.isFail().msg("参数错误");
    }
}
