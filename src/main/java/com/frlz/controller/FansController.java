package com.frlz.controller;

import com.frlz.service.FansService;
import com.frlz.service.UserService;
import com.frlz.util.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@RestController
@Api(value="粉丝controller",tags={"粉丝信息操作接口"})
public class FansController {


    private final FansService fansService;
    private final UserService userService;
    @Autowired
    public FansController(FansService fansService,UserService userService){
        this.fansService = fansService;
        this.userService = userService;
    }

    @Transactional
    @RequestMapping("/interest")//关注
    @ApiOperation(value="关注", notes="根据url的信息来关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户识别码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "fansUId", value = "粉丝用户识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<String> interest(String uid,String fansUId){
        if (userService.checkUserByUid(uid) != 0 && userService.checkUserByUid(fansUId) != 0){
            fansService.insertFans(uid,fansUId);
            userService.updateFansNumberAdd(uid);
            userService.updateInterestNumberAdd(fansUId);
            return R.isOk().msg("success");
        }else {
            return R.isFail().msg("参数错误");
        }
    }

    @Transactional
    @PostMapping("/notInterest")//取消关注
    @ApiOperation(value="取消关注", notes="根据url的信息来取消关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户识别码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "fansUId", value = "粉丝用户识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<String> notInterest(String uid,String fansUId) {
        if (userService.checkUserByUid(uid) != 0 && userService.checkUserByUid(fansUId) != 0) {
            fansService.deleteFans(fansUId);
            userService.updateFansNumberReduce(uid);
            userService.updateInterestNumberReduce(fansUId);
            return R.isOk().msg("success");
        }else {
            return R.isFail().msg("参数错误");
        }
    }

    @PostMapping("/myFans")
    @ApiOperation(value="查看粉丝", notes="根据url的信息来查看粉丝")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R myFans(String uid){
        if (userService.checkUserByUid(uid) !=0 ){
            return R.isOk().data(fansService.selectFansByUid(uid));
        }else {
            return R.isFail().msg("参数错误");
        }
    }
}
