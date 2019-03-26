package com.frlz.service.serviceImpl;

import com.frlz.mapper.UpdateDataMapper;
import com.frlz.pojo.Shares;
import com.frlz.pojo.UpdateData;
import com.frlz.service.UpdateDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: frlz
 * @description: 股票更新实现类
 * @author: cz
 * @date: 2019-03-26 09:19
 **/
@Transactional
@Service
public class UpdateDataServiceImpl implements UpdateDataService {

    @Autowired
    private UpdateDataMapper updateDataMapper;

    @Override
    public String updateDate() {
        String name = "";
        int count = 0 ;
        String time = "";
        String close = "";
        String high = "";
        String low = "";
        String open = "";
        String change = "";
        String volume = "";
        String money = "";
        String traded_market_value = "";
        String market_value = "";
        String turnover = "";
        String adjust_price = "";
        String report_type = "";
        String report_date = "";
        String PE_TTM = "";
        String PS_TTM = "";
        String PC_TTM = "";
        String PB = "";
        String adjust_price_f = "";
        List<UpdateData> date = updateDataMapper.selectAllUpdateData();
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        for (int i = 0 ; i < date.size() ; i++){
            name =date.get(i).getCode();
            Matcher m = p.matcher(name);
            String realCode = m.replaceAll("").trim();//编译前台数据，进行转换为后台应用数据
            time = date.get(i).getDate();
            close = date.get(i).getClose();
            high = date.get(i).getHigh();
            low = date.get(i).getLow();
            open = date.get(i).getOpen();
            change = date.get(i).getChange();
            volume = date.get(i).getVolume();
            money = date.get(i).getMoney();
            traded_market_value = date.get(i).getTraded_market_value();
            market_value = date.get(i).getMarket_value();
            turnover = date.get(i).getTurnover();
            adjust_price = date.get(i).getAdjust_price();
            report_type = date.get(i).getReport_type();
            report_date = date.get(i).getReport_date();
            PE_TTM = date.get(i).getPE_TTM();
            PS_TTM = date.get(i).getPS_TTM();
            PC_TTM = date.get(i).getPC_TTM();
            PB = date.get(i).getPB();
            adjust_price_f = date.get(i).getAdjust_price_f();
            /*if (updateDataMapper.selectTrue(realCode) > 0){
                *//*updateDataMapper.insertUpdateDate(realCode,name,time,open,high,low,close,change,volume,
                        money,traded_market_value,market_value,turnover,adjust_price,report_type,report_date
                ,PE_TTM,PS_TTM,PC_TTM,PB,adjust_price_f);*//*
//                updateDataMapper.deletetByDate(name,time);
                count ++;
                System.out.println("第"+count+"次"+name+"~~~~~~~~~~");
            }*/
            if (updateDataMapper.selectTrue(realCode) == 0){
                count ++;
                System.out.println("第"+count+"次无"+name+"表~~~~~~~~~~");
            }
        }
        System.out.println(count);
        return null;
    }
}
