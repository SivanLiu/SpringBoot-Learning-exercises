use test;
create table if not exists `user`
(
    `id`      int      not null,
    `name`    char(20) not null,
    `gender`  char(10) not null,
    `age`     int      not null,
    `address` char(20) not null,
    `qq`      char(10) not null,
    `email`   char(20) not null
);

insert into user(id, name, gender, age, address, qq, email)
values (2010282110, 'sivan', '男', 20, '余杭区1600号','4680712',
        '731212305@qq.com');
insert into user(id, name, gender, age, address, qq, email)
values (2010282111, 'sivan1', '女', 20, '余杭区1600号','468071256',
        '731212356@qq.com');
insert into user(id, name, gender, age, address, qq, email)
values (2010282112, 'sivan3', '男', 23, '余杭区1600号','4680712567',
        '73121230567@qq.com');
insert into user(id, name, gender, age, address, qq, email)
values (2010282113, 'sivan4', '男', 24, '余杭区1600号','4680712654',
        '7312123009@qq.com');
insert into user(id, name, gender, age, address, qq, email)
values (2010282114, 'sivan5', '女', 25, '余杭区1600号','4680712123',
        '731212305123@qq.com');

select *
from user;
