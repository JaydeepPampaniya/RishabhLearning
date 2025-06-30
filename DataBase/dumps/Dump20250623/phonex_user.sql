-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: phonex
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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `role_id` int DEFAULT NULL,
  `created_time` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `FK_user_roles` (`role_id`),
  CONSTRAINT `FK_user_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'owner@gmail.com','$2a$12$lZAakA25Nm6tQifg5n378e00ZKqTdE9W4pzRep3ipwAlPead9F0F2',3,'2025-02-16 09:54:12'),(2,'admin@gmail.com','$2a$12$dDq.8z5EJW/Ng48k5Y1kCeR83acoNt8sBWeM10/ZgxvpHPKI.NZyq',1,'2025-02-16 09:54:47'),(14,'aashish@gmail.com','$2a$12$RwCxE/0w52GQSv8nZLJx8uE6Jz5Fj3YstJqEKToWjpOuwC.8m32n2',2,'2025-02-24 12:08:17'),(15,'Kapil@gmail.com','$2a$12$jSxhvjEnN48fAhVt6PJ5A.fzTm8XtXY6gzLvtNipZhHHldc6HWnae',2,'2025-02-24 12:09:39'),(16,'atul@gmail.com','$2a$12$RLoK9dYE2gJWllDg33SGmOA/qkKyEPtjvx.sKjmiCqfyzqP8xiZlu',2,'2025-02-24 12:10:25'),(17,'kuldip@gmail.com','$2a$12$zlqE3dExrzTdUle1eOqR8.PA6e4eY6gcT4AIPXmwbtmRxbMM.Ydta',2,'2025-02-24 12:11:12'),(18,'krishna@gmail.com','$2a$12$fpnmLEyUt0Hyaj9VX2459uh.dZ5veRVySSczPUHk0igNqh8Qk6fAW',2,'2025-02-24 12:11:58'),(19,'nilampampaniya@gmail.com','$2a$12$q.qqyuy5GeOVnfZZWXXZQOqOxx4yHGmIqWz61CzdlD20Z4pp1YzKC',2,'2025-02-24 12:18:57'),(20,'nikunj@gmail.com','$2a$12$vOUsiD93KBxLs8jgZn9jpeMB8uw2g3PlX6kMXY9L2oxjtIBbEvP9C',2,'2025-02-24 12:19:48'),(34,'jaydeeppampaniya77@gmail.com','$2a$12$ZUD3920V6HsX9T/Zev4pyuFHoxwtUfOb0XcnkLviqb/lDFtA3RUv6',2,'2025-03-06 10:09:27');
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

-- Dump completed on 2025-06-23 10:37:49
