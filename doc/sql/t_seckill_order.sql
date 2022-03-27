DROP TABLE IF EXISTS `t_seckill_order`;
CREATE TABLE `t_seckill_order`  (
                                    `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '秒杀订单ID',
                                    `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户ID',
                                    `order_id` bigint(0) NULL DEFAULT NULL COMMENT '订单id',
                                    `goods_id` bigint(0) NULL DEFAULT NULL COMMENT '商品ID',
                                    PRIMARY KEY (`id`) USING BTREE,
                                    UNIQUE INDEX `seckill_uid_gid`(`user_id`, `goods_id`) USING BTREE COMMENT '用户id+商品id的唯一索引，解决同一个用户秒杀多商品'
) ENGINE = InnoDB AUTO_INCREMENT = 983 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;