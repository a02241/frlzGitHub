package com.frlz.service;

import com.frlz.utilPojo.UtilAfterFather;

import java.util.Date;
import java.util.List;

public interface AfterFatherService {
    List<Date> selectTimeByMonthUid(String time , String uid);

    int checkAfterFatherByUid(String uid);

    List<UtilAfterFather> selectAfterByTimeUid(String uid , String time);

    int checkAfterFatherByUidTime(String uid,String time);

    int checkAfterFatherByAfterFatherId(String afterFatherId);

    void deleteAfterFatherByAfterFatherId(String afterFatherId);
}
