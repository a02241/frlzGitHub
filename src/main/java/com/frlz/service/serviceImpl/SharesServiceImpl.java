package com.frlz.service.serviceImpl;

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
    public Object[][] getShares(String code){
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(code);
        String realCode = m.replaceAll("").trim();//编译前台数据，进行转换为后台应用数据
        List<Shares> list = sharesMapper.selectShares(realCode);
        int count = sharesMapper.selectCount(realCode);
        Object[][] obj = new Object[count][5];
        for (int i = 0; i < list.size(); i++) {
            obj[i][0]=list.get(i).getDate();
            obj[i][1]=list.get(i).getOpen();
            obj[i][2]=list.get(i).getClose();
            obj[i][3]=list.get(i).getLow();
            obj[i][4]=list.get(i).getHigh();
        }
        return obj;
    }

    @Override
    public int getCount(String code){
        return sharesMapper.selectCount(code);
    }
}
