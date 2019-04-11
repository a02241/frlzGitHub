package com.frlz.utilPojo;

import com.frlz.pojo.AfterTag;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @program: frlz
 * @description: 展示盘后信息
 * @author: cz
 * @date: 2019-04-11 10:56
 **/
@Data
public class UtilAfterFather {
    private String afterFatherId;//主键
    private Date time;//创建时间
    private List<AfterTag> afterTag;//字表
}
