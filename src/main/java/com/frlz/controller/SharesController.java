package com.frlz.controller;

import com.frlz.service.SharesService;
import com.frlz.util.DateTime;
import com.frlz.util.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * @Description: TODO 必填参数code股票代码,before,end(格式为xxxx-xx-xx字符串格式) 返回参数为股票信息的二维数组
     * @return java.lang.Object[][]
     * @throws 
     */
    
    public R<Object[][]> getShares(@Param("code") String code ,@RequestParam(defaultValue="")String before ,@RequestParam(defaultValue="")String end) throws Exception {
        String nowEnd = DateTime.getNowTimeToString();
        String nowbefore = DateTime.getBeforeTwoYearTimeToString();
        if ("".equals(before)){
            before = nowbefore;
        }
        if ("".equals(end)){
            end = nowEnd;
        }
        Object[][] obj = sharesService.getShares(code,before,end);
        return R.isOk().data(obj);

    }
}
