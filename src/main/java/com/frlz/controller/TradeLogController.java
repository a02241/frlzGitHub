package com.frlz.controller;

import com.frlz.service.TradeLogService;
import com.frlz.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @program: frlz
 * @description: 交易日志controller
 * @author: cz
 * @date: 2019-03-07 15:42
 **/
@RestControllerAdvice
@RestController
public class TradeLogController {

    private TradeLogService tradeLogService;
    @Autowired
    public TradeLogController(TradeLogService tradeLogService){
        this.tradeLogService = tradeLogService;
    }

    @PostMapping("getTradeLog")
    /**
     *
     * @title getTradeLog
     * @create by: cz
     * @description: TODO 必填参数uid
     * @create time: 2019/3/27 18:06
     * @Param: uid
     * @throws
     * @return com.frlz.util.R<java.util.List>
     * @version V1.0
     */

    public R<List> getTradeLog(String uid){
        return R.isOk().data(tradeLogService.getTradeLogByUid(uid));
    }
}
