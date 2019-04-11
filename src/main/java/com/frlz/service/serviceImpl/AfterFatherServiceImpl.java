package com.frlz.service.serviceImpl;

import com.frlz.mapper.AfterFatherMapper;
import com.frlz.mapper.AfterTagMapper;
import com.frlz.pojo.AfterFather;
import com.frlz.pojo.AfterTag;
import com.frlz.service.AfterFatherService;
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
    private final AfterTagMapper afterTagMapper;
    @Autowired
    public AfterFatherServiceImpl(AfterFatherMapper afterFatherMapper,AfterTagMapper afterTagMapper) {
        this.afterFatherMapper = afterFatherMapper;
        this.afterTagMapper = afterTagMapper;
    }

    @Override
    public void addAfterDiscuss(String uid) {
        AfterFather afterFather = new AfterFather();
        afterFather.setUid(uid);
        afterFatherMapper.insertAfterFather(afterFather);
    }

    @Override
    public List<Date> selectTimeByMonthUid(String time, String uid) {
        return afterFatherMapper.selectTimeByMonthUid(time,uid);
    }

    @Override
    public int checkAfterFatherByUid(String uid) {
        return afterFatherMapper.checkAfterFatherByUid(uid);
    }
}
