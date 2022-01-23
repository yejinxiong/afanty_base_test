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

SET FOREIGN_KEY_CHECKS = 1;
