package com.frlz.controller;

import com.frlz.service.FansService;
import com.frlz.service.UserService;
import com.frlz.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/interest")//关注
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

    public R<String> interest(String uid, String fansUId){
        fansService.insertFans(uid,fansUId);
        userService.updateFansNumberAdd(uid);
        userService.updateInterestNumberAdd(fansUId);
        return R.isOk().msg("success");
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

    public R<String> notInterest(String uid,String fansUId){
        fansService.deleteFans(fansUId);
        userService.updateFansNumberReduce(uid);
        userService.updateInterestNumberReduce(fansUId);
        return R.isOk().msg("success");
    }
}
