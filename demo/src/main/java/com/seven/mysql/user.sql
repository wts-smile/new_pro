use shop;

create table `user`(
    `id` int not null auto_increment comment '主键id',
    `name` varchar(32) not null default '' comment '用户名',
    `pass` varchar(32) not null default '' comment '密码',
    `detail` text not null comment '用户资料',
    `balance` double not null default 0 comment '余额',
    `role` int not null default 1 comment '1系统用户2管理员3游客',
    primary key (`id`),
    unique index uni_name(`name`)
) engine = InnoDb default charset = utf8mb4;