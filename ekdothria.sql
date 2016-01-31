/*
Navicat MySQL Data Transfer

Source Server         : Con
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : ekdothria

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2016-01-31 14:23:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `Username` varchar(20) DEFAULT NULL,
  `Password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('Left', 'Mike');

-- ----------------------------
-- Table structure for `anakoinwseis`
-- ----------------------------
DROP TABLE IF EXISTS `anakoinwseis`;
CREATE TABLE `anakoinwseis` (
  `Flag` tinyint(4) DEFAULT NULL,
  `Keimeno` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of anakoinwseis
-- ----------------------------
INSERT INTO `anakoinwseis` VALUES ('1', 'Το δρομολόγιο για Λάρισα στις 9:15 δε θα προγραμματιστεί.');
INSERT INTO `anakoinwseis` VALUES ('1', 'Το δρομολόγιο για Βόλο στις 9:00 θα έχει καθυστέρησει 45 λεπτών.');

-- ----------------------------
-- Table structure for `dromologia`
-- ----------------------------
DROP TABLE IF EXISTS `dromologia`;
CREATE TABLE `dromologia` (
  `Afethria` varchar(20) DEFAULT NULL,
  `Proorismos` varchar(20) DEFAULT NULL,
  `wraAnaxwrhshs` time DEFAULT NULL,
  `busName` varchar(10) DEFAULT NULL,
  KEY `busName` (`busName`),
  CONSTRAINT `dromologia_ibfk_1` FOREIGN KEY (`busName`) REFERENCES `stoixeia_lewforeiwn` (`busName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dromologia
-- ----------------------------
INSERT INTO `dromologia` VALUES ('Αθήνα', 'Πάτρα', '08:00:00', '1B');
INSERT INTO `dromologia` VALUES ('Αθήνα', 'Πάτρα', '12:00:00', '1C');
INSERT INTO `dromologia` VALUES ('Αθήνα', 'Πάτρα', '16:30:00', '1E');
INSERT INTO `dromologia` VALUES ('Αθήνα', 'Γιάννενα', '08:30:00', '1A');
INSERT INTO `dromologia` VALUES ('Αθήνα', 'Γιάννενα', '16:15:00', '1F');
INSERT INTO `dromologia` VALUES ('Αθήνα', 'Θεσσαλονίκη', '07:30:00', '1D');
INSERT INTO `dromologia` VALUES ('Αθήνα', 'Θεσσαλονίκη', '15:45:00', '2B');
INSERT INTO `dromologia` VALUES ('Αθήνα', 'Λάρισα', '09:15:00', '2D');
INSERT INTO `dromologia` VALUES ('Αθήνα', 'Λάρισα', '13:50:00', '2A');
INSERT INTO `dromologia` VALUES ('Αθήνα', 'Λάρισα', '17:40:00', '2E');
INSERT INTO `dromologia` VALUES ('Αθήνα', 'Βόλος', '09:00:00', '2F');
INSERT INTO `dromologia` VALUES ('Αθήνα', 'Βόλος', '14:20:00', '2C');
INSERT INTO `dromologia` VALUES ('Πάτρα', 'Αθήνα', '08:40:00', '3B');
INSERT INTO `dromologia` VALUES ('Πάτρα', 'Αθήνα', '13:20:00', '3A');

-- ----------------------------
-- Table structure for `stoixeia_lewforeiwn`
-- ----------------------------
DROP TABLE IF EXISTS `stoixeia_lewforeiwn`;
CREATE TABLE `stoixeia_lewforeiwn` (
  `busName` varchar(10) NOT NULL,
  `numberOfSheets` tinyint(4) DEFAULT NULL,
  `facilities` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`busName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stoixeia_lewforeiwn
-- ----------------------------
INSERT INTO `stoixeia_lewforeiwn` VALUES ('1A', '60', 'Wifi');
INSERT INTO `stoixeia_lewforeiwn` VALUES ('1B', '65', 'TV');
INSERT INTO `stoixeia_lewforeiwn` VALUES ('1C', '70', 'Wifi,TV');
INSERT INTO `stoixeia_lewforeiwn` VALUES ('1D', '75', 'WC');
INSERT INTO `stoixeia_lewforeiwn` VALUES ('1E', '60', 'Wifi');
INSERT INTO `stoixeia_lewforeiwn` VALUES ('1F', '65', 'TV,Wifi,WC');
INSERT INTO `stoixeia_lewforeiwn` VALUES ('2A', '70', 'TV');
INSERT INTO `stoixeia_lewforeiwn` VALUES ('2B', '65', 'TV,WC');
INSERT INTO `stoixeia_lewforeiwn` VALUES ('2C', '70', null);
INSERT INTO `stoixeia_lewforeiwn` VALUES ('2D', '70', 'TV,WC');
INSERT INTO `stoixeia_lewforeiwn` VALUES ('2E', '75', 'Wifi,TV');
INSERT INTO `stoixeia_lewforeiwn` VALUES ('2F', '80', 'TV');
INSERT INTO `stoixeia_lewforeiwn` VALUES ('3A', '60', 'Wifi');
INSERT INTO `stoixeia_lewforeiwn` VALUES ('3B', '65', 'WC');

-- ----------------------------
-- Table structure for `stoixeia_pelatwn`
-- ----------------------------
DROP TABLE IF EXISTS `stoixeia_pelatwn`;
CREATE TABLE `stoixeia_pelatwn` (
  `firstName` varchar(20) DEFAULT NULL,
  `lastName` varchar(20) DEFAULT NULL,
  `Ekptwsh` varchar(15) DEFAULT NULL,
  `kleismenoDromologio` varchar(50) DEFAULT NULL,
  `Thesh` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stoixeia_pelatwn
-- ----------------------------

-- ----------------------------
-- Table structure for `tamies`
-- ----------------------------
DROP TABLE IF EXISTS `tamies`;
CREATE TABLE `tamies` (
  `Username` varchar(20) DEFAULT NULL,
  `Password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tamies
-- ----------------------------
INSERT INTO `tamies` VALUES ('Left', 'Aleks');
INSERT INTO `tamies` VALUES ('Mike', 'Lioudakhs');

-- ----------------------------
-- Table structure for `ticketprice`
-- ----------------------------
DROP TABLE IF EXISTS `ticketprice`;
CREATE TABLE `ticketprice` (
  `price` double DEFAULT NULL,
  `cityMatching` varchar(80) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ticketprice
-- ----------------------------
INSERT INTO `ticketprice` VALUES ('25.8', 'Αθήνα-Πάτρα');
INSERT INTO `ticketprice` VALUES ('33.5', 'Αθήνα-Γιάννενα');
INSERT INTO `ticketprice` VALUES ('34', 'Αθήνα-Θεσσαλονίκη');
INSERT INTO `ticketprice` VALUES ('30.25', 'Αθήνα-Λάρισα');
INSERT INTO `ticketprice` VALUES ('26.4', 'Αθήνα-Βόλος');
