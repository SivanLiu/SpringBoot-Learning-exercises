drop table if exists `mood`;
create table `mood`
(
    `id`           int not null auto_increment,
    `content`      varchar(256) default null,
    `user_id`      varchar(32)  default null,
    `publish_time` datetime     default null,
    `praise_num`   int(11)      default null,
    primary key (`id`),
    key `mood_user_id_index` (`user_id`) using btree
) engine = InnoDB
  default charset = utf8;
insert into `mood` values('1', '今天天气真好','1','2018-06-30 22:09:06','100');
insert into `mood` values('2', '厦门真美','2','2018-07-30 22:09:06','99');