package com.frlz.controller;

import com.frlz.service.ProPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: frlz
 * @description: 盘前计划controller
 * @author: cz
 * @date: 2019-03-11 15:09
 **/
@RestController
public class ProPlanController {
    @Autowired
    private ProPlanService proPlanService;
}
