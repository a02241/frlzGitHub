package com.frlz.service;


import com.frlz.pojo.LoginLog;

import java.util.List;

public interface LoginLogService {

    void insertLoginLog(String uid);

    List<LoginLog> getAllLoginLog();

    LoginLog getLatestLoginLog(String uid);
}
