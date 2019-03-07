package com.frlz.service;

import com.frlz.pojo.Shares;

import java.util.List;

public interface SharesService {

    public List<Shares> getShares(String code);

    public int getCount(String code);
}
