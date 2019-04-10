package com.frlz.service.serviceImpl;

import com.frlz.mapper.ShorthandPhotoMapper;
import com.frlz.service.ShorthandPhotoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: frlz
 * @description: 盘中图片位置实现类
 * @author: cz
 * @date: 2019-03-11 15:14
 **/
@Service
public class ShorthandPhotoServiceImpl implements ShorthandPhotoService {

    private final ShorthandPhotoMapper shorthandPhotoMapper;
    @Autowired
    public ShorthandPhotoServiceImpl(ShorthandPhotoMapper shorthandPhotoMapper){
        this.shorthandPhotoMapper = shorthandPhotoMapper;
    }
}
