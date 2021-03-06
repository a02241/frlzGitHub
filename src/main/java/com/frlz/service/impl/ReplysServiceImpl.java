package com.frlz.service.impl;

import com.frlz.mapper.ReplysMapper;
import com.frlz.pojo.Replys;
import com.frlz.service.ReplysService;
import com.frlz.dto.UtilReplys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: frlz
 * @description: 博客回复接口
 * @author: cz
 * @date: 2019-03-04 13:46
 **/
@Service
public class ReplysServiceImpl implements ReplysService {
    
    private final ReplysMapper replysMapper;

    @Autowired
    public ReplysServiceImpl(ReplysMapper replysMapper) {
        this.replysMapper = replysMapper;
    }

    @Override
    public void addReplys(Replys replys) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newtime = sdf.format(date);
        try {
            Date time = sdf.parse(newtime);
            replys.setContentTime(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        replysMapper.addReplys(replys);
    }

    @Override
    public UtilReplys selectRelysByCId(String cId) {
        return replysMapper.selectReplysByCId(cId);
    }

    @Override
    public List<UtilReplys> selectRelysAllByCId(String cId) {
        return replysMapper.selectReplysAllByCId(cId);
    }
}
