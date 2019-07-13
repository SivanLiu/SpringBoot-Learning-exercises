drop table if exists `user`;
create table `user`
(
    `id`      INT not null auto_increment,
    `name`    varchar(20) default null,
    `account` varchar(20) default null,
    primary key (`id`),
    key `user_name_index` (`name`) using btree,
    key `user_account_index` (`account`) using btree
) ENGINE = INNODB
  default CHARSET = utf8;
insert into `user`
values ('1', '阿毅', 'ay');
insert into `user`
values ('2', '阿兰', 'al');