package com.frlz.service.impl;

import com.frlz.mapper.BalanceMapper;
import com.frlz.pojo.Balance;
import com.frlz.service.BalanceService;
import com.frlz.dto.UtilBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: frlz
 * @description: 方块量子接口实现类
 * @author: cz
 * @date: 2019-03-07 11:26
 **/
@Service
public class BalanceServiceImpl implements BalanceService {

    private final BalanceMapper balanceMapper;

    @Autowired
    public BalanceServiceImpl(BalanceMapper balanceMapper){
        this.balanceMapper = balanceMapper;
    }
    @Override
    public void insertBalance(int blockBalance,int quantumBalance,int magicCubeBalance,String uid){
        Balance balance = new Balance();
        if (quantumBalance == 0){
            balance.setQuantumBalance(1);
        }
        balance.setUid(uid);
        balanceMapper.insertBalance(balance);
    }

    @Override
    public Balance selectFromBalanceByUid(String uid){
        return balanceMapper.selectFromBalanceByUid(uid);
    }

    @Override
    public UtilBalance selectShowBalanceByUid(String uid) {
        return balanceMapper.selectShowBalanceByUid(uid);
    }

    @Override
    public void updateQuantumBalanceByUid(String uid, int quantumBalance) {
        balanceMapper.updateQuantumBalanceByUid(uid,quantumBalance);
    }

    @Override
    public void updateBlockBalanceByUid(String uid, int blockBalance) {
        balanceMapper.updateBlockBalanceByUid(uid,blockBalance);
    }

    @Override
    public void updateMagicCubeBalanceByUid(String uid, int magicCubeBalance) {
        balanceMapper.updateMagicCubeBalanceByUid(uid,magicCubeBalance);
    }

    @Override
    public List<Balance> selectAllBalance() {
        return balanceMapper.selectAllBalance();
    }

    @Override
    public void loginAddQuantumBalanceByUid (String uid, int quantumBalance) {

    }
}
