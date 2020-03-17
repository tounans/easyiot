/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3307
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3307
 Source Schema         : easyiot

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 16/03/2020 21:14:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for iot_config_mqtt
-- ----------------------------
DROP TABLE IF EXISTS `iot_config_mqtt`;
CREATE TABLE `iot_config_mqtt`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 1,
  `is_def` tinyint(1) NOT NULL DEFAULT 1,
  `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `port` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of iot_config_mqtt
-- ----------------------------
INSERT INTO `iot_config_mqtt` VALUES (1, 1, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for iot_device
-- ----------------------------
DROP TABLE IF EXISTS `iot_device`;
CREATE TABLE `iot_device`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imei` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `user_device_id` int(11) NULL DEFAULT NULL COMMENT '用户的设备ID',
  `model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '别名',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本号',
  `firmware_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '固件名',
  `lbs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '基站定位信息',
  `state` tinyint(1) NULL DEFAULT NULL,
  `network_signal` int(3) NULL DEFAULT NULL COMMENT '信号强度',
  `add_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of iot_device
-- ----------------------------
INSERT INTO `iot_device` VALUES (2, '868575027635640', 1, 2, NULL, '测试设备11', '备注1122', '版本号11', '固件型号11', '定位11', 1, 20, '2020-03-11 00:02:13');
INSERT INTO `iot_device` VALUES (31, '111123132123', 1, 3, NULL, '测试设备2', '啊实打实', NULL, NULL, NULL, 1, NULL, '2020-03-14 19:24:00');

-- ----------------------------
-- Table structure for iot_device_gpio
-- ----------------------------
DROP TABLE IF EXISTS `iot_device_gpio`;
CREATE TABLE `iot_device_gpio`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_id` int(11) NULL DEFAULT NULL COMMENT '设备ID',
  `user_gpio_id` int(11) NULL DEFAULT NULL COMMENT '用户管脚ID',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户iD',
  `gpio_id` int(2) NULL DEFAULT NULL COMMENT 'GPIO',
  `method` int(2) NULL DEFAULT NULL COMMENT '--1输出 0中断',
  `def` int(2) NULL DEFAULT NULL COMMENT '默认电平',
  `current` int(2) NULL DEFAULT NULL COMMENT '当前电平',
  `add_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `state` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of iot_device_gpio
-- ----------------------------
INSERT INTO `iot_device_gpio` VALUES (39, 2, 1, 1, 1, 0, 0, 1, '2020-03-16 17:38:01', 1);
INSERT INTO `iot_device_gpio` VALUES (40, 3, 2, 1, 22, 0, 0, 0, '2020-03-16 17:38:01', 1);
INSERT INTO `iot_device_gpio` VALUES (41, 3, 3, 1, 21, 0, 0, 0, '2020-03-16 17:38:01', 1);

