-- 用户表
drop table if exists `user`;
create table `user`
(
    `id`       bigint auto_increment not null comment 'ID',
    `username` varchar(50)           not null comment '用户名',
    `password` char(32)              not null comment '密码',
    `email`    varchar(30)           not null comment '邮箱',
    `photo`    varchar(200) comment '头像url',
    primary key (`id`),
    unique key `login_name_unique` (`username`),
    unique key `student_no_unique` (`email`)
) engine = innodb
  default charset = utf8mb4 comment ='用户';

insert into `user` (id, `username`, `password`, `email`, `photo`)
values (1, 'test', 'C18F80EFEE1FA267583B5DF3D7E948C3', '446067382@qq.com', '');

# 短信验证码
drop table if exists `verification_code`;
create table `verification_code`
(
    `id`     bigint auto_increment not null comment 'id',
    `email`  varchar(50)           not null comment '邮箱',
    `code`   char(6)               not null comment '验证码',
    `at`     datetime(3)           not null comment '生成时间',
    `status` char(1)               not null comment '用途|枚举[SmsStatusEnum]：USED("U", "已使用"), NOT_USED("N", "未使用")',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='短信验证码';

insert into `verification_code` (id, email, code, at, status)
values (1, '446067382@qq.com', '123456', now(), 'N');

# 助眠之声
drop table if exists `voice`;
create table `voice`
(
    `id`          bigint auto_increment not null comment 'id',
    `name`        varchar(50)           not null comment '名称',
    `cover`       varchar(200) comment '封面url',
    `description` varchar(2000) comment '概述',
    `time`        int default 0 comment '时长|单位秒',
    `category`    char(1) comment '类别|枚举[VoiceCategory]：MUSIC("M", "轻音乐"),STORY("S", "睡眠故事"),WHITE_NOISE("W", "白噪音")',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='助眠之声';

-- 音乐内容文件
drop table if exists `voice_file`;
create table `voice_file`
(
    `id`       bigint auto_increment not null comment 'id',
    `voice_id` bigint                not null comment '音乐id',
    `url`      varchar(100) comment '地址',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='音乐内容文件';

# 用户收藏音乐
drop table if exists `user_voice`;
create table `user_voice`
(
    `id`       bigint auto_increment not null comment 'id',
    `user_id`  bigint not null comment '用户id',
    `voice_id` bigint not null comment '音乐id',
    primary key (`id`),
    unique key `user_voice_unique` (`user_id`, `voice_id`)
) engine = innodb
  default charset = utf8mb4 comment ='用户收藏音乐';