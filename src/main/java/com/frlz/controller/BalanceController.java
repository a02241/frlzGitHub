package com.frlz.controller;

import com.frlz.pojo.Balance;
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

    @Transactional
    @RequestMapping("/quantumToBlock")
    public String quantumToBlock(String uid,int quantum){
        Balance balance = balanceService.selectFromBanlanceByUid(uid);
        if (balance.getQuantumBalance() >= 10){
            int newQuantum = balance.getQuantumBalance() - quantum;
            balanceService.updateQuantumBalanceByUid(uid,newQuantum);
            int newBlock = balance.getBlockBalance() + quantum / 10 ;
            balanceService.updateBlockBalanceByUid(uid,newBlock);
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
            return "兑换成功";
        }else {
            return "余额不足";
        }

    }
}
