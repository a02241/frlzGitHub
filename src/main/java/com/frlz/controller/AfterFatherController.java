package com.frlz.controller;

import com.frlz.pojo.AfterTag;
import com.frlz.service.AfterFatherService;
import com.frlz.service.AfterTagService;
import com.frlz.service.UserService;
import com.frlz.util.DateTime;
import com.frlz.util.R;
import com.frlz.utilPojo.UtilAfterFather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

/**
 * @program: frlz
 * @description: 盘前主表controller
 * @author: cz
 * @date: 2019-04-11 10:09
 **/
@RestControllerAdvice
@RestController
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

    @PostMapping("addAfterDiscuss")
    /**
     * TODO 添加和更新标签
     * @title addAfterDiscuss
     * @create by: cz
     * @description: TODO 必填参数uid , shares ,changes,message,highest
     * @create time: 2019/4/11 15:56
     * @Param: uid
     * @Param: afterTag
     * @throws
     * @return com.frlz.util.R
     * @version V1.0
     */

    public R addAfterDiscuss(String uid, AfterTag afterTag){
        if (userService.checkUserByUid(uid) == 0){
            return R.isFail().msg("参数错误");
        }
        if (afterFatherService.checkAfterFatherByUidTime(uid, DateTime.getNowTimeToString()) == 0){
            afterFatherService.addAfterDiscuss(uid);
        }
        if (afterTagService.selectAfterTagByAfterTag(afterTag) == null){//如果没有标签，则添加标签，否则更新
            afterTag.setAfterFatherId(afterFatherService.getAfterFatherId(uid));
            afterTagService.addAfterTag(afterTag);
        }else {
            afterTagService.updateAfterTag(afterTag.getMessage(), afterTagService.selectAfterTagByAfterTag(afterTag).getAfterTagId());
        }
        return R.isOk();
    }

    @PostMapping("searchAfterTag")
    /**
     * TODO 获取盘后观察信息
     * @title searchAfterTag
     * @create by: cz
     * @description: TODO 必填参数uid,time(String)
     * @create time: 2019/4/11 14:55
     * @Param: uid
     * @Param: time
     * @throws
     * @return com.frlz.util.R
     * @version V1.0
     */

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
     * TODO 删除盘后标签
     * @title deleteAfterTag
     * @create by: cz
     * @description: TODO 必填参数 afterTagId
     * @create time: 2019/4/11 15:43
     * @Param: afterTagId
     * @throws
     * @return com.frlz.util.R
     * @version V1.0
     */

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
     * TODO 删除盘后观察
     * @title deleteAfterFather
     * @create by: cz
     * @description: TODO 必填参数 afterFatherId
     * @create time: 2019/4/11 15:50
     * @Param: afterFatherId
     * @throws
     * @return com.frlz.util.R
     * @version V1.0
     */

    public R deleteAfterFather(String afterFatherId){
        if (afterFatherService.checkAfterFatherByAfterFatherId(afterFatherId) != 0){
            afterFatherService.deleteAfterFatherByAfterFatherId(afterFatherId);
            return R.isOk().msg("success");
        }else {
            return R.isFail().msg("参数错误");
        }
    }
}
