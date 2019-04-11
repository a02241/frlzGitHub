package com.frlz.service.serviceImpl;

import com.frlz.mapper.AfterTagMapper;
import com.frlz.pojo.AfterTag;
import com.frlz.service.AfterTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: frlz
 * @description: 盘后信息表实现类
 * @author: cz
 * @date: 2019-04-11 10:11
 **/
@Service
public class AfterTagServiceIml implements AfterTagService {
    private  final AfterTagMapper afterTagMapper;

    @Autowired
    public AfterTagServiceIml(AfterTagMapper afterTagMapper) {
        this.afterTagMapper = afterTagMapper;
    }


    @Override
    public void addAfterTag(AfterTag afterTag) {
        afterTagMapper.insertAfterTag(afterTag);
    }

    @Override
    public AfterTag selectAfterTagByAfterTag(AfterTag afterTag) {
        return afterTagMapper.selectAfterTagByAfterTag(afterTag);
    }

    @Override
    public void updateAfterTag(String message, String afterTagId) {
        afterTagMapper.updateAfterTag(message,afterTagId);
    }


}
