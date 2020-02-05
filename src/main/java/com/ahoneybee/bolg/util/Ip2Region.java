package com.ahoneybee.bolg.util;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ip2Region {

    public static String sendGet(String ip) {
        try {

            String url = "http://api.online-service.vip/ip3?ip=";

            URL query = new URL(url + ip);

            HttpURLConnection conn = (HttpURLConnection) query.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();

            String regEx = "[\\u4e00-\\u9fa5]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(br.readLine());

            while (m.find()) {
                sb.append(m.group());
            }
            return String.valueOf(sb);

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void judgeIp(String ip) {
        if (StringUtils.isEmpty(ip)) {
            throw new RuntimeException("nginx无法获取ip!!!");
        }
    }
}
