package com.frlz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: frlz
 * @description: 密保
 * @author: cz
 * @date: 2019-03-09 10:16
 **/

@Data
@ApiModel(value = "secret对象",description = "密保对象")
public class Secret {
    @ApiModelProperty(value = "主键",name = "id")
    private int id;//主键
    @ApiModelProperty(value = "问题一",name = "questionOne")
    private String questionOne;//问题一
    @ApiModelProperty(value = "答案一",name = "answerOne")
    private String answerOne;//答案一
    @ApiModelProperty(value = "问题二",name = "questionTwo")
    private String questionTwo;//问题二
    @ApiModelProperty(value = "答案二",name = "answerTwo")
    private String answerTwo;//答案二
    @ApiModelProperty(value = "问题三",name = "questionThree")
    private String questionThree;//问题三
    @ApiModelProperty(value = "答案三",name = "answerThree")
    private String answerThree;//答案三
    @ApiModelProperty(value = "外键",name = "uid")
    private String uid;//外键
}
