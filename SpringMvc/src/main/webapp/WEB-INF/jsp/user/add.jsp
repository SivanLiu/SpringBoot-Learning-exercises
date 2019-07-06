<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>新增用户</title>
    <!--加载 Query 文件-->
    <script src="https://code.jquery.com/jquery-3.2.0.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#submit").click(function () {
                var userName = $("#userName").val();
                var note = $("#note").val();
                if ($.trim(userName) == '') {
                    alert("用户名不能为空！");
                    return;
                }

                var params = {
                    userName: userName,
                    note: note
                };

                $.post({
                    url: "./insert",
                    contentType: "application/json",
                    data: JSON.stringify(params),
                    success: function (result) {
                        if (result == null || result.id == null) {
                            alert("插入失败");
                            return;
                        }

                        alert("插入成功");
                    }
                });
            })
        });
    </script>
</head>

<body>
<div style="margin: 20px 0;"></div>
<form id="insertForm">
    <table>
        <tr>
            <td>用户名称：</td>
            <td><input id="userName" name="userName"></td>
        </tr>
        <tr>
            <td>备注</td>
            <td><input id="note" name="note"></td>
        </tr>
        <tr>
            <td></td>
            <td align="right"><input id="submit" type="button" value="提交"></td>
        </tr>
    </table>
</form>
</body>