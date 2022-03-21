create table `t_seckill_goods`(
    `id` bigint(20) not null auto_increment comment '订单ID',
    `goods_id` bigint(20) not null comment '秒杀商品ID',
    `seckill_price` decimal(10,2) default '0.00' comment '秒杀价格',
    `stock_count` int(10) default null comment '库存数量',
    `start_date` datetime default null comment '秒杀开始时间',
    `end_date` datetime default null comment '秒杀结束时间',
    PRIMARY KEY (`id`)
    )engine = INNODB auto_increment=3 default charset = utf8mb4