package com.frlz.controller;

import com.frlz.service.QuizService;
import com.frlz.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

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
    /**
     *
     * @title startQuiz
     * @create by: cz
     * @description: TODO 获取所有题目
     * @create time: 2019/3/26 17:43
     * @Param:
     * @throws
     * @return com.frlz.util.R<java.util.Map>
     * @version V1.0
     */

    public R<Map> startQuiz(){
        return R.isOk().data(quizService.getAllQuestion());
    }

    @PostMapping("checkAnswer")//传入大写ABCD的答案字符串，返回int答对的题数
    /**
     *
     * @title checkAnswer
     * @create by: cz
     * @description: TODO 传入大写ABCD的答案字符串，返回int答对的题数,传过来ABCDAB格式
     * @create time: 2019/3/26 17:43
     * @Param: answer
     * @throws
     * @return com.frlz.util.R<java.lang.Integer>
     * @version V1.0
     */

    public R<Integer> checkAnswer(String answer){
        return R.isOk().data(quizService.checkAnswer(answer));
    }
}
