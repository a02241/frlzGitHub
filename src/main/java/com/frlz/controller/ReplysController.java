package com.frlz.controller;

import com.frlz.pojo.Replys;
import com.frlz.pojo.User;
import com.frlz.service.ReplysService;
import com.frlz.service.UserService;
import com.frlz.util.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

/**
 * @program frlz
 * @description  博客回复controller
 * @author cz
 * @date 2019-03-04 13:56
 **/
@RestControllerAdvice
@RestController
@Transactional
@Api(value="回复评论controller",tags={"回复评论信息操作接口"})
public class ReplysController {

    private final ReplysService replysService;
    private final UserService userService;

    @Autowired
    public ReplysController(ReplysService replysService, UserService userService){
        this.replysService = replysService;
        this.userService = userService;
    }


    
    /**
     * 根据cId获取该评论的所有回复
     * @title showAllRelysByCId
     * @create by: cz
     * @description  必填参数cId
     * @create time: 2019/3/25 10:23
     * @param cId
     * @return com.frlz.util.R<java.util.HashMap<java.lang.String,java.lang.Object>>
     * @version V1.0
     */
    @PostMapping("showAllRelysByCId")
    @ApiOperation(value="根据cId获取该评论的所有回复", notes="根据url的信息来根据cId获取该评论的所有回复")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cId", value = "评论识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<HashMap<String,Object>> showAllRelysByCId(String cId){
        HashMap<String,Object> map = new HashMap<>(1);
        map.put("replys",replysService.selectRelysAllByCId(cId));
        return R.isOk().data(map);
    }


    /**
     * 添加评论回复
     * @title addReplys
     * @author cz
     * @date 2019/3/4 14:44
     * @param replys(username,content)
     * @description  回复评论,必填字段cId(评论的主键),username,content 成功则返回success
     * @return java.lang.String
     */
    @PostMapping("addReplys")
    @ApiOperation(value="添加评论回复", notes="根据url的信息来添加评论回复")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R addReplys(@ApiParam(name="用户对象",value="cId(评论的主键),content",required=true)Replys replys, String uid){
        User user = userService.selectUserByUid(uid);
        if (user != null) {
            if (user.getExperience() > 0) {
                replys.setUsername(user.getUsername());
                replysService.addReplys(replys);
                return R.isOk().msg("success");
            }else {
                return R.isFail().msg("等级为0,答题后回复评论");
            }
        }else {
            return R.isFail().msg("username错误");
        }
    }
}
