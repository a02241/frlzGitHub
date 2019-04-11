package com.frlz.service.serviceImpl;

import com.frlz.mapper.AfterFatherMapper;
import com.frlz.service.AfterFatherService;
import com.frlz.utilPojo.UtilAfterFather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: frlz
 * @description: 盘后主表接口实现层
 * @author: cz
 * @date: 2019-04-11 10:02
 **/
@Service
public class AfterFatherServiceImpl implements AfterFatherService {
    private final AfterFatherMapper afterFatherMapper;

    @Autowired
    public AfterFatherServiceImpl(AfterFatherMapper afterFatherMapper) {
        this.afterFatherMapper = afterFatherMapper;
    }

    @Override
    public List<Date> selectTimeByMonthUid(String time, String uid) {
        return afterFatherMapper.selectTimeByMonthUid(time,uid);
    }

    @Override
    public int checkAfterFatherByUid(String uid) {
        return afterFatherMapper.checkAfterFatherByUid(uid);
    }

    @Override
    public List<UtilAfterFather> selectAfterByTimeUid(String uid, String time) {

        return afterFatherMapper.selectAfterByTimeUid(uid,time);
    }
}