-- ----------------------------
-- Table structure for iot_device_gpio_log
-- ----------------------------
DROP TABLE IF EXISTS `iot_device_gpio_log`;
CREATE TABLE `iot_device_gpio_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_device_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `user_log_id` int(11) NULL DEFAULT NULL,
  `user_gpio_id` int(11) NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL COMMENT '类型   0上报 1下发',
  `state` int(11) NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 117 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of iot_device_gpio_log
-- ----------------------------
INSERT INTO `iot_device_gpio_log` VALUES (106, 2, 1, 1, 1, 1, 0, '2020-03-14 19:21:28');
INSERT INTO `iot_device_gpio_log` VALUES (107, 3, 1, 2, 2, 1, 0, '2020-03-14 19:24:49');
INSERT INTO `iot_device_gpio_log` VALUES (108, 2, 1, 3, 1, 1, 0, '2020-03-14 20:28:26');
INSERT INTO `iot_device_gpio_log` VALUES (109, 2, 1, 4, 1, 1, 0, '2020-03-14 20:29:07');
INSERT INTO `iot_device_gpio_log` VALUES (110, 2, 1, 5, 1, 1, 0, '2020-03-14 20:32:53');
INSERT INTO `iot_device_gpio_log` VALUES (111, 2, 1, 6, 1, 1, 0, '2020-03-14 20:33:03');
INSERT INTO `iot_device_gpio_log` VALUES (112, 2, 1, 7, 1, 0, 0, '2020-03-14 20:33:10');
INSERT INTO `iot_device_gpio_log` VALUES (113, 2, 1, 8, 1, 1, 0, '2020-03-16 19:56:10');
INSERT INTO `iot_device_gpio_log` VALUES (114, 2, 1, 8, 1, 1, 0, '2020-03-16 19:56:10');
INSERT INTO `iot_device_gpio_log` VALUES (115, 2, 1, 9, 1, 1, 0, '2020-03-16 19:56:42');
INSERT INTO `iot_device_gpio_log` VALUES (116, 2, 1, 10, 1, 1, 1, '2020-03-16 19:57:53');

-- ----------------------------
-- Table structure for iot_device_uart
-- ----------------------------
DROP TABLE IF EXISTS `iot_device_uart`;
CREATE TABLE `iot_device_uart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `user_uart_id` int(11) NULL DEFAULT NULL,
  `uart_id` int(11) NULL DEFAULT NULL COMMENT '--1读  0写',
  `method` int(2) NULL DEFAULT NULL,
  `baud` int(11) NULL DEFAULT NULL COMMENT '波特率',
  `databits` int(2) NULL DEFAULT NULL COMMENT '数据位',
  `parity` int(2) NULL DEFAULT NULL COMMENT '校验位',
  `stopbits` int(2) NULL DEFAULT NULL COMMENT '停止位',
  `state` tinyint(4) NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of iot_device_uart
-- ----------------------------
INSERT INTO `iot_device_uart` VALUES (6, 2, 1, 1, 2, 0, 115200, 8, 0, 0, 0, '2020-03-14 19:21:55');
INSERT INTO `iot_device_uart` VALUES (7, 3, 1, 2, 2333, 0, 111, 8, 0, 0, 0, '2020-03-14 19:24:38');

-- ----------------------------
-- Table structure for iot_device_uart_log
-- ----------------------------
DROP TABLE IF EXISTS `iot_device_uart_log`;
CREATE TABLE `iot_device_uart_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_device_id` int(11) NULL DEFAULT NULL,
  `user_log_id` int(11) NULL DEFAULT NULL,
  `user_uart_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL COMMENT '类型   0上报 1下发',
  `data` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of iot_device_uart_log
-- ----------------------------
INSERT INTO `iot_device_uart_log` VALUES (54, 2, 1, 1, 1, 1, 'msg', '2020-03-14 19:22:11');
INSERT INTO `iot_device_uart_log` VALUES (55, 3, 2, 2, 1, 1, 'a\'s\'da\'s啊实打实的', '2020-03-14 19:35:20');
INSERT INTO `iot_device_uart_log` VALUES (56, 2, 3, 1, 1, 0, 'abcdefg', '2020-03-14 20:39:23');
INSERT INTO `iot_device_uart_log` VALUES (57, 2, 4, 1, 1, 0, 'abcdefg', '2020-03-14 20:43:10');
INSERT INTO `iot_device_uart_log` VALUES (58, 2, 5, 1, 1, 1, '132213', '2020-03-14 20:43:31');
INSERT INTO `iot_device_uart_log` VALUES (59, 2, 6, 1, 1, 1, '132213', '2020-03-14 20:43:31');
INSERT INTO `iot_device_uart_log` VALUES (60, 2, 7, 1, 1, 0, 'abcdefg', '2020-03-14 20:44:08');
INSERT INTO `iot_device_uart_log` VALUES (61, 2, 8, 1, 1, 1, '', '2020-03-14 20:44:14');
INSERT INTO `iot_device_uart_log` VALUES (62, 2, 9, 1, 1, 1, '112233', '2020-03-16 19:58:02');
INSERT INTO `iot_device_uart_log` VALUES (63, 2, 10, 1, 1, 0, 'abcdefg', '2020-03-16 19:58:13');

-- ----------------------------
-- Table structure for iot_permission
-- ----------------------------
DROP TABLE IF EXISTS `iot_permission`;
CREATE TABLE `iot_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父权限',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `enname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限英文名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '授权路径',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `created` datetime(0) NOT NULL,
  `updated` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of iot_permission
