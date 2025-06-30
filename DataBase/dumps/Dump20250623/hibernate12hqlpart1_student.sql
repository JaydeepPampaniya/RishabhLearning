-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: hibernate12hqlpart1
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `age` int NOT NULL,
  `rollNo` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rollNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (88,1,'name 1'),(25,2,'name 2'),(48,3,'name 3'),(51,4,'name 4'),(53,5,'name 5'),(3,6,'name 6'),(51,7,'name 7'),(93,8,'name 8'),(61,9,'name 9'),(54,10,'name 10'),(62,11,'name 11'),(71,12,'name 12'),(69,13,'name 13'),(84,14,'name 14'),(71,15,'name 15'),(70,16,'name 16'),(39,17,'name 17'),(81,18,'name 18'),(52,19,'name 19'),(52,20,'name 20'),(11,21,'name 21'),(92,22,'name 22'),(35,23,'name 23'),(31,24,'name 24'),(53,25,'name 25'),(87,26,'name 26'),(11,27,'name 27'),(89,28,'name 28'),(34,29,'name 29'),(68,30,'name 30'),(26,31,'name 31'),(0,32,'name 32'),(78,33,'name 33'),(91,34,'name 34'),(18,35,'name 35'),(12,36,'name 36'),(93,37,'name 37'),(9,38,'name 38'),(51,39,'name 39'),(91,40,'name 40'),(63,41,'name 41'),(65,42,'name 42'),(18,43,'name 43'),(28,44,'name 44'),(15,45,'name 45'),(0,46,'name 46'),(32,47,'name 47'),(1,48,'name 48'),(35,49,'name 49');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-23 10:37:46
