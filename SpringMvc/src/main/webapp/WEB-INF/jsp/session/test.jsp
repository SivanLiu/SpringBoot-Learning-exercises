<%@ page import="com.spring.mvc.pojo.User" %>
<%@ page language="java" contentType="text/html;  charset = UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset = UTF-8">
    <title>测试 @SessionAttributes</title>
</head>
<body>
<%
    //从 session 中获取数据
    User user = (User) session.getAttribute("user");
    Long id = (Long) session.getAttribute("id_new");

    //展示数据
    out.print("<br>user_name = " + user.getUserName());
    out.println("<br>id_name = " + id);
%>
</body>
</html>
