package com.frlz.service;

import com.frlz.pojo.Replys;
import com.frlz.dto.UtilReplys;

import java.util.List;
/**
 * @author cz
 */
public interface ReplysService {
    public void addReplys(Replys replys);

    public UtilReplys selectRelysByCId (String cId);

    public List<UtilReplys> selectRelysAllByCId (String cId);
}
