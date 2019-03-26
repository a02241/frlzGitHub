package com.frlz.pojo;

import lombok.Data;

/**
 * @program: frlz
 * @description: 更新股票数据
 * @author: cz
 * @date: 2019-03-26 09:19
 **/
@Data
public class UpdateData {
    private String code;//股票更新表名
    private String date;
    private String open;
    private String close;
    private String low;
    private String high;
    private String volume;
    private String money;
    private String change;
    private String traded_market_value;
    private String market_value;
    private String turnover	;
    private String adjust_price;
    private String report_type;
    private String report_date;
    private String PE_TTM;
    private String PS_TTM;
    private String PC_TTM;
    private String PB;
    private String adjust_price_f;
}
