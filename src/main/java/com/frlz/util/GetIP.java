package com.frlz.util;

import javax.servlet.http.HttpServletRequest;

public class GetIP {
    public String getIp(HttpServletRequest request)  {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");

        /*// 多个路由时，取第一个非unknown的ip
        final String[] arr = ipString.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ipString = str;
                break;
            }
        }

        return ipString;*/


    }


}
