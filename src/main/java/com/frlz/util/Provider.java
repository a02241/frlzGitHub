package com.frlz.util;

public class Provider {
    public String queryShares(String code ,String before ,String end){
        StringBuilder sb = new StringBuilder();
        sb.append("select * from `" + code + "`WHERE date BETWEEN cast('"+before+"' as datetime) AND cast('"+end+"' as datetime)");
        return sb.toString();
    }

    public String querySharesCount(String code ,String before ,String end){
        StringBuilder sb = new StringBuilder();
        sb.append("select count(low) from `" + code + "`WHERE date BETWEEN cast('"+before+"' as datetime) AND cast('"+end+"' as datetime)" );
        return sb.toString();
    }
}
