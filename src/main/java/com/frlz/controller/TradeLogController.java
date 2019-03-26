package com.frlz.controller;

import com.frlz.service.TradeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
