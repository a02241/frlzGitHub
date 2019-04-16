package com.frlz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "quiz对象",description = "问卷调查对象")
public class Quiz {
    @ApiModelProperty(value = "问题Id",name = "quizid")
    private int quizid;//问题Id
    @ApiModelProperty(value = "问题",name = "question")
    private String question;//问题
    @ApiModelProperty(value = "选项一",name = "optionOne")
    private String optionOne;//选项一
    @ApiModelProperty(value = "选项二",name = "optionTwo")
    private String optionTwo;//选项二
    @ApiModelProperty(value = "选项三",name = "optionThree")
    private String optionThree;//选项三
    @ApiModelProperty(value = "选项四",name = "optionFour")
    private String optionFour;//选项四
    @ApiModelProperty(value = "答案",name = "answer")
    private int answer;//答案
    @ApiModelProperty(value = "问题类型",name = "type")
    private String type;//问题类型
    @ApiModelProperty(value = "备注",name = "remark")
    private String remark;//备注
}
