insert into SYS_USER (id, username, password) values(1, 'wyf', '321456');
insert into SYS_USER (id, username, password) values(2, 'wisely', '321456');


insert into SYS_ROLE (id, name) values(1, 'ROLE_ADMIN');
insert into SYS_ROLE (id, name) values(2, 'ROLE_USER');

insert into SYS_USER_ROLES (SYS_USER_ID, ROLES_ID) values(1, 1);
insert into SYS_USER_ROLES (SYS_USER_ID, ROLES_ID) values(2, 2);


