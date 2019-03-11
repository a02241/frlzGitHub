package com.frlz.service.serviceImpl;

import com.frlz.mapper.PrePlanMapper;
import com.frlz.service.PrePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: frlz
 * @description: 盘前计划实现类
 * @author: cz
 * @date: 2019-03-11 15:08
 **/
@Service
public class ProPlanServiceImpl implements PrePlanService {
    @Autowired
    PrePlanMapper prePlanMapper;
}
