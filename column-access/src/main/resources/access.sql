/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : access

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-08-24 18:09:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for market_data
-- ----------------------------
DROP TABLE IF EXISTS `market_data`;
CREATE TABLE `market_data` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `add_user_count` bigint(11) DEFAULT NULL COMMENT '新增用户数',
  `user_cost` decimal(10,0) DEFAULT NULL COMMENT '用户成本',
  `all_recharge` decimal(10,0) DEFAULT NULL COMMENT '总充值额',
  `today_user_pay` decimal(10,0) DEFAULT NULL COMMENT '当日新增用户付费额',
  `today_add_user_count` bigint(11) DEFAULT NULL COMMENT '当日新增用户付费数',
  `a_user_cost` decimal(10,0) DEFAULT NULL COMMENT '单用户获取成本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of market_data
-- ----------------------------
INSERT INTO `market_data` VALUES ('1', '1', '2', '3', '4', '5', '6');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `desc` varchar(255) DEFAULT NULL COMMENT '角色描述信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '普通用户', '默认的用户角色');
INSERT INTO `sys_role` VALUES ('2', '管理员', '系统管理员');
INSERT INTO `sys_role` VALUES ('3', '测试人员', '测试~');

-- ----------------------------
-- Table structure for sys_role_table
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_table`;
CREATE TABLE `sys_role_table` (
  `role_id` int(11) NOT NULL,
  `tb_name` varchar(255) NOT NULL COMMENT '数据库中的表名',
  `tb_column_include` varchar(255) DEFAULT NULL COMMENT '该表可以查看的列(逗号隔开的多个值)',
  PRIMARY KEY (`role_id`,`tb_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_table
-- ----------------------------
INSERT INTO `sys_role_table` VALUES ('1', 'market_data', 'add_user_count,user_cost');
INSERT INTO `sys_role_table` VALUES ('2', 'market_data', 'add_user_count,user_cost,all_recharge,today_user_pay,today_add_user_count,a_user_cost');
INSERT INTO `sys_role_table` VALUES ('3', 'market_data', 'user_cost,today_user_pay,today_add_user_count');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'user1');
INSERT INTO `sys_user` VALUES ('2', 'admin1');
INSERT INTO `sys_user` VALUES ('3', 'cs1');
INSERT INTO `sys_user` VALUES ('4', 'user2');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1', '3');
INSERT INTO `sys_user_role` VALUES ('2', '2');
INSERT INTO `sys_user_role` VALUES ('3', '3');
INSERT INTO `sys_user_role` VALUES ('4', '1');

-- ----------------------------
-- Table structure for sys_user_table
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_table`;
CREATE TABLE `sys_user_table` (
  `user_id` int(11) NOT NULL,
  `tb_name` varchar(255) NOT NULL COMMENT '数据库中的表名',
  `tb_column_include` varchar(255) DEFAULT NULL COMMENT '该表可以查看的列(逗号隔开的多个值)',
  PRIMARY KEY (`user_id`,`tb_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_table
-- ----------------------------
INSERT INTO `sys_user_table` VALUES ('4', 'market_data', 'add_user_count,user_cost,all_recharge,today_user_pay,today_add_user_count,a_user_cost');
