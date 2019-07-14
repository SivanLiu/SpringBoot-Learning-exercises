<%@ page language="java" contentType="text/html; ISO-8859-1; charset=utf-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hello Spring Boot</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
        function post() {
            var params = {
                'userName': 'user_name_new',
                'sexCode': 1,
                'note': 'note_new'
            };

            $.post({
                url: "./user",
                //此处需要告知传递参数类型为 JSON, 不能缺少
                contentType: "application/json",
                //将 JSON 转化为字符串传递
                data: JSON.stringify(params),
                //成功后的方法
                success: function (result) {
                    if (result == null || result.id == null) {
                        alert("插入失败");
                        return;
                    }

                    alert("插入成功");
                }
            });
        }

        function updateUser() {
            var params = {
                'userName': 'user_name_new',
                'sexCode': 0,
                'note': 'note_new_update'
            };

            $.ajax({
                url: "./user/4",
                //此处告知使用 PUT 请求
                type: 'PUT',
                contentType: "application/json",
                //将 JSON 转化为字符串传递
                data: JSON.stringify(params),
                success: function (user, status) {
                    if (user == null) {
                        alert("结果为空");
                    } else {
                        alert(JSON.stringify(user));
                    }
                }
            });
        }

        function changeUserName() {
            $.ajax({
                url: './user/4/user_name_patch',
                type: 'PATCH',
                success: function (result, status) {
                    if (result == null) {
                        alert("结果为空");
                    } else {
                        alert(result.success ? "更新成功" : "更新失败");
                    }
                }
            });
        }

        function postStatus() {
            //请求体
            var params = {
                'userName': 'user_name_new',
                'sexCode': 1,
                'note': 'note_new'
            }

            var url = "./user2/entity";
            //var url = "./user2/annotation";
            $.post({
                url: url,
                contentType: "application/json",
                data: JSON.stringify(params),
                success: function (result, status, jqXHR) {
                    var success = jqXHR.getResponseHeader("success");
                    var status = jqXHR.status;
                    alert("响应头参数是：" + success + ", 状态码是：" + status);
                    if (result == null || result.id == null) {
                        alert("插入失败");
                        return;
                    }
                    alert("插入成功")
                }
            })
        }

        function postExp() {
            var url = "./user2/exp/5333";
            //var url = "./user2/annotation";
            $.ajax({
                url: url,
                type: 'GET'
            })
        }

        function deleteUser() {
            $.ajax({
                url: "./user/4",
                type: 'DELETE',
                success: function (result) {
                    if (result == null) {
                        alert("结果为空");
                    } else {
                        alert(result.success ? "删除成功" : "删除失败");
                    }
                }
            });
        }

        function get() {
            $.get("./user/1", function (user, status) {
                if (user == null) {
                    alert("结果为空 status = " + status)
                } else {
                    alert("用户信息为" + JSON.stringify(user));
                }
            });
        }

        // post();
        // updateUser();
        // changeUserName();
        // deleteUser();
        // postStatus();
        postExp();
        // get();
    </script>
</head>
<body>
测试 RESTFUL 下的请求
</body>
</html>
