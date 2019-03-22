package com.frlz.service.serviceImpl;

import com.frlz.mapper.Area_ListMapper;
import com.frlz.pojo.Area_List;
import com.frlz.service.Area_ListService;
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
public class Area_ListServiceImpl implements Area_ListService {

    private final Area_ListMapper area_listMapper;

    @Autowired
    public Area_ListServiceImpl(Area_ListMapper area_listMapper){
        this.area_listMapper = area_listMapper;
    }

    @Override
    public String selectCode(String name) {
        return area_listMapper.selectCode(name);
    }

    @Override
    public List<Area_List> selectAll() {
        return area_listMapper.selectAll();
    }

    @Override
    public List<Area_List> selectName(String name) {
        return area_listMapper.selectName(name);
    }

    @Override
    public String selectRealName(String realcode) {
        return area_listMapper.selectRealName(realcode);
    }
}
