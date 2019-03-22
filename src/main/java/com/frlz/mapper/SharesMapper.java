package com.frlz.mapper;

import com.frlz.pojo.Shares;
import com.frlz.util.Provider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface SharesMapper {

    @SelectProvider(type = Provider.class,method = "queryShares")
    List<Shares> selectShares(String code ,String before ,String end);

    @SelectProvider(type = Provider.class,method = "querySharesCount")
    int selectCount(String code ,String before ,String end);
}
