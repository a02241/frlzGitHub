package com.frlz.mapper;

import com.frlz.pojo.Shares;
import com.frlz.pojo.UpdateData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UpdateDataMapper {

    @Select("select * from updateData")
    List<UpdateData> selectAllUpdateData();

    @Select("select * from #{name}")
    Shares selectShares(String name);

    @Select("SELECT count(table_name) FROM information_schema.TABLES WHERE table_name = #{name};")
    int selectTrue(String name);

    @Insert("insert into `"+"${name}"+"`values(#{code},#{date},#{open},#{high},#{low},#{close}" +
            ",#{change},#{volume},#{money},#{traded_market_value},#{market_value},#{turnover},#{adjust_price}" +
            ",#{report_type},#{report_date},#{PE_TTM},#{PS_TTM},#{PC_TTM},#{PB},#{adjust_price_f});")
    void insertUpdateDate(String name , String code ,String date , String open , String high , String low ,String close
            ,String change,String volume,String money,String traded_market_value,String market_value,
                          String turnover,String adjust_price,String report_type,String report_date,String PE_TTM
            ,String PS_TTM,String PC_TTM,String PB,String adjust_price_f);

    @Delete("DELETE from `"+"${name}"+"` where date = #{date};")
    void deletetByDate(String name , String date);
}
