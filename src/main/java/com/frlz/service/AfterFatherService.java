package com.frlz.service;

import java.util.Date;
import java.util.List;

public interface AfterFatherService {
    List<Date> selectTimeByMonthUid(String time , String uid);
}
