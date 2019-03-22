package com.frlz.controller;

import com.frlz.service.Area_ListService;
import com.frlz.service.QuizService;
import com.frlz.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @program: frlz
 * @description: 问答
 * @author: cz
 * @date: 2019-03-22 17:02
 **/
@RestControllerAdvice
@RestController
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService){
        this.quizService = quizService;
    }

    @PostMapping("startQuiz")
    public R<List> startQuiz(){
        return R.isOk().data(quizService.getAllQuestion());
    }
}
