-- MariaDB dump 10.19  Distrib 10.7.3-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: public-health-center-recommendation
-- ------------------------------------------------------
-- Server version	10.7.3-MariaDB-1:10.7.3+maria~focal

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `publicHealthCenter`
--

DROP TABLE IF EXISTS `public_health_center`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `public_health_center` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `created_date` datetime(6) DEFAULT NULL,
                            `modified_date` datetime(6) DEFAULT NULL,
                            `latitude` double NOT NULL,
                            `longitude` double NOT NULL,
                            `public_health_center_address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                            `public_health_center_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=202 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publicHealthCenter`
--

LOCK TABLES `public_health_center` WRITE;
/*!40000 ALTER TABLE `public_health_center` DISABLE KEYS */;
INSERT INTO `public_health_center` VALUES
                           (1,'2022-10-17 23:46:34.982624','2022-10-17 23:46:34.982624',35.10650760,129.0321071,'부산광역시 중구 중구로 120','중구'),
                           (2,'2022-10-17 23:46:35.025774','2022-10-17 23:46:35.025774',35.10753860,129.0159046,'부산광역시 서구 부용로 30','서구'),
                           (3,'2022-10-17 23:46:35.028083','2022-10-17 23:46:35.028083',35.12934840,129.0427185,'부산광역시 동구 구청로 1','동구'),
                           (4,'2022-10-17 23:46:35.030080','2022-10-17 23:46:35.030080',35.09029060,129.0658315,'부산광역시 영도구 태종로 423','영도구'),
                           (5,'2022-10-17 23:46:35.032241','2022-10-17 23:46:35.032241',35.14631420,129.0577904,'부산광역시 부산진구 황령대로8번길 36','부산진구'),
                           (6,'2022-10-17 23:46:35.034546','2022-10-17 23:46:35.034546',35.21138860,129.0776738,'부산광역시 동래구 명륜로187번길 56','동래구'),
                           (7,'2022-10-17 23:46:35.036422','2022-10-17 23:46:35.036422',35.13604920,129.0827931,'부산광역시 남구 못골로 23','남구'),
                           (8,'2022-10-17 23:46:35.038377','2022-10-17 23:46:35.038377',35.23924520,129.0125958,'부산광역시 북구 금곡대로 348','북구'),
                           (9,'2022-10-17 23:46:35.040175','2022-10-17 23:46:35.040175',35.16411830,129.1764714,'부산광역시 해운대구 양운로37번길 59','북구'),
                           (10,'2022-10-17 23:46:35.041806','2022-10-17 23:46:35.041806',35.09401520,128.9564904,'부산광역시 사하구 하신중앙로185','사하구'),
                           (11,'2022-10-17 23:46:35.043405','2022-10-17 23:46:35.043405',35.24205190,129.0902829,'부산광역시 금정구 중앙대로 1777','금정구'),
                           (12,'2022-10-17 23:46:35.045021','2022-10-17 23:46:35.045021',35.18040310,128.9550841,'부산광역시 강서구 공항로 811번길 10','강서구'),
                           (13,'2022-10-17 23:46:35.046680','2022-10-17 23:46:35.046680',35.17683710,129.0769526,'부산광역시 연제구 연제로 2','연제구'),
                           (14,'2022-10-17 23:46:35.048536','2022-10-17 23:46:35.048536',35.16257060,129.1113024,'부산광역시 수영구 수영로 637-5','수영구'),
                           (15,'2022-10-17 23:46:35.050249','2022-10-17 23:46:35.050249',35.15242140,128.9898503,'부산광역시 사상구 학감대로 242','사상구'),
                           (16,'2022-10-17 23:46:35.052790','2022-10-17 23:46:35.052790',35.24352670,129.2207769,'부산광역시 기장군 기장읍 기장대로560','기장군');


/*!40000 ALTER TABLE `public_health_center` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-17 23:49:14