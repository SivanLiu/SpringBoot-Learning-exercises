package com.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/cookie")
public class CookieServletDemo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("lastTime".equals(cookie.getName())) {
                    flag = true;
                    //不是首次登陆，需要展示上次登陆时间
                    String strDate = cookie.getValue();
                    //URL 解码
                    System.out.println("解码前: strDate = " + strDate);
                    strDate = URLDecoder.decode(strDate, "utf-8");
                    System.out.println("解码后: strDate = " + strDate);
                    response.getWriter().write("<h1>" + "您此次登陆不是首次登陆，上次登陆时间为：" + strDate + "</h1>");

                    Date date = new Date();
                    SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
                    strDate = simpleFormatter.format(date);
                    //URL编码
                    System.out.println("编码前：strDate = " + strDate);
                    strDate = URLEncoder.encode(strDate, "utf-8");
                    System.out.println("编码后：strDate = " + strDate);
                    cookie.setValue(strDate);
                    cookie.setMaxAge(60 * 60 * 24 * 30);
                    response.addCookie(cookie);
                    break;
                } else {
                    flag = false;
                }
            }
        }

        if (cookies == null || cookies.length == 0 || flag == false) {
            Date date = new Date();
            SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
            String strDate = simpleFormatter.format(date);
            //URL编码
            System.out.println("编码前：strDate = " + strDate);
            strDate = URLEncoder.encode(strDate, "utf-8");
            System.out.println("编码后：strDate = " + strDate);
            Cookie cookie = new Cookie("lastTime", strDate);
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);
            response.getWriter().write("<h1>" + "您此次登陆是首次登陆！" + "</h1>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
