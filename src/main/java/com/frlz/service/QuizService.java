package com.frlz.service;

import com.frlz.pojo.Quiz;

import java.util.List;

public interface QuizService {

    List<Quiz> getAllQuestion();

    void addQuestionToQuiz(Quiz quiz);
}
