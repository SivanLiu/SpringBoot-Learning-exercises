drop table if exists `user`;
create table `user`(
`id` INT not null auto_increment,
`name` varchar(20) default null,
`account` varchar(20) default null,
primary key (`id`),
key `user_name_index` (`name`) using btree,
key `user_account_index` (`account`) using btree
)ENGINE=INNODB default CHARSET=utf8;


drop table if exists `mood`;
create table `mood`(
`id` int not null auto_increment,
`content` varchar(256) default null,
`user_id` varchar(32) default null,
`publish_time` datetime default null,
`praise_num` int(11) default null,
primary key(`id`),
key `mood_user_id_index` (`user_id`) using btree
)engine=InnoDB default charset=utf8;

drop table if exists `user_mood_praise_rel`;
create table `user_mood_praise_rel`(
`id` int not null auto_increment,
`user_id` varchar(32) default null,
`mood_id` varchar(32) default null,
primary key(`id`),
key `usre_mood_praise_rel_user_id_index` (`user_id`) using btree,
key `user_mood_praise_rel_mood_id_index` (`mood_id`) using btree
)engine=InnoDB auto_increment=2 default charset=utf8;


insert into `user` values('1', '阿毅','ay');
insert into `user` values('2', '阿兰','al');

insert into `mood` values('1', '今天天气真好','1','2018-06-30 22:09:06','100');

insert into `mood` values('2', '厦门真美','2','2018-07-30 22:09:06','99');
