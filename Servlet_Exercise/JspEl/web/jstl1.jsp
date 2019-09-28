<%--
  Created by IntelliJ IDEA.
  User: sivanliu
  Date: 2019/9/28
  Time: 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.jsp.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    List list = new ArrayList();
    list.add(new User("张三", 24, new Date()));
    list.add(new User("李四", 20, new Date()));
    list.add(new User("王五", 14, new Date()));
    request.setAttribute("list", list);
%>

<table border="1" width="500" align="center">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>生日</th>
    </tr>
    <%--数据行--%>
    <c:forEach items="${list}" var="user" varStatus="s">

        <c:if test="${s.count mod 2 == 0}">
            <tr bgcolor="#8a2be2">
                <td>${s.count}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.birStr}</td>
            </tr>
        </c:if>

        <c:if test="${s.count mod 2 != 0}">
            <tr bgcolor="#faebd7">
                <td>${s.count}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.birStr}</td>
            </tr>
        </c:if>

    </c:forEach>
</table>
</body>
</html>
