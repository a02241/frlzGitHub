package com.frlz.controller;

import com.frlz.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretController {

    private SecretService secretService;

    @Autowired
    public SecretController(SecretService secretService){
        this.secretService = secretService;
    }


}
