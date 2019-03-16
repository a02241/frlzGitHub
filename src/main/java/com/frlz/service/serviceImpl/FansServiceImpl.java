package com.frlz.service.serviceImpl;

import com.frlz.mapper.FansMapper;
import com.frlz.pojo.Fans;
import com.frlz.service.FansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FansServiceImpl implements FansService {
    @Autowired
    private FansMapper fansMapper;

    @Override
    public void insertFans(String uid, String fansUId) {
        fansMapper.insertFans(uid,fansUId);
    }

    @Override
    public List<Fans> selectFansForOne(String uid) {
        return fansMapper.selectFansForOne(uid);
    }
}
