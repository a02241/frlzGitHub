package com.frlz.service;

import com.frlz.pojo.Quiz;

import java.util.Map;
/**
 * @author cz
 */
public interface QuizService {

    Map<String,Map> getAllQuestion();

    void addQuestionToQuiz(Quiz quiz);

    int checkAnswer(String answer);
}
