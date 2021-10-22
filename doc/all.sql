-- user
drop table if exists `user`;
create table `user`
(
    `id`       bigint auto_increment not null comment 'ID',
    `username` varchar(50)           not null comment 'username',
    `password` char(32)              not null comment 'password',
    `email`    varchar(30)           not null comment 'email',
    `photo`    varchar(200) comment 'photo url',
    primary key (`id`),
    unique key `login_name_unique` (`username`),
    unique key `student_no_unique` (`email`)
) engine = innodb
  default charset = utf8mb4 comment ='user';

insert into `user` (id, `username`, `password`, `email`, `photo`)
values (1, 'test', 'C18F80EFEE1FA267583B5DF3D7E948C3', '446067382@qq.com', '');

# email verification code
drop table if exists `verification_code`;
create table `verification_code`
(
    `id`     bigint auto_increment not null comment 'id',
    `email`  varchar(50)           not null comment 'email',
    `code`   char(6)               not null comment 'verification code',
    `at`     datetime(3)           not null comment 'created/sent time',
    `status` char(1)               not null comment 'status|enum[SmsStatusEnum]：USED("U", "used"), NOT_USED("N", "unused")',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='短信验证码';

insert into `verification_code` (id, email, code, at, status)
values (1, '446067382@qq.com', '123456', now(), 'N');

# Voice of Sleep
drop table if exists `voice`;
create table `voice`
(
    `id`          bigint auto_increment not null comment 'id',
    `name`        varchar(50)           not null comment 'name',
    `cover`       varchar(200) comment 'cover url',
    `description` varchar(2000) comment 'description',
    `time`        int default 0 comment 'time|second',
    `category`    char(1) comment 'category|enum[VoiceCategory]：MUSIC("M", "Light Music"),STORY("S", "Sleep Story"),WHITE_NOISE("W", "White Noise")',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='Voice of Sleep';

-- voice file
drop table if exists `voice_file`;
create table `voice_file`
(
    `id`       bigint auto_increment not null comment 'id',
    `voice_id` bigint                not null,
    `url`      varchar(100) comment 'url of the voice content',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='the content file of the music';

# the favorite voice of the users
drop table if exists `user_voice`;
create table `user_voice`
(
    `id`       bigint auto_increment not null comment 'id',
    `user_id`  bigint                not null not null comment 'user_id',
    `voice_id` bigint                not null not null comment 'voice_id',
    primary key (`id`),
    unique key `user_voice_unique` (`user_id`, `voice_id`)
) engine = innodb
  default charset = utf8mb4 comment ='the favorite voice of the users';

# role
drop table if exists `role`;
create table `role`
(
    `id`   bigint auto_increment not null comment 'id',
    `name` varchar(50)           not null comment 'role',
    `desc` varchar(100)          not null comment 'description',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='role';

insert into `role`
values ('1', 'admin', 'administrator who can update resources');
insert into `role`
values ('2', 'user', 'common user');

# role and user related
drop table if exists `role_user`;
create table `role_user`
(
    `id`      bigint auto_increment not null comment 'id',
    `role_id` bigint                not null comment 'role|id',
    `user_id` bigint                not null comment 'user|id',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='role and user related';

insert into `role_user` values ('1', '1', '13');
insert into `role_user` values ('2', '2', '1');
insert into `role_user` values ('3', '2', '12');


# daily
drop table if exists `daily`;
create table `daily`
(
    `id`          bigint auto_increment not null comment 'id',
    `pic`       varchar(200) comment 'picture url',
    `sentence` varchar(2000) comment 'sentence',
    `date` date not null comment 'date',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='daily';