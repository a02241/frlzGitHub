package com.frlz.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class GetIP {
    public static String getIp() throws IOException {
        Document document = Jsoup.connect("http://2018.ip138.com/ic.asp").get();
        Elements links = document.getElementsByTag("center");
        String info = links.text();
        String ip = info.substring(info.indexOf("[")+1, info.indexOf("]"));
        return ip;
    }
    public static String getAdress() throws IOException {
        Document document = Jsoup.connect("http://2018.ip138.com/ic.asp").get();
        Elements links = document.getElementsByTag("center");
        String info = links.text();
        String address = info.substring(info.lastIndexOf("：")+1);
        return address;
    }

}
