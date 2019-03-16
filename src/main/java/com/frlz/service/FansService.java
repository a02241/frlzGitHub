package com.frlz.service;

import com.frlz.pojo.Fans;

import java.util.List;

public interface FansService {

    void insertFans(String uid,String fansUId);

    List<Fans> selectFansForOne(String uid);

    void deleteFans(String fansUId);
}
