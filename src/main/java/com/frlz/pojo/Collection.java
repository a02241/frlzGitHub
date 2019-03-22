package com.frlz.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Collection {
    private int collectionId;
    private String uid;
    private String blogId;
    private Date collectTime;
}
