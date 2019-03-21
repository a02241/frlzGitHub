package com.frlz.service;


import com.frlz.pojo.Balance;

import java.util.List;

public interface BalanceService {

    void insertBalance(int blockBalance,int quantumBalance,int magicCubeBalance,String uid) throws Exception;

    Balance selectFromBanlanceByUid(String uid) throws Exception;

    void updateQuantumBalanceByUid(String uid,int quantumBalance) throws Exception;

    void updateBlockBalanceByUid(String uid,int blockBalance) throws Exception;

    void updateMagicCubeBalanceByUid(String uid,int magicCubeBalance) throws Exception;

    List<Balance> selectAllBalance() throws Exception;

    void LoginAddQuantumBalanceByUid(String uid,int quantumBalance) throws Exception;
}
