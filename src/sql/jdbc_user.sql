/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-11-20 14:03:59
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `jdbc_user`
-- ----------------------------
DROP TABLE IF EXISTS `jdbc_user`;
CREATE TABLE `jdbc_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) DEFAULT NULL COMMENT '户用名',
  `password` varchar(255) DEFAULT NULL COMMENT '户用密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jdbc_user
-- ----------------------------
