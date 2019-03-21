package com.frlz.service;

import com.frlz.pojo.Area_List;

import java.util.List;

public interface Area_ListService {
    public String selectCode(String name) throws Exception;

    List<Area_List> selectAll() throws Exception;
    
    public List<Area_List> selectName(String name) throws Exception;

    public String selectRealName(String realcode) throws Exception;
}
