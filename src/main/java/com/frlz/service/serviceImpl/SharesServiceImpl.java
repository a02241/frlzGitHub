package com.frlz.service.serviceImpl;

import com.frlz.mapper.SharesMapper;
import com.frlz.pojo.Shares;
import com.frlz.service.SharesService;
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
public class SharesServiceImpl implements SharesService {

    private final SharesMapper sharesMapper;

    @Autowired
    public SharesServiceImpl(SharesMapper sharesMapper) {
        this.sharesMapper = sharesMapper;
    }

    @Autowired


    @Override
    public List<Shares> getShares(String code){
        return sharesMapper.selectShares(code);
    }

    @Override
    public int getCount(String code){
        return sharesMapper.selectCount(code);
    }
}
