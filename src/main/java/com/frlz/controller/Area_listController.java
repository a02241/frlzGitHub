package com.frlz.controller;

import com.frlz.service.Area_ListService;
import com.frlz.service.SessionService;
import com.frlz.util.GetIP;
import com.frlz.util.R;
import com.frlz.util.TaobaoIP;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program frlz
 * @description 所有股票对应信息
 * @author cz
 * @date 2019-03-02 10:55
 **/
@RestControllerAdvice
@RestController
@Api(value="所有股票信息controller",tags={"所有股票信息操作接口"})
public class Area_listController{

    private final Area_ListService area_ListService;
    private final SessionService sessionService;

    @Autowired
    public Area_listController(Area_ListService area_ListService,SessionService sessionService){
        this.area_ListService = area_ListService;
        this.sessionService = sessionService;
    }

    /**
     *
     * @title selectAllShares
     * @create by: cz
     * @description
     * @create time: 2019/3/18 17:23
     * @param
     * @return com.frlz.util.R<java.util.List>
     * @version V1.0
     */
    @RequestMapping("selectAllShares")
    @ApiOperation(value="获取所有股票信息", notes="根据url的信息来获取所有股票信息")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<List> selectAllShares (){
        return R.isOk().data(area_ListService.selectAll());
    }

    @PostMapping("getip")
    public R getip(HttpServletRequest request){
        return R.isOk().data(TaobaoIP.getResult(GetIP.getip(request)));
    }

}
