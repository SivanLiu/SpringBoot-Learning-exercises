<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>测试 JSR-303</title>
    <!--加载 Query 文件-->
    <script src="https://code.jquery.com/jquery-3.2.0.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            //请求验证的 POJO
            var pojo = {
                id: null,
                date: '2017-08-08',
                doubleValue: 999999.09,
                integer: 100,
                range: 1000,
                email: 'email',
                size: 'adv121',
                regexp: 'a,b,c,d'
            }

            $.post({
                url: "./validate",
                //此处需要告知传递参数类型为 JSON, 不能缺少
                contentType: "application/json",
                //将 JSON 转化为字符串传递
                data: JSON.stringify(pojo),
                //成功后的方法
                success: function (result) {
                }
            });
        });
    </script>
</head>
<body></body>
</html>
