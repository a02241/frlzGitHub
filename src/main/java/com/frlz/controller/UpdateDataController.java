package com.frlz.controller;

import com.frlz.service.UpdateDataService;
import com.frlz.util.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: frlz
 * @description: 股票数据更新脚本
 * @author: cz
 * @date: 2019-03-26 09:20
 **/
@RestController
@RestControllerAdvice
public class UpdateDataController {
    private UpdateDataService updateDataService;
    @Autowired
    public UpdateDataController(UpdateDataService updateDataService){
        this.updateDataService = updateDataService;
    }

    @PostMapping("updateData")
    @ApiOperation(value="更新数据", notes="更新每日数据")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<String> updateData(){
        updateDataService.updateData();
        return R.isOk().msg("成功");
    }
}
