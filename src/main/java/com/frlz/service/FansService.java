package com.frlz.service;

import com.frlz.pojo.Fans;

import java.util.List;

public interface FansService {

    void insertFans(String uid,String fansUId) throws Exception;

    List<Fans> selectFansForOne(String uid) throws Exception;

    void deleteFans(String fansUId) throws Exception;
}
