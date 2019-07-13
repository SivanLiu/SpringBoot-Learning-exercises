drop table if exists `user_mood_praise_rel`;
create table `user_mood_praise_rel`
(
    `id`      int not null auto_increment,
    `user_id` varchar(32) default null,
    `mood_id` varchar(32) default null,
    primary key (`id`),
    key `usre_mood_praise_rel_user_id_index` (`user_id`) using btree,
    key `user_mood_praise_rel_mood_id_index` (`mood_id`) using btree
) engine = InnoDB
  auto_increment = 2
  default charset = utf8;