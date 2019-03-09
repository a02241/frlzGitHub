package com.frlz.pojo;

import lombok.Data;

/**
 * @program: frlz
 * @description: 密保
 * @author: cz
 * @date: 2019-03-09 10:16
 **/

@Data
public class Secret {
    private int id;//主键
    private String questionOne;//问题一
    private String answerOne;//答案一
    private String questionTwo;//问题二
    private String answerTwo;//答案二
    private String questionThree;//问题三
    private String answerThree;//答案三
    private String uid;//外键
}
