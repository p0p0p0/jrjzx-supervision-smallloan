/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50636
Source Host           : 127.0.0.1:3306
Source Database       : rejoice

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-06-14 09:36:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `rejoice_active_history`
-- ----------------------------
DROP TABLE IF EXISTS `rejoice_active_history`;
CREATE TABLE `rejoice_active_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(200) DEFAULT NULL,
  `company_code` varchar(20) DEFAULT NULL,
  `active_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rejoice_active_history
-- ----------------------------
INSERT INTO `rejoice_active_history` VALUES ('1', '21', '2', '2017-06-11 22:00:34', '2017-06-11 22:00:34', '2017-06-11 22:00:34', null);
INSERT INTO `rejoice_active_history` VALUES ('2', '21', '2', '2017-06-11 22:00:37', '2017-06-11 22:00:37', '2017-06-11 22:00:37', null);
INSERT INTO `rejoice_active_history` VALUES ('3', '21', '4', '2017-06-11 22:00:55', '2017-06-11 22:00:55', '2017-06-11 22:00:55', null);
INSERT INTO `rejoice_active_history` VALUES ('4', '21', '6', '2017-06-11 22:03:14', '2017-06-11 22:03:14', '2017-06-11 22:03:14', null);
INSERT INTO `rejoice_active_history` VALUES ('5', '21', '6', '2017-06-11 22:03:14', '2017-06-11 22:03:14', '2017-06-11 22:03:14', null);
INSERT INTO `rejoice_active_history` VALUES ('6', '21', '6', '2017-06-11 22:03:15', '2017-06-11 22:03:15', '2017-06-11 22:03:15', null);
INSERT INTO `rejoice_active_history` VALUES ('7', '21', '6', '2017-06-11 22:03:16', '2017-06-11 22:03:16', '2017-06-11 22:03:16', null);
INSERT INTO `rejoice_active_history` VALUES ('8', '21', '6', '2017-06-11 22:03:16', '2017-06-11 22:03:16', '2017-06-11 22:03:16', null);
INSERT INTO `rejoice_active_history` VALUES ('9', '21', '6', '2017-06-11 22:03:17', '2017-06-11 22:03:17', '2017-06-11 22:03:17', null);
INSERT INTO `rejoice_active_history` VALUES ('10', '21', '6', '2017-06-11 22:03:18', '2017-06-11 22:03:18', '2017-06-11 22:03:18', null);
INSERT INTO `rejoice_active_history` VALUES ('11', '21', '6', '2017-06-11 22:03:18', '2017-06-11 22:03:18', '2017-06-11 22:03:18', null);
INSERT INTO `rejoice_active_history` VALUES ('12', '21', '6', '2017-06-11 22:03:18', '2017-06-11 22:03:18', '2017-06-11 22:03:18', null);
INSERT INTO `rejoice_active_history` VALUES ('13', '21', '6', '2017-06-11 22:03:19', '2017-06-11 22:03:19', '2017-06-11 22:03:19', null);
INSERT INTO `rejoice_active_history` VALUES ('14', '21', '6', '2017-06-11 22:03:19', '2017-06-11 22:03:19', '2017-06-11 22:03:19', null);
INSERT INTO `rejoice_active_history` VALUES ('15', '21', '6', '2017-06-11 22:03:19', '2017-06-11 22:03:19', '2017-06-11 22:03:19', null);
INSERT INTO `rejoice_active_history` VALUES ('16', '21', '6', '2017-06-11 22:03:19', '2017-06-11 22:03:19', '2017-06-11 22:03:19', null);
INSERT INTO `rejoice_active_history` VALUES ('17', '21', '6', '2017-06-11 22:03:20', '2017-06-11 22:03:20', '2017-06-11 22:03:20', null);
INSERT INTO `rejoice_active_history` VALUES ('18', '21', '6', '2017-06-11 22:03:20', '2017-06-11 22:03:20', '2017-06-11 22:03:20', null);
INSERT INTO `rejoice_active_history` VALUES ('19', '21', '6', '2017-06-11 22:03:20', '2017-06-11 22:03:20', '2017-06-11 22:03:20', null);
INSERT INTO `rejoice_active_history` VALUES ('20', '21', '6', '2017-06-11 22:03:20', '2017-06-11 22:03:20', '2017-06-11 22:03:20', null);
INSERT INTO `rejoice_active_history` VALUES ('21', '21', '6', '2017-06-11 22:03:21', '2017-06-11 22:03:21', '2017-06-11 22:03:21', null);
INSERT INTO `rejoice_active_history` VALUES ('22', '21', '6', '2017-06-11 22:03:21', '2017-06-11 22:03:21', '2017-06-11 22:03:21', null);
INSERT INTO `rejoice_active_history` VALUES ('23', '21', '6', '2017-06-11 22:03:21', '2017-06-11 22:03:21', '2017-06-11 22:03:21', null);
INSERT INTO `rejoice_active_history` VALUES ('24', '21', '6', '2017-06-11 22:03:22', '2017-06-11 22:03:22', '2017-06-11 22:03:22', null);
INSERT INTO `rejoice_active_history` VALUES ('25', '21', '6', '2017-06-11 22:03:22', '2017-06-11 22:03:22', '2017-06-11 22:03:22', null);
INSERT INTO `rejoice_active_history` VALUES ('26', '21', '6', '2017-06-11 22:03:22', '2017-06-11 22:03:22', '2017-06-11 22:03:22', null);
INSERT INTO `rejoice_active_history` VALUES ('27', '21', '6', '2017-06-11 22:03:22', '2017-06-11 22:03:22', '2017-06-11 22:03:22', null);
INSERT INTO `rejoice_active_history` VALUES ('28', '21', '6', '2017-06-11 22:03:22', '2017-06-11 22:03:22', '2017-06-11 22:03:22', null);
INSERT INTO `rejoice_active_history` VALUES ('29', '21', '6', '2017-06-11 22:03:23', '2017-06-11 22:03:23', '2017-06-11 22:03:23', null);
INSERT INTO `rejoice_active_history` VALUES ('30', '21', '6', '2017-06-11 22:03:23', '2017-06-11 22:03:23', '2017-06-11 22:03:23', null);
INSERT INTO `rejoice_active_history` VALUES ('31', '21', '6', '2017-06-11 22:03:23', '2017-06-11 22:03:23', '2017-06-11 22:03:23', null);
INSERT INTO `rejoice_active_history` VALUES ('32', '21', '6', '2017-06-11 22:03:23', '2017-06-11 22:03:23', '2017-06-11 22:03:23', null);
INSERT INTO `rejoice_active_history` VALUES ('33', '21', '6', '2017-06-11 22:03:23', '2017-06-11 22:03:23', '2017-06-11 22:03:23', null);
INSERT INTO `rejoice_active_history` VALUES ('34', '21', '6', '2017-06-11 22:03:24', '2017-06-11 22:03:24', '2017-06-11 22:03:24', null);
INSERT INTO `rejoice_active_history` VALUES ('35', '21', '6', '2017-06-11 22:03:24', '2017-06-11 22:03:24', '2017-06-11 22:03:24', null);
INSERT INTO `rejoice_active_history` VALUES ('36', '21', '6', '2017-06-11 22:03:24', '2017-06-11 22:03:24', '2017-06-11 22:03:24', null);
INSERT INTO `rejoice_active_history` VALUES ('37', '21', '6', '2017-06-11 22:03:24', '2017-06-11 22:03:24', '2017-06-11 22:03:24', null);
INSERT INTO `rejoice_active_history` VALUES ('38', '21', '6', '2017-06-11 22:03:24', '2017-06-11 22:03:24', '2017-06-11 22:03:24', null);
INSERT INTO `rejoice_active_history` VALUES ('39', '21', '6', '2017-06-11 22:03:25', '2017-06-11 22:03:25', '2017-06-11 22:03:25', null);
INSERT INTO `rejoice_active_history` VALUES ('40', '发多少离开', '3', '2017-06-11 22:06:58', '2017-06-11 22:06:58', '2017-06-11 22:06:58', null);
INSERT INTO `rejoice_active_history` VALUES ('41', '发多少离开', '3', '2017-06-11 22:06:59', '2017-06-11 22:06:59', '2017-06-11 22:06:59', null);
INSERT INTO `rejoice_active_history` VALUES ('42', '发多少离开', '3', '2017-06-11 22:07:00', '2017-06-11 22:07:00', '2017-06-11 22:07:00', null);
INSERT INTO `rejoice_active_history` VALUES ('43', '发多少离开', '3', '2017-06-11 22:07:00', '2017-06-11 22:07:00', '2017-06-11 22:07:00', null);
INSERT INTO `rejoice_active_history` VALUES ('44', '发多少离开', '3', '2017-06-11 22:07:01', '2017-06-11 22:07:01', '2017-06-11 22:07:01', null);
INSERT INTO `rejoice_active_history` VALUES ('45', '发多少离开', '3', '2017-06-11 22:07:01', '2017-06-11 22:07:01', '2017-06-11 22:07:01', null);
INSERT INTO `rejoice_active_history` VALUES ('46', '321	21	1221		2017-05-08 14:59:46	2017-06-09 14:59:50	\r\n', '1', '2017-06-11 22:07:09', '2017-06-11 22:07:09', '2017-06-11 22:07:09', null);
INSERT INTO `rejoice_active_history` VALUES ('47', '321	21	1221		2017-05-08 14:59:46	2017-06-09 14:59:50	\r\n', '1', '2017-06-11 22:07:10', '2017-06-11 22:07:10', '2017-06-11 22:07:10', null);
INSERT INTO `rejoice_active_history` VALUES ('48', '321	21	1221		2017-05-08 14:59:46	2017-06-09 14:59:50	\r\n', '1', '2017-06-11 22:08:58', '2017-06-11 22:08:58', '2017-06-11 22:08:58', null);
INSERT INTO `rejoice_active_history` VALUES ('49', '321	21	1221		2017-05-08 14:59:46	2017-06-09 14:59:50	\r\n', '1', '2017-06-12 15:36:05', '2017-06-12 15:36:05', '2017-06-12 15:36:05', null);

-- ----------------------------
-- Table structure for `rejoice_company`
-- ----------------------------
DROP TABLE IF EXISTS `rejoice_company`;
CREATE TABLE `rejoice_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `max_active_size` bigint(20) DEFAULT NULL COMMENT '最大激活量',
  `active_size` bigint(20) DEFAULT '0' COMMENT '已激活',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL COMMENT '公司代码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rejoice_company
-- ----------------------------
INSERT INTO `rejoice_company` VALUES ('1', '21', '21', '1221', '2', '2017-05-08 14:59:46', '2017-06-11 22:00:37', '2');
INSERT INTO `rejoice_company` VALUES ('2', '发多少离开', '23', '6', '6', '2017-06-09 15:00:05', '2017-06-11 22:07:01', '3');
INSERT INTO `rejoice_company` VALUES ('4', '21', '21', '1221', '1', '2017-05-08 14:59:46', '2017-06-11 22:00:55', '4');
INSERT INTO `rejoice_company` VALUES ('5', '21', '21', '1221', '0', '2017-05-08 14:59:46', '2017-06-09 14:59:50', '5');
INSERT INTO `rejoice_company` VALUES ('6', '21', '21', '1221', '36', '2017-05-08 14:59:46', '2017-06-11 22:03:25', '6');
INSERT INTO `rejoice_company` VALUES ('7', '21', '21', '1221', '232323', '2017-05-08 14:59:46', '2017-06-09 14:59:50', '7');
INSERT INTO `rejoice_company` VALUES ('8', '21', '21', '1221', '232323', '2017-05-08 14:59:46', '2017-06-09 14:59:50', '8');

-- ----------------------------
-- Table structure for `rejoice_user`
-- ----------------------------
DROP TABLE IF EXISTS `rejoice_user`;
CREATE TABLE `rejoice_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `real_name` varchar(50) DEFAULT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `english_name` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码，已加密',
  `address` varchar(200) DEFAULT NULL,
  `age` smallint(6) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rejoice_user
-- ----------------------------
INSERT INTO `rejoice_user` VALUES ('23', 'admin', '', '212121', 'admin', '21232f297a57a5a743894a0e4a801fc3', '', null, 'women', '2017-06-13 23:42:52', '2017-06-14 00:55:03', null);
INSERT INTO `rejoice_user` VALUES ('24', '12', '1221', '1221', '12', 'c20ad4d76fe97759aa27a0c99bff6710', '1221211212', null, 'man', '2017-06-13 23:43:38', '2017-06-14 00:53:58', null);
