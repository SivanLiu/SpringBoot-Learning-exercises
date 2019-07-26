<%@ page language="java" contentType="text/html; ISO-8859-1; charset=utf-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>购买产品测试</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">

        for (var i = 1; i < 50000; i++) {
            var params = {
                userId: 1,
                productId: 1,
                quantity: 1
            };
            //通过 post 请求后端
            $.post("./purchase", params, function (result) {
                // alert(result.message)
            })
        }

    </script>
</head>
<body>
<h1>抢购产品测试</h1>
</body>
