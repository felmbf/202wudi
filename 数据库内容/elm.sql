/*
Navicat MySQL Data Transfer

Source Server         : qmzp
Source Server Version : 50547
Source Host           : localhost:3306
Source Database       : elm

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2019-01-03 15:13:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dingdan`
-- ----------------------------
DROP TABLE IF EXISTS `dingdan`;
CREATE TABLE `dingdan` (
  `O_user` varchar(255) NOT NULL,
  `O_restName` varchar(255) NOT NULL,
  `O_food` varchar(255) NOT NULL,
  `O_totalPrice` double NOT NULL,
  `O_date` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dingdan
-- ----------------------------
INSERT INTO `dingdan` VALUES ('棒棒糖', '鸡不厌炸', '+考神套餐+鸡腿堡+薯条+可乐', '211.52', '2018-12-22 10:04');
INSERT INTO `dingdan` VALUES ('或许我爱萝莉', '鸡不厌炸', '考神套餐鸡腿堡+雪花鸡柳鸡腿堡+薯条+可乐', '364.16', '2018-12-22 09:08');
INSERT INTO `dingdan` VALUES ('棒棒糖', '鸡不厌炸', '+考神套餐+鸡腿堡+薯条+可乐', '160.64000000000001', '2018-12-22 10:36');
INSERT INTO `dingdan` VALUES ('棒棒糖', '鸡不厌炸', '+考神套餐+鸡腿堡+薯条+可乐+鸡腿堡+雪花鸡柳', '317.28000000000003', '2018-12-22 11:11');
INSERT INTO `dingdan` VALUES ('棒棒糖', '鸡不厌炸', '+考神套餐+鸡腿堡+薯条+可乐+鸡腿堡+雪花鸡柳+双人汉堡大咖套餐', '788.32', '2019-01-01 11:20');
INSERT INTO `dingdan` VALUES ('或许我爱萝莉', '鸡不厌炸', '+鸡腿堡+薯条+可乐+鸡腿堡+雪花鸡柳+考神套餐+双人汉堡大咖套餐', '235.52', '2019-01-01 17:31');

-- ----------------------------
-- Table structure for `food`
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food` (
  `restName` varchar(255) NOT NULL,
  `foodName` varchar(255) NOT NULL,
  `foodPrice` double DEFAULT NULL,
  `salesCount` int(5) NOT NULL,
  `foodType` varchar(255) NOT NULL,
  PRIMARY KEY (`foodName`),
  KEY `Rest_Name` (`restName`),
  KEY `foodType` (`foodType`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES ('鸡不厌炸', '考神套餐', '54.88', '58', '超值套餐');
INSERT INTO `food` VALUES ('鸡不厌炸', '鸡腿堡+薯条+可乐', '50.88', '44', '超值套餐');
INSERT INTO `food` VALUES ('鸡不厌炸', '鸡腿堡+雪花鸡柳', '52.88', '49', '超值套餐');
INSERT INTO `food` VALUES ('鸡不厌炸', '双人汉堡大咖套餐', '76.88', '15', '乐享双人餐');
INSERT INTO `food` VALUES ('鸡不厌炸', '双人卷堡大咖套餐', '76.88', '8', '乐享双人餐');
INSERT INTO `food` VALUES ('鸡不厌炸', '香辣鸡腿堡', '9', '1', '汉堡系列');
INSERT INTO `food` VALUES ('鸡不厌炸', '墨西哥鸡肉卷', '9', '1', '汉堡系列');
INSERT INTO `food` VALUES ('鸡不厌炸', '美式薯条', '6.88', '1', '小吃系列');
INSERT INTO `food` VALUES ('鸡不厌炸', '上校鸡块', '7', '1', '小吃系列');
INSERT INTO `food` VALUES ('鸡不厌炸', '劲爆鸡米花', '7', '0', '小吃系列');
INSERT INTO `food` VALUES ('鸡不厌炸', '雪花鸡柳丝', '7', '14', '小吃系列');
INSERT INTO `food` VALUES ('鸡不厌炸', '香辣小鸡腿（2个）', '15', '0', '小吃系列');
INSERT INTO `food` VALUES ('鸡不厌炸', '原味鸡排', '49.5', '0', '小吃系列');
INSERT INTO `food` VALUES ('深夜食堂', '奥尔良鸡柳炒饭', '57', '5', '精品炒饭');
INSERT INTO `food` VALUES ('深夜食堂', '铁板煎饺', '55', '5', '日式小食');
INSERT INTO `food` VALUES ('深夜食堂', '西红柿鸡蛋炒饭', '54', '2', '精品炒饭');
INSERT INTO `food` VALUES ('深夜食堂', '牛肉孜然炒饭', '57', '3', '精品炒饭');
INSERT INTO `food` VALUES ('深夜食堂', '香菇肉丝盖浇饭', '54', '13', '美味盖饭');
INSERT INTO `food` VALUES ('深夜食堂', '牛肉盖浇饭', '57', '11', '美味盖饭');
INSERT INTO `food` VALUES ('深夜食堂', '铁板年糕', '53', '4', '日式小食');
INSERT INTO `food` VALUES ('深夜食堂', '什锦虾仁炒饭', '57', '3', '精品炒饭');
INSERT INTO `food` VALUES ('深夜食堂', '咖喱炒饭', '54', '1', '精品炒饭');
INSERT INTO `food` VALUES ('深夜食堂', '雪菜肉丝炒饭', '54', '1', '精品炒饭');
INSERT INTO `food` VALUES ('深夜食堂', '腊鸡腿盖浇饭', '57', '15', '美味盖饭');
INSERT INTO `food` VALUES ('深夜食堂', '鸡柳盖浇饭', '57', '20', '美味盖饭');
INSERT INTO `food` VALUES ('海鲜爱尚面', '花甲汤面', '51.9', '3', '海鲜面');
INSERT INTO `food` VALUES ('海鲜爱尚面', '花甲叉烧汤面', '54.9', '4', '海鲜面');
INSERT INTO `food` VALUES ('海鲜爱尚面', '花甲牛丸汤面', '53.9', '3', '海鲜面');
INSERT INTO `food` VALUES ('海鲜爱尚面', '叉烧汤面', '53.9', '0', '营养高汤面');
INSERT INTO `food` VALUES ('海鲜爱尚面', '明虾汤面', '53.9', '0', '营养高汤面');
INSERT INTO `food` VALUES ('海鲜爱尚面', '牛肉丸汤面', '51.9', '0', '营养高汤面');
INSERT INTO `food` VALUES ('海鲜爱尚面', '特色风味炒饭', '50', '0', '炒炒');
INSERT INTO `food` VALUES ('海鲜爱尚面', '特色风味炒面', '50', '0', '炒炒');
INSERT INTO `food` VALUES ('海鲜爱尚面', '特色风味炒河粉', '50', '0', '炒炒');
INSERT INTO `food` VALUES ('海鲜爱尚面', '特色风味炒干', '50', '0', '炒炒');
INSERT INTO `food` VALUES ('海鲜爱尚面', '', null, '0', '');
INSERT INTO `food` VALUES ('海鲜爱尚面', '特色风味炒粉', '50', '0', '炒炒');
INSERT INTO `food` VALUES ('海鲜爱尚面', '特色', '50', '0', '炒炒');
INSERT INTO `food` VALUES ('海鲜爱尚面', '风味', '55', '0', '炒炒');

-- ----------------------------
-- Table structure for `foodtype`
-- ----------------------------
DROP TABLE IF EXISTS `foodtype`;
CREATE TABLE `foodtype` (
  `typeName` varchar(255) NOT NULL,
  `restName` varchar(255) NOT NULL,
  PRIMARY KEY (`typeName`),
  KEY `restName` (`restName`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of foodtype
-- ----------------------------
INSERT INTO `foodtype` VALUES ('超值套餐', '鸡不厌炸');
INSERT INTO `foodtype` VALUES ('乐享双人餐', '鸡不厌炸');
INSERT INTO `foodtype` VALUES ('汉堡系列', '鸡不厌炸');
INSERT INTO `foodtype` VALUES ('小吃系列', '鸡不厌炸');
INSERT INTO `foodtype` VALUES ('美味盖饭', '深夜食堂');
INSERT INTO `foodtype` VALUES ('日式小食', '深夜食堂');
INSERT INTO `foodtype` VALUES ('精品炒饭', '深夜食堂');
INSERT INTO `foodtype` VALUES ('海鲜面', '海鲜爱尚面');
INSERT INTO `foodtype` VALUES ('营养高汤面', '海鲜爱尚面');
INSERT INTO `foodtype` VALUES ('炒炒', '海鲜爱尚面');

-- ----------------------------
-- Table structure for `restaurant`
-- ----------------------------
DROP TABLE IF EXISTS `restaurant`;
CREATE TABLE `restaurant` (
  `restName` varchar(255) NOT NULL,
  `restIconUrl` varchar(255) NOT NULL,
  `salesCount` int(5) DEFAULT NULL,
  PRIMARY KEY (`restName`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of restaurant
-- ----------------------------
INSERT INTO `restaurant` VALUES ('鸡不厌炸', 'http://192.168.43.13:8080/ELM/image/img_1.jpg', '190');
INSERT INTO `restaurant` VALUES ('深夜食堂', 'http://192.168.43.13:8080/ELM/image/img_2.jpg', '73');
INSERT INTO `restaurant` VALUES ('海鲜爱尚面', 'http://192.168.43.13:8080/ELM/image/img_4.jpg', '10');
INSERT INTO `restaurant` VALUES ('张飞牛肉', 'http://192.168.43.13:8080/ELM/image/img_12.jpg', '0');
INSERT INTO `restaurant` VALUES ('川味食朴', 'http://192.168.43.13:8080/ELM/image/img_3.jpg', '0');
INSERT INTO `restaurant` VALUES ('土耳其烧烤', 'http://192.168.43.13:8080/ELM/image/img_11.jpg', '0');
INSERT INTO `restaurant` VALUES ('后家便当', 'http://192.168.43.13:8080/ELM/image/img_10.jpg', '0');
INSERT INTO `restaurant` VALUES ('龙门花甲', 'http://192.168.43.13:8080/ELM/image/img_5.jpg', '0');
INSERT INTO `restaurant` VALUES ('蒙城牛肉汤', 'http://192.168.43.13:8080/ELM/image/img_6.jpg', '0');
INSERT INTO `restaurant` VALUES ('许记粥铺', 'http://192.168.43.13:8080/ELM/image/img_7.jpg', '0');
INSERT INTO `restaurant` VALUES ('维客炊', 'http://192.168.43.13:8080/ELM/image/img_8.jpg', '0');
INSERT INTO `restaurant` VALUES ('正新鸡排', 'http://192.168.43.13:8080/ELM/image/img_9.jpg', '0');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `yuE` double DEFAULT NULL,
  `telnum` varchar(11) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('或许我爱萝莉', '123456', '5744.919999999999', '17367077037');
INSERT INTO `user` VALUES ('棒棒糖', '123456', '3100.5999999999976', '15258815970');
