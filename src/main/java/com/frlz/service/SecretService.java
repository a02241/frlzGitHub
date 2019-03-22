package com.frlz.service;

import com.frlz.pojo.Secret;

public interface SecretService {

    void insertSecret(Secret secret, String uid);

    Secret selectFromSecret(String uid);
}
