package com.frlz.service.serviceImpl;

import com.frlz.mapper.QuizMapper;
import com.frlz.pojo.Quiz;
import com.frlz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private QuizMapper quizMapper;
    @Autowired
    public QuizServiceImpl(QuizMapper quizMapper){
        this.quizMapper = quizMapper;
    }

    @Override
    public List<Quiz> getAllQuestion() {
        return quizMapper.selectAll();
    }

    @Override
    public void addQuestionToQuiz(Quiz quiz) {
        quizMapper.insertIntoQuiz(quiz);
    }
}
