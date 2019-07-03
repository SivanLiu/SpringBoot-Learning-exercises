drop table if exists user;

create table user
(
  id        int(12)      not null auto_increment,
  user_name varchar(60)  not null,
  note      varchar(256) null,
  primary key (id)
);
