package com.frlz.service;

import com.frlz.pojo.Comments;
import com.frlz.pojo.Replys;
import com.frlz.utilPojo.UtilReplys;

import java.util.List;

public interface ReplysService {
    public void addReplys(Replys replys);

    public List<UtilReplys> selectRelysByCId (String cId);

    public List<UtilReplys> selectRelysAllByCId (String cId);
}
