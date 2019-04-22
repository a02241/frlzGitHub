package com.frlz.service.serviceImpl;

import com.frlz.mapper.QuizMapper;
import com.frlz.pojo.Quiz;
import com.frlz.service.QuizService;
import com.frlz.util.AnswerChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuizServiceImpl implements QuizService {

    private QuizMapper quizMapper;
    @Autowired
    public QuizServiceImpl(QuizMapper quizMapper){
        this.quizMapper = quizMapper;
    }

    @Override
    public Map<String,Map> getAllQuestion() {
        List<Quiz> quizList = quizMapper.selectAll();
        Map<String,Map> questionMap = new HashMap<>(10);
        for (int i = 0,questionNumber = 30;i < questionNumber;i++){
            Map<String,String> map = new HashMap<>(7);
            map.put("question",quizList.get(i).getQuestion());
            map.put("optionOne",quizList.get(i).getOptionOne());
            map.put("optionTwo",quizList.get(i).getOptionTwo());
            map.put("optionThree",quizList.get(i).getOptionThree());
            map.put("optionFour",quizList.get(i).getOptionFour());
            questionMap.put("question" + i,map);
        }
        return questionMap;
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
