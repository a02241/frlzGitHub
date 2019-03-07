package com.frlz.service.serviceImpl;

import com.frlz.mapper.LoginLogMapper;
import com.frlz.pojo.LoginLog;
import com.frlz.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @program: frlz
 * @description:
 * @author: cz
 * @date: 2019-03-01 10:51
 **/
@Service
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public void insertLoginLog(String uid) {
        loginLogMapper.insertLoginLog(uid);
    }

    @Override
    public List<LoginLog> getAllLoginLog() {
        return loginLogMapper.selectAllLoginLog();
    }

    @Override
    public LoginLog getLatestLoginLog(String uid) {
        return loginLogMapper.selectLatestLoginLog(uid);
    }
}
