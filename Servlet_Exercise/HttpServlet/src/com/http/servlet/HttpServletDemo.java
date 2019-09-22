package com.http.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet("/servlet1")
public class HttpServletDemo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //        BufferedReader reader = request.getReader();
        //        String line = null;
        //        while ((line = reader.readLine()) != null) {
        //            System.out.println("line = " + line);
        //        }
        //        System.out.println("=============getReader=============");

        String username = request.getParameter("username");
        System.out.println("username = " + username);
        System.out.println("=============getParameter=============");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String param = parameterNames.nextElement();
            String parameter = request.getParameter(param);
            System.out.println("param key = " + param + " value = " + parameter);
        }
        System.out.println("==============getParameterNames============");

//        String[] hobbies = request.getParameterValues("hobby");
//        for (String hobby : hobbies) {
//            System.out.println("hobby = " + hobby);
//        }

        System.out.println("============getParameterValues==============");
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> keySet = parameterMap.keySet();
        for (String key : keySet) {
            String[] values = parameterMap.get(key);
            System.out.println("key " + key);
            for (String value : values) {
                System.out.println(value);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //        request.setCharacterEncoding("utf-8");
        //        String method = request.getMethod();
        //        System.out.println("method = " + method);
        //        String contextPath = request.getContextPath();
        //        System.out.println("contextPath = " + contextPath);
        //        String servletPath = request.getServletPath();
        //        System.out.println("servletPath = " + servletPath);
        //        String queryString = request.getQueryString();
        //        System.out.println("queryString = " + queryString);
        //        String requestURI = request.getRequestURI();
        //        System.out.println("requestURI = " + requestURI);
        //        StringBuffer requestURL = request.getRequestURL();
        //        System.out.println("requestURL = " + requestURL);
        //        String protocol = request.getProtocol();
        //        System.out.println("protocol = " + protocol);
        //        String remoteAddr = request.getRemoteAddr();
        //        System.out.println("remoteAddr = " + remoteAddr);
        //
        //        Enumeration<String> headerNames = request.getHeaderNames();
        //        while (headerNames.hasMoreElements()) {
        //            String name = headerNames.nextElement();
        //            String header = request.getHeader(name);
        //            System.out.println("header : name = " + name + " value = " + header);
        //        }
        //
        //        String referer = request.getHeader("referer");
        //        System.out.println("referer = " + referer);


    }
}
