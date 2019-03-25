package com.frlz.service.serviceImpl;

import com.frlz.mapper.SecretMapper;
import com.frlz.pojo.Secret;
import com.frlz.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: frlz
 * @description: 密保接口实现类
 * @author: cz
 * @date: 2019-03-09 10:19
 **/
@Service
public class SecretServiceImpl implements SecretService {

    private final SecretMapper secretMapper;

    @Autowired
    public SecretServiceImpl(SecretMapper secretMapper) {
        this.secretMapper = secretMapper;
    }



    @Override
    public void insertSecret(Secret secret, String uid) {
        secretMapper.insertSecret(secret,uid);

    }

    @Override
    public Secret selectFromSecret(String uid) {
        return secretMapper.selectFromSecret(uid);
    }

    @Override
    public void updateSecret(Secret secret) {
        Secret secretOld = secretMapper.selectFromSecret(secret.getUid());
        if ("".equals(secret.getQuestionOne())){
            secret.setQuestionOne(secretOld.getQuestionOne());
            secret.setAnswerOne(secretOld.getAnswerOne());
        }
        if ("".equals(secret.getQuestionTwo())){
            secret.setQuestionTwo(secretOld.getQuestionTwo());
            secret.setAnswerTwo(secretOld.getAnswerTwo());
        }
        if ("".equals(secret.getQuestionThree())){
            secret.setQuestionThree(secretOld.getQuestionThree());
            secret.setAnswerThree(secretOld.getAnswerThree());
        }
        secretMapper.updateSecret(secret);
    }
}
