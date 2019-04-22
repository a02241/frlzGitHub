package com.frlz.mapper;

import com.frlz.pojo.Quiz;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * @author cz
 */
public interface QuizMapper {

    @Select("select * from quiz")
    List<Quiz> selectAll();

    @Insert("insert into quiz values(default,#{question},#{optionOne},#{optionTwo},#{optionThree},#{optionFour},#{answer},#{type},#{remark})")
    void insertIntoQuiz(Quiz quiz);
}
