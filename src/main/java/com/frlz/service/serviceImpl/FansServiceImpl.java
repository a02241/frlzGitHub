package com.frlz.service.serviceImpl;

import com.frlz.mapper.FansMapper;
import com.frlz.pojo.Fans;
import com.frlz.service.FansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FansServiceImpl implements FansService {

    private final FansMapper fansMapper;

    @Autowired
    public FansServiceImpl(FansMapper fansMapper) {
        this.fansMapper = fansMapper;
    }

    @Override
    public void insertFans(String uid, String fansUId) throws Exception{
        fansMapper.insertFans(uid,fansUId);
    }

    @Override
    public List<Fans> selectFansForOne(String uid) throws Exception{
        return fansMapper.selectFansForOne(uid);
    }

    @Override
    public void deleteFans(String fansUId) throws Exception{
        fansMapper.deleteFans(fansUId);
    }
}
