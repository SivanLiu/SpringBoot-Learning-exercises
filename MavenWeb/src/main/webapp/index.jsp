<%--<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<!-- 指定字符集 -->
<meta charset="utf-8">
<meta http-equiv="Content-Type"  content="text/html; charset=UTF-8">
<body>
<h2>Hello World!</h2>

<div>
    <form action="myServlet" method="post">
        <div>
            <label for="name">姓名：</label>
            <input type="text" id="name" name="name" placeholder="请输入姓名">
        </div>

        <div >
            <input class="btn btn-primary" type="submit" value="提交"/>
        </div>
    </form>
</div>
</body>
</html>
