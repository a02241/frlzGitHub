package com.frlz.service.serviceImpl;

import com.frlz.mapper.BalanceMapper;
import com.frlz.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: frlz
 * @description: 方块量子接口实现类
 * @author: cz
 * @date: 2019-03-07 11:26
 **/
@Service
public class BalanceServiceImpl implements BalanceService {
    @Autowired
    private BalanceMapper balanceMapper;
}
