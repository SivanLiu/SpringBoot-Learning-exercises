<%--
  Created by IntelliJ IDEA.
  User: sivanliu
  Date: 2019/9/28
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<div >${user.name},欢迎您</div>
<div align="center">
    <a
            href="${pageContext.request.contextPath}/findUserByPageServlet" style="text-decoration:none;font-size:33px">查询所有用户信息
    </a>
</div>
</body>
</html>
