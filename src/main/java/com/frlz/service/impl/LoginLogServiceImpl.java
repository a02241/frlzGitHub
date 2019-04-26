package com.frlz.service.impl;

import com.frlz.mapper.LoginLogMapper;
import com.frlz.pojo.LoginLog;
import com.frlz.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
/**
 * @program: frlz
 * @description:
 * @author: cz
 * @date: 2019-03-01 10:51
 **/
@Transactional
@Service
public class LoginLogServiceImpl implements LoginLogService {

    private final LoginLogMapper loginLogMapper;

    @Autowired
    public LoginLogServiceImpl(LoginLogMapper loginLogMapper) {
        this.loginLogMapper = loginLogMapper;
    }

    @Override
    public void insertLoginLog(String uid){
        loginLogMapper.insertLoginLog(uid);
    }

    @Override
    public List<LoginLog> getAllLoginLog() {
        return loginLogMapper.selectAllLoginLog();
    }

    @Override
    public Date getLatestLoginLog(String uid) {
        return loginLogMapper.selectLatestLoginLog(uid);
    }

    @Override
    public List<LoginLog> selectLoginLogByDate(String date) {
        return loginLogMapper.selectLoginLogByDate(date);
    }
}
