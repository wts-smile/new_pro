use shop;

create table `product`
(
    `id`      int          not null auto_increment comment '主键id',
    `name`    varchar(64)  not null default '' comment '商品名称',
    `ptype`   varchar(32)  not null default '' comment '商品类别',
    `pic`     varchar(512) not null default '' comment '图片-逗号隔开',
    `detail`  text         not null comment '详情',
    `price`   double       not null default 0.0 comment '价格',
    `status`  int          not null default 1 comment '1上架0下架',
    `salecnt` int          not null default 0 comment '销量',
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;