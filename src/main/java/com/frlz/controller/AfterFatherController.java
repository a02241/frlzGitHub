package com.frlz.controller;

import com.frlz.pojo.AfterTag;
import com.frlz.service.AfterFatherService;
import com.frlz.service.AfterTagService;
import com.frlz.service.UserService;
import com.frlz.util.DateTime;
import com.frlz.util.R;
import com.frlz.utilPojo.UtilAfterFather;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program frlz
 * @description 盘前主表controller
 * @author cz
 * @date 2019-04-11 10:09
 **/
@RestControllerAdvice
@RestController
@Api(value="盘后速记controller",tags={"盘后操作接口"})
public class AfterFatherController {
    private final AfterFatherService afterFatherService;
    private final AfterTagService afterTagService;
    private final UserService userService;

    @Autowired
    public AfterFatherController(AfterFatherService afterFatherService, AfterTagService afterTagService, UserService userService) {
        this.afterFatherService = afterFatherService;
        this.afterTagService = afterTagService;
        this.userService = userService;
    }


    /**
     *  添加和更新标签
     * @title addAfterDiscuss
     * @create by: cz
     * @description  必填参数uid , shares ,changes,message,highest
     * @create time: 2019/4/11 15:56
     * @param uid
     * @param afterTag
     *
     * @return com.frlz.util.R
     * @version V1.0
     */
    @PostMapping("addAfterDiscuss")
    @ApiOperation(value="添加和更新标签", notes="根据url的信息来添加和更新标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R addAfterDiscuss(@ApiParam(name="盘和标签对象",value="必填参数shares ,changes,message,highest",required=true) AfterTag afterTag , String uid){
        if (userService.checkUserByUid(uid) == 0){
            return R.isFail().msg("参数错误");
        }
        if (afterFatherService.checkAfterFatherByUidTime(uid, DateTime.getNowTimeToString()) == 0){
            afterFatherService.addAfterDiscuss(uid);
        }
        afterTag.setAfterFatherId(afterFatherService.getAfterFatherId(uid,DateTime.getNowTimeToString()));
        if (afterTagService.selectAfterTagByAfterTag(afterTag) == null){//如果没有标签，则添加标签，否则更新
            afterTag.setAfterFatherId(afterFatherService.getAfterFatherId(uid,DateTime.getNowTimeToString()));
            afterTagService.addAfterTag(afterTag);
        }else {
            afterTagService.updateAfterTag(afterTag.getMessage(), afterTagService.selectAfterTagByAfterTag(afterTag).getAfterTagId());
        }
        return R.isOk();
    }

    @PostMapping("searchAfterTag")
    /**
     *  获取盘后观察信息
     * @title searchAfterTag
     * @create by: cz
     * @description  必填参数uid,time(String)
     * @create time: 2019/4/11 14:55
     * @param uid
     * @param time
     *
     * @return com.frlz.util.R
     * @version V1.0
     */
    @ApiOperation(value="获取盘后观察信息", notes="根据url的信息来获取盘后观察信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户识别码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "time", value = "时间(格式yyyy-mm-dd)", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R searchAfterTag(String uid , String time){
        if (afterFatherService.checkAfterFatherByUid(uid) != 0){
            List<UtilAfterFather> utilAfterFathers = afterFatherService.selectAfterByTimeUid(uid,time);
            return R.isOk().data(utilAfterFathers);
        }else {
            return R.isFail().msg("参数错误");
        }
    }

    @PostMapping("deleteAfterTag")
    /**
     *  删除盘后标签
     * @title deleteAfterTag
     * @create by: cz
     * @description  必填参数 afterTagId
     * @create time: 2019/4/11 15:43
     * @param afterTagId
     * 
     * @return com.frlz.util.R
     * @version V1.0
     */
    @ApiOperation(value="删除盘后标签", notes="根据url的信息来删除盘后标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "afterTagId", value = "盘后标签识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R deleteAfterTag(String afterTagId){
        if (afterTagService.checkAfterTagByAfterTagId(afterTagId) != 0){
            afterTagService.deleteAfterTagByAfterTagId(afterTagId);
            return R.isOk().msg("success");
        }else {
            return R.isFail().msg("参数错误");
        }
    }

    @PostMapping("deleteAfterFather")
    /**
     *  删除盘后观察
     * @title deleteAfterFather
     * @create by: cz
     * @description  必填参数 afterFatherId
     * @create time: 2019/4/11 15:50
     * @param afterFatherId
     * 
     * @return com.frlz.util.R
     * @version V1.0
     */
    @ApiOperation(value="删除盘后观察", notes="根据url的信息来删删除盘后观察")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "afterFatherId", value = "盘后父表识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R deleteAfterFather(String afterFatherId){
        if (afterFatherService.checkAfterFatherByAfterFatherId(afterFatherId) != 0){
            afterFatherService.deleteAfterFatherByAfterFatherId(afterFatherId);
            return R.isOk().msg("success");
        }else {
            return R.isFail().msg("参数错误");
        }
    }
}
