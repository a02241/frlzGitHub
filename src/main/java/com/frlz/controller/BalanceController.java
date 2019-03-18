package com.frlz.controller;

import com.frlz.pojo.Balance;
import com.frlz.pojo.TradeLog;
import com.frlz.service.BalanceService;
import com.frlz.service.TradeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: frlz
 * @description: 方块量子余额controller
 * @author: cz
 * @date: 2019-03-07 11:28
 **/
@RestController
public class BalanceController {

    private final BalanceService balanceService;
    private final TradeLogService tradeLogService;

    @Autowired
    public BalanceController(BalanceService balanceService,TradeLogService tradeLogService){
        this.balanceService = balanceService;
        this.tradeLogService = tradeLogService;
    }

    @Transactional
    @RequestMapping("/quantumToBlock")
    /**
     * 量子换方块
     * @title quantumToBlock
     * @create by: cz
     * @description: TODO 必填字段:uid,quantum,返回中文信息
     * @create time: 2019/3/8 10:24
     * @Param: uid
     * @Param: quantum
     * @throws
     * @return java.lang.String
     * @version V1.0
     */

    public String quantumToBlock(String uid,int quantum){
        Balance balance = balanceService.selectFromBanlanceByUid(uid);
        if (balance.getQuantumBalance() >= 10){
            int newQuantum = balance.getQuantumBalance() - quantum;
            balanceService.updateQuantumBalanceByUid(uid,newQuantum);
            int newBlock = balance.getBlockBalance() + quantum / 10 ;
            balanceService.updateBlockBalanceByUid(uid,newBlock);
            TradeLog tradeLog = new TradeLog();
            tradeLog.setBalanceId(balance.getBalanceId());
            tradeLog.setTradeQuantum(-quantum);
            tradeLog.setTradeBlock(quantum/10);
            tradeLog.setRemarks("量子兑换方块");
            tradeLogService.insertTradeLog(tradeLog);
            return "兑换成功";
        }else {
            return "余额不足";
        }

    }

    @Transactional
    @RequestMapping("/blockToQuantum")
    /**
     * 方块换量子
     * @title blockToQuantum
     * @create by: cz
     * @description: TODO 必填字段uid,block,返回中文信息
     * @create time: 2019/3/8 10:26
     * @Param: uid
     * @Param: block
     * @throws
     * @return java.lang.String
     * @version V1.0
     */

    public String blockToQuantum(String uid,int block){
        Balance balance = balanceService.selectFromBanlanceByUid(uid);
        if (balance.getBlockBalance() > 0){
            int newQuantum = balance.getQuantumBalance() + block * 10;
            balanceService.updateQuantumBalanceByUid(uid,newQuantum);
            int newBlock = balance.getBlockBalance() - block ;
            balanceService.updateBlockBalanceByUid(uid,newBlock);
            TradeLog tradeLog = new TradeLog();
            tradeLog.setBalanceId(balance.getBalanceId());
            tradeLog.setTradeQuantum(block * 10);
            tradeLog.setTradeBlock(-block);
            tradeLog.setRemarks("方块兑换量子");
            tradeLogService.insertTradeLog(tradeLog);
            return "兑换成功";
        }else {
            return "余额不足";
        }

    }

    @Transactional
    @RequestMapping("/magicCubeToBlock")
    /**
     * 魔方换方块
     * @title magicCubeToBlock
     * @create by: cz
     * @description: TODO 必填字段uid,magicCube,返回中文参数
     * @create time: 2019/3/8 10:27
     * @Param: uid
     * @Param: magicCube
     * @throws
     * @return java.lang.String
     * @version V1.0
     */

    public String magicCubeToBlock(String uid,int magicCube){
        Balance balance = balanceService.selectFromBanlanceByUid(uid);
        if (balance.getMagicCubeBalance() > 0){
            int newBlock = balance.getBlockBalance() + magicCube * 26;
            balanceService.updateBlockBalanceByUid(uid,newBlock);
            int newMagicCube = balance.getMagicCubeBalance() - magicCube;
            balanceService.updateMagicCubeBalanceByUid(uid,newMagicCube);
            TradeLog tradeLog = new TradeLog();
            tradeLog.setBalanceId(balance.getBalanceId());
            tradeLog.setTradeMagicCube(-magicCube);
            tradeLog.setTradeBlock(magicCube * 26);
            tradeLog.setRemarks("魔方兑换方块");
            tradeLogService.insertTradeLog(tradeLog);
            return "兑换成功";
        }else {
            return "余额不足";
        }

    }

    @Transactional
    @RequestMapping("/blockToMagicCube")
    /**
     * 方块换魔方
     * @title blockToMagicCube
     * @create by: cz
     * @description: TODO 必填字段uid,block,返回中文参数
     * @create time: 2019/3/8 10:28
     * @Param: uid
     * @Param: block
     * @throws
     * @return java.lang.String
     * @version V1.0
     */

    public String blockToMagicCube(String uid,int block){
        Balance balance = balanceService.selectFromBanlanceByUid(uid);
        if (balance.getBlockBalance() >= 27){
            int newBlock = balance.getBlockBalance() - block;
            balanceService.updateBlockBalanceByUid(uid,newBlock);
            int newMagicCube = balance.getMagicCubeBalance() + block / 27;
            balanceService.updateMagicCubeBalanceByUid(uid,newMagicCube);
            TradeLog tradeLog = new TradeLog();
            tradeLog.setBalanceId(balance.getBalanceId());
            tradeLog.setTradeMagicCube(block / 27);
            tradeLog.setTradeBlock(-block);
            tradeLog.setRemarks("方块兑换魔方");
            tradeLogService.insertTradeLog(tradeLog);
            return "兑换成功";
        }else {
            return "余额不足";
        }

    }
}
