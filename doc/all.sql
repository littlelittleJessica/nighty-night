-- 用户表
drop table if exists `user`;
create table `user` (
                        `id` bigint auto_increment not null comment 'ID',
                        `username` varchar(50) not null comment '用户名',
                        `password` char(32) not null comment '密码',
                        `email` varchar(30) not null comment '邮箱',
                        `photo` varchar(200) comment '头像url',
                        primary key (`id`),
                        unique key `login_name_unique` (`username`),
                        unique key `student_no_unique` (`email`)
) engine=innodb default charset=utf8mb4 comment='用户';

insert into `user` (id, `username`, `password`,`email`,`photo`) values (1, 'test', 'C18F80EFEE1FA267583B5DF3D7E948C3','446067382@qq.com','');

# 短信验证码
drop table if exists `verification_code`;
create table `verification_code` (
                       `id` bigint auto_increment not null comment 'id',
                       `email` varchar(50) not null comment '邮箱',
                       `code` char(6) not null comment '验证码',
                       `at` datetime(3) not null comment '生成时间',
                       `status` char(1) not null comment '用途|枚举[SmsStatusEnum]：USED("U", "已使用"), NOT_USED("N", "未使用")',
                       primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='短信验证码';

insert into `verification_code` (id, email, code, at, status) values (1, '446067382@qq.com', '123456', now(), 'N');

