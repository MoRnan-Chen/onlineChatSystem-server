-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: online_chat_system
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `add_friend`
--

create database online_chat_system;
DROP TABLE IF EXISTS `add_friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `add_friend` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `from_id` bigint NOT NULL,
  `to_id` bigint NOT NULL,
  `state` int NOT NULL DEFAULT '0' COMMENT '0:待处理，1：已过期，2：同意申请，3：拒绝',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `add_friend`
--

LOCK TABLES `add_friend` WRITE;
/*!40000 ALTER TABLE `add_friend` DISABLE KEYS */;
INSERT INTO `add_friend` VALUES (1,1,14,0,'2024-12-10 14:31:15','2024-12-10 14:31:15');
/*!40000 ALTER TABLE `add_friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_list`
--

DROP TABLE IF EXISTS `friend_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friend_list` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `friend_id` bigint NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `friend_list_user_id_fk` (`user_id`),
  KEY `friend_list_user_id_fk2` (`friend_id`),
  CONSTRAINT `friend_list_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `friend_list_user_id_fk2` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_list`
--

LOCK TABLES `friend_list` WRITE;
/*!40000 ALTER TABLE `friend_list` DISABLE KEYS */;
INSERT INTO `friend_list` VALUES (1,1,4,'2024-12-10 11:03:54'),(2,4,1,'2024-12-10 11:03:54'),(3,1,5,'2024-12-10 11:03:54'),(4,5,1,'2024-12-10 11:03:54'),(5,1,6,'2024-12-10 11:03:54'),(6,6,1,'2024-12-10 11:03:54'),(7,1,7,'2024-12-10 11:03:54'),(8,7,1,'2024-12-10 11:03:54'),(9,1,9,'2024-12-10 11:03:54'),(10,9,1,'2024-12-10 11:03:54'),(11,1,11,'2024-12-10 11:03:54'),(12,11,1,'2024-12-10 11:03:54');
/*!40000 ALTER TABLE `friend_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '群id',
  `group_name` varchar(32) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `group_desc` varchar(256) DEFAULT NULL,
  `group_pic` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `group_pk2` (`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='群聊表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_members`
--

DROP TABLE IF EXISTS `group_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_members` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_id` bigint NOT NULL,
  `member_id` bigint NOT NULL,
  `role` int NOT NULL DEFAULT '0' /*!80023 INVISIBLE */ COMMENT '0表示普通用户，1表示管理员，2表示群主',
  PRIMARY KEY (`id`),
  KEY `group_members_group_id_fk` (`group_id`),
  KEY `group_members_user_id_fk` (`member_id`),
  CONSTRAINT `group_members_group_id_fk` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`),
  CONSTRAINT `group_members_user_id_fk` FOREIGN KEY (`member_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_members`
--

LOCK TABLES `group_members` WRITE;
/*!40000 ALTER TABLE `group_members` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_message`
--

DROP TABLE IF EXISTS `group_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `message` varchar(512) NOT NULL,
  `from_id` bigint NOT NULL,
  `group_id` bigint NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `group_message_group_id_fk` (`group_id`),
  KEY `group_message_user_id_fk` (`from_id`),
  CONSTRAINT `group_message_group_id_fk` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`),
  CONSTRAINT `group_message_user_id_fk` FOREIGN KEY (`from_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_message`
--

LOCK TABLES `group_message` WRITE;
/*!40000 ALTER TABLE `group_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `single_message`
--

DROP TABLE IF EXISTS `single_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `single_message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(512) NOT NULL,
  `from_id` bigint NOT NULL,
  `to_id` bigint NOT NULL,
  `type` int NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `single_message`
--

LOCK TABLES `single_message` WRITE;
/*!40000 ALTER TABLE `single_message` DISABLE KEYS */;
INSERT INTO `single_message` VALUES (1,'hello',1,4,0,'2024-11-10 20:49:02'),(2,'hi',4,1,0,'2024-11-10 20:50:06'),(3,'how are you',1,4,0,'2024-11-10 20:50:15'),(4,'I\'m fine,thank you',4,1,0,'2024-11-10 20:50:52'),(5,'are you doing',4,1,0,'2024-11-10 20:58:15'),(6,'I\'m watching TV',1,4,0,'2024-11-10 20:58:43'),(7,'GoodBye',4,1,0,'2024-11-10 20:59:10'),(8,'ByeBye',1,4,0,'2024-11-10 20:59:19'),(9,'good afternoon',11,1,0,'2024-11-10 21:03:46'),(10,'good night',1,11,0,'2024-11-10 21:05:27'),(11,'11',1,11,0,'2024-11-10 22:03:03'),(12,'hi',1,11,0,'2024-11-10 22:23:31'),(13,'hello',1,11,0,'2024-11-10 22:25:18'),(14,'含含糊糊',1,11,0,'2024-11-10 22:28:31'),(15,'hi',4,1,0,'2024-11-10 22:35:55'),(16,'ok',4,1,0,'2024-11-10 22:36:09'),(17,'hi',5,1,0,'2024-11-10 22:40:37'),(18,'函数',6,1,0,'2024-11-10 22:41:41'),(19,'good morning!😀',1,4,0,'2024-11-15 11:18:03'),(20,'123😶',11,1,0,'2024-11-28 11:40:46'),(21,'go!',1,4,0,'2024-12-01 20:26:21'),(22,'偷偷？😀',1,4,0,'2024-12-03 10:48:16');
/*!40000 ALTER TABLE `single_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `email` varchar(128) DEFAULT NULL,
  `user_pic` varchar(128) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `sex` varchar(8) NOT NULL DEFAULT '未知',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_pk2` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'MoRan','$2a$10$xVjvDu1Te92RFGrAuWTdauJWXOGKVae81eLllmOoRUF/0Zmf9cmsK','3072740995@qq.com','https://online-chat-system.oss-cn-guangzhou.aliyuncs.com/2890b51b-a111-4df5-831d-50ae49538270.jpg','2024-10-18 22:34:53','2024-11-30 14:13:12','男'),(4,'wlt','$2a$10$FeyvOSlKvcc.zmd5f.IPxeINLtqeS2XV3TSzWw68Y9AegjUydrCSC','18312189793@qq.com','https://online-chat-system.oss-cn-guangzhou.aliyuncs.com/d2d76bc4-0d85-436c-9c70-162a10a0b3d9.jpg','2024-10-22 15:49:57','2024-11-12 21:18:54','未知'),(5,'admin','$2a$10$RU6V6bLoT.5Sciw/qIbJDOZ872meVdu6rAzYP4EdPCNtBNpAXPHA2',NULL,'https://online-chat-system.oss-cn-guangzhou.aliyuncs.com/b786ce93-a63b-4841-9700-875e6965c28b.jpg','2024-10-26 20:34:10','2024-11-15 16:02:30','未知'),(6,'cph','$2a$10$aMsa5RACoxPozNx75D9lSeIFA5cidCj1ZfGT9KchajeNf1BH5UsvC',NULL,NULL,'2024-10-26 20:35:44','2024-10-26 20:35:44','未知'),(7,'cmr','$2a$10$J.9HhLHjN1dC5h4FEdAIZeJcOSAk.vLyXgxqQsbjHTG2Wc3gIcsfy',NULL,NULL,'2024-10-26 20:37:53','2024-10-26 20:37:53','未知'),(8,'zyy','$2a$10$RYS0he10Ex/i/cTxlJv6..aXlei.kjNkLqH91AlLyjv.np0XTB4g6',NULL,NULL,'2024-10-26 20:39:44','2024-10-26 20:39:44','未知'),(9,'lc','$2a$10$mNukTGwLLDXHEmzK2VyOfOVcvz6X/9/q6q9LdUG5eko0WQTeAktXe',NULL,NULL,'2024-10-26 20:56:56','2024-10-26 20:56:56','未知'),(10,'wxx','$2a$10$Ug0RJfWQLepHsKp1/w7IoOgxI9tbSLZdp70sUfGkaplp.BDundwWy',NULL,NULL,'2024-10-26 21:02:50','2024-10-26 21:02:50','未知'),(11,'lcc','$2a$10$gsaoCRx0HBhVgOX97fsFrO5lNrTrsn1/gXjKFmxb9aLxvvwzIkVX.',NULL,'https://online-chat-system.oss-cn-guangzhou.aliyuncs.com/4a85b135-225b-481e-a3e9-86d512098c0c.jpg','2024-10-26 21:05:05','2024-11-10 21:18:55','未知'),(12,'cjy','$2a$10$2dgQOPVvQjKj65ptV8S/XeqpYDmwfQYEyWitC8NR7TiJ86OPgyiGq',NULL,NULL,'2024-10-27 20:21:34','2024-10-27 20:21:34','未知'),(13,'ych','$2a$10$4rMK2mRLhAhzysXaR7J9UujH0bByyS3WVqs1PwJ9Wt19VFLrO4Uja',NULL,NULL,'2024-10-27 20:36:58','2024-10-27 20:36:58','未知'),(14,'root','$2a$10$SbRbFRvbXfi0jqqSNFzgSuGU.6hgAeE3y1oiudKwPjDC1Eu0SAxle',NULL,NULL,'2024-10-27 20:40:18','2024-10-27 20:40:18','未知');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-11 21:50:05
