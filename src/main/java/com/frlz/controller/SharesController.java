package com.frlz.controller;

import com.frlz.service.SharesService;
import com.frlz.util.DateTime;
import com.frlz.util.R;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RestController
@Api(value="股票controller",tags={"股票信息操作接口"})
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
    @ApiOperation(value="图形化界面返回二位数组", notes="根据url的信息来图形化界面返回二位数组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "before", value = "时间前", required = false, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "end", value = "时间后", required = false, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<Object[][]> getShares(@Param("code") String code ,@RequestParam(defaultValue="")String before ,@RequestParam(defaultValue="")String end) {
        String nowEnd = DateTime.getNowTimeToString();
        String nowBefore = DateTime.getBeforeTwoYearTimeToString();
        if ("".equals(before)){
            before = nowBefore;
        }
        if ("".equals(end)){
            end = nowEnd;
        }
        Object[][] obj = sharesService.getShares(code,before,end);
        return R.isOk().data(obj);

    }
}
