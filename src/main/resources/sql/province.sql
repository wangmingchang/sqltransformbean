/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50626
 Source Host           : localhost:3306
 Source Schema         : pccv

 Target Server Type    : MySQL
 Target Server Version : 50626
 File Encoding         : 65001

 Date: 17/08/2018 18:10:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for province
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省-编码',
  `province_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省-名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_province_code`(`province_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '行政区地域省份信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of province
-- ----------------------------
INSERT INTO `province` VALUES (1, '11', '北京市');
INSERT INTO `province` VALUES (2, '12', '天津市');
INSERT INTO `province` VALUES (15, '13', '河北省');
INSERT INTO `province` VALUES (16, '14', '山西省');
INSERT INTO `province` VALUES (20, '15', '内蒙古自治区');
INSERT INTO `province` VALUES (21, '21', '辽宁省');
INSERT INTO `province` VALUES (23, '22', '吉林省');
INSERT INTO `province` VALUES (25, '23', '黑龙江省');
INSERT INTO `province` VALUES (26, '31', '上海市');
INSERT INTO `province` VALUES (27, '32', '江苏省');
INSERT INTO `province` VALUES (28, '33', '浙江省');
INSERT INTO `province` VALUES (29, '34', '安徽省');
INSERT INTO `province` VALUES (33, '35', '福建省');
INSERT INTO `province` VALUES (35, '36', '江西省');
INSERT INTO `province` VALUES (37, '37', '山东省');
INSERT INTO `province` VALUES (38, '41', '河南省');
INSERT INTO `province` VALUES (39, '42', '湖北省');
INSERT INTO `province` VALUES (40, '43', '湖南省');
INSERT INTO `province` VALUES (41, '44', '广东省');
INSERT INTO `province` VALUES (43, '45', '广西壮族自治区');
INSERT INTO `province` VALUES (44, '46', '海南省');
INSERT INTO `province` VALUES (45, '50', '重庆市');
INSERT INTO `province` VALUES (46, '51', '四川省');
INSERT INTO `province` VALUES (47, '52', '贵州省');
INSERT INTO `province` VALUES (48, '53', '云南省');
INSERT INTO `province` VALUES (49, '54', '西藏自治区');
INSERT INTO `province` VALUES (51, '61', '陕西省');
INSERT INTO `province` VALUES (53, '62', '甘肃省');
INSERT INTO `province` VALUES (54, '63', '青海省');
INSERT INTO `province` VALUES (55, '64', '宁夏回族自治区');
INSERT INTO `province` VALUES (56, '65', '新疆维吾尔自治区');

SET FOREIGN_KEY_CHECKS = 1;
