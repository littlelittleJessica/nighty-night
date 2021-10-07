-- 用户表
drop table if exists `user`;
create table `user` (
                        `id` bigint auto_increment not null comment 'ID',
                        `username` varchar(50) not null comment '用户名',
                        `password` char(32) not null comment '密码',
                        `mobile` varchar(11) not null comment '手机号',
                        `photo` varchar(200) comment '头像url',
                        primary key (`id`),
                        unique key `login_name_unique` (`username`),
                        unique key `student_no_unique` (`mobile`)
) engine=innodb default charset=utf8mb4 comment='用户';

insert into `user` (id, `username`, `password`,`mobile`,`photo`) values (1, 'test', 'C18F80EFEE1FA267583B5DF3D7E948C3','12312341234','');
