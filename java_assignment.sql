/*
 Navicat Premium Data Transfer

 Source Server         : wb
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : java_assignment

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 29/12/2023 15:06:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book_details
-- ----------------------------
DROP TABLE IF EXISTS `book_details`;
CREATE TABLE `book_details`  (
                                 `book_id` int NOT NULL AUTO_INCREMENT,
                                 `book_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                 `publisher` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                 `publish_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                 `is_borrowed` tinyint(1) NULL DEFAULT NULL,
                                 `user_id` int NULL DEFAULT NULL,
                                 `start_date` date NULL DEFAULT NULL,
                                 `due_date` date NULL DEFAULT NULL,
                                 PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11307 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_details
-- ----------------------------
INSERT INTO `book_details` VALUES (11126, 'Java EE程序设计', '清华大学出版社', '2023-09-01', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11128, 'Java EE程序设计', '清华大学出版社', '2023-09-01', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11129, 'Java EE程序设计', '清华大学出版社', '2023-09-01', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11130, 'Java EE程序设计', '清华大学出版社', '2023-09-01', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11131, 'Java EE程序设计', '清华大学出版社', '2023-09-01', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11132, 'Java EE程序设计', '清华大学出版社', '2023-09-01', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11133, 'Java EE程序设计', '清华大学出版社', '2023-09-01', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11134, 'Java EE程序设计', '清华大学出版社', '2023-09-01', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11135, 'Java EE程序设计', '清华大学出版社', '2023-09-01', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11136, 'Java EE程序设计', '清华大学出版社', '2023-09-01', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11137, 'Java EE程序设计', '清华大学出版社', '2023-09-01', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11138, 'Java EE程序设计', '清华大学出版社', '2023-09-01', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11139, 'Java EE程序设计', '清华大学出版社', '2023-09-01', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11140, 'Java EE程序设计', '清华大学出版社', '2023-09-01', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11141, 'Java EE程序设计', '清华大学出版社', '2023-09-01', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11142, 'Java EE程序设计', '清华大学出版社', '2023-09-01', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11143, 'Java EE程序设计', '清华大学出版社', '2023-09-01', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11144, 'Java EE程序设计', '清华大学出版社', '2023-09-01', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11145, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11146, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11147, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11148, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11149, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11150, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11151, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11152, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11153, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11154, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11155, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11156, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11157, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11158, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11159, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11160, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11161, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11162, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11163, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11164, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11165, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11166, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11167, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11168, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11169, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11170, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11171, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11172, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11173, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11174, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11175, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11176, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11177, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11178, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11179, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11180, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11181, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11182, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11183, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11184, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11185, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11186, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11187, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11188, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11189, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11190, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11191, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11192, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11193, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11194, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11195, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11196, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11197, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11198, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11199, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11200, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11201, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11202, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11203, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11204, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11205, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11206, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11207, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11208, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11209, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11210, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11211, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11212, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11213, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11214, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11215, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11216, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11217, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11218, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11219, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11220, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11221, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11222, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11223, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11224, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11225, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11226, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11227, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11228, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11229, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11230, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11231, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11232, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11233, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11234, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11235, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11236, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11237, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11238, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11239, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11240, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11241, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11242, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11243, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11244, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11245, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11246, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11247, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11248, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11249, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11250, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11251, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11252, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11253, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11254, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11255, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11256, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11257, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11258, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11259, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11260, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11261, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11262, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11263, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11264, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11265, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11266, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11267, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11268, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11269, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11270, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11271, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11272, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11273, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11274, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11275, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11276, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11277, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11278, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11279, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11280, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11281, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11282, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11283, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);
INSERT INTO `book_details` VALUES (11284, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for book_info
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info`  (
                              `book_info_id` int NOT NULL AUTO_INCREMENT,
                              `book_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                              `publisher` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                              `publish_date` date NULL DEFAULT NULL,
                              `total_inventory` int NULL DEFAULT NULL,
                              `available_books` int NULL DEFAULT NULL,
                              PRIMARY KEY (`book_info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 120 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_info
-- ----------------------------
INSERT INTO `book_info` VALUES (109, 'Java EE程序设计', '清华大学出版社', '2023-09-01', 18, 18);
INSERT INTO `book_info` VALUES (110, 'JavaWeb开发实战', '机械工业出版社', '2015-07-11', 20, 20);
INSERT INTO `book_info` VALUES (111, 'mysql从入门到精通', '清华大学出版社', '2017-12-07', 20, 20);
INSERT INTO `book_info` VALUES (112, 'Java开发从入门到精通（第2版）', '人民邮电出版社', '2020-03-03', 20, 20);
INSERT INTO `book_info` VALUES (113, '疯狂Java讲义（第5版）', '电子工业出版社', '2019-12-11', 20, 20);
INSERT INTO `book_info` VALUES (114, '码出高效：Java开发手册', '电子工业出版社', '2018-07-12', 20, 20);
INSERT INTO `book_info` VALUES (115, 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2020-05-14', 20, 20);
INSERT INTO `book_info` VALUES (116, 'Spring Boot实战派', '电子工业出版社', '2020-09-26', 20, 20);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
                              `user_id` int NOT NULL,
                              `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                              `max_books_allowed` int NULL DEFAULT NULL,
                              `borrowed_books` int NULL DEFAULT NULL,
                              `is_vip` tinyint(1) NULL DEFAULT NULL,
                              `overdue_books` int NULL DEFAULT NULL,
                              PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 'ganbro', 5, 0, 0, 0);
INSERT INTO `user_info` VALUES (25, '测试', 10, 0, 1, 0);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
                          `user_id` int NOT NULL AUTO_INCREMENT,
                          `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                          `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '默认密码为username',
                          `is_admin` tinyint NULL DEFAULT NULL,
                          PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'ganbro', '123456', 1);
INSERT INTO `users` VALUES (25, '测试', '123456', 0);

SET FOREIGN_KEY_CHECKS = 1;
