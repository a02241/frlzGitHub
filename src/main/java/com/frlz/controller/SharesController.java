package com.frlz.controller;

import com.frlz.pojo.Shares;
import com.frlz.service.SharesService;
import com.frlz.util.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class SharesController {

    private final SharesService sharesService;

    @Autowired
    public SharesController(SharesService sharesService){
        this.sharesService = sharesService;
    }

    @PostMapping("/getShares")
    /**
     * 图形化界面返回二位数组
     * @title getShares
     * @author cz
     * @date 2019/3/2 10:51
     * @param code
     * @Description: TODO 必填参数code股票代码 返回参数为股票信息的二维数组
     * @return java.lang.Object[][]
     * @throws 
     */
    
    public R<Object[][]> getShares(@Param("code") String code){
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(code);
        String realCode = m.replaceAll("").trim();//编译前台数据，进行转换为后台应用数据
        List<Shares> list = sharesService.getShares(realCode);
        int count = sharesService.getCount(realCode);
        Object[][] obj = new Object[count][5];
        for (int i = 0; i < list.size(); i++) {
            obj[i][0]=list.get(i).getDate();
            obj[i][1]=list.get(i).getOpen();
            obj[i][2]=list.get(i).getClose();
            obj[i][3]=list.get(i).getLow();
            obj[i][4]=list.get(i).getHigh();
        }
        return R.isOk().data(obj);

    }
}
