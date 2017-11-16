package com.java.study.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhongjing on 2015/12/4 0004.
 */
public class RegexExample {
    public static void main(String[] args) {
        //验证IP地址是否合法
        String ip = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                +"(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                +"(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                +"(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d|\\*?)$";
        String ipAddress = "192.168.1.*";
        String ipAddress1 = "192.168.1.1";
        String ipAddress2 = "192.168.1.**";
        String ipAddress3 = "192.1683.1.1";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(ipAddress);
        System.out.println(pattern.matcher(ipAddress).matches());
        System.out.println(pattern.matcher(ipAddress1).matches());
        System.out.println(pattern.matcher(ipAddress2).matches());
        System.out.println(pattern.matcher(ipAddress3).matches());

    }
}
