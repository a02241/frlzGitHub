package com.frlz.service;

import com.frlz.pojo.Fans;
import com.frlz.utilPojo.UtilFans;

import java.util.List;
/**
 * @author cz
 */
public interface FansService {

    void insertFans(String uid,String fansUId);

    List<Fans> selectFansForOne(String uid);

    List<UtilFans> selectFansByUid(String uid);

    void deleteFans(String fansUId);
}
