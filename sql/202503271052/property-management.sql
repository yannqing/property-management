-- ************************************************************
--
-- close fk
--
-- skip


-- MySQL dump 10.13  Distrib 5.7.35, for Linux (x86_64)
--
-- Host: 30.47.14.20    Database: property-management
-- ------------------------------------------------------
-- Server version	5.7.18-txsql-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity_registration`
--

DROP TABLE IF EXISTS `activity_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_registration` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `activityId` int(11) NOT NULL COMMENT '活动ID',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态(1:已报名 2:已签到 3:已取消)',
  `registerTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '报名时间',
  `checkInTime` datetime DEFAULT NULL COMMENT '签到时间',
  `participantCount` int(11) DEFAULT '1' COMMENT '参与人数（一人可带家属）',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='活动报名表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_registration`
--

/*!40000 ALTER TABLE `activity_registration` DISABLE KEYS */;
INSERT INTO `activity_registration` VALUES (1,1,1,1,'2025-02-17 18:38:57',NULL,1,'2025-02-17 18:38:57','2025-02-17 18:38:57',0),(2,1,1,1,'2025-02-17 20:24:10','2023-10-15 10:00:00',1,'2025-02-17 20:24:10','2025-02-17 20:24:10',0),(3,2,2,1,'2025-02-17 20:24:10',NULL,2,'2025-02-17 20:24:10','2025-02-17 20:24:10',0),(4,3,3,2,'2025-02-17 20:24:10','2023-10-15 11:00:00',1,'2025-02-17 20:24:10','2025-02-17 20:24:10',0),(5,5,5,1,'2025-02-17 20:24:10',NULL,3,'2025-02-17 20:24:10','2025-02-17 20:24:10',0),(6,6,6,3,'2025-02-17 20:24:10',NULL,1,'2025-02-17 20:24:10','2025-02-17 20:24:10',0),(7,7,7,1,'2025-02-17 20:24:10','2023-10-15 12:00:00',2,'2025-02-17 20:24:10','2025-02-17 20:24:10',0),(8,8,8,2,'2025-02-17 20:24:10','2023-10-15 13:00:00',1,'2025-02-17 20:24:10','2025-02-17 20:24:10',0),(9,9,1,1,'2025-02-17 20:24:10',NULL,1,'2025-02-17 20:24:10','2025-02-17 20:24:10',0),(10,10,2,3,'2025-02-17 20:24:10',NULL,2,'2025-02-17 20:24:10','2025-02-17 20:24:10',0),(11,11,3,1,'2025-02-17 20:24:10','2023-10-15 14:00:00',1,'2025-02-17 20:24:10','2025-02-17 20:24:10',0),(12,12,5,2,'2025-02-17 20:24:10','2023-10-15 15:00:00',1,'2025-02-17 20:24:10','2025-02-17 20:24:10',0),(13,1,6,1,'2025-02-17 20:24:10',NULL,3,'2025-02-17 20:24:10','2025-02-17 20:24:10',0),(14,2,7,1,'2025-02-17 20:24:10','2023-10-15 16:00:00',1,'2025-02-17 20:24:10','2025-02-17 20:24:10',0),(15,3,8,3,'2025-02-17 20:24:10',NULL,2,'2025-02-17 20:24:10','2025-02-17 20:24:10',0),(16,4,1,1,'2025-02-17 20:24:10',NULL,1,'2025-02-17 20:24:10','2025-02-17 20:24:10',0),(17,5,2,2,'2025-02-17 20:24:10','2023-10-15 17:00:00',1,'2025-02-17 20:24:10','2025-02-17 20:24:10',0),(18,6,3,1,'2025-02-17 20:24:10',NULL,2,'2025-02-17 20:24:10','2025-02-17 20:24:10',0),(19,7,5,1,'2025-02-17 20:24:10','2023-10-15 18:00:00',1,'2025-02-17 20:24:10','2025-02-17 20:24:10',0),(20,8,6,3,'2025-02-17 20:24:10',NULL,1,'2025-02-17 20:24:10','2025-02-17 20:24:10',0),(21,9,7,1,'2025-02-17 20:24:10',NULL,2,'2025-02-17 20:24:10','2025-02-17 20:24:10',0);
/*!40000 ALTER TABLE `activity_registration` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-27 10:49:11
-- ************************************************************
--
-- close fk
--
-- skip


-- MySQL dump 10.13  Distrib 5.7.35, for Linux (x86_64)
--
-- Host: 30.47.14.20    Database: property-management
-- ------------------------------------------------------
-- Server version	5.7.18-txsql-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcement` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `noticeTitle` varchar(1024) DEFAULT NULL COMMENT '公告标题',
  `noticeContent` text COMMENT '公告内容',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '公告类型（通知/新闻/紧急公告）',
  `publishTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `publishUser` int(11) NOT NULL COMMENT '发布者id',
  `isTop` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否置顶（0否，1是）',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态（0未发布，1已发布）',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) DEFAULT '0' COMMENT '逻辑删除',
  `description` varchar(1024) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement`
--

/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
INSERT INTO `announcement` VALUES (1,'第一个公告1','公告表建立完成',1,'2025-02-09 14:35:49',1,0,1,'2025-02-09 14:35:49','2025-02-09 14:50:18',0,'备注'),(2,'第二个公告','公告表建立完成',1,'2025-02-09 14:48:53',1,0,1,'2025-02-09 14:48:53','2025-02-09 14:50:18',0,'备注2');
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-27 10:49:11
-- ************************************************************
--
-- close fk
--
-- skip


-- MySQL dump 10.13  Distrib 5.7.35, for Linux (x86_64)
--
-- Host: 30.47.14.20    Database: property-management
-- ------------------------------------------------------
-- Server version	5.7.18-txsql-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cabinet`
--

DROP TABLE IF EXISTS `cabinet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cabinet` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 id',
  `code` varchar(50) NOT NULL COMMENT '外卖柜编号',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（可用/占用/异常）',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='外卖柜表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cabinet`
--

/*!40000 ALTER TABLE `cabinet` DISABLE KEYS */;
INSERT INTO `cabinet` VALUES (1,'MT-001',1,NULL,NULL,'2025-02-17 17:55:42','2025-02-17 18:05:14',0),(2,'MT-002',0,NULL,NULL,'2025-02-17 20:05:29','2025-02-17 20:05:29',0),(3,'MT-003',0,NULL,NULL,'2025-02-17 20:05:32','2025-02-17 20:05:32',0),(4,'MT-004',0,NULL,NULL,'2025-02-17 20:05:34','2025-02-17 20:05:34',0),(5,'MT-005',0,NULL,NULL,'2025-02-17 20:05:37','2025-02-17 20:05:37',0),(6,'MT-006',1,NULL,NULL,'2025-02-17 20:05:40','2025-02-17 20:34:09',0),(7,'MT-007',1,NULL,NULL,'2025-02-17 20:05:42','2025-02-17 20:34:09',0),(8,'MT-008',0,NULL,NULL,'2025-02-17 20:05:44','2025-02-17 20:05:44',0),(9,'MT-009',1,NULL,NULL,'2025-02-17 20:05:47','2025-02-17 20:34:09',0),(10,'MT-010',0,NULL,NULL,'2025-02-17 20:05:51','2025-02-17 20:05:51',0),(11,'ELM-001',0,NULL,NULL,'2025-02-17 20:06:02','2025-02-17 20:06:02',0),(12,'ELM-002',1,NULL,NULL,'2025-02-17 20:06:04','2025-02-17 20:34:09',0),(13,'ELM-003',0,NULL,NULL,'2025-02-17 20:06:06','2025-02-17 20:06:06',0),(14,'ELM-004',0,NULL,NULL,'2025-02-17 20:06:09','2025-02-17 20:06:09',0),(15,'ELM-005',1,NULL,NULL,'2025-02-17 20:06:12','2025-02-17 20:34:28',0),(16,'ELM-006',1,NULL,NULL,'2025-02-17 20:06:15','2025-02-17 20:34:28',0),(17,'ELM-007',1,NULL,NULL,'2025-02-17 20:06:17','2025-02-17 20:34:28',0),(18,'ELM-008',0,NULL,NULL,'2025-02-17 20:06:19','2025-02-17 20:06:19',0),(19,'ELM-009',0,NULL,NULL,'2025-02-17 20:06:22','2025-02-17 20:06:22',0),(20,'ELM-010',0,NULL,NULL,'2025-02-17 20:06:25','2025-02-17 20:06:25',0);
/*!40000 ALTER TABLE `cabinet` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-27 10:49:12
-- ************************************************************
--
-- close fk
--
-- skip


-- MySQL dump 10.13  Distrib 5.7.35, for Linux (x86_64)
--
-- Host: 30.47.14.20    Database: property-management
-- ------------------------------------------------------
-- Server version	5.7.18-txsql-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `community_activity`
--

DROP TABLE IF EXISTS `community_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `community_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(100) NOT NULL COMMENT '活动标题',
  `description` text COMMENT '活动描述',
  `type` tinyint(4) NOT NULL COMMENT '活动类型(1:文化活动 2:体育活动 3:志愿服务 4:节日庆祝 5:教育讲座)',
  `startTime` datetime NOT NULL COMMENT '活动开始时间',
  `endTime` datetime NOT NULL COMMENT '活动结束时间',
  `location` varchar(255) DEFAULT NULL COMMENT '活动地点',
  `maxParticipants` int(11) DEFAULT NULL COMMENT '最大参与人数',
  `currentParticipants` int(11) DEFAULT '0' COMMENT '当前参与人数',
  `organizer` int(11) DEFAULT NULL COMMENT '组织者id',
  `contactPhone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `status` tinyint(4) DEFAULT '1' COMMENT '活动状态(0:草稿 1:报名中 2:进行中 3:已结束 4:已取消)',
  `coverImage` varchar(255) DEFAULT NULL COMMENT '活动封面图片',
  `signUpDeadline` datetime DEFAULT NULL COMMENT '报名截止时间',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='社区活动表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `community_activity`
--

/*!40000 ALTER TABLE `community_activity` DISABLE KEYS */;
INSERT INTO `community_activity` VALUES (1,'新活动','测试新活动',1,'2024-02-17 20:20:03','2024-02-18 20:20:05',NULL,30,10,1,'12222222222',1,'https://images.pexels.com/photos/30243611/pexels-photo-30243611.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2','2025-02-17 20:19:46','2025-02-17 18:22:26','2025-02-17 20:21:32',0),(2,'2024秋季志愿献血','献血活动测试',2,'2024-02-19 20:20:05','2024-02-20 20:20:06',NULL,30,1,2,'12222222222',1,'https://images.pexels.com/photos/30474533/pexels-photo-30474533.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2','2025-02-17 20:19:49','2025-02-17 20:14:09','2025-02-17 20:21:32',0),(3,'2024秋季篮球比赛',NULL,1,'2024-02-21 20:20:08','2024-02-22 20:20:09',NULL,30,2,3,'12222222222',1,'https://images.pexels.com/photos/30474533/pexels-photo-30474533.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2','2025-02-17 20:19:50','2025-02-17 20:14:22','2025-02-17 20:21:32',0),(4,'2024秋季诗朗诵',NULL,0,'2024-02-23 20:20:09','2024-02-24 20:20:10',NULL,66,11,1,'12222222222',1,'https://images.pexels.com/photos/30474533/pexels-photo-30474533.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2','2025-02-17 20:19:51','2025-02-17 20:14:36','2025-02-17 20:21:32',0),(5,'2024秋季足球比赛','足球',1,'2024-02-25 20:20:10','2024-02-26 20:20:11',NULL,12,2,5,'12222222222',1,'https://images.pexels.com/photos/30474533/pexels-photo-30474533.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2','2025-02-17 20:19:51','2025-02-17 20:14:47','2025-02-17 20:21:32',0),(6,'2024秋季跳高比赛',NULL,1,'2025-02-17 20:20:11','2025-02-17 20:20:12',NULL,30,0,3,'12222222222',1,'https://images.pexels.com/photos/30474533/pexels-photo-30474533.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2','2025-02-17 20:19:52','2025-02-17 20:14:53','2025-02-17 20:21:32',0),(7,'2024秋季拔河比赛','拔河',1,'2025-02-17 20:20:12','2025-02-17 20:20:13',NULL,34,34,2,'12222222222',1,'https://images.pexels.com/photos/30474533/pexels-photo-30474533.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2','2025-02-17 20:19:54','2025-02-17 20:14:59','2025-02-17 20:21:32',0),(8,'2025春季九人十足比赛',NULL,1,'2025-02-17 20:20:14','2025-02-17 20:20:14',NULL,23,23,5,'12222222222',1,'https://images.pexels.com/photos/30474533/pexels-photo-30474533.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2','2025-02-17 20:19:53','2025-02-17 20:15:20','2025-02-17 20:21:32',0),(9,'2025春季跳绳比赛',NULL,1,'2025-02-17 20:20:15','2025-02-17 20:20:16',NULL,45,22,5,'12222222222',1,'https://images.pexels.com/photos/30474533/pexels-photo-30474533.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2','2025-02-17 20:19:54','2025-02-17 20:15:32','2025-02-17 20:21:32',0),(10,'2025春季吟诗比赛',NULL,0,'2025-02-17 20:20:17','2025-02-17 20:20:17',NULL,23,12,8,'12222222222',1,'https://images.pexels.com/photos/30474533/pexels-photo-30474533.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2','2025-02-17 20:19:55','2025-02-17 20:15:47','2025-02-17 20:21:32',0),(11,'2025春季辩论赛','辩论赛',0,'2025-02-17 20:20:18','2025-02-17 20:20:19',NULL,34,0,8,'12222222222',1,'https://images.pexels.com/photos/30474533/pexels-photo-30474533.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2','2025-02-17 20:19:55','2025-02-17 20:15:55','2025-02-17 20:21:32',0),(12,'2025春季艾滋病防患',NULL,4,'2025-02-17 20:20:19','2025-02-17 20:20:20',NULL,30,0,1,'12222222222',1,'https://images.pexels.com/photos/30474533/pexels-photo-30474533.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2','2025-02-17 20:19:56','2025-02-17 20:16:19','2025-02-17 20:21:32',0);
/*!40000 ALTER TABLE `community_activity` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-27 10:49:12
-- ************************************************************
--
-- close fk
--
-- skip


-- MySQL dump 10.13  Distrib 5.7.35, for Linux (x86_64)
--
-- Host: 30.47.14.20    Database: property-management
-- ------------------------------------------------------
-- Server version	5.7.18-txsql-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cost`
--

DROP TABLE IF EXISTS `cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cost` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '费用id',
  `name` varchar(255) DEFAULT NULL COMMENT '费用名称',
  `type` int(11) NOT NULL COMMENT '费用类型id',
  `paymentMethod` tinyint(4) DEFAULT NULL COMMENT '支付方式（0现金，1支付宝，-1微信支付）',
  `amount` decimal(15,2) NOT NULL COMMENT '金额',
  `consumer` int(11) NOT NULL COMMENT '消费者id',
  `payee` int(11) DEFAULT NULL COMMENT '收款者id',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态（0未支付，1已支付，-1待审批）',
  `expenseTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '实际支付时间',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) DEFAULT '0' COMMENT '逻辑删除',
  `description` varchar(1024) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cost`
--

/*!40000 ALTER TABLE `cost` DISABLE KEYS */;
INSERT INTO `cost` VALUES (1,'测试新费用1',1,NULL,22.22,1,NULL,1,'2025-02-09 09:46:15','2025-02-09 09:46:15','2025-02-09 09:52:14',1,NULL),(2,'晚餐补贴',5,NULL,22.22,1,NULL,1,'2025-02-09 10:56:44','2025-02-09 10:56:44','2025-02-09 10:56:44',0,NULL),(3,'聚餐补贴',5,NULL,687.50,2,NULL,1,'2025-02-09 10:57:06','2025-02-09 10:57:06','2025-02-09 10:57:06',0,NULL),(4,'杭州出差',52,NULL,67.50,2,NULL,1,'2025-02-09 10:57:59','2025-02-09 10:57:59','2025-02-09 10:57:59',0,NULL);
/*!40000 ALTER TABLE `cost` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-27 10:49:13
-- ************************************************************
--
-- close fk
--
-- skip


-- MySQL dump 10.13  Distrib 5.7.35, for Linux (x86_64)
--
-- Host: 30.47.14.20    Database: property-management
-- ------------------------------------------------------
-- Server version	5.7.18-txsql-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cost_type`
--

DROP TABLE IF EXISTS `cost_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cost_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '费用类型id',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '费用类型父级id',
  `name` varchar(255) NOT NULL COMMENT '费用类型名称',
  `code` varchar(255) NOT NULL COMMENT '费用类型编码',
  `description` text COMMENT '费用类型描述',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cost_type`
--

/*!40000 ALTER TABLE `cost_type` DISABLE KEYS */;
INSERT INTO `cost_type` VALUES (1,0,'日常费用','DAILY_EXPENSES','日常支出费用','2025-02-09 10:41:58','2025-02-09 10:54:19',0),(2,0,'差旅费用','TRAVEL_EXPENSES','出差旅行费用','2025-02-09 10:44:06','2025-02-09 10:54:19',0),(3,0,'项目费用','PROJECT_EXPENSES','项目相关费用','2025-02-09 10:44:37','2025-02-09 10:54:19',0),(4,0,'财务费用','FINANCIAL_EXPENSES','财务相关费用','2025-02-09 10:45:58','2025-02-09 10:52:41',0),(5,1,'餐补费用','MEAL_ALLOWANCE','日常餐补费用','2025-02-09 10:47:26','2025-02-09 10:52:41',0),(6,1,'交通费用','TRANSPORTATION_COSTS','日常交通费用','2025-02-09 10:48:00','2025-02-09 10:51:44',0);
/*!40000 ALTER TABLE `cost_type` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-27 10:49:13
-- ************************************************************
--
-- close fk
--
-- skip


-- MySQL dump 10.13  Distrib 5.7.35, for Linux (x86_64)
--
-- Host: 30.47.14.20    Database: property-management
-- ------------------------------------------------------
-- Server version	5.7.18-txsql-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `express_order`
--

DROP TABLE IF EXISTS `express_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `express_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `pickupUser` int(11) DEFAULT NULL COMMENT '取件用户id',
  `userId` int(11) DEFAULT NULL COMMENT '订单用户id',
  `pickupCode` varchar(255) DEFAULT NULL COMMENT '取件码',
  `trackingNumber` varchar(255) NOT NULL COMMENT '快递单号',
  `expressCompany` int(11) NOT NULL COMMENT '快递公司',
  `publishTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态（待处理/已取件/已存放/已完成/已取消/异常）',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='快递订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `express_order`
--

/*!40000 ALTER TABLE `express_order` DISABLE KEYS */;
INSERT INTO `express_order` VALUES (1,NULL,1,NULL,'2025021719112692286511548289824',3,'2025-02-17 19:11:27',1,'2025-02-17 19:11:27','2025-02-17 19:49:06',0),(2,NULL,1,NULL,'20250217191502989103699e0fcab97',2,'2025-02-17 19:15:03',1,'2025-02-17 19:15:03','2025-02-17 19:44:27',0),(3,2,1,'12-2-1001','20250217191562989103699e0fcab97',5,'2025-02-17 19:56:08',2,'2025-02-17 19:56:08','2025-02-17 19:56:26',0),(4,3,2,'12-2-1002','20250217191502989103699e0fcab96',6,'2025-02-17 19:57:34',2,'2025-02-17 19:57:34','2025-02-17 19:57:34',0),(5,NULL,2,'12-2-1003','20250217191502989103699e0fcab10',4,'2025-02-17 19:58:12',3,'2025-02-17 19:58:22','2025-02-17 20:13:09',0),(6,3,5,'12-2-1004','20250217191502989103699e0fcab11',1,'2025-02-17 19:58:40',2,'2025-02-17 19:58:49','2025-02-17 20:11:57',0),(7,5,5,'13-1-2001','20250217191502989103699e0fcab12',2,'2025-02-17 20:01:31',2,'2025-02-17 20:01:31','2025-02-17 20:01:31',0),(8,NULL,5,'14-1-2001','20250217191502989103699e0fcab13',3,'2025-02-17 20:02:05',3,'2025-02-17 20:02:05','2025-02-17 20:11:57',0),(9,NULL,6,'15-2-3021','20250217191502989103699e0fcab14',3,'2025-02-17 20:02:44',3,'2025-02-17 20:02:44','2025-02-17 20:03:21',0),(10,NULL,7,'2-2-1001','20250217191502989103699e0fcab15',4,'2025-02-17 20:03:21',3,'2025-02-17 20:03:21','2025-02-17 20:03:21',0),(11,7,7,'24-4-1005','20250217191502989103699e0fcab16',7,'2025-02-17 20:04:20',2,'2025-02-17 20:04:20','2025-02-17 20:04:20',0);
/*!40000 ALTER TABLE `express_order` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-27 10:49:14
-- ************************************************************
--
-- close fk
--
-- skip


-- MySQL dump 10.13  Distrib 5.7.35, for Linux (x86_64)
--
-- Host: 30.47.14.20    Database: property-management
-- ------------------------------------------------------
-- Server version	5.7.18-txsql-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `type` tinyint(4) DEFAULT '0' COMMENT '消息类型',
  `content` text COMMENT '消息内容',
  `sendUser` int(11) NOT NULL DEFAULT '0' COMMENT '发送者id（系统默认0）',
  `receiveUser` int(11) NOT NULL COMMENT '接收者id',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（下单时间）',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='消息通知表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,4,'您的外卖下单成功，等待骑手接单。',0,1,0,'2025-02-17 17:32:10','2025-02-17 17:32:10',0),(2,4,'您的外卖开始派送，请耐心等候',0,1,0,'2025-02-17 17:58:30','2025-02-17 17:58:30',0),(3,4,'您的外卖已被骑手接单，请耐心等候',0,1,0,'2025-02-17 18:02:09','2025-02-17 18:02:09',0),(4,4,'您的外卖已送达，外卖柜编号：MT-001 请及时取走!',0,1,0,'2025-02-17 18:05:14','2025-02-17 18:05:14',0),(5,5,'您已成功报名新活动活动，请准时参加',0,1,0,'2025-02-17 18:38:57','2025-02-17 18:38:57',0),(6,4,'您的外卖下单成功，等待骑手接单。',0,1,0,'2025-02-17 20:09:01','2025-02-17 20:09:01',0),(7,4,'您的外卖下单成功，等待骑手接单。',0,3,0,'2025-02-17 20:09:28','2025-02-17 20:09:28',0),(8,4,'您的外卖下单成功，等待骑手接单。',0,6,0,'2025-02-17 20:10:46','2025-02-17 20:10:46',0),(9,4,'您的外卖下单成功，等待骑手接单。',0,8,0,'2025-02-17 20:10:58','2025-02-17 20:10:58',0),(10,4,'您的外卖下单成功，等待骑手接单。',0,5,0,'2025-02-17 20:11:37','2025-02-17 20:11:37',0),(11,4,'您的外卖下单成功，等待骑手接单。',0,5,0,'2025-02-17 20:12:10','2025-02-17 20:12:10',0);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-27 10:49:15
-- ************************************************************
--
-- close fk
--
-- skip


-- MySQL dump 10.13  Distrib 5.7.35, for Linux (x86_64)
--
-- Host: 30.47.14.20    Database: property-management
-- ------------------------------------------------------
-- Server version	5.7.18-txsql-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT '该权限的父id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `code` varchar(255) DEFAULT NULL COMMENT '权限编码',
  `type` int(11) DEFAULT '1' COMMENT '0代表菜单1权限',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) DEFAULT '0' COMMENT '0代表未删除，1代表已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (1,0,'用户认证','USER_AUTH',0,'2025-01-17 10:58:27','2025-01-17 16:05:15',0),(2,1,'用户登录','AUTH_LOGIN',1,'2025-01-17 14:33:43','2025-01-17 16:04:20',0),(3,1,'用户注册','AUTH_REGISTER',1,'2025-01-17 14:35:50','2025-01-17 16:04:20',0),(4,1,'用户登出','AUTH_LOGOUT',1,'2025-01-17 16:04:30','2025-01-17 16:06:57',0),(5,0,'用户管理','USER_MANAGEMENT',0,'2025-01-17 16:05:15','2025-01-17 16:14:32',0),(6,5,'查询所有用户','USER_GET_ALL',1,'2025-01-17 16:05:46','2025-01-17 16:14:32',0),(7,5,'新增用户','USER_ADD',1,'2025-01-17 16:06:15','2025-01-17 16:14:32',0),(8,5,'修改个人信息','USER_UPDATE_MYSELF',1,'2025-01-17 16:06:50','2025-01-17 16:06:57',0),(9,5,'修改用户信息','USER_UPDATE_USER_INFO',1,'2025-01-17 16:07:14','2025-01-17 16:10:20',0),(10,5,'修改个人密码','USER_UPDATE_MYSELF_PASSWORD',1,'2025-01-17 16:07:26','2025-01-17 16:10:20',0),(11,5,'重置用户密码','USER_RESET_PASSWORD',1,'2025-01-17 16:07:38','2025-01-17 16:10:20',0),(12,5,'导出所有用户信息','USER_EXPORT',1,'2025-01-17 16:07:50','2025-01-17 16:10:20',0),(13,5,'给用户添加角色','USER_ADD_ROLE_TO_USER',1,'2025-01-17 16:08:02','2025-01-17 16:10:20',0),(14,5,'查询单个用户','USER_GET_ONE',1,'2025-01-17 16:08:14','2025-01-17 16:10:20',0),(15,5,'删除用户','USER_DELETE_ONE',1,'2025-01-17 16:08:23','2025-01-17 16:10:20',0),(16,5,'查询用户角色','USER_GET_ROLE_BY_USER',1,'2025-01-17 16:08:33','2025-01-17 16:10:20',0),(17,5,'批量删除用户','USER_DELETE_BATCH',1,'2025-01-17 16:08:42','2025-01-17 16:10:20',0),(18,0,'角色管理','ROLE_MANAGEMENT',0,'2025-01-17 16:10:40','2025-01-17 16:14:32',0),(19,18,'获取所有角色','ROLE_GET_ALL',1,'2025-01-17 16:12:18','2025-01-17 16:14:32',0),(20,18,'新增角色','ROLE_ADD',1,'2025-01-17 16:12:18','2025-01-17 16:14:32',0),(21,18,'更新角色','ROLE_UPDATE',1,'2025-01-17 16:12:18','2025-01-17 16:14:32',0),(22,18,'给角色新增权限','ROLE_ADD_PERMISSION_TO_ROLE',1,'2025-01-17 16:12:18','2025-01-17 16:14:32',0),(23,18,'查询角色权限','ROLE_GET_PERMISSION',1,'2025-01-17 16:12:18','2025-01-17 16:14:32',0),(24,18,'删除角色','ROLE_DELETE_ONE',1,'2025-01-17 16:12:18','2025-01-17 16:14:32',0),(25,18,'批量删除角色','ROLE_DELETE_BATCH',1,'2025-01-17 16:12:18','2025-01-17 16:14:32',0),(26,0,'权限管理','PERMISSION_MANAGEMENT',0,'2025-01-17 16:15:11','2025-01-17 16:16:45',0),(27,26,'查询所有权限','PERMISSION_GET_ALL',1,'2025-01-17 16:16:01','2025-01-17 16:16:45',0),(28,26,'新增权限','PERMISSION_ADD',1,'2025-01-17 16:16:01','2025-01-17 16:16:45',0),(29,26,'编辑权限','PERMISSION_UPDATE',1,'2025-01-17 16:16:01','2025-01-17 16:16:45',0),(30,26,'删除权限','PERMISSION_DELETE_ONE',1,'2025-01-17 16:16:01','2025-01-17 16:16:45',0),(31,26,'批量删除权限','PERMISSION_DELETE_BATCH',1,'2025-01-17 16:16:01','2025-01-17 16:16:45',0),(32,5,'查询用户权限','USER_GET_PERMISSION_BY_USER',1,'2025-01-17 17:45:16','2025-01-17 17:45:16',0),(33,5,'获取个人信息','USER_GET_MYSELF_INFO',1,'2025-02-03 01:17:40','2025-02-03 01:17:40',0);
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-27 10:49:15
-- ************************************************************
--
-- close fk
--
-- skip


-- MySQL dump 10.13  Distrib 5.7.35, for Linux (x86_64)
--
-- Host: 30.47.14.20    Database: property-management
-- ------------------------------------------------------
-- Server version	5.7.18-txsql-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `roleName` varchar(255) DEFAULT NULL COMMENT '角色名',
  `remark` varchar(255) DEFAULT NULL COMMENT '角色含义',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (0,'admin','管理员','2025-01-16 10:16:35','2025-02-12 18:04:46',0),(1,'user','租客','2025-01-16 10:16:35','2025-02-12 18:05:04',0),(2,'updateRole','业主','2025-01-17 09:50:22','2025-02-12 18:05:04',0);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-27 10:49:16
-- ************************************************************
--
-- close fk
--
-- skip


-- MySQL dump 10.13  Distrib 5.7.35, for Linux (x86_64)
--
-- Host: 30.47.14.20    Database: property-management
-- ------------------------------------------------------
-- Server version	5.7.18-txsql-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `role_permissions`
--

DROP TABLE IF EXISTS `role_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permissions` (
  `rid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permissions`
--

/*!40000 ALTER TABLE `role_permissions` DISABLE KEYS */;
INSERT INTO `role_permissions` VALUES (1,1,'2025-01-17 14:34:01','2025-01-17 14:34:01',0),(1,2,'2025-01-17 14:34:13','2025-01-17 14:34:13',0),(1,3,'2025-01-17 14:35:55','2025-01-17 14:35:55',0),(1,4,'2025-01-17 17:49:26','2025-01-17 17:49:26',0),(1,8,'2025-01-17 17:52:13','2025-01-17 17:52:13',0),(1,10,'2025-01-17 17:52:13','2025-01-17 17:52:13',0),(1,16,'2025-01-17 17:52:13','2025-01-17 17:52:13',0),(1,32,'2025-01-17 17:52:13','2025-01-17 17:52:13',0),(1,5,'2025-01-17 17:53:02','2025-01-17 17:53:02',0),(1,18,'2025-01-17 17:53:02','2025-01-17 17:53:02',0),(1,23,'2025-01-17 17:53:02','2025-01-17 17:53:02',0),(0,1,'2025-01-17 17:53:32','2025-02-11 20:14:05',0),(0,2,'2025-01-17 17:53:32','2025-02-11 20:14:05',0),(0,3,'2025-01-17 17:53:32','2025-02-11 20:14:05',0),(0,4,'2025-01-17 17:53:32','2025-02-11 20:14:05',0),(0,5,'2025-01-17 17:53:32','2025-02-11 20:14:05',0),(0,6,'2025-01-17 17:53:32','2025-02-11 20:14:05',0),(0,7,'2025-01-17 17:53:32','2025-02-11 20:14:05',0),(0,8,'2025-01-17 17:53:32','2025-02-11 20:14:05',0),(0,9,'2025-01-17 17:53:32','2025-02-11 20:14:05',0),(0,10,'2025-01-17 17:53:32','2025-02-11 20:14:05',0),(0,11,'2025-01-17 17:53:32','2025-02-11 20:14:05',0),(0,12,'2025-01-17 17:53:32','2025-02-11 20:14:05',0),(0,13,'2025-01-17 17:53:32','2025-02-11 20:14:05',0),(0,14,'2025-01-17 17:53:32','2025-02-11 20:14:05',0),(0,15,'2025-01-17 17:53:32','2025-02-11 20:14:05',0),(0,16,'2025-01-17 17:54:12','2025-02-11 20:14:05',0),(0,17,'2025-01-17 17:54:12','2025-02-11 20:14:05',0),(0,18,'2025-01-17 17:54:12','2025-02-11 20:14:05',0),(0,19,'2025-01-17 17:54:12','2025-02-11 20:14:05',0),(0,20,'2025-01-17 17:54:12','2025-02-11 20:14:05',0),(0,21,'2025-01-17 17:54:12','2025-02-11 20:14:05',0),(0,22,'2025-01-17 17:54:12','2025-02-11 20:14:05',0),(0,23,'2025-01-17 17:54:12','2025-02-11 20:14:05',0),(0,24,'2025-01-17 17:54:12','2025-02-11 20:14:05',0),(0,25,'2025-01-17 17:54:12','2025-02-11 20:14:05',0),(0,26,'2025-01-17 17:54:12','2025-02-11 20:14:05',0),(0,27,'2025-01-17 17:54:12','2025-02-11 20:14:05',0),(0,28,'2025-01-17 17:54:12','2025-02-11 20:14:05',0),(0,29,'2025-01-17 17:54:12','2025-02-11 20:14:05',0),(0,30,'2025-01-17 17:54:12','2025-02-11 20:14:05',0),(0,31,'2025-01-17 17:54:21','2025-02-11 20:14:05',0),(0,32,'2025-01-17 17:54:21','2025-02-11 20:14:05',0),(1,33,'2025-02-03 01:18:24','2025-02-03 01:18:24',0),(0,33,'2025-02-03 01:18:27','2025-02-11 20:14:05',0),(2,33,'2025-02-03 01:18:30','2025-02-11 20:30:51',0);
/*!40000 ALTER TABLE `role_permissions` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-27 10:49:16
-- ************************************************************
--
-- close fk
--
-- skip


-- MySQL dump 10.13  Distrib 5.7.35, for Linux (x86_64)
--
-- Host: 30.47.14.20    Database: property-management
-- ------------------------------------------------------
-- Server version	5.7.18-txsql-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `role_user`
--

DROP TABLE IF EXISTS `role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_user` (
  `uid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_user`
--

/*!40000 ALTER TABLE `role_user` DISABLE KEYS */;
INSERT INTO `role_user` VALUES (0,0,'2025-02-14 17:28:07','2025-02-14 17:28:07',0),(1,0,'2025-02-21 17:17:49','2025-02-21 17:17:49',0),(3,0,'2025-02-21 17:18:30','2025-02-21 17:18:30',0),(15,1,'2025-02-22 15:49:34','2025-02-22 15:49:34',0),(16,0,'2025-02-22 15:56:34','2025-02-22 15:56:34',0),(2,1,'2025-02-24 18:31:40','2025-02-24 18:31:40',0),(4,1,'2025-02-24 18:32:33','2025-02-24 18:32:33',0),(5,1,'2025-02-24 18:32:33','2025-02-24 18:32:33',0),(6,1,'2025-02-24 18:32:33','2025-02-24 18:32:33',0);
/*!40000 ALTER TABLE `role_user` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-27 10:49:17
-- ************************************************************
--
-- close fk
--
-- skip


-- MySQL dump 10.13  Distrib 5.7.35, for Linux (x86_64)
--
-- Host: 30.47.14.20    Database: property-management
-- ------------------------------------------------------
-- Server version	5.7.18-txsql-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `takeout_pickup_record`
--

DROP TABLE IF EXISTS `takeout_pickup_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `takeout_pickup_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `orderNumber` varchar(50) NOT NULL COMMENT '外卖订单号',
  `userId` int(11) NOT NULL COMMENT '收件人id',
  `address` varchar(255) NOT NULL COMMENT '外卖地址',
  `courierId` int(11) DEFAULT NULL COMMENT '快递员id',
  `platform` tinyint(4) DEFAULT NULL COMMENT '外卖平台',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态(已下单/骑手已接单/派送中/已送达/已完成/已取消/异常)',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注信息',
  `cabinetNumber` int(11) DEFAULT NULL COMMENT '柜号',
  `notifyTime` datetime DEFAULT NULL COMMENT '送达通知时间',
  `estimatedDeliveryTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '预计送达时间',
  `pickupTime` datetime DEFAULT NULL COMMENT '领取时间',
  `timeoutTime` datetime DEFAULT NULL COMMENT '外卖超时时间',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='外卖代收记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `takeout_pickup_record`
--

/*!40000 ALTER TABLE `takeout_pickup_record` DISABLE KEYS */;
INSERT INTO `takeout_pickup_record` VALUES (1,'20250217173210144144106b9f5c1cd',1,'新地址',2,2,3,NULL,1,'2025-02-17 18:05:14','2025-02-17 17:32:10',NULL,NULL,'2025-02-17 17:32:10','2025-02-17 18:08:24',0),(2,'20250217200901493679847edeb7d19',1,'上海闵行区东兰路288号',2,1,2,NULL,NULL,NULL,'2025-02-17 20:09:01',NULL,NULL,'2025-02-17 20:09:01','2025-02-17 20:09:01',0),(3,'20250217200928658846021188201a5',3,'上海闵行区东兰路288号',2,2,2,NULL,NULL,NULL,'2025-02-17 20:09:28',NULL,NULL,'2025-02-17 20:09:28','2025-02-17 20:33:06',0),(4,'202502172010459958628749fc70b9a',6,'上海闵行区东兰路288号',2,1,4,NULL,4,NULL,'2025-02-17 20:10:45',NULL,NULL,'2025-02-17 20:10:45','2025-02-17 20:10:45',0),(5,'20250217201058880664822737e354d',8,'上海闵行区东兰路288号',3,2,4,NULL,4,NULL,'2025-02-17 20:10:58',NULL,NULL,'2025-02-17 20:10:58','2025-02-17 20:10:58',0),(6,'202502172011378668056977ef73333',5,'上海闵行区东兰路288号',5,1,1,NULL,NULL,NULL,'2025-02-17 20:11:37',NULL,NULL,'2025-02-17 20:11:37','2025-02-17 20:11:37',0),(7,'20250217201210808455976da2d4abe',5,'上海闵行区东兰路288号',5,1,1,NULL,NULL,NULL,'2025-02-17 20:12:10',NULL,NULL,'2025-02-17 20:12:10','2025-02-17 20:12:10',0),(8,'20250217173210144144106b9f5c1cd',1,'地址1',3,0,4,'备注1',5,'2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:58:38','2025-02-17 20:28:38','2025-02-17 20:33:06',0),(9,'20250217173210144144106b9f5c1ce',2,'地址2',5,1,3,'备注2',6,'2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:58:38','2025-02-17 20:28:38','2025-02-17 20:33:06',0),(10,'20250217173210144144106b9f5c1cf',3,'地址3',5,2,3,'备注3',7,'2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:58:38','2025-02-17 20:28:38','2025-02-17 20:33:06',0),(11,'20250217173210144144106b9f5c1d0',5,'地址4',5,3,4,'备注4',8,'2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:58:38','2025-02-17 20:28:38','2025-02-17 20:33:06',0),(12,'20250217173210144144106b9f5c1d1',6,'地址5',5,2,3,'备注5',9,'2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:58:38','2025-02-17 20:28:38','2025-02-17 20:33:06',0),(13,'20250217173210144144106b9f5c1d2',7,'地址6',5,1,4,'备注6',10,'2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:58:38','2025-02-17 20:28:38','2025-02-17 20:33:06',0),(14,'20250217173210144144106b9f5c1d3',8,'地址7',5,2,4,'备注7',11,'2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:58:38','2025-02-17 20:28:38','2025-02-17 20:33:06',0),(15,'20250217173210144144106b9f5c1d4',1,'地址8',5,0,3,'备注8',12,'2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:58:38','2025-02-17 20:28:38','2025-02-17 20:33:06',0),(16,'20250217173210144144106b9f5c1d5',2,'地址9',5,2,4,'备注9',13,'2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:58:38','2025-02-17 20:28:38','2025-02-17 20:33:06',0),(17,'20250217173210144144106b9f5c1d6',3,'地址10',5,1,3,'备注10',14,'2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:58:38','2025-02-17 20:28:38','2025-02-17 20:33:06',0),(18,'20250217173210144144106b9f5c1d7',5,'地址11',5,2,3,'备注11',15,'2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:58:38','2025-02-17 20:28:38','2025-02-17 20:33:06',0),(19,'20250217173210144144106b9f5c1d8',6,'地址12',5,3,3,'备注12',16,'2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:58:38','2025-02-17 20:28:38','2025-02-17 20:33:06',0),(20,'20250217173210144144106b9f5c1d9',7,'地址13',5,2,3,'备注13',17,'2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:58:38','2025-02-17 20:28:38','2025-02-17 20:33:06',0),(21,'20250217173210144144106b9f5c1da',8,'地址14',5,1,4,'备注14',18,'2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:58:38','2025-02-17 20:28:38','2025-02-17 20:33:06',0),(22,'20250217173210144144106b9f5c1db',1,'地址15',5,0,4,'备注15',19,'2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:58:38','2025-02-17 20:28:38','2025-02-17 20:33:06',0),(23,'20250217173210144144106b9f5c1dc',2,'地址16',5,0,4,'备注16',20,'2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:58:38','2025-02-17 20:28:38','2025-02-17 20:33:06',0),(24,'20250217173210144144106b9f5c1dd',3,'地址17',1,0,4,'备注17',5,'2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:58:38','2025-02-17 20:28:38','2025-02-17 20:33:06',0),(25,'20250217173210144144106b9f5c1df',6,'地址19',3,1,4,'备注19',7,'2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:58:38','2025-02-17 20:28:38','2025-02-17 20:33:06',0),(26,'20250217173210144144106b9f5c1e0',7,'地址20',4,2,4,'备注20',8,'2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:28:38','2025-02-17 20:58:38','2025-02-17 20:28:38','2025-02-17 20:33:06',0);
/*!40000 ALTER TABLE `takeout_pickup_record` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-27 10:49:17
-- ************************************************************
--
-- close fk
--
-- skip


-- MySQL dump 10.13  Distrib 5.7.35, for Linux (x86_64)
--
-- Host: 30.47.14.20    Database: property-management
-- ------------------------------------------------------
-- Server version	5.7.18-txsql-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `signature` varchar(255) DEFAULT NULL COMMENT '签名',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别（1男，0女）',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `nickName` varchar(255) DEFAULT NULL COMMENT '昵称',
  `enabled` int(11) DEFAULT '1' COMMENT '账户是否可用',
  `accountNoExpired` int(11) DEFAULT '1' COMMENT '账户是否过期',
  `credentialsNoExpired` int(11) DEFAULT '1' COMMENT '密码是否过期',
  `accountNoLocked` int(11) DEFAULT '1' COMMENT '是否被锁定',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) DEFAULT '0' COMMENT '逻辑删除',
  `description` varchar(1024) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (0,'system',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'系统',1,1,1,1,'2025-02-14 17:26:37','2025-02-14 17:26:46',0,NULL),(1,'yannqing','$2a$10$akT3pwOeS6XpNC.Ktxvh5ONX9no1qOhjlmkB1nan2B.6QCirle2gy','sda99','17766470888','67121851@qq.com',4,'233',1,'https://cdn.pixabay.com/photo/2021/08/24/15/13/boy-6570938_1280.jpg','yannqing000',1,1,1,1,'2025-01-10 15:58:59','2025-02-22 15:50:01',0,'33'),(2,'testtest1','$2a$10$g9DYqJ3RNy/Hfd67XRHlAuNLpU6ngUXXTgvpLFYLA/6hqZz4GlKtO','null','null','null',NULL,NULL,NULL,'https://cdn.pixabay.com/photo/2022/01/27/03/06/samurai-6970968_1280.png','newNickName0000',1,1,1,1,'2025-01-15 09:48:38','2025-02-24 18:27:56',0,NULL),(3,'test2test2','$2a$10$x98PDytpFNmjN1wr/gq35um1X9FoRDOk2PnwpAPSwfC8h8foSn1Fy','nusdbc','null','null',NULL,NULL,NULL,'https://img.520wangming.com/uploads/allimg/2023092411/x5qfghi3evj.jpg','pig',1,1,1,1,'2025-01-16 16:51:06','2025-02-21 20:08:30',0,NULL),(4,'newNickName','$2a$10$d5kuJXpkhWM.hBPzsBWLIOQhgVj5HQyXW9JAxPdx/ANEP.zgPVQ76','t63r34t','trq78t','rtf9q8tr',0,'ttr7w6rt',1,'https://img.520wangming.com/uploads/allimg/2023092411/5bpnckl2ktq.jpg','7rgt',1,1,1,1,'2025-01-31 18:31:23','2025-02-24 18:27:56',0,'rtew'),(5,'tuxiu22','$2a$10$BCQLx6YB03k2kXXJpkFs2OJnKMVxafbLDhvl5VjeEux7Hx/KsGRKa',NULL,NULL,NULL,NULL,NULL,NULL,'https://img.520wangming.com/uploads/allimg/2023092410/ug2qkmj4k0w.jpg','1212',1,1,1,1,'2025-01-31 18:33:59','2025-02-24 18:27:56',0,NULL),(6,'tuxiu23','$2a$10$005RpwmDcJxzS6QGahN2pOIXvpMgdOcZotQAs9ghtC52jyjYv0Ux6',NULL,NULL,NULL,NULL,NULL,NULL,'https://img.520wangming.com/uploads/allimg/2023092411/53qndu03ezf.jpg','hfsdc',1,1,1,1,'2025-01-31 18:46:07','2025-02-24 18:27:56',0,NULL),(7,'tuxiu24','$2a$10$7CBVFYVc9egXXm3qxRREeOh9CaY8JFyu5Qg64oEhMHmpEedPrrgGu',NULL,NULL,NULL,NULL,NULL,NULL,'https://img.520wangming.com/uploads/allimg/2023092410/pz5vmgpd4xp.jpg','efdsjc',1,1,1,1,'2025-01-31 18:46:47','2025-02-21 20:08:41',1,NULL),(8,'testtest2','$2a$10$hX2cLy9QFW40brxJDj/TgOODsVAnXD/HYKj9uBM5LagUP/B6Qdabm','地址','电话',NULL,0,NULL,0,'https://img.520wangming.com/uploads/allimg/2023092410/mrvltjxzvla.jpg','testtest1昵称',1,1,1,1,'2025-02-08 09:15:22','2025-02-21 20:08:42',1,NULL),(9,'test11zk','$2a$10$dBkKFhdsrHL78qVo3YLxQOfsegtsvBOjEqAV9Az6TmMKCT.YXifPO',NULL,NULL,NULL,NULL,NULL,NULL,'https://img.520wangming.com/uploads/allimg/2023092411/xouxo2chll2.jpg','dsdv',1,1,1,1,'2025-02-09 21:00:30','2025-02-21 20:08:43',1,NULL),(10,'zk0000000','$2a$10$wh5ZkfePPo0aCr0o8L3Bg.Ly9a45gKVVrrA6679LDAY8ZC1H9ICh2',NULL,NULL,NULL,NULL,NULL,NULL,'https://img.520wangming.com/uploads/allimg/2023092411/5spdokgsr3b.jpg','njksdc',1,1,1,1,'2025-02-11 20:22:26','2025-02-21 20:08:44',1,NULL),(11,'yz000000','$2a$10$PaUmh8a/zVx.utfTYlxx3uUr7GVlcR6P5AaAu3M0dF44iMKCRoz1K',NULL,NULL,NULL,NULL,NULL,NULL,'https://img.520wangming.com/uploads/allimg/2023092410/e1qpczxivyl.jpg','sdnkksjzdv',1,1,1,1,'2025-02-11 20:23:15','2025-02-21 20:08:45',1,NULL),(12,'zk0000002','$2a$10$h6aB5XNUfJIgrfjVdxvYWeOtVK66HznOgQ6NYnxMzVN0hzwK.2i26','湖州市','1356363636',NULL,NULL,NULL,NULL,'https://img.520wangming.com/uploads/allimg/2023092411/bxblp13gnme.jpg','刘璋',1,1,1,1,'2025-02-12 14:09:00','2025-02-21 20:08:45',1,NULL),(14,'princess','$2a$10$Uc6KYayhgj.CnTfHYKToKeKtxSw.0SVDFIm8KTfzuUJevZE.n6Mry',NULL,'13333333333','676767@qq.com',NULL,NULL,NULL,'https://img.520wangming.com/uploads/allimg/2023092411/2hnrlgxqz2l.jpg','princess',1,1,1,1,'2025-02-21 15:17:09','2025-02-21 20:08:47',1,NULL),(15,'wanggang','$2a$10$AmWdlF8A1QtFm.r5zdGcDe2wV5SL7KXk9310N43k6.jwe2fNcauy2','安吉县','13693242896','djsn@11',-3,'dsjkld',0,'','王刚',1,1,1,1,'2025-02-22 15:49:34','2025-02-22 15:51:43',0,'dws'),(16,'gl642897','$2a$10$bik3B6dlTND.rufPLxU2zObnEL1no39gFtS1CRwQcrhRDBgy14iP6','362837','36876','3868',361287,'462',0,'','6127836',1,1,1,1,'2025-02-22 15:56:34','2025-02-22 15:56:34',0,'4639c82');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-27 10:49:18
-- ************************************************************
--
-- close fk
--
-- skip


-- MySQL dump 10.13  Distrib 5.7.35, for Linux (x86_64)
--
-- Host: 30.47.14.20    Database: property-management
-- ------------------------------------------------------
-- Server version	5.7.18-txsql-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user_user`
--

DROP TABLE IF EXISTS `user_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_user` (
  `pid` int(11) DEFAULT NULL COMMENT '父id',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) DEFAULT '0' COMMENT '逻辑删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_user`
--

/*!40000 ALTER TABLE `user_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_user` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-27 10:49:18
