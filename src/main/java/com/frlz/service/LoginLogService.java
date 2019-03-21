package com.frlz.service;


import com.frlz.pojo.LoginLog;

import java.util.Date;
import java.util.List;

public interface LoginLogService {

    void insertLoginLog(String uid) throws Exception;

    List<LoginLog> getAllLoginLog() throws Exception;

    Date getLatestLoginLog(String uid) throws Exception;

    List<LoginLog> selectLoginLogByDate(String date) throws Exception;
}
