package com.frlz.service.serviceImpl;

import com.frlz.mapper.TradeLogMapper;
import com.frlz.pojo.TradeLog;
import com.frlz.service.TradeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: frlz
 * @description: 交易日志实现类
 * @author: cz
 * @date: 2019-03-07 15:41
 **/
@Service
public class TradeLogServiceImpl implements TradeLogService {

    @Autowired
    private TradeLogMapper tradeLogMapper;

    @Override
    public void insertTradeLog(TradeLog tradeLog) {
        tradeLogMapper.insertTradeLog(tradeLog);
    }
}
