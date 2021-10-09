/*
 Navicat Premium Data Transfer

 Source Server         : localhost-mysql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : afanty_test

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 08/10/2021 22:43:26
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
  `items_type` tinyint(4) NULL DEFAULT NULL COMMENT '评分类型：0-加分项  1-减分项',
  `is_use` tinyint(4) NULL DEFAULT NULL COMMENT '是否已被用作评分：0-未使用 1-已使用',
  `is_delete` tinyint(2) NULL DEFAULT NULL COMMENT '是否删除：0-否 1-是',
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

-- ----------------------------
-- Records of tbl_qm_items
-- ----------------------------
INSERT INTO `tbl_qm_items` VALUES ('01a4d6dd5ace4e1cbf56c31d3dd698a4', '372246facd174fa8a3cbe6a5b2992152', '获取租户', 7, 1, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-11 17:41:09', 'yejx', '叶金雄', '2020-03-11 17:41:09');
INSERT INTO `tbl_qm_items` VALUES ('07d35201cf2a43468161f5792e615ff6', '1d4cd94950d34f3bb91744896ff38299', '吐字模糊，普通话不标准', 6, 1, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-12 14:31:55', 'yejx', '叶金雄', '2020-03-12 14:36:39');
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
INSERT INTO `tbl_qm_items` VALUES ('b5bac06681d9443fb6895af05656b2c9', '45f634acad9744399d8995ffd8324d42', '测试时间 不必恐慌', 60, 1, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'KF45100000', '省分管理员', '2020-03-12 23:05:33', 'KF45100000', '省分管理员', '2020-03-12 23:05:33');
INSERT INTO `tbl_qm_items` VALUES ('bd4dacee85dd47f8917b6c8e24a28e67', '42253a630d5243599c331ff8e72a35ea', '口吐芬芳', 5, 1, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-04 17:07:38', 'yejx', '叶金雄', '2020-03-11 21:59:41');
INSERT INTO `tbl_qm_items` VALUES ('be4b79f17d3f480fa2879a9d59322ed0', '42253a630d5243599c331ff8e72a35ea', '嚣张跋扈，谩骂客户', 60, 1, 1, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-04 17:07:38', 'yejx', '叶金雄', '2020-03-11 21:59:41');
INSERT INTO `tbl_qm_items` VALUES ('c54a4c02fe3e422db4b12217c7b4705f', 'a787aa61a33c4f3e80bb72a8f7a13a39', '吐词清晰，使用标准普通话', 5, 1, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-03 14:55:21', 'yejx', '叶金雄', '2020-03-12 16:24:17');
INSERT INTO `tbl_qm_items` VALUES ('cfe1eb06014e42ed82aa9bb8876df488', '45f634acad9744399d8995ffd8324d42', '你愁啥', 50, 0, 0, 1, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'KF45100000', '省分管理员', '2020-03-12 23:05:33', 'KF45100000', '省分管理员', '2020-03-12 23:05:33');
INSERT INTO `tbl_qm_items` VALUES ('db1c55c09f013e3410546482679111b0', '12345678', '业务能力薄弱', 10, 1, 1, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2021-04-23 21:41:18', 'yejx', '叶金雄', '2021-04-23 21:41:18');
INSERT INTO `tbl_qm_items` VALUES ('dd86d40b1c75402fafd815fc7569c99b', 'a787aa61a33c4f3e80bb72a8f7a13a39', '灵活运用', 5, 0, 0, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2020-03-03 14:55:21', 'yejx', '叶金雄', '2020-03-12 16:24:17');
INSERT INTO `tbl_qm_items` VALUES ('f82b663b765f64d41771f4f7d72d38a2', 'fcefdbf3e5e3497f923b966f759cde71', '服务态度友好', 10, 0, 1, 0, NULL, NULL, NULL, 'AFANTY', 'xlm', 'development', 'yejx', '叶金雄', '2021-04-23 21:37:18', 'yejx', '叶金雄', '2021-04-23 21:37:18');

-- ----------------------------
-- Table structure for tbl_qm_label
-- ----------------------------
DROP TABLE IF EXISTS `tbl_qm_label`;
CREATE TABLE `tbl_qm_label`  (
  `label_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `label_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `label_type` tinyint(4) NULL DEFAULT NULL COMMENT '0-全部 1-用户 2-坐席',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '匹配规则',
  `is_enable` tinyint(4) NULL DEFAULT NULL COMMENT '0未启用 、1启用',
  `tenant_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pro_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人账号',
  `create_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人账号',
  `update_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人姓名',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除：0-否 1-是',
  PRIMARY KEY (`label_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签表存放的已经定义好的标签数据，\r\n读写表,\r\n数据量： < 10000条' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
