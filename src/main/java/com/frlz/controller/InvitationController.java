package com.frlz.controller;

import com.frlz.pojo.Balance;
import com.frlz.pojo.Invitation;
import com.frlz.service.BalanceService;
import com.frlz.service.InvitationService;
import com.frlz.service.TradeLogService;
import com.frlz.util.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
/**
 * @author cz
 */
@RestControllerAdvice
@RestController
@Api(value="邀请码controller",tags={"邀请码信息操作接口"})
public class InvitationController {

    private final InvitationService invitationService;
    private final BalanceService balanceService;
    private final TradeLogService tradeLogService;

    @Autowired
    public InvitationController(InvitationService invitationService,BalanceService balanceService,TradeLogService tradeLogService){
        this.invitationService = invitationService;
        this.balanceService = balanceService;
        this.tradeLogService = tradeLogService;
    }

    
    /**
     * 根据uid找查兑换邀请码信息
     * @title selectInvatationByUid
     * @create by: cz
     * @description TODO 必填参数:uid,返回为邀请表的集合
     * @create time: 2019/3/5 16:19
     * @param uid
     * @return java.util.List<com.frlz.pojo.Invitation>
     * @version V1.0
     */
    @PostMapping("selectInvatationByUid")
    @ApiOperation(value="根据uid找查兑换邀请码信息", notes="根据url的信息来根据uid找查兑换邀请码信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<List<Invitation>> selectInvatationByUid(String uid){
        return R.isOk().data(invitationService.selectInvatationByUid(uid));
    }

    @PostMapping("findStateBycode")
    /**
     * 找查邀请码是否被使用
     * @title findStateBycode
     * @create by: cz
     * @description 必填参数:code,返回值为true则为被使用,false则为未被使用
     * @create time: 2019/3/5 16:20
     * @param code
     * @return java.lang.Boolean
     * @version V1.0
     */
    @ApiOperation(value="找查邀请码是否被使用", notes="根据url的信息来找查邀请码是否被使用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "邀请码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<Boolean> findStateBycode(@RequestParam(defaultValue = "")String code){
        int result = invitationService.findStateBycode(code);
        if (code.trim().length()==0){
            return R.isOk().data("未填写邀请码");
        }
        if (result == 0){
            return R.isFail().data("没有该邀请码");
        }
        if (result == 1){
            return R.isOk().data("邀请码未被使用");
        }
        if (result == 2){
            return R.isFail().data("邀请码已被兑换");
        }
        if (result == 3){
            return R.isFail().data("邀请码已被使用");
        }
        else {
            return R.isFail();
        }
    }

    /**
     *
     * @title exchangeInvitation
     * @create by: cz
     * @description 必填参数uid
     * @create time: 2019/4/18 17:35
     * @param uid
     * @return com.frlz.util.R
     * @version V1.0
     */
    //量子兑换邀请码
    @Transactional
    @PostMapping("exchangeInvitation")
    @ApiOperation(value="量子兑换邀请码", notes="根据url的信息来量子兑换邀请码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R exchangeInvitation(String uid){
        String code;
        Balance balance = balanceService.selectFromBalanceByUid(uid);
        if (balance.getQuantumBalance() >= 10){
            code = invitationService.getOneInvitation().getCode();
            balanceService.updateQuantumBalanceByUid(uid,balance.getQuantumBalance() - 10);
            invitationService.updateInviteUid(uid,code);
            tradeLogService.insertTradeLog(balance.getBalanceId(),-10,0,0,"兑换邀请码");
        }else {
            return R.isFail().msg("余额不足");
        }
        return R.isOk().data(code);
    }




}
