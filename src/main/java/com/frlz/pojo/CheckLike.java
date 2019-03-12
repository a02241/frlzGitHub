package com.frlz.pojo;

import lombok.Data;

@Data
public class CheckLike {
    private String blogId;
    private String uid;
    private int likeId;
    private boolean state;
}
