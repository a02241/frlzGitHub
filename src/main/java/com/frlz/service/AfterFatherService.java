package com.frlz.service;

import com.frlz.dto.UtilAfterFather;

import java.util.Date;
import java.util.List;
/**
 * @author cz
 */
public interface AfterFatherService {
    String getAfterFatherId(String uid,String time);

    void addAfterDiscuss(String uid);

    List<Date> selectTimeByMonthUid(String time , String uid);

    int checkAfterFatherByUid(String uid);

    List<UtilAfterFather> selectAfterByTimeUid(String uid , String time);

    int checkAfterFatherByUidTime(String uid,String time);

    int checkAfterFatherByAfterFatherId(String afterFatherId);

    void deleteAfterFatherByAfterFatherId(String afterFatherId);
}
