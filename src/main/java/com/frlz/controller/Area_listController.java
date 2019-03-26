package com.frlz.controller;

import com.frlz.service.Area_ListService;
import com.frlz.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @program: frlz
 * @description: 所有股票对应信息
 * @author: cz
 * @date: 2019-03-02 10:55
 **/
@RestControllerAdvice
@RestController
public class Area_listController{

    private final Area_ListService area_ListService;

    @Autowired
    public Area_listController(Area_ListService area_ListService){
        this.area_ListService = area_ListService;
    }

    @RequestMapping("selectAllShares")
    public R<List> selectAllShares (){
        return R.isOk().data(area_ListService.selectAll());
    }

}
