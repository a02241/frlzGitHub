package com.frlz.service;


import com.frlz.pojo.Balance;

import java.util.List;

public interface BalanceService {

    void insertBalance(Balance balance);

    Balance selectFromBanlanceByUid(String uid);

    void updateQuantumBalanceByUid(String uid,int quantumBalance);

    void updateBlockBalanceByUid(String uid,int blockBalance);

    void updateMagicCubeBalanceByUid(String uid,int magicCubeBalance);

    List<Balance> selectAllBalance();

    void LoginAddQuantumBalanceByUid(String uid,int quantumBalance);
}
