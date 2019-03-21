package com.frlz.pojo;

import lombok.Data;

@Data
public class Quiz {
    private int quizid;//问题Id
    private String question;//问题
    private String optionOne;//选项一
    private String optionTwo;//选项二
    private String optionThree;//选项三
    private String optionFour;//选项四
    private int answer;//答案
    private String type;//问题类型
    private String remark;//备注
}
