-- MySQL dump 10.13  Distrib 8.0.34, for Linux (x86_64)
--
-- Host: localhost    Database: lego
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `lego`
--

DROP TABLE IF EXISTS `lego`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lego` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lego`
--

LOCK TABLES `lego` WRITE;
/*!40000 ALTER TABLE `lego` DISABLE KEYS */;
INSERT INTO `lego` VALUES (1,'');
/*!40000 ALTER TABLE `lego` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `legosetting`
--

DROP TABLE IF EXISTS `legosetting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `legosetting` (
  `id` int NOT NULL AUTO_INCREMENT,
  `speed` int NOT NULL,
  `direction` int NOT NULL COMMENT '0=forward, 1=backward',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `legoid` int DEFAULT NULL,
  `running` tinyint(1) NOT NULL DEFAULT '1',
  `turn` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `legoid` (`legoid`),
  CONSTRAINT `legosetting_ibfk_1` FOREIGN KEY (`legoid`) REFERENCES `lego` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `legosetting`
--

LOCK TABLES `legosetting` WRITE;
/*!40000 ALTER TABLE `legosetting` DISABLE KEYS */;
INSERT INTO `legosetting` VALUES (1,26,0,'2024-04-28 20:45:28',1,1,0),(2,600,0,'2024-04-29 20:00:40',1,1,0),(3,600,0,'2024-04-29 20:07:21',1,1,0),(4,50,0,'2024-04-29 20:11:27',1,1,0),(5,200,0,'2024-04-29 20:11:45',1,1,0),(6,100,0,'2024-04-29 20:25:57',1,1,0),(7,150,0,'2024-04-29 20:26:02',1,1,0),(8,150,0,'2024-04-29 20:26:09',1,1,0),(9,200,0,'2024-04-29 20:26:17',1,1,0),(10,100,0,'2024-04-29 20:39:39',1,1,0),(11,150,0,'2024-04-29 20:39:46',1,1,0),(12,150,1,'2024-04-29 20:39:51',1,1,0),(13,400,0,'2024-04-29 23:51:44',1,1,0),(14,300,1,'2024-04-30 00:36:14',1,1,0),(15,0,0,'2024-04-30 12:05:04',1,1,0),(16,100,0,'2024-04-30 12:05:09',1,1,0),(17,0,0,'2024-04-30 12:09:21',1,1,0),(18,0,0,'2024-04-30 12:09:28',1,1,0),(19,122,0,'2024-04-30 12:09:38',1,1,0),(20,0,0,'2024-04-30 12:09:42',1,1,0),(21,50,0,'2024-05-01 20:38:36',1,1,50),(22,60,0,'2024-05-01 20:38:56',1,0,70),(23,60,0,'2024-05-01 20:39:00',1,1,70),(24,40,0,'2024-05-01 20:40:59',1,0,50),(25,40,0,'2024-05-01 20:41:04',1,1,50),(26,40,0,'2024-05-01 20:41:09',1,1,50),(27,100,0,'2024-05-01 20:41:38',1,1,100),(28,100,1,'2024-05-01 20:41:47',1,1,100),(29,100,1,'2024-05-01 20:42:48',1,1,100),(30,100,1,'2024-05-01 20:42:50',1,0,100),(31,50,0,'2024-05-01 20:42:58',1,1,50),(32,50,0,'2024-05-01 20:43:04',1,0,50),(33,80,0,'2024-05-01 20:43:10',1,0,60),(34,80,1,'2024-05-01 20:43:17',1,1,60),(35,80,1,'2024-05-01 20:43:19',1,0,60),(36,80,1,'2024-05-01 20:43:47',1,0,60),(37,0,0,'2024-05-01 20:43:53',1,0,0),(38,0,0,'2024-05-01 20:43:58',1,1,0),(39,0,0,'2024-05-01 20:44:02',1,1,0),(40,0,0,'2024-05-01 20:44:05',1,0,0),(41,50,0,'2024-05-01 21:04:07',1,1,50),(42,50,0,'2024-05-01 21:04:16',1,1,50),(43,100,0,'2024-05-01 21:10:52',1,1,100),(44,100,0,'2024-05-01 21:16:36',1,1,100),(45,50,0,'2024-05-01 21:16:45',1,0,50),(46,50,0,'2024-05-01 21:16:50',1,1,50),(47,50,0,'2024-05-01 21:16:58',1,1,50),(48,50,0,'2024-05-01 21:16:58',1,1,50),(49,50,0,'2024-05-01 21:16:59',1,1,50),(50,50,0,'2024-05-01 21:16:59',1,1,50),(51,50,0,'2024-05-01 21:22:54',1,1,50),(52,50,0,'2024-05-01 21:22:55',1,1,50),(53,50,0,'2024-05-01 21:23:00',1,1,55),(54,55,0,'2024-05-01 21:23:08',1,1,55),(55,55,0,'2024-05-01 21:23:16',1,1,70),(56,55,0,'2024-05-01 21:34:01',1,1,70),(57,55,0,'2024-05-01 21:34:01',1,1,70),(58,70,0,'2024-05-01 21:34:05',1,1,70),(59,70,0,'2024-05-01 21:55:39',1,1,70),(60,70,0,'2024-05-01 21:55:42',1,1,70),(61,77,0,'2024-05-01 21:55:46',1,1,70),(62,77,1,'2024-05-01 21:55:51',1,1,70),(63,50,0,'2024-05-02 00:45:15',1,1,50),(64,70,0,'2024-05-02 00:45:29',1,1,55),(65,100,0,'2024-05-02 00:51:00',1,1,55),(66,40,0,'2024-05-02 01:57:26',1,0,1),(67,500,0,'2024-05-02 02:05:22',1,0,-1),(68,500,1,'2024-05-02 02:07:04',1,1,1),(69,283,1,'2024-05-02 13:04:17',1,1,1),(70,221,0,'2024-05-02 13:54:34',1,1,-1),(71,189,0,'2024-05-02 14:17:09',1,0,-1),(72,444,0,'2024-05-02 14:21:10',1,0,-1),(73,281,0,'2024-05-07 21:41:18',1,1,0),(74,195,0,'2024-05-12 22:34:11',1,1,0);
/*!40000 ALTER TABLE `legosetting` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-12 20:11:59
