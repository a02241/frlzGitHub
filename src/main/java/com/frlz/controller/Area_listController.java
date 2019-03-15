package com.frlz.controller;

import com.frlz.pojo.Area_List;
import com.frlz.service.Area_ListService;
import com.frlz.util.GetIP;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: frlz
 * @description: 所有股票对应信息
 * @author: cz
 * @date: 2019-03-02 10:55
 **/
@RestController
public class Area_listController{
    @RequestMapping("/getip")
    public String getip(HttpServletRequest request) throws Exception {
        GetIP getIP = new GetIP();
        return getIP.getIp(request) ;
    }



    @Autowired
    private Area_ListService area_ListService;

    @RequestMapping("selectName")
    /**
     * 搜索股票
     * @title selectName
     * @author cz
     * @date 2019/3/2 10:56
     * @param name
     * @Description: TODO 根据股票名或者股票代码模糊搜索前5条信息
     *                  返回
     * @return java.util.List<com.frlz.pojo.Area_List>
     * @throws 
     */
    
    public List<Area_List> selectName (@Param("name")String name) throws Exception {
        List<Area_List> list = area_ListService.selectName(name);
        return list;
    }
}
