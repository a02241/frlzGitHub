package com.frlz.controller;

import com.frlz.pojo.Balance;
import com.frlz.service.BalanceService;
import com.frlz.service.TradeLogService;
import com.frlz.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: frlz
 * @description: 方块量子余额controller
 * @author: cz
 * @date: 2019-03-07 11:28
 **/
@RestControllerAdvice
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
    @PostMapping("/quantumToBlock")
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

    public R<String> quantumToBlock(String uid, int quantum) throws Exception {
        Balance balance = null;
        try {
            balance = balanceService.selectFromBanlanceByUid(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (balance.getQuantumBalance() >= 10){
            try {
                balanceService.updateQuantumBalanceByUid(uid,balance.getQuantumBalance() - quantum);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                balanceService.updateBlockBalanceByUid(uid,balance.getBlockBalance() + quantum / 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            tradeLogService.insertTradeLogQuantumToBlock(balance.getBalanceId(),quantum);
            return R.isOk().msg("兑换成功");
        }else {
            return R.isFail().msg("余额不足");
        }

    }

    @Transactional
    @PostMapping("/blockToQuantum")
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

    public R<String> blockToQuantum(String uid,int block){
        Balance balance = null;
        try {
            balance = balanceService.selectFromBanlanceByUid(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (balance.getBlockBalance() > 0){
            try {
                balanceService.updateQuantumBalanceByUid(uid,balance.getQuantumBalance() + block * 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                balanceService.updateBlockBalanceByUid(uid,balance.getBlockBalance() - block);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                tradeLogService.insertTradeLogBlockToQuantum(balance.getBalanceId(),block);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return R.isOk().msg("兑换成功");
        }else {
            return R.isFail().msg("余额不足");
        }

    }

    @Transactional
    @PostMapping("/magicCubeToBlock")
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

    public R<String> magicCubeToBlock(String uid,int magicCube){
        Balance balance = null;
        try {
            balance = balanceService.selectFromBanlanceByUid(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (balance.getMagicCubeBalance() > 0){
            try {
                balanceService.updateBlockBalanceByUid(uid,balance.getBlockBalance() + magicCube * 26);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                balanceService.updateMagicCubeBalanceByUid(uid,balance.getMagicCubeBalance() - magicCube);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                tradeLogService.insertTradeLogMagicCubeToBlock(balance.getBalanceId(),magicCube);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return R.isOk().msg("兑换成功");
        }else {
            return R.isFail().msg("余额不足");
        }

    }

    @Transactional
    @PostMapping("/blockToMagicCube")
    /**
     * 方块换魔方
     * @title blockToMagicCube
     * @create by: cz
     * @description: TODO 必填字段uid,block,返回中文参数
     * @create time: 2019/3/8 10:28
     * @Param: uid
     * @Param: block
     * @throws
     * @return java.lang.String
     * @version V1.0
     */

    public R<String> blockToMagicCube(String uid,int block){
        Balance balance = null;
        try {
            balance = balanceService.selectFromBanlanceByUid(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (balance.getBlockBalance() >= 27){
            try {
                balanceService.updateBlockBalanceByUid(uid,balance.getBlockBalance() - block);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                balanceService.updateMagicCubeBalanceByUid(uid,balance.getMagicCubeBalance() + block / 27);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                tradeLogService.insertTradeLogBlockToMagicCube(balance.getBalanceId(),block);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return R.isOk().msg("兑换成功");
        }else {
            return R.isFail().msg("余额不足");
        }

    }
}
