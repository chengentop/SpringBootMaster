/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : master

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 21/11/2019 23:09:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_power
-- ----------------------------
DROP TABLE IF EXISTS `t_power`;
CREATE TABLE `t_power`  (
  `powerid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id',
  `menucode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归属菜单,前端判断并展示菜单使用',
  `menuname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单的中文释义',
  `permissioncode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限的代码/通配符,对应代码中@RequiresPermissions 的value',
  `permissionname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本权限的中文释义',
  `requiredpermission` tinyint(2) NULL DEFAULT 2 COMMENT '是否本菜单必选权限, 1.必选 2非必选 通常是\"列表\"权限是必选',
  `intime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`powerid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_power
-- ----------------------------
INSERT INTO `t_power` VALUES ('101', 'user', '用户', 'user:list', '列表', 1, '2019-10-20 19:22:09');
INSERT INTO `t_power` VALUES ('102', 'user', '用户', 'user:add', '新增', 2, '2019-10-20 19:22:09');
INSERT INTO `t_power` VALUES ('103', 'user', '用户', 'user:update', '修改', 2, '2019-10-20 19:22:09');
INSERT INTO `t_power` VALUES ('201', 'role', '角色权限', 'role:list', '列表', 1, '2019-10-20 19:22:09');
INSERT INTO `t_power` VALUES ('202', 'role', '角色权限', 'role:add', '新增', 2, '2019-10-20 19:22:09');
INSERT INTO `t_power` VALUES ('203', 'role', '角色权限', 'role:update', '修改', 2, '2019-10-20 19:22:09');
INSERT INTO `t_power` VALUES ('204', 'role', '角色权限', 'role:delete', '删除', 2, '2019-10-20 19:22:10');
INSERT INTO `t_power` VALUES ('701', 'article', '文章管理', 'article:list', '列表', 1, '2019-10-20 19:22:09');
INSERT INTO `t_power` VALUES ('702', 'article', '文章管理', 'article:add', '新增', 2, '2019-10-20 19:22:09');
INSERT INTO `t_power` VALUES ('703', 'article', '文章管理', 'article:update', '修改', 2, '2019-10-20 19:22:09');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `roleid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `rolename` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `intime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`roleid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('r3t3t4y5u6i7o8i4ythyT4IDBW2Eat78', '管理员', '2019-10-20 19:43:51');
INSERT INTO `t_role` VALUES ('tyuiote56789tdgfhjkmn789j765hgrt', '普通用户', '2019-10-20 19:43:54');

-- ----------------------------
-- Table structure for t_role_power
-- ----------------------------
DROP TABLE IF EXISTS `t_role_power`;
CREATE TABLE `t_role_power`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `roleid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `powerid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id',
  `state` int(2) NOT NULL DEFAULT 1 COMMENT '状态:1. 有效 2. 无效',
  `modtime` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `intime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_power
-- ----------------------------
INSERT INTO `t_role_power` VALUES ('qkIgv7svcTFg3UUBqm1r1asQ1gULWv4s', 'r3t3t4y5u6i7o8i4ythyT4IDBW2Eat78', '101', 1, NULL, '2019-10-20 20:00:51');
INSERT INTO `t_role_power` VALUES ('t8eb9ThhlbwTog4XHhEGUIQGcaN5GIgr', 'r3t3t4y5u6i7o8i4ythyT4IDBW2Eat78', '102', 1, NULL, '2019-10-20 20:36:27');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `userid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `salt` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐值',
  `fullname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `intime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('gB22KacKqpDoEwBZNqvaQYgzVWHp2wt6', 'admin', 'def0e4568a393af9aca7bfa3d54c5147', 'salt', NULL, '2019-10-20 19:44:03');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `userid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `roleid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `intime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('gB22KacKqpDoEwBZNqvaQYgzVWHp2wt6', 'r3t3t4y5u6i7o8i4ythyT4IDBW2Eat78', '2019-10-20 19:44:08');

SET FOREIGN_KEY_CHECKS = 1;
