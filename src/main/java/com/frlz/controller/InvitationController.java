package com.frlz.controller;

import com.frlz.pojo.Invitation;
import com.frlz.service.InvitationService;
import com.frlz.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InvitationController {

    private final InvitationService invitationService;

    @Autowired
    public InvitationController(InvitationService invitationService){
        this.invitationService = invitationService;
    }

    @PostMapping("selectInvatationByUid")
    /**
     * 根据uid找查兑换邀请码信息
     * @title selectInvatationByUid
     * @create by: cz
     * @description: TODO 必填参数:uid,返回为邀请表的集合
     * @create time: 2019/3/5 16:19
     * @Param: uid
     * @throws
     * @return java.util.List<com.frlz.pojo.Invitation>
     * @version V1.0
     */
    public R<List<Invitation>> selectInvatationByUid(String uid) throws Exception{
        List<Invitation> invitation = invitationService.selectInvatationByUid(uid);
        return R.isOk().data(invitation);
    }

    @PostMapping("findStateBycode")
    /**
     * 找查邀请码是否被使用
     * @title findStateBycode
     * @create by: cz
     * @description: 必填参数:code,返回值为true则为被使用,false则为未被使用
     * @create time: 2019/3/5 16:20
     * @Param: code
     * @throws
     * @return java.lang.Boolean
     * @version V1.0
     */

    public R<Boolean> findStateBycode(String code){
        int returnResult = invitationService.findStateBycode(code);
        if (returnResult>0){
            return R.isOk();
        }else {
            return R.isFail();
        }
    }




}
