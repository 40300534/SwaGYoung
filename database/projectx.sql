/*
Navicat MySQL Data Transfer

Source Server         : 47.93.56.157
Source Server Version : 80016
Source Host           : 47.93.56.157:3306
Source Database       : projectx

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-06-30 10:15:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `brand`
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名字',
  `desc` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '介绍',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='品牌列表';

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES ('1', '茅台', '国酒');
INSERT INTO `brand` VALUES ('2', 'Macallan', '苏格兰威士忌第一品牌');

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL,
  `title` varchar(3000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '文章标题',
  `content` blob COMMENT '文章内容',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '文章图片',
  `news_category_id` int(11) DEFAULT NULL COMMENT '文章类别Id',
  `create_time` datetime DEFAULT NULL COMMENT '文章创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_create` (`create_time`),
  KEY `fk_news_category_news_idx` (`news_category_id`),
  CONSTRAINT `fk_news_category_news` FOREIGN KEY (`news_category_id`) REFERENCES `news_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of news
-- ----------------------------

-- ----------------------------
-- Table structure for `news_category`
-- ----------------------------
DROP TABLE IF EXISTS `news_category`;
CREATE TABLE `news_category` (
  `id` int(11) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '文章类别Id' COMMENT '文章类别名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of news_category
-- ----------------------------

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL,
  `buyer_id` bigint(20) NOT NULL COMMENT '买家用户Id',
  `seller_id` bigint(20) DEFAULT NULL COMMENT '卖家用户id',
  `price` decimal(20,0) NOT NULL COMMENT '订单价格',
  `create_time` datetime NOT NULL COMMENT '下单时间',
  `status` int(11) DEFAULT NULL COMMENT '订单状态',
  `seller_deposit` decimal(20,2) DEFAULT NULL COMMENT '卖家立即出售支付定金',
  `payment_type_id` int(11) DEFAULT NULL COMMENT '支付方法',
  PRIMARY KEY (`id`),
  KEY `fk_user_order_idx` (`buyer_id`),
  KEY `idx_create` (`create_time`),
  KEY `fk_user_order_seller_idx` (`seller_id`),
  KEY `fk_payment_type_order_idx` (`payment_type_id`),
  CONSTRAINT `fk_payment_type_order` FOREIGN KEY (`payment_type_id`) REFERENCES `payment_type` (`id`),
  CONSTRAINT `fk_user_buyer_order` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_user_seller_order` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for `origin`
-- ----------------------------
DROP TABLE IF EXISTS `origin`;
CREATE TABLE `origin` (
  `id` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='产地';

-- ----------------------------
-- Records of origin
-- ----------------------------
INSERT INTO `origin` VALUES ('1', '中国');
INSERT INTO `origin` VALUES ('2', '美国');

-- ----------------------------
-- Table structure for `payment_type`
-- ----------------------------
DROP TABLE IF EXISTS `payment_type`;
CREATE TABLE `payment_type` (
  `id` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付方法名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='支付方法';

-- ----------------------------
-- Records of payment_type
-- ----------------------------

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `product_category_id` int(11) NOT NULL COMMENT '商品分类Id',
  `brand_id` int(11) NOT NULL COMMENT '品牌',
  `origin_id` int(11) NOT NULL COMMENT '产地',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `image_folder` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '图片地址',
  `status` tinyint(3) unsigned NOT NULL COMMENT '状态',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品位置',
  `description` varchar(3000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '产品描述',
  `create_time` datetime NOT NULL COMMENT '上架时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `fk_product_category_product_idx` (`product_category_id`),
  KEY `fk_origin_product_idx` (`origin_id`),
  KEY `fk_brand_product_idx` (`brand_id`),
  CONSTRAINT `fk_brand_product` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  CONSTRAINT `fk_origin_product` FOREIGN KEY (`origin_id`) REFERENCES `origin` (`id`),
  CONSTRAINT `fk_product_category_product` FOREIGN KEY (`product_category_id`) REFERENCES `product_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `product_attr_key`
-- ----------------------------
DROP TABLE IF EXISTS `product_attr_key`;
CREATE TABLE `product_attr_key` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='商品属性类别';

-- ----------------------------
-- Records of product_attr_key
-- ----------------------------
INSERT INTO `product_attr_key` VALUES ('1', 'volume');
INSERT INTO `product_attr_key` VALUES ('2', 'alcohol');
INSERT INTO `product_attr_key` VALUES ('3', 'liquor_type');

-- ----------------------------
-- Table structure for `product_attr_value`
-- ----------------------------
DROP TABLE IF EXISTS `product_attr_value`;
CREATE TABLE `product_attr_value` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `attr_key_id` int(11) DEFAULT NULL COMMENT '商品属性类别',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`id`),
  KEY `fk_product_attr_key_product_attr_value_idx` (`attr_key_id`),
  KEY `fk_product_product_attr_value_idx` (`product_id`),
  CONSTRAINT `fk_product_attr_key_product_attr_value` FOREIGN KEY (`attr_key_id`) REFERENCES `product_attr_key` (`id`),
  CONSTRAINT `fk_product_product_attr_value` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='商品属性';

-- ----------------------------
-- Records of product_attr_value
-- ----------------------------

-- ----------------------------
-- Table structure for `product_category`
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '商品分类Id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品分类名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `product_image`
-- ----------------------------
DROP TABLE IF EXISTS `product_image`;
CREATE TABLE `product_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `order` int(11) DEFAULT '0',
  `is_master` tinyint(4) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_product_image_idx` (`product_id`),
  CONSTRAINT `fk_product_product_image` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
-- ----------------------------
-- Table structure for `product_order`
-- ----------------------------
DROP TABLE IF EXISTS `product_order`;
CREATE TABLE `product_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品订单Id',
  `product_id` bigint(20) NOT NULL COMMENT '商品Id',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `sold_price` decimal(20,2) NOT NULL COMMENT '成交价格',
  `quantity` int(5) NOT NULL COMMENT '数目',
  PRIMARY KEY (`id`),
  KEY `fk_product_product_order` (`product_id`),
  KEY `fk_order_product_order` (`order_id`),
  CONSTRAINT `fk_order_product_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
  CONSTRAINT `fk_product_product_order` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `product_user_buy`
-- ----------------------------
DROP TABLE IF EXISTS `product_user_buy`;
CREATE TABLE `product_user_buy` (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL COMMENT '商品Id',
  `user_id` bigint(20) NOT NULL COMMENT '用户Id',
  `price` decimal(10,0) NOT NULL COMMENT '价格',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `deposit` decimal(10,0) DEFAULT NULL COMMENT '买家出价押金',
  `fee` json DEFAULT NULL COMMENT '买家出家费用',
  PRIMARY KEY (`id`),
  KEY `fk_user_product_user_idx` (`user_id`),
  KEY `fk_product_product_user_buy` (`product_id`),
  CONSTRAINT `fk_product_product_user_buy` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_user_product_user_buy` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `product_user_favorate`
-- ----------------------------
DROP TABLE IF EXISTS `product_user_favorate`;
CREATE TABLE `product_user_favorate` (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_user_like_idx` (`product_id`),
  KEY `fk_user_product_user_like_idx` (`user_id`),
  CONSTRAINT `fk_product_product_user_like` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_user_product_user_like` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户收藏产品';

-- ----------------------------
-- Records of product_user_favorate
-- ----------------------------

-- ----------------------------
-- Table structure for `product_user_sell`
-- ----------------------------
DROP TABLE IF EXISTS `product_user_sell`;
CREATE TABLE `product_user_sell` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL COMMENT '商品id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `inventory` int(20) NOT NULL COMMENT '库存',
  `deposit` decimal(10,2) DEFAULT NULL COMMENT '卖家出售押金',
  `fee` json DEFAULT NULL COMMENT '卖家费用',
  PRIMARY KEY (`id`),
  KEY `fk_product_product_user_sell` (`product_id`),
  KEY `fk_user_product_user_sell` (`user_id`),
  CONSTRAINT `fk_product_product_user_sell` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_user_product_user_sell` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `shoppingcart`
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL COMMENT '用户Id',
  `product_id` bigint(20) NOT NULL COMMENT '商品Id',
  `create_time` datetime NOT NULL COMMENT '加入购物车时间',
  `quantity` int(20) NOT NULL COMMENT '数量',
  PRIMARY KEY (`id`),
  KEY `fk_user_shoppingcart_idx` (`user_id`),
  KEY `fk_product_shoppingcart` (`product_id`),
  CONSTRAINT `fk_product_shoppingcart` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_user_shoppingcart` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------

-- ----------------------------
-- Table structure for `swipe_image`
-- ----------------------------
DROP TABLE IF EXISTS `swipe_image`;
CREATE TABLE `swipe_image` (
  `id` int(20) NOT NULL COMMENT 'id',
  `image_folder` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '轮播图图片地址',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '轮播URL',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` tinyint(2) NOT NULL COMMENT '0未使用，1使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `displayname` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '昵称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `sex` int(2) NOT NULL COMMENT '性别',
  `dob` date NOT NULL COMMENT '生日',
  `user_type_id` int(11) NOT NULL COMMENT '用户类型Id',
  `phone` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT ' 手机号',
  PRIMARY KEY (`id`),
  KEY `fk_user_type_user` (`user_type_id`),
  CONSTRAINT `fk_user_type_user` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `user_address`
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL COMMENT '用户Id',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '地址',
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '城市',
  `province` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '省份',
  `phone` int(20) NOT NULL COMMENT '电话号码',
  `default` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1为默认地址，0为备用地址',
  PRIMARY KEY (`id`),
  KEY `fk_user_user_address_idx` (`user_id`),
  CONSTRAINT `fk_user_user_address` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user_address
-- ----------------------------

-- ----------------------------
-- Table structure for `user_type`
-- ----------------------------
DROP TABLE IF EXISTS `user_type`;
CREATE TABLE `user_type` (
  `id` int(11) NOT NULL COMMENT '用户类别Id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户类别名称',
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user_type
-- ----------------------------
INSERT INTO `user_type` VALUES ('1', 'Admin');
INSERT INTO `user_type` VALUES ('2', '第三方平台');
