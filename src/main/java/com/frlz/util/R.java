package com.frlz.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: frlz
 * @description: 统一响应规范
 * @author: cz
 * @date: 2019-03-19 09:45
 **/
@Data
public class R<T> implements Serializable {
    private T data; //服务端数据
    private int status = 0; //状态码，0：成功，1：失败
    private String msg = ""; //描述信息

    // 省略 set get

    public static R isOk() {
        return new R().msg("success");
    }

    public static R isFail() {
        return new R().status(1).msg("fail");
    }

    public static R isFail(Throwable e) {
        return isFail().msg(e);
    }

    public R msg(Throwable e) {
        this.setMsg(e.toString());
        return this;
    }

    public R data(T data) {
        this.setData(data);
        return this;
    }

    public R msg(String msg){
        this.setMsg(msg);
        return this;
    }

    public R status(int status) {
        this.setStatus(status);
        return this;
    }
}
