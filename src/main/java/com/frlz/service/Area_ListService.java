package com.frlz.service;

import com.frlz.pojo.Area_List;

import java.util.List;

public interface Area_ListService {
    public String selectCode(String name);

    List<Area_List> selectAll();
    
    public List<Area_List> selectName(String name);

    public String selectRealName(String realcode);
}