-- ----------------------------
INSERT INTO `iot_permission` VALUES (37, 0, '系统管理', 'System', '/', NULL, '2019-04-04 23:22:54', '2019-04-04 23:22:56');
INSERT INTO `iot_permission` VALUES (38, 37, '用户管理', 'SystemContentView', '/users/', NULL, '2019-04-04 23:25:31', '2019-04-04 23:25:33');
INSERT INTO `iot_permission` VALUES (39, 38, '查看用户', 'SystemUserView', '', NULL, '2019-04-04 15:30:30', '2019-04-04 15:30:43');
INSERT INTO `iot_permission` VALUES (40, 38, '新增用户', 'SystemUserInsert', '', NULL, '2019-04-04 15:30:31', '2019-04-04 15:30:44');
INSERT INTO `iot_permission` VALUES (41, 38, '编辑用户', 'SystemUserUpdate', '', NULL, '2019-04-04 15:30:32', '2019-04-04 15:30:45');
INSERT INTO `iot_permission` VALUES (42, 38, '删除用户', 'SystemUserDelete', '', NULL, '2019-04-04 15:30:48', '2019-04-04 15:30:45');

-- ----------------------------
-- Table structure for iot_push_http
-- ----------------------------
DROP TABLE IF EXISTS `iot_push_http`;
CREATE TABLE `iot_push_http`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(5) NULL DEFAULT NULL,
  `user_push_id` int(2) NULL DEFAULT NULL,
  `method` int(2) NULL DEFAULT NULL COMMENT '0 get 1 post',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time_out` int(10) NULL DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` tinyint(4) NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of iot_push_http
-- ----------------------------
INSERT INTO `iot_push_http` VALUES (1, 1, 1, 1, 'https://www.baidu.com', 30, '1', 0, '2020-01-05 00:38:03');
INSERT INTO `iot_push_http` VALUES (2, 1, 2, 1, 'https://www.abc.com', 30, '1', 0, '2020-01-05 00:36:11');

-- ----------------------------
-- Table structure for iot_role
-- ----------------------------
DROP TABLE IF EXISTS `iot_role`;
CREATE TABLE `iot_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父角色',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `enname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色英文名称',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `created` datetime(0) NOT NULL,
  `updated` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of iot_role
-- ----------------------------
INSERT INTO `iot_role` VALUES (1, 0, '超级管理员', 'admin', NULL, '2019-04-04 23:22:03', '2019-04-04 23:22:05');

-- ----------------------------
-- Table structure for iot_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `iot_role_permission`;
CREATE TABLE `iot_role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色 ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限 ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of iot_role_permission
-- ----------------------------
INSERT INTO `iot_role_permission` VALUES (37, 1, 37);
INSERT INTO `iot_role_permission` VALUES (38, 1, 38);
INSERT INTO `iot_role_permission` VALUES (39, 1, 39);
INSERT INTO `iot_role_permission` VALUES (40, 1, 40);
INSERT INTO `iot_role_permission` VALUES (41, 1, 41);
INSERT INTO `iot_role_permission` VALUES (42, 1, 42);

-- ----------------------------
-- Table structure for iot_user
-- ----------------------------
DROP TABLE IF EXISTS `iot_user`;
CREATE TABLE `iot_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码，加密存储',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册邮箱',
  `created` datetime(0) NOT NULL,
  `updated` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of iot_user
-- ----------------------------
INSERT INTO `iot_user` VALUES (1, 'admin', '$2a$10$YkU2BVdN8CZC/ICRMj3WaOswDeH1ZLLuzUIE6.sntTXpsbpXSJyBu', '15888888888', 'tounans@tounans.com', '2019-04-04 23:21:27', '2019-04-04 23:21:29');

-- ----------------------------
-- Table structure for iot_user_role
-- ----------------------------
DROP TABLE IF EXISTS `iot_user_role`;
CREATE TABLE `iot_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户 ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色 ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of iot_user_role
-- ----------------------------
INSERT INTO `iot_user_role` VALUES (37, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
