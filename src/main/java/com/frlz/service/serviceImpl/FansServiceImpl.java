package com.frlz.service.serviceImpl;

import com.frlz.mapper.FansMapper;
import com.frlz.pojo.Fans;
import com.frlz.service.FansService;
import com.frlz.utilPojo.UtilFans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author cz
 */
@Transactional
@Service
public class FansServiceImpl implements FansService {

    private final FansMapper fansMapper;

    @Autowired
    public FansServiceImpl(FansMapper fansMapper) {
        this.fansMapper = fansMapper;
    }

    @Override
    public void insertFans(String uid, String fansUId){
        fansMapper.insertFans(uid,fansUId);
    }

    @Override
    public List<Fans> selectFansForOne(String uid){
        return fansMapper.selectFansForOne(uid);
    }

    @Override
    public List<UtilFans> selectFansByUid(String uid) {
        return fansMapper.selectFansByUid(uid);
    }

    @Override
    public void deleteFans(String fansUId){
        fansMapper.deleteFans(fansUId);
    }
}
