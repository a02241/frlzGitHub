package com.frlz.service;

import com.frlz.pojo.AfterTag;

import java.util.Date;
import java.util.List;

public interface AfterFatherService {
    void addAfterDiscuss(String uid);

    List<Date> selectTimeByMonthUid(String time , String uid);

    int checkAfterFatherByUid(String uid);
}
