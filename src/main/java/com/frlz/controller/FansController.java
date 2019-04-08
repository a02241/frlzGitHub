package com.frlz.controller;

import com.frlz.pojo.User;
import com.frlz.service.FansService;
import com.frlz.service.UserService;
import com.frlz.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public R<String> notInterest(String uid,String fansUId) {
        fansService.deleteFans(fansUId);
        userService.updateFansNumberReduce(uid);
        userService.updateInterestNumberReduce(fansUId);
        return R.isOk().msg("success");
    }

    @PostMapping("/myFans")
    public R<List> myFans(String uid){
        List<Map> ul = new ArrayList<>();
        for (int i = 0,a = fansService.selectFansForOne(uid).size();i < a;i++){
            User user = userService.selectUserByUid(fansService.selectFansForOne(uid).get(i).getFansUId());
            Map<String,String> map = new HashMap<>();
            map.put("username",user.getUsername());
            map.put("uid",user.getUid());
            map.put("icon",user.getIcon());
            ul.add(map);
        }
        return R.isOk().data(ul);
    }
}
