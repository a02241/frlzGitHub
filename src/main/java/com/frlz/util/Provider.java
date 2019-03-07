package com.frlz.util;

public class Provider {
    public String queryShares(String code){
        StringBuilder sb = new StringBuilder();
        sb.append("select * from `" + code + "`");
        return sb.toString();
    }

    public String querySharesCount(String code){
        StringBuilder sb = new StringBuilder();
        sb.append("select count(low) from `" + code + "`");
        return sb.toString();
    }
}
