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
    `voice`       varchar(200) comment 'voice url',
    `description` varchar(2000) comment 'description',
    `time`        varchar(50)           not null comment 'time',
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

# daily
drop table if exists `daily`;
create table `daily`
(
    `id`   bigint auto_increment not null comment 'id',
    `image`  varchar(200) comment 'picture url',
    `title` varchar(200) comment 'title',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='daily';

# daily content
drop table if exists `daily_content`;
create table `daily_content`
(
    `id`   bigint auto_increment not null comment 'daily_id',
    `content` mediumtext not null comment 'content',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='daily content';

# file
drop table if exists `file`;
create table `file`
(
    `id`          char(8)      not null default '' comment 'id',
    `path`        varchar(100) not null comment 'url',
    `name`        varchar(100) comment 'name',
    `suffix`      varchar(10) comment 'suffix',
    `size`        int comment 'size|Byte',
    `created_at`  datetime(3) comment 'created time',
    `updated_at`  datetime(3) comment 'updated time',
    primary key (`id`),
    unique key `path_unique` (`path`)
) engine = innodb
  default charset = utf8mb4 comment ='文件';

# sleep record
drop table if exists `record`;
create table `record`
(
    `id`          bigint auto_increment not null comment 'id',
    `user_id`     bigint                not null comment 'user ID',
    `date`        char(10)                  not null comment 'date',
    `sleep_time`  char(10)           not null comment 'sleep time of last night',
    `wakeup_time` char(10)           not null comment 'wake up time of last night',
    `emotion`     char(1) comment 'emotion|enum[sleep_emotion]: RELAX("R", "Relax and comfort"),
ANXIETY("A", "Anxious and depressed"),STRESSFUL("S", "Stressed out"), TIRED("T", "Tired and exhausted"),
NONE("N", "None of them")',
    `dream`       char(1) comment 'dream|enum[dream]: NO("N", "No dreams"),GOOD("G", "Had sweet dreams"),
COMMON("C","Had common dreams", NIGHTMARES("M","Had nightmares"))',
    `evaluation`  char(1) comment 'feeling|enum[evaluation]: AWESOME("A", "Awesome"),Good("G", "Good"),
BAD("B", "Bad"), TERRIBLE("T", "Terrible")',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='sleep record';

# diary
drop table if exists `t_article`;
create table `t_article`
(
    `aid`   bigint auto_increment not null comment 'article_id',
    `title`  varchar(200) comment 'article title',
    `content` longtext not null comment 'content',
    `uid` bigint                not null comment 'user|id',
    primary key (`aid`)
) engine = innodb
  default charset = utf8mb4 comment ='article';
