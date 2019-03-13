package com.frlz.pojo;

import lombok.Data;

@Data
public class CheckLike {
    private String blogId;//外键
    private String uid;//外键
    private int likeId;//主键
    private boolean state;//状态
}
