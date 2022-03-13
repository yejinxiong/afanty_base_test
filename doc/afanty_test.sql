/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : 127.0.0.1:3306
 Source Schema         : afanty_test

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 24/01/2022 01:40:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_qm_items
-- ----------------------------
DROP TABLE IF EXISTS `tbl_qm_items`;
CREATE TABLE `tbl_qm_items`  (
  `items_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评分项id',
  `standard_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评分模板id',
  `items_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评分项名称',
  `items_score` decimal(3, 0) NULL DEFAULT NULL COMMENT '评分项分值',
  `items_type` tinyint(0) NULL DEFAULT NULL COMMENT '评分类型：0-加分项  1-减分项',
  `is_use` tinyint(0) NULL DEFAULT NULL COMMENT '是否已被用作评分：0-未使用 1-已使用',
  `is_delete` tinyint(0) NULL DEFAULT NULL COMMENT '是否删除：0-否 1-是',
  `field1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用字段1',
  `field2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用字段2',
  `field3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用字段3',
  `tenant_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '租户ID',
  `pro_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份ID',
  `org_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门ID',
  `create_user` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人账号',
  `create_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人账号',
  `update_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人姓名',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`items_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评分项表存的个省自定义的评分项数据，\r\n读写，\r\n数据量：< 10000条' ROW_FORMAT = Dynamic;

INSERT INTO `tbl_qm_items` VALUES ('01a4d6dd5ace4e1cbf56c31d3dd698a4', '372246facd174fa8a3cbe6a5b2992152', '获取租户', 7, 1, 0, 0, '测试3', NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-11 17:41:09', 'yejx', '叶金雄', '2020-03-11 17:41:09');
INSERT INTO `tbl_qm_items` VALUES ('07d35201cf2a43468161f5792e615ff6', '1d4cd94950d34f3bb91744896ff38299', '吐字模糊，普通话不标准', 6, 1, 0, 0, '测试4', NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-12 14:31:55', 'yejx', '叶金雄', '2020-03-12 14:36:39');
INSERT INTO `tbl_qm_items` VALUES ('1cb662ff70c3491f8d2a89e1ea6ab76a', '1d4cd94950d34f3bb91744896ff38299', '音量、语速不符合要求', 2, 1, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-12 14:31:55', 'yejx', '叶金雄', '2020-03-12 14:36:39');
INSERT INTO `tbl_qm_items` VALUES ('20dcc3bbf6254afa943c71cf518c201b', '2c91809f69110b43016927ebe76d000b', '奢侈', 34, 1, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-10 18:57:57', 'yejx', '叶金雄', '2020-03-11 17:03:11');
INSERT INTO `tbl_qm_items` VALUES ('210dd3063d7948e4ad5417d4e6534585', '1845c4a4eb054fbeb084c4e1cee8b6e1', '使用文明用语', 9, 0, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-10 15:16:19', 'yejx', '叶金雄', '2020-03-10 16:25:27');
INSERT INTO `tbl_qm_items` VALUES ('217092a5f1f9a7bcd2da2a19d5e5ffa2', 'd09e2062d8f445ba9ad45a0211786ab1', '谩骂客户', 10, 1, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2021-04-24 23:07:23', 'yejx', '叶金雄', '2021-04-24 23:07:23');
INSERT INTO `tbl_qm_items` VALUES ('28f48eaad77a4c2786003a2c1d080408', '42253a630d5243599c331ff8e72a35ea', '态度恶劣', 60, 1, 1, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-04 17:07:38', 'yejx', '叶金雄', '2020-03-11 21:59:41');
INSERT INTO `tbl_qm_items` VALUES ('2c91808467ccc12601682ae89f9a000d', '8a7d8a906ce1e894016ce1f9a5a60002', '主动解决问题', 10, 0, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-02-27 17:56:27', 'yejx', '叶金雄', '2020-02-27 17:56:34');
INSERT INTO `tbl_qm_items` VALUES ('2f3e6a1ceeaf4ee29430c9b6f649dccd', '1d4cd94950d34f3bb91744896ff38299', '亲切、耐心，积极、主动', 4, 0, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-12 14:31:55', 'yejx', '叶金雄', '2020-03-12 14:36:39');
INSERT INTO `tbl_qm_items` VALUES ('380b848207bf4e91b4a490e464f5ddf4', '2c91809f69110b43016927ebe76d000b', '你个嘚嘚', 90, 0, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-10 18:57:51', 'yejx', '叶金雄', '2020-03-11 17:03:11');
INSERT INTO `tbl_qm_items` VALUES ('4aff2f7c9df245759fe7adb3a16622b6', '372246facd174fa8a3cbe6a5b2992152', '获取子公司', 8, 1, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-11 17:41:09', 'yejx', '叶金雄', '2020-03-11 17:41:09');
INSERT INTO `tbl_qm_items` VALUES ('4cb752dfe4ea46ccb3075d679298710s', '17056c068dcd4b5fbe7ff0c183c9262b', '使用不文明用语2', 5, 1, 1, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-02 23:38:36', 'yejx', '叶金雄', '2020-03-02 23:38:36');
INSERT INTO `tbl_qm_items` VALUES ('50151b32f3ef4fe484fef4f75cd62e14', '45f634acad9744399d8995ffd8324d42', '未倾听并适当回应', 10, 1, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-12 14:36:39', 'yejx', '叶金雄', '2020-03-12 14:36:39');
INSERT INTO `tbl_qm_items` VALUES ('5d5918f6fa484296aae4a5fd85d5a3ca', '42253a630d5243599c331ff8e72a35ea', '礼貌用语', 5, 0, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-04 17:07:38', 'yejx', '叶金雄', '2020-03-11 21:59:41');
INSERT INTO `tbl_qm_items` VALUES ('624e67ea8f7640eca8f0b691b2089670', 'a787aa61a33c4f3e80bb72a8f7a13a39', '使用不文明用语', 5, 0, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-03 14:55:21', 'yejx', '叶金雄', '2020-03-12 16:24:17');
INSERT INTO `tbl_qm_items` VALUES ('7497b3119b854166bb60e471d314deb4', '17056c068dcd4b5fbe7ff0c183c9262b', '吧啦吧啦一大堆', 4, 0, 1, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-02 23:38:36', 'yejx', '叶金雄', '2020-03-02 23:38:36');
INSERT INTO `tbl_qm_items` VALUES ('7d38c3b915b64de98eb38cd0de02a805', '42253a630d5243599c331ff8e72a35ea', '善良的控股', 5, 0, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-09 15:28:50', 'yejx', '叶金雄', '2020-03-11 21:59:41');
INSERT INTO `tbl_qm_items` VALUES ('8a7d844b6c9ae34c016c9af4a0da0000', '2c9180826bbb3243016bdbb14fbd0000', '业务不熟', 4, 1, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-02-27 15:42:20', 'yejx', '叶金雄', '2020-02-27 15:42:23');
INSERT INTO `tbl_qm_items` VALUES ('8a7d8a906ce1e894016ce1f72efa0001', '8a7d8a906ce1e894016ce1f9a5a60002', '吐字清晰，普通话标准', 5, 0, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-02-27 15:43:50', 'yejx', '叶金雄', '2020-02-27 15:43:52');
INSERT INTO `tbl_qm_items` VALUES ('9d9e395890d74dcdbe07ff05efe3b196', '42253a630d5243599c331ff8e72a35ea', '我不听我不听', 41, 1, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-04 17:07:38', 'yejx', '叶金雄', '2020-03-11 21:59:41');
INSERT INTO `tbl_qm_items` VALUES ('9e424b02a2bd442bacbd65b19eca0c95', '151381e555274bf99f09cf3d6112cf63', '立刻搭街坊', 40, 0, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-11 17:48:09', 'yejx', '叶金雄', '2020-03-12 16:45:11');
INSERT INTO `tbl_qm_items` VALUES ('a9cfff9bae304fb2b829e0265a8c7735', '151381e555274bf99f09cf3d6112cf63', '收到反馈结果', 70, 0, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-11 17:48:09', 'yejx', '叶金雄', '2020-03-12 16:45:11');
INSERT INTO `tbl_qm_items` VALUES ('aa6e4c0dc39b48cdbe7d9a6254d41a94', '45f634acad9744399d8995ffd8324d42', '测试编辑', 30, 1, 1, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'KF45100000', '省分管理员', '2020-03-12 23:01:25', 'KF45100000', '省分管理员', '2020-03-12 23:05:33');
INSERT INTO `tbl_qm_items` VALUES ('b254ca8ffe1186c0c2cd9c92dc877eac', '123', '获取租户1', 5, 2, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2022-01-22 06:16:00', 'yejx', '叶金雄', '2022-01-22 06:16:00');
INSERT INTO `tbl_qm_items` VALUES ('b5bac06681d9443fb6895af05656b2c9', '45f634acad9744399d8995ffd8324d42', '测试时间 不必恐慌', 60, 1, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'KF45100000', '省分管理员', '2020-03-12 23:05:33', 'KF45100000', '省分管理员', '2020-03-12 23:05:33');
INSERT INTO `tbl_qm_items` VALUES ('bd4dacee85dd47f8917b6c8e24a28e67', '42253a630d5243599c331ff8e72a35ea', '口吐芬芳', 5, 1, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-04 17:07:38', 'yejx', '叶金雄', '2020-03-11 21:59:41');
INSERT INTO `tbl_qm_items` VALUES ('be4b79f17d3f480fa2879a9d59322ed0', '42253a630d5243599c331ff8e72a35ea', '嚣张跋扈，谩骂客户', 60, 1, 1, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-04 17:07:38', 'yejx', '叶金雄', '2020-03-11 21:59:41');
INSERT INTO `tbl_qm_items` VALUES ('c54a4c02fe3e422db4b12217c7b4705f', 'a787aa61a33c4f3e80bb72a8f7a13a39', '吐词清晰，使用标准普通话', 5, 1, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-03 14:55:21', 'yejx', '叶金雄', '2020-03-12 16:24:17');
INSERT INTO `tbl_qm_items` VALUES ('cfe1eb06014e42ed82aa9bb8876df488', '45f634acad9744399d8995ffd8324d42', '你愁啥', 50, 0, 0, 1, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'KF45100000', '省分管理员', '2020-03-12 23:05:33', 'KF45100000', '省分管理员', '2020-03-12 23:05:33');
INSERT INTO `tbl_qm_items` VALUES ('db1c55c09f013e3410546482679111b0', '12345678', '业务能力薄弱', 10, 1, 1, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2021-04-23 21:41:18', 'yejx', '叶金雄', '2021-04-23 21:41:18');
INSERT INTO `tbl_qm_items` VALUES ('dd86d40b1c75402fafd815fc7569c99b', 'a787aa61a33c4f3e80bb72a8f7a13a39', '灵活运用', 5, 0, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-03 14:55:21', 'yejx', '叶金雄', '2020-03-12 16:24:17');
INSERT INTO `tbl_qm_items` VALUES ('f82b663b765f64d41771f4f7d72d38a2', 'fcefdbf3e5e3497f923b966f759cde71', '服务态度友好', 10, 0, 1, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2021-04-23 21:37:18', 'yejx', '叶金雄', '2021-04-23 21:37:18');


-- ----------------------------
-- Table structure for tbl_sys_code_type
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sys_code_type`;
CREATE TABLE `tbl_sys_code_type`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型编码',
  `type_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型名称',
  `type_desc` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型描述',
  `dict_or_tree` tinyint(0) NOT NULL COMMENT '字典编码还是树形编码：1-字典，2-树形',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `delete_flag` tinyint(0) NULL DEFAULT 0 COMMENT '是否删除：1-是，0-否（默认）',
  `enable_flag` tinyint(0) NOT NULL DEFAULT 1 COMMENT '是否启用：1-是（默认），0-否',
  `create_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人账号',
  `create_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人账号',
  `update_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人姓名',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_TYPE_CODE_DICT_OR_TREE`(`type_code`, `dict_or_tree`) USING BTREE COMMENT '联合索引：类型编码-字典还是树形'
) ENGINE = InnoDB AUTO_INCREMENT = 1485171234608574466 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型' ROW_FORMAT = Dynamic;

INSERT INTO `tbl_sys_code_type` VALUES (1482761104411631618, 'DICT_SYS_SEX', '性别', '系统公用性别', 1, NULL, 0, 1, 'yejx', '叶金雄', '2022-01-23 16:42:30', 'yejx', '叶金雄', '2022-01-23 16:42:30');


-- ----------------------------
-- Table structure for tbl_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sys_dict`;
CREATE TABLE `tbl_sys_dict`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dict_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '字典名称',
  `dict_value` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '字典值',
  `type_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '类型编码',
  `dict_keyword` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键词',
  `pinyin_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拼音码',
  `sort_no` smallint(0) NULL DEFAULT NULL COMMENT '排序',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `delete_flag` tinyint(0) NULL DEFAULT 0 COMMENT '是否删除：1-是，0-否（默认）',
  `enable_flag` tinyint(0) NOT NULL DEFAULT 1 COMMENT '是否启用：1-是（默认），0-否',
  `create_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人账号',
  `create_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人账号',
  `update_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人姓名',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_DICT_VALUE_TYPE_CODE`(`dict_value`, `type_code`) USING BTREE COMMENT '联合索引：字典值-类型编码',
  INDEX `IDX_DICT_NAME`(`dict_name`) USING BTREE COMMENT '字典名称'
) ENGINE = InnoDB AUTO_INCREMENT = 1483113287258763266 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

INSERT INTO `tbl_sys_dict` VALUES (1483108408695996417, '男', '1', 'DIC_SYS_SEX', '男', 'n', 1, '性别男', 2, 1, 'yejx', '叶金雄', '2022-01-18 00:23:51', 'yejx', '叶金雄', '2022-01-18 00:23:51');
INSERT INTO `tbl_sys_dict` VALUES (1483113287258763265, '女', '2', 'DIC_SYS_SEX', '女', 'n', 2, '性别女', 2, 1, 'yejx', '叶金雄', '2022-01-17 16:25:37', 'yejx', '叶金雄', '2022-01-17 16:25:37');


-- ----------------------------
-- Table structure for tbl_sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sys_dept`;
CREATE TABLE `tbl_sys_dept`  (
  `dept_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `d_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据唯一uuid',
  `parent_id` bigint(0) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(0) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 203 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_sys_dept
-- ----------------------------
INSERT INTO `tbl_sys_dept` VALUES (100, NULL, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', NULL, '2022-02-20 15:10:57', NULL, NULL);
INSERT INTO `tbl_sys_dept` VALUES (101, NULL, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', NULL, '2022-02-20 15:10:57', NULL, NULL);
INSERT INTO `tbl_sys_dept` VALUES (102, NULL, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', NULL, '2022-02-20 15:10:57', NULL, NULL);
INSERT INTO `tbl_sys_dept` VALUES (103, NULL, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', NULL, '2022-02-20 15:10:57', NULL, NULL);
INSERT INTO `tbl_sys_dept` VALUES (104, NULL, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', NULL, '2022-02-20 15:10:57', NULL, NULL);
INSERT INTO `tbl_sys_dept` VALUES (105, NULL, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', NULL, '2022-02-20 15:10:57', NULL, NULL);
INSERT INTO `tbl_sys_dept` VALUES (106, NULL, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', NULL, '2022-02-20 15:10:57', NULL, NULL);
INSERT INTO `tbl_sys_dept` VALUES (107, NULL, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', NULL, '2022-02-20 15:10:57', NULL, NULL);
INSERT INTO `tbl_sys_dept` VALUES (108, NULL, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', NULL, '2022-02-20 15:10:57', NULL, NULL);
INSERT INTO `tbl_sys_dept` VALUES (109, NULL, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', NULL, '2022-02-20 15:10:57', NULL, NULL);
INSERT INTO `tbl_sys_dept` VALUES (200, NULL, 100, '0,100', '武汉分公司', 3, '林凡', '17671667711', 'linfan@afanty.com', '0', '0', NULL, '2022-02-27 17:24:47', NULL, NULL);
INSERT INTO `tbl_sys_dept` VALUES (201, NULL, 200, '0,100,200', '软件事业部', 1, '冷锋', NULL, NULL, '0', '0', NULL, '2022-02-27 17:29:33', NULL, NULL);
INSERT INTO `tbl_sys_dept` VALUES (202, NULL, 200, '0,100,200', '硬件事业部', 2, '高琪', NULL, NULL, '0', '0', NULL, '2022-02-27 17:30:35', NULL, NULL);


-- ----------------------------
-- Table structure for tbl_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sys_menu`;
CREATE TABLE `tbl_sys_menu`  (
  `menu_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `d_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据唯一uuid',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(0) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(0) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由参数',
  `frame_flag` int(0) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `cache_flag` int(0) NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible_flag` int(0) NULL DEFAULT 0 COMMENT '是否隐藏（0显示 1隐藏）',
  `state` int(0) NULL DEFAULT 0 COMMENT '菜单状态（0正常 1停用 2删除）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2000 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_sys_menu
-- ----------------------------
INSERT INTO `tbl_sys_menu` VALUES (1, '95afcbde4fae930d6e91538f03227119', '系统管理', 0, 1, 'system', NULL, '', 1, 0, 'M', 0, 0, '', 'system', '系统管理目录', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (2, 'c2152dd0c28bc441869a6b0100c490a4', '系统监控', 0, 2, 'monitor', NULL, '', 1, 0, 'M', 0, 0, '', 'monitor', '系统监控目录', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (3, 'a040e2835b865350565d41dda8a8d446', '系统工具', 0, 3, 'tool', NULL, '', 1, 0, 'M', 0, 0, '', 'tool', '系统工具目录', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (4, 'dca7aa20cbb035e292bd2d7f30e4f2d7', '若依官网', 0, 4, 'http://ruoyi.vip', NULL, '', 0, 0, 'M', 0, 0, '', 'guide', '若依官网地址', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (100, '7e847aee630beb547d9bc0118227b2b7', '用户管理', 1, 1, 'user', 'system/user/index', '', 1, 0, 'C', 0, 0, 'system:user:list', 'user', '用户管理菜单', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (101, '8b9c6110cb4a4863177fa9e6fe21a063', '角色管理', 1, 2, 'role', 'system/role/index', '', 1, 0, 'C', 0, 0, 'system:role:list', 'peoples', '角色管理菜单', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (102, 'fc667ad33b12571b0a38b06a13a56638', '菜单管理', 1, 3, 'menu', 'system/menu/index', '', 1, 0, 'C', 0, 0, 'system:menu:list', 'tree-table', '菜单管理菜单', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (103, '4e54a1d50b0b410d8348da69ce950ffe', '部门管理', 1, 4, 'dept', 'system/dept/index', '', 1, 0, 'C', 0, 0, 'system:dept:list', 'tree', '部门管理菜单', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (104, '6e4eb9f596651794382f426fe55f1555', '岗位管理', 1, 5, 'post', 'system/post/index', '', 1, 0, 'C', 0, 0, 'system:post:list', 'post', '岗位管理菜单', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (105, '35d528ea3595cf1157d9859d4c7dcace', '字典管理', 1, 6, 'dict', 'system/dict/index', '', 1, 0, 'C', 0, 0, 'system:dict:list', 'dict', '字典管理菜单', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (106, '38f319df9c76775b5b70c968a1a6559d', '参数设置', 1, 7, 'config', 'system/config/index', '', 1, 0, 'C', 0, 0, 'system:config:list', 'edit', '参数设置菜单', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (107, 'dc0a730bf7539dc59981c915040d4bc7', '通知公告', 1, 8, 'notice', 'system/notice/index', '', 1, 0, 'C', 0, 0, 'system:notice:list', 'message', '通知公告菜单', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (108, '31973166ac12b51bf3d2cd4e59822283', '日志管理', 1, 9, 'log', '', '', 1, 0, 'M', 0, 0, '', 'log', '日志管理菜单', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (109, '6356720df221be84d728b3e980c7262f', '在线用户', 2, 1, 'online', 'monitor/online/index', '', 1, 0, 'C', 0, 0, 'monitor:online:list', 'online', '在线用户菜单', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (110, '490f5de51fe46bfb9ee059b32202e586', '定时任务', 2, 2, 'job', 'monitor/job/index', '', 1, 0, 'C', 0, 0, 'monitor:job:list', 'job', '定时任务菜单', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (111, '8d70775f75b64aecf4d60d587f38be26', '数据监控', 2, 3, 'druid', 'monitor/druid/index', '', 1, 0, 'C', 0, 0, 'monitor:druid:list', 'druid', '数据监控菜单', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (112, '4a27d2c69fa4b1052937dd56290333f9', '服务监控', 2, 4, 'server', 'monitor/server/index', '', 1, 0, 'C', 0, 0, 'monitor:server:list', 'server', '服务监控菜单', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (113, '1c59d16db836336bc782f10941d029c0', '缓存监控', 2, 5, 'cache', 'monitor/cache/index', '', 1, 0, 'C', 0, 0, 'monitor:cache:list', 'redis', '缓存监控菜单', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (114, '1d033e361ef463bb2b09cb8b86ccf156', '表单构建', 3, 1, 'build', 'tool/build/index', '', 1, 0, 'C', 0, 0, 'tool:build:list', 'build', '表单构建菜单', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (115, '41c4ec8159a79eb477f0d53f22acff43', '代码生成', 3, 2, 'gen', 'tool/gen/index', '', 1, 0, 'C', 0, 0, 'tool:gen:list', 'code', '代码生成菜单', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (116, '06d26a6ac45ff851cbc0abf4909d2a1d', '系统接口', 3, 3, 'swagger', 'tool/swagger/index', '', 1, 0, 'C', 0, 0, 'tool:swagger:list', 'swagger', '系统接口菜单', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (500, '09f67cfd786b9f017d82edad8f126594', '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', '', 1, 0, 'C', 0, 0, 'monitor:operlog:list', 'form', '操作日志菜单', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (501, 'd051276a4c5723835d33836651b0b282', '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', '', 1, 0, 'C', 0, 0, 'monitor:logininfor:list', 'logininfor', '登录日志菜单', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1001, '86d962a072f10e0cfe4c8136fdd6b1ac', '用户查询', 100, 1, '', '', '', 1, 0, 'F', 0, 0, 'system:user:query', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1002, '6af6ef05bd3939e0e9b2076365028794', '用户新增', 100, 2, '', '', '', 1, 0, 'F', 0, 0, 'system:user:add', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1003, '3122a782351863f2126437ba3a945f00', '用户修改', 100, 3, '', '', '', 1, 0, 'F', 0, 0, 'system:user:edit', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1004, 'c0fe377755d9469ffb81980dad8ddea9', '用户删除', 100, 4, '', '', '', 1, 0, 'F', 0, 0, 'system:user:remove', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1005, 'b47e2e3525b17f8fde59afe04ecbf97e', '用户导出', 100, 5, '', '', '', 1, 0, 'F', 0, 0, 'system:user:export', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1006, '0227848600e2a3aee429f6d39a3e053e', '用户导入', 100, 6, '', '', '', 1, 0, 'F', 0, 0, 'system:user:import', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1007, '7f28dd50d815c3cea77cf9eefab0f155', '重置密码', 100, 7, '', '', '', 1, 0, 'F', 0, 0, 'system:user:resetPwd', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1008, '6d95668f232920c4f37df0cd08a6979d', '角色查询', 101, 1, '', '', '', 1, 0, 'F', 0, 0, 'system:role:query', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1009, 'daa80ca99e3c343b1381492d229f0875', '角色新增', 101, 2, '', '', '', 1, 0, 'F', 0, 0, 'system:role:add', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1010, '75ea5764ab12e6c61d5c6d932d1024e7', '角色修改', 101, 3, '', '', '', 1, 0, 'F', 0, 0, 'system:role:edit', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1011, 'ba6a424388fa8642cc0a468396109888', '角色删除', 101, 4, '', '', '', 1, 0, 'F', 0, 0, 'system:role:remove', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1012, 'cfab9fc091c595c5e46534ae89a450fb', '角色导出', 101, 5, '', '', '', 1, 0, 'F', 0, 0, 'system:role:export', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1013, '6e34ab681dab68e9a3aeabf9815ce911', '菜单查询', 102, 1, '', '', '', 1, 0, 'F', 0, 0, 'system:menu:query', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1014, 'd47b5d3eaa492b8f50c092f22c1e3654', '菜单新增', 102, 2, '', '', '', 1, 0, 'F', 0, 0, 'system:menu:add', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1015, '3ee05d69ec6d7e54524253f70e61fa25', '菜单修改', 102, 3, '', '', '', 1, 0, 'F', 0, 0, 'system:menu:edit', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1016, '99cbf063d4caffd72aee29ec49258e44', '菜单删除', 102, 4, '', '', '', 1, 0, 'F', 0, 0, 'system:menu:remove', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1017, '2bdae5f739a256d1eb1a2517d8aeaea6', '部门查询', 103, 1, '', '', '', 1, 0, 'F', 0, 0, 'system:dept:query', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1018, 'ebe832b269c7387b472ce49564438580', '部门新增', 103, 2, '', '', '', 1, 0, 'F', 0, 0, 'system:dept:add', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1019, '26ce45c930c117d597fd334d47482abc', '部门修改', 103, 3, '', '', '', 1, 0, 'F', 0, 0, 'system:dept:edit', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1020, '1d10d952c21d647194b434a48dd2edaf', '部门删除', 103, 4, '', '', '', 1, 0, 'F', 0, 0, 'system:dept:remove', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1021, 'e843014127b04765eb2c51df4fda7a1c', '岗位查询', 104, 1, '', '', '', 1, 0, 'F', 0, 0, 'system:post:query', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1022, 'ac1ff3ef8d8b5e868f21f41ca05fcb4a', '岗位新增', 104, 2, '', '', '', 1, 0, 'F', 0, 0, 'system:post:add', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1023, '68e1640d8d41f03a91090b2e05fbc9b4', '岗位修改', 104, 3, '', '', '', 1, 0, 'F', 0, 0, 'system:post:edit', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1024, 'b7d1d43dd44c4832080a5529249e74c0', '岗位删除', 104, 4, '', '', '', 1, 0, 'F', 0, 0, 'system:post:remove', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1025, '4a6419cbd7094cb824b1e8d719daa078', '岗位导出', 104, 5, '', '', '', 1, 0, 'F', 0, 0, 'system:post:export', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1026, 'fd5924a5dcb672d46f81733de91dbb8c', '字典查询', 105, 1, '#', '', '', 1, 0, 'F', 0, 0, 'system:dict:query', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1027, 'fd0c2b1d68e62b31358bcb47f5a95ab3', '字典新增', 105, 2, '#', '', '', 1, 0, 'F', 0, 0, 'system:dict:add', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1028, '29588b897b0703baad2464c5ff04e2b2', '字典修改', 105, 3, '#', '', '', 1, 0, 'F', 0, 0, 'system:dict:edit', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1029, 'fe193b65e7f3ab0f8ec55d043d002d81', '字典删除', 105, 4, '#', '', '', 1, 0, 'F', 0, 0, 'system:dict:remove', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1030, '252fbb98b9dcc8463fdd74ea0011a9e5', '字典导出', 105, 5, '#', '', '', 1, 0, 'F', 0, 0, 'system:dict:export', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1031, '2e7f37c2b3161f76c44ef077c3820f26', '参数查询', 106, 1, '#', '', '', 1, 0, 'F', 0, 0, 'system:config:query', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1032, 'a84a44b85a71e693c77e37fc09abc0ce', '参数新增', 106, 2, '#', '', '', 1, 0, 'F', 0, 0, 'system:config:add', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1033, 'ccff0542bbde232b7c8aa2fc7c891f0e', '参数修改', 106, 3, '#', '', '', 1, 0, 'F', 0, 0, 'system:config:edit', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1034, '4cbef4a387a38c501067c106c9cc80b6', '参数删除', 106, 4, '#', '', '', 1, 0, 'F', 0, 0, 'system:config:remove', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1035, '591e7e3681a519af33e55489fa754a41', '参数导出', 106, 5, '#', '', '', 1, 0, 'F', 0, 0, 'system:config:export', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1036, '7c1435fc2f82ed8744aa094fc0c6c67b', '公告查询', 107, 1, '#', '', '', 1, 0, 'F', 0, 0, 'system:notice:query', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1037, '0d0f637cabf5cc8c8214ce488baf2e70', '公告新增', 107, 2, '#', '', '', 1, 0, 'F', 0, 0, 'system:notice:add', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1038, 'b4550e033daf03a8ec96495a31025a7b', '公告修改', 107, 3, '#', '', '', 1, 0, 'F', 0, 0, 'system:notice:edit', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1039, '5455ca0b59bb2d92f29d5711289f90c4', '公告删除', 107, 4, '#', '', '', 1, 0, 'F', 0, 0, 'system:notice:remove', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1040, '9818d11ef429d2498147191bf8fd53c4', '操作查询', 500, 1, '#', '', '', 1, 0, 'F', 0, 0, 'monitor:operlog:query', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1041, 'e058fb001adc89c92142a984d3af4c7f', '操作删除', 500, 2, '#', '', '', 1, 0, 'F', 0, 0, 'monitor:operlog:remove', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1042, '2b4fc50a8d0e51d3f727f64591dd8eff', '日志导出', 500, 4, '#', '', '', 1, 0, 'F', 0, 0, 'monitor:operlog:export', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1043, 'cc3480af1c189c10a9bd50379caf12ce', '登录查询', 501, 1, '#', '', '', 1, 0, 'F', 0, 0, 'monitor:logininfor:query', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1044, '90484094e8d31e29b39a3dfddbdc64b7', '登录删除', 501, 2, '#', '', '', 1, 0, 'F', 0, 0, 'monitor:logininfor:remove', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1045, '94a9cb451ef80e3761f110529fa7b1d9', '日志导出', 501, 3, '#', '', '', 1, 0, 'F', 0, 0, 'monitor:logininfor:export', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1046, 'b5ad55ca26acb39867bb192cc89b0692', '在线查询', 109, 1, '#', '', '', 1, 0, 'F', 0, 0, 'monitor:online:query', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1047, '2645b7d328c689eb5f9be67ceb1bd87e', '批量强退', 109, 2, '#', '', '', 1, 0, 'F', 0, 0, 'monitor:online:batchLogout', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1048, '53a3137bca87b684b8f84f0c45d9a9f5', '单条强退', 109, 3, '#', '', '', 1, 0, 'F', 0, 0, 'monitor:online:forceLogout', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1049, '65c86ade21da15409457921574a2e7d1', '任务查询', 110, 1, '#', '', '', 1, 0, 'F', 0, 0, 'monitor:job:query', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1050, '56486ad5591de8bd3a4c7f980781cda0', '任务新增', 110, 2, '#', '', '', 1, 0, 'F', 0, 0, 'monitor:job:add', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1051, '2bdf4df3d3323eacddb2bfaa9eec3487', '任务修改', 110, 3, '#', '', '', 1, 0, 'F', 0, 0, 'monitor:job:edit', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1052, '0a00da4424d4657ecd18c12993e93420', '任务删除', 110, 4, '#', '', '', 1, 0, 'F', 0, 0, 'monitor:job:remove', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1053, '17d1d40aca8ca03ca7d2c63d1defefd5', '状态修改', 110, 5, '#', '', '', 1, 0, 'F', 0, 0, 'monitor:job:changeStatus', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1054, '0fa3a3927d9767c6afd64721f8b7501f', '任务导出', 110, 7, '#', '', '', 1, 0, 'F', 0, 0, 'monitor:job:export', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1055, '8e0dc774979f596daa175445b0739066', '生成查询', 115, 1, '#', '', '', 1, 0, 'F', 0, 0, 'tool:gen:query', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1056, 'ba415e3b1dc1e278d1c40d314ed1d4fa', '生成修改', 115, 2, '#', '', '', 1, 0, 'F', 0, 0, 'tool:gen:edit', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1057, '498a4ec08a3618b471378da77058d624', '生成删除', 115, 3, '#', '', '', 1, 0, 'F', 0, 0, 'tool:gen:remove', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1058, '0aae531d8b7a8d9639803ed388fc47c7', '导入代码', 115, 2, '#', '', '', 1, 0, 'F', 0, 0, 'tool:gen:import', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1059, '2c2213a4a42d45639e56ffa75a74b237', '预览代码', 115, 4, '#', '', '', 1, 0, 'F', 0, 0, 'tool:gen:preview', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);
INSERT INTO `tbl_sys_menu` VALUES (1060, '16b8af0f85c9fe63eccc2bd55418a945', '生成代码', 115, 5, '#', '', '', 1, 0, 'F', 0, 0, 'tool:gen:code', '#', '', NULL, '2022-02-20 15:10:58', NULL, NULL);


-- ----------------------------
-- Procedure structure for auto_insert_tbl_qm_label
-- ----------------------------
DROP PROCEDURE IF EXISTS `auto_insert_tbl_qm_label`;
delimiter ;;
CREATE PROCEDURE `auto_insert_tbl_qm_label`()
BEGIN
    declare i int default 4000001;
    while(i<=5000000)do
        INSERT INTO `tbl_qm_label`(`label_id`, `label_name`, `label_type`, `content`, `is_enable`, `tenant_id`, `pro_id`, `org_id`, `create_user`, `create_name`, `create_time`, `update_user`, `update_name`, `update_time`, `is_delete`) VALUES (REPLACE(UUID(),"-",""), concat('自动生成', i), 2, '自动批量生成的标签', 0, 'AFANTY', 'xlm', 'xlm001', 'yejx', '叶金雄', current_timestamp, 'yejx', '叶金雄', current_timestamp, 0);
        set i=i+1;
    end while;
END
;;
delimiter ;


-- ----------------------------
-- Triggers structure for table tbl_sys_dept
-- ----------------------------
DROP TRIGGER IF EXISTS `dkey_trigger_tbl_sys_dept`;
delimiter ;;
CREATE TRIGGER `dkey_trigger_tbl_sys_dept` BEFORE INSERT ON `tbl_sys_dept` FOR EACH ROW begin set new.d_key = md5(uuid()); end
;;
delimiter ;


-- ----------------------------
-- Triggers structure for table tbl_sys_menu
-- ----------------------------
DROP TRIGGER IF EXISTS `dkey_trigger_tbl_sys_menu`;
delimiter ;;
CREATE TRIGGER `dkey_trigger_tbl_sys_menu` BEFORE INSERT ON `tbl_sys_menu` FOR EACH ROW BEGIN SET new.d_key = md5(UUID()); END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
