package com.frlz.controller;

import com.frlz.service.TradeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: frlz
 * @description: 交易日志controller
 * @author: cz
 * @date: 2019-03-07 15:42
 **/
@RestController
public class TradeLogController {

    @Autowired
    private TradeLogService tradeLogService;
}