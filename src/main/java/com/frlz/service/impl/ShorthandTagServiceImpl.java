package com.frlz.service.impl;

import com.frlz.mapper.ShorthandTagMapper;
import com.frlz.service.ShorthandTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: frlz
 * @description: 股票标签信息实现类
 * @author: cz
 * @date: 2019-03-11 15:19
 **/
@Service
public class ShorthandTagServiceImpl implements ShorthandTagService {
    private ShorthandTagMapper shorthandTagMapper;

    @Autowired
    public ShorthandTagServiceImpl(ShorthandTagMapper shorthandTagMapper){
        this.shorthandTagMapper = shorthandTagMapper;
    }
}
