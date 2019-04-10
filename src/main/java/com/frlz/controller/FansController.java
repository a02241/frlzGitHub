package com.frlz.controller;

import com.frlz.service.FansService;
import com.frlz.service.UserService;
import com.frlz.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@RestController
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
    /**
     * TODO 关注
     * @title interest
     * @create by: cz
     * @description: TODO 必填参数uid,fansUid
     * @create time: 2019/3/21 10:43
     * @Param: uid
     * @Param: fansUId
     * @throws
     * @return com.frlz.util.R<java.lang.String>
     * @version V1.0
     */

    public R<String> interest(@PathVariable String uid,@PathVariable String fansUId){
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
    /**
     * TODO 取消关注
     * @title notInterest
     * @create by: cz
     * @description: TODO 必填参数uid,fansUid
     * @create time: 2019/3/21 10:43
     * @Param: uid
     * @Param: fansUId
     * @throws
     * @return com.frlz.util.R<java.lang.String>
     * @version V1.0
     */

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
    /**
     * TODO 查看粉丝
     * @title myFans
     * @create by: cz
     * @description: TODO 必填参数 uid
     * @create time: 2019/4/9 10:48
     * @Param: uid
     * @throws
     * @return com.frlz.util.R<java.util.List>
     * @version V1.0
     */

    public R myFans(String uid){
        if (userService.checkUserByUid(uid) !=0 ){
            return R.isOk().data(fansService.selectFansByUid(uid));
        }else {
            return R.isFail().msg("参数错误");
        }
    }
}
