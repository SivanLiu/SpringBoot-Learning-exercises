package com.maven;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

@WebServlet("/myServlet")
public class MyServlet extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String characterEncoding = request.getCharacterEncoding();
        System.out.println("local encoding = " + characterEncoding);

        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : parameterMap.keySet()) {
            stringBuilder.append("key = " + key);

            String[] values = parameterMap.get(key);
            for (String value : values) {
                String item = URLDecoder.decode(value, "UTF-8"); // and finally : Hélène
                System.out.println("gggg value = " + value + "  item = " + item);
                stringBuilder.append("  value = " + item);
            }
        }
        System.out.println("gggg parameter : = " + stringBuilder.toString());
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
