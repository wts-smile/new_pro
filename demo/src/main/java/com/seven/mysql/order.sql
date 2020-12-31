use shop;

create table `order` (
    `id` int not null auto_increment comment '主键id',
    `username` varchar(32) not null default '' comment '用户名',
    `productid` int not null comment '商品id',
    `status` int not null default 1 comment '1-未付款，2-已付款, 3-已取消',
    `starttime` varchar(32) comment '创建时间',
    `endtime` varchar(32) comment '结束时间',
    primary key (`id`)
) engine = InnoDB default charset = utf8mb4;