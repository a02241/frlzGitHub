package com.frlz.pojo;


import lombok.Data;

import java.util.Date;

/**
 * @program: frlz
 * @description: 回复类
 * @author: cz
 * @date: 2019-03-04 10:44
 **/
@Data
public class Replys {
    private String replyId;//回复表主键uid
    private String cId;//评论类主键
    private String content;//回复内容
    private String username;//用户名
    private Date contentTime;//回复时间

    public Replys(String replyId, String cId, String content, String username, Date contentTime) {
        this.replyId = replyId;
        this.cId = cId;
        this.content = content;
        this.username = username;
        this.contentTime = contentTime;
    }

    public Replys() {
    }
}
