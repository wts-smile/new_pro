use shop;

create table `cart`
(
    `id`        int         not null auto_increment comment '主键id',
    `username`  varchar(32) not null default '' comment '用户名',
    `productid` int         not null comment '商品id',
    `count`    int         not null default 1 comment '数量',
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;