package com.frlz.service.serviceImpl;

import com.frlz.mapper.AfterFatherMapper;
import com.frlz.mapper.AfterTagMapper;
import com.frlz.pojo.AfterTag;
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
    private final AfterTagMapper afterTagMapper;
    @Autowired
    public AfterFatherServiceImpl(AfterFatherMapper afterFatherMapper,AfterTagMapper afterTagMapper) {
        this.afterFatherMapper = afterFatherMapper;
        this.afterTagMapper = afterTagMapper;
    }

    @Override
    public void addAfterDiscuss(String uid, AfterTag afterTag) {
        afterFatherMapper.insertAfterFather(uid);
        System.out.println("1111111111111");
       /* afterTag.setAfterFatherId(afterFatherMapper.selectAfterFatherId(uid));
        System.out.println("1111122221");
        afterTagMapper.insertAfterTag(afterTag);
        System.out.println("122222222222");*/
    }
}
