use shop;

create table `address` (
    `id` int not null auto_increment comment '主键id',
    `name` varchar(32) not null default '' comment '用户名',
    `addr` varchar(256) not null default '' comment '地址',
    `isdefault` int not null default 0 comment '1默认0非默认',
    primary key (`id`),
    unique index `unq_name_addr` (`name`, `addr`)
) engine = InnoDB default charset = utf8mb4;