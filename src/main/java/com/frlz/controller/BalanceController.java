package com.frlz.controller;

import com.frlz.mapper.TradeLogMapper;
import com.frlz.pojo.Balance;
import com.frlz.pojo.TradeLog;
import com.frlz.service.BalanceService;
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

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private TradeLogMapper tradeLogMapper;

    @Transactional
    @RequestMapping("/quantumToBlock")
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
            tradeLogMapper.insertTradeLogMapper(tradeLog);
            return "兑换成功";
        }else {
            return "余额不足";
        }

    }

    @Transactional
    @RequestMapping("/blockToQuantum")
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
            tradeLogMapper.insertTradeLogMapper(tradeLog);
            return "兑换成功";
        }else {
            return "余额不足";
        }

    }

    @Transactional
    @RequestMapping("/magicCubeToBlock")
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
            tradeLogMapper.insertTradeLogMapper(tradeLog);
            return "兑换成功";
        }else {
            return "余额不足";
        }

    }

    @Transactional
    @RequestMapping("/blockToMagicCube")
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
            tradeLogMapper.insertTradeLogMapper(tradeLog);
            return "兑换成功";
        }else {
            return "余额不足";
        }

    }
}
