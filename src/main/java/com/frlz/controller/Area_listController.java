package com.frlz.controller;

import com.frlz.service.Area_ListService;
import com.frlz.service.QuizService;
import com.frlz.util.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: frlz
 * @description: 所有股票对应信息
 * @author: cz
 * @date: 2019-03-02 10:55
 **/
@RestController
public class Area_listController{

    private final Area_ListService area_ListService;
    private final QuizService quizService;

    @Autowired
    public Area_listController(Area_ListService area_ListService,QuizService quizService){
        this.area_ListService = area_ListService;
        this.quizService = quizService;
    }

    @RequestMapping("selectAllShares")
    public R<List> selectAllShares (@Param("name")String name) throws Exception {
        return R.isOk().data(area_ListService.selectAll());
    }

    @PostMapping("startQuiz")
    public R<List> startQuiz(){
        return R.isOk().data(quizService.getAllQuestion());
    }
}
