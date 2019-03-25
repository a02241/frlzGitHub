package com.frlz.service.serviceImpl;

import com.frlz.mapper.QuizMapper;
import com.frlz.pojo.Quiz;
import com.frlz.service.QuizService;
import com.frlz.util.AnswerChange;
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

    @Override
    public int checkAnswer(String answer) {
        char[] answerList = answer.toCharArray();
        List<Quiz> quizList = quizMapper.selectAll();
        int n = 0;
        for (int i = 0,b = answerList.length;i < b;i++){
            if (AnswerChange.answer(quizList.get(i).getAnswer()) == (answerList[i])){
                n++;
            }
        }
        return n;
    }


}
