package com.frlz.controller;

import com.frlz.service.UpdateDataService;
import com.frlz.util.R;
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
    public R<String> updateData(){
        updateDataService.updateData();
        return R.isOk().msg("成功");
    }
}
