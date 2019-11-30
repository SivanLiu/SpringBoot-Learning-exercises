<%--
  Created by IntelliJ IDEA.
  User: sivanliu
  Date: 2019/11/25
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.nio.charset.Charset" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<%-- Here we fetch the data using the name attribute
     of the text from the previous page
--%>
<%
    request.setCharacterEncoding("utf-8");
    Map<String, String[]> val = request.getParameterMap();
    StringBuilder valueBuilder = new StringBuilder();
    valueBuilder.append("default encode = "+Charset.defaultCharset().displayName());
    for (String key : val.keySet()) {
        String[] values = val.get(key);
        valueBuilder.append(key).append(" = ");
        for (String value : values) {
            valueBuilder.append(value).append(";");
        }

        System.out.println("key = " + key + " value = " + valueBuilder.toString());
    }
%>
</body>
<%-- Here we use the JSP expression tag to display value
     stored in a variable
--%>
<h2>The text entered was : </h2><%=valueBuilder.toString()%>
</html>
