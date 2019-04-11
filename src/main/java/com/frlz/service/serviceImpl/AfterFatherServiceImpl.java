package com.frlz.service.serviceImpl;

import com.frlz.mapper.AfterFatherMapper;
import com.frlz.service.AfterFatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}