package com.frlz.controller;

import com.frlz.service.Area_ListService;
import com.frlz.service.SessionService;
import com.frlz.util.GetIP;
import com.frlz.util.R;
import com.frlz.util.TaobaoIP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
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
    private final SessionService sessionService;

    @Autowired
    public Area_listController(Area_ListService area_ListService,SessionService sessionService){
        this.area_ListService = area_ListService;
        this.sessionService = sessionService;
    }

    @RequestMapping("selectAllShares")
    public R<List> selectAllShares (){
        return R.isOk().data(area_ListService.selectAll());
    }

    @PostMapping("getip")
    public R getip(HttpServletRequest request){
        return R.isOk().data(TaobaoIP.getResult(GetIP.getip(request)));
    }

}
