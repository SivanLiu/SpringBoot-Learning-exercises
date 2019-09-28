CREATE DATABASE `test_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use test_db;
create table if not exists user
(
    id int not null,
    name varchar(30) not null,
    gender varchar(10) not null,
    age int(2) not null,
    adderss varchar(50),
    qq varchar(30),
    email varchar(30),
    username varchar(30) not null,
    password varchar(30) not null
);

INSERT INTO `user` (`id`, `name`, `gender`, `age`, `adderss`, `qq`, `email`, `username`, `password`)
VALUES ('1', '张三', '男', '23', '北京', '458888325', '8955802544@qq.com', 'zhangsan', '123');
INSERT INTO `user` (`id`, `name`, `gender`, `age`, `adderss`, `qq`, `email`, `username`, `password`)
VALUES ('2', '李四', '男', '33', '广州', '458888225', '8955852544@qq.com', 'lisi', '123');
INSERT INTO `user` (`id`, `name`, `gender`, `age`, `adderss`, `qq`, `email`, `username`, `password`)
VALUES ('3', '王五', '男', '43', '上海', '475837237', '475837237@qq.com', 'wangwu', '123');
INSERT INTO `user` (`id`, `name`, `gender`, `age`, `adderss`, `qq`, `email`, `username`, `password`)
VALUES ('4', '小红', '女', '23', '深圳', '275837237', '275837237@qq.com', 'xiaohong', '123');
INSERT INTO `user` (`id`, `name`, `gender`, `age`, `adderss`, `qq`, `email`, `username`, `password`)
VALUES ('4', '小琴', '女', '12', '广州', '175837237', '175837237@qq.com', 'xiaoqin', '123');
