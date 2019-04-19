package com.frlz.controller;

import com.frlz.service.QuizService;
import com.frlz.util.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * @program frlz
 * @description 问答
 * @author cz
 * @date 2019-03-22 17:02
 **/
@RestControllerAdvice
@RestController
@Api(value="答题controller",tags={"答题信息操作接口"})
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService){
        this.quizService = quizService;
    }


    /**
     *
     * @title startQuiz
     * @create by: cz
     * @description  获取所有题目
     * @create time: 2019/3/26 17:43
     * @param
     * @return com.frlz.util.R<java.util.Map>
     * @version V1.0
     */
    @PostMapping("startQuiz")
    @ApiOperation(value="获取所有题目", notes="根据url的信息来获取所有题目")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<Map> startQuiz(){
        return R.isOk().data(quizService.getAllQuestion());
    }


    /**
     *
     * @title checkAnswer
     * @create by: cz
     * @description  传入大写ABCD的答案字符串，返回int答对的题数,传过来ABCDAB格式
     * @create time: 2019/3/26 17:43
     * @param answer
     * @return com.frlz.util.R<java.lang.Integer>
     * @version V1.0
     */
    @PostMapping("checkAnswer")//传入大写ABCD的答案字符串，返回int答对的题数
    @ApiOperation(value="传入大写ABCD的答案字符串", notes="根据url的信息来传入大写ABCD的答案字符串")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "answer", value = "答案", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<Integer> checkAnswer(String answer){
        return R.isOk().data(quizService.checkAnswer(answer));
    }
}
