package com.frlz.service.impl;

import com.frlz.mapper.SharesMapper;
import com.frlz.pojo.Shares;
import com.frlz.service.SharesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: frlz
 * @description:
 * @author: cz
 * @date: 2019-03-01 10:51
 **/
@Service
public class SharesServiceImpl implements SharesService {

    private final SharesMapper sharesMapper;

    @Autowired
    public SharesServiceImpl(SharesMapper sharesMapper) {
        this.sharesMapper = sharesMapper;
    }


    @Override
    public Object[][] getShares(String code,String before ,String end){
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(code);
        String realCode = m.replaceAll("").trim();//编译前台数据，进行转换为后台应用数据
        List<Shares> list = sharesMapper.selectShares(realCode,before,end);
        int count = sharesMapper.selectCount(realCode,before,end);
        Object[][] obj = new Object[count][5];
        for (int i = 0; i < list.size(); i++) {
            obj[i][0]=list.get(i).getDate();
            obj[i][1]=Double.parseDouble(list.get(i).getOpen());
            obj[i][2]=Double.parseDouble(list.get(i).getClose());
            obj[i][3]=Double.parseDouble(list.get(i).getLow());
            obj[i][4]=Double.parseDouble(list.get(i).getHigh());
        }
        return obj;
    }

}
