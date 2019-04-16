package com.frlz.util;

import io.swagger.annotations.ApiModelProperty;
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
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "数据对象", name = "数据对象")
    private T data; //服务端数据
    @ApiModelProperty(value = "错误码", name = "错误码")
    private int status = 0; //状态码，0：成功，1：失败
    @ApiModelProperty(value = "错误码描述", name = "错误码描述")
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
