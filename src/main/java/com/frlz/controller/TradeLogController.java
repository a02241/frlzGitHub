package com.frlz.controller;

import com.frlz.service.TradeLogService;
import com.frlz.util.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @program frlz
 * @description 交易日志controller
 * @author cz
 * @date 2019-03-07 15:42
 **/
@RestControllerAdvice
@RestController
@Api(value="登录日志controller",tags={"登录日志信息操作接口"})
public class TradeLogController {

    private TradeLogService tradeLogService;
    @Autowired
    public TradeLogController(TradeLogService tradeLogService){
        this.tradeLogService = tradeLogService;
    }

    
    /**
     *
     * @title getTradeLog
     * @create by: cz
     * @description 必填参数uid
     * @create time: 2019/3/27 18:06
     * @param uid
     * @return com.frlz.util.R<java.util.List>
     * @version V1.0
     */
    @PostMapping("getTradeLog")
    @ApiOperation(value="获取交易记录", notes="根据uid获取用户的交易记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "uid", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<List> getTradeLog(@RequestParam String uid){
        return R.isOk().data(tradeLogService.getTradeLogByUid(uid));
    }
}
