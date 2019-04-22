package com.frlz.controller;

import com.frlz.pojo.Balance;
import com.frlz.service.BalanceService;
import com.frlz.service.TradeLogService;
import com.frlz.util.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program frlz
 * @description 方块量子余额controller
 * @author cz
 * @date 2019-03-07 11:28
 **/
@RestControllerAdvice
@RestController
@Api(value="用户余额controller",tags={"用户余额操作接口"})
public class BalanceController {

    private final BalanceService balanceService;
    private final TradeLogService tradeLogService;

    @Autowired
    public BalanceController(BalanceService balanceService,TradeLogService tradeLogService){
        this.balanceService = balanceService;
        this.tradeLogService = tradeLogService;
    }

    
    /**
     * 获取账户余额信息
     * @title quantumToBlock
     * @create by: cz
     * @description TODO 必填字段:uid,返回个人余额账户
     * @create time: 2019/3/8 10:24
     * @param uid
     * @throws
     * @return java.lang.String
     * @version V1.0
     */
    @PostMapping("/getBalance")
    @ApiOperation(value="获取账户余额信息", notes="根据url的信息来获取账户余额信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<Balance> getBalance(String uid){
        return R.isOk().data(balanceService.selectFromBalanceByUid(uid));
    }


    /**
     * 量子换方块
     * @title quantumToBlock
     * @create by: cz
     * @description TODO 必填字段:uid,quantum,返回中文信息
     * @create time: 2019/3/8 10:24
     * @param uid
     * @param quantum
     * @throws
     * @return java.lang.String
     * @version V1.0
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/quantumToBlock")
    @ApiOperation(value="量子换方块", notes="根据url的信息来量子换方块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户识别码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "quantum", value = "量子", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R quantumToBlock(String uid, int quantum) {
        Balance balance = balanceService.selectFromBalanceByUid(uid);
        if (balance.getQuantumBalance() >= 10){
            balanceService.updateQuantumBalanceByUid(uid,balance.getQuantumBalance() - quantum);
            balanceService.updateBlockBalanceByUid(uid,balance.getBlockBalance() + quantum / 10);
            tradeLogService.insertTradeLogQuantumToBlock(balance.getBalanceId(),quantum);
            return R.isOk().msg("兑换成功");
        }else {
            return R.isFail().msg("余额不足");
        }

    }


    /**
     * 方块换量子
     * @title blockToQuantum
     * @create by: cz
     * @description TODO 必填字段uid,block,返回中文信息
     * @create time: 2019/3/8 10:26
     * @param uid
     * @param block
     * @throws
     * @return java.lang.String
     * @version V1.0
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/blockToQuantum")
    @ApiOperation(value="方块换量子", notes="根据url的信息来方块换量子")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户识别码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "block", value = "方块", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R blockToQuantum(String uid, int block){
        Balance balance = balanceService.selectFromBalanceByUid(uid);
        if (balance.getBlockBalance() > 0){
            balanceService.updateQuantumBalanceByUid(uid,balance.getQuantumBalance() + block * 10);
            balanceService.updateBlockBalanceByUid(uid,balance.getBlockBalance() - block);
            tradeLogService.insertTradeLogBlockToQuantum(balance.getBalanceId(),block);
            return R.isOk().msg("兑换成功");
        }else {
            return R.isFail().msg("余额不足");
        }

    }


    /**
     * 魔方换方块
     * @title magicCubeToBlock
     * @create by: cz
     * @description TODO 必填字段uid,magicCube,返回中文参数
     * @create time: 2019/3/8 10:27
     * @param uid
     * @param magicCube
     * @throws
     * @return java.lang.String
     * @version V1.0
     */
    @Transactional
    @PostMapping("/magicCubeToBlock")
    @ApiOperation(value="魔方换方块", notes="根据url的信息来魔方换方块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户识别码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "magicCube", value = "魔方", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R magicCubeToBlock(String uid, int magicCube){
        Balance balance = balanceService.selectFromBalanceByUid(uid);
        if (balance.getMagicCubeBalance() > 0){
            balanceService.updateBlockBalanceByUid(uid,balance.getBlockBalance() + magicCube * 26);
            balanceService.updateMagicCubeBalanceByUid(uid,balance.getMagicCubeBalance() - magicCube);
            tradeLogService.insertTradeLogMagicCubeToBlock(balance.getBalanceId(),magicCube);
            return R.isOk().msg("兑换成功");
        }else {
            return R.isFail().msg("余额不足");
        }

    }


    /**
     * 方块换魔方
     * @title blockToMagicCube
     * @create by: cz
     * @description TODO 必填字段uid,block,返回中文参数
     * @create time: 2019/3/8 10:28
     * @param uid
     * @param block
     * @throws
     * @return java.lang.String
     * @version V1.0
     */
    @Transactional
    @PostMapping("/blockToMagicCube")
    @ApiOperation(value="方块换魔方", notes="根据url的信息来方块换魔方")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户识别码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "block", value = "方块", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R blockToMagicCube(String uid, int block){
        Balance balance = balanceService.selectFromBalanceByUid(uid);
        if (balance.getBlockBalance() >= 27){
            balanceService.updateBlockBalanceByUid(uid,balance.getBlockBalance() - block);
            balanceService.updateMagicCubeBalanceByUid(uid,balance.getMagicCubeBalance() + block / 27);
            tradeLogService.insertTradeLogBlockToMagicCube(balance.getBalanceId(),block);
            return R.isOk().msg("兑换成功");
        }else {
            return R.isFail().msg("余额不足");
        }

    }
}
