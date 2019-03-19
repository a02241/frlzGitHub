package com.frlz.util;

import com.frlz.pojo.Balance;
import com.frlz.pojo.TradeLog;
import com.frlz.service.BalanceService;
import com.frlz.service.LoginLogService;
import com.frlz.service.TradeLogService;
import com.frlz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @program: frlz
 * @description: 交易工具类
 * @author: cz
 * @date: 2019-03-14 15:57
 **/
@Component
public class BalanceUtil {
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private UserService userService;
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private TradeLogService tradeLogService;
    @Autowired
    private static BalanceUtil balanceUtil;

    @PostConstruct
    public void init(){
        balanceUtil = this;
        balanceUtil.balanceService = this.balanceService;
        balanceUtil.tradeLogService = this.tradeLogService;
        balanceUtil.userService = this.userService;
        balanceUtil.loginLogService = this.loginLogService;
    }
    public void addQuantumBalance(String uid, int count){
        Balance balance = balanceUtil.balanceService.selectFromBanlanceByUid(uid);//根据uid查询余额
        System.out.println(balance.getQuantumBalance()+"~~~~~~~~~~~~~~");
        int countAdd = balance.getQuantumBalance() + count;//交易后量子余额
        balanceUtil.balanceService.updateQuantumBalanceByUid(uid,countAdd);//交易写入数据库
        balanceUtil.tradeLogService.insertTradeLog(balance.getBalanceId(),count,0,0,"奖励增加"+count+"点量子");//写入交易记录
    }
}
