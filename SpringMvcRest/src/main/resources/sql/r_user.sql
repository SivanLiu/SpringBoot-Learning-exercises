drop table if exists r_user;

create table r_user
(
    id        int(12)      not null auto_increment,
    user_name varchar(60)  not null,
    sex       int(12)      not null,
    note      varchar(256) null,
    primary key (id)
);
