-- MySQL dump 10.13  Distrib 5.7.19, for Linux (x86_64)
--
-- Host: localhost    Database: wisdom_garden
-- ------------------------------------------------------
-- Server version	5.7.19-0ubuntu0.16.04.1

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
-- Table structure for table `ecology`
--

DROP TABLE IF EXISTS `ecology`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ecology` (
  `sight_id` int(10) unsigned NOT NULL,
  `temperature` float NOT NULL,
  `humidity` float NOT NULL,
  `pm25` int(10) unsigned NOT NULL,
  `wind` varchar(20) NOT NULL,
  `dressing` varchar(50) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`sight_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ecology`
--

LOCK TABLES `ecology` WRITE;
/*!40000 ALTER TABLE `ecology` DISABLE KEYS */;
INSERT INTO `ecology` VALUES (1,25,0.6,40,'微风','短袖','2017-10-18 17:12:20','2017-10-18 17:28:16'),(2,28,0.4,88,'微风','短袖','2017-10-19 00:10:14','2017-10-19 00:10:14'),(3,12,0.3,300,'大风','羽绒服','2017-10-19 00:11:11','2017-10-19 00:11:11'),(7,8,0.2,250,'飓风','棉衣','2017-10-19 00:12:14','2017-10-19 00:12:14');
/*!40000 ALTER TABLE `ecology` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plants`
--

DROP TABLE IF EXISTS `plants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plants` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `description` text NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plants`
--

LOCK TABLES `plants` WRITE;
/*!40000 ALTER TABLE `plants` DISABLE KEYS */;
INSERT INTO `plants` VALUES (1,'乔木','乔木是指树身高大的树木，由根部发生独立的主干，树干和树冠有明显区分。有一个直立主干，且通常高达六米至数十米的木本植物称为乔木。其往往树体高大，具有明显的高大主干。又可依其高度而分为伟乔（31米以上）、大乔(21～30米)、中乔（11～20米）、小乔（6～10米）等有四级。','2017-10-17 19:38:24','2017-10-17 19:38:24'),(2,'茶树','灌木或小乔木，嫩枝无毛。叶革质，长圆形或椭圆形，长4-12厘米，宽2-5厘米，先端钝或尖锐，基部楔形，上面发亮，下面无毛或初时有柔毛，侧脉5-7对，边缘有锯齿，叶柄长3-8毫米，无毛。花1-3朵腋生，白色，花柄长4-6毫米，有时稍长；苞片2片，早落；萼片5片，阔卵形至圆形，长3-4毫米，无毛，宿存；花瓣5-6片，阔卵形，长1-1.6厘米，基部略连合，背面无毛，有时有短柔毛；雄蕊长8-13毫米，基部连生1-2毫米；子房密生白毛；花柱无毛，先端3裂，裂片长2-4毫米。蒴果3球形或1-2球形，高1.1-1.5厘米，每球有种子1-2粒。花期10月至翌年2月。','2017-10-17 19:41:20','2017-10-17 19:41:20'),(3,'枫树','枫树为高大乔木，可高达29米以上，冠幅可达16米。花期4到5月，果期9到10月。随着树龄增长，树冠逐渐敞开，呈圆形。枝条棕红色到棕色，有小孔，冬季枝条是黑棕色或灰色。枫叶色泽绚烂、形态别致优美，可制作书签、标本等。在秋天则变成火红色，落在地上时变成深红。','2017-10-17 23:33:26','2017-10-17 23:33:26');
/*!40000 ALTER TABLE `plants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plants_file`
--

DROP TABLE IF EXISTS `plants_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plants_file` (
  `plants_id` int(10) unsigned NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `category` varchar(10) NOT NULL,
  `url` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plants_file`
--

LOCK TABLES `plants_file` WRITE;
/*!40000 ALTER TABLE `plants_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `plants_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plants_to_sight`
--

DROP TABLE IF EXISTS `plants_to_sight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plants_to_sight` (
  `plants_id` int(10) unsigned NOT NULL,
  `sight_id` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plants_to_sight`
--

LOCK TABLES `plants_to_sight` WRITE;
/*!40000 ALTER TABLE `plants_to_sight` DISABLE KEYS */;
INSERT INTO `plants_to_sight` VALUES (3,1),(3,2);
/*!40000 ALTER TABLE `plants_to_sight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `route` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `sight_id_chain` varchar(1000) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (1,'线路一','开始线路一的旅程','1,4,5,6,8,2','2017-10-18 23:30:20','2017-10-18 23:30:20'),(2,'线路二','这是线路二','1,3,7,2','2017-10-18 23:34:11','2017-10-18 23:34:11');
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'卫生间',1,1,'2017-10-17 20:14:22','2017-10-17 20:14:22'),(2,'卫生间',0.5,0.5,'2017-10-18 14:25:16','2017-10-18 14:25:16'),(3,'商店',1.5,1.5,'2017-10-18 14:59:17','2017-10-18 14:59:17');
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sight`
--

DROP TABLE IF EXISTS `sight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sight` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `name` varchar(30) NOT NULL,
  `description` text NOT NULL,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sight`
--

LOCK TABLES `sight` WRITE;
/*!40000 ALTER TABLE `sight` DISABLE KEYS */;
INSERT INTO `sight` VALUES (1,1,'入口','这是智慧园林入口',0,0,'2017-10-17 17:04:53','2017-10-17 17:04:53'),(2,2,'出口','智慧园林出口',80,120,'2017-10-17 17:11:59','2017-10-17 17:11:59'),(3,0,'松树林','好大一片松树林',10,10,'2017-10-17 17:19:45','2017-10-17 17:19:45'),(4,0,'知园','提供较为隐秘的空间，让人们在室外拥有开放而有自我的交流空间，促进沟通和理解。',3,3,'2017-10-18 23:13:39','2017-10-18 23:13:39'),(5,0,'畅园','较长的花架提供了更大的交流空间，广场上嬉戏的儿童让居民享受到温暖的天伦之乐。',3.5,3.5,'2017-10-18 23:14:11','2017-10-18 23:14:11'),(6,0,'静园','大面积的多季玫瑰在嗅觉上吸引使用者的注意力，从而削弱了视觉带来的心理波动，整个植物围合的通透而又紧密的空间创造出别样的静谧。',3.3,3.3,'2017-10-18 23:14:45','2017-10-18 23:14:45'),(7,0,'逸园','有花架和植物围合成主要休憩空间，广场设有健身器材，让紧张工作的人们有促膝倾诉和休闲健身的场所，体验到轻松和快乐。',3.2,3.2,'2017-10-18 23:15:58','2017-10-18 23:15:58'),(8,0,'欣园','涌泉和雕塑新颖别致，在体量和造型上与周围环境相辅相成，使人心旷神怡。',3.1,3.1,'2017-10-18 23:17:05','2017-10-18 23:17:05');
/*!40000 ALTER TABLE `sight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sight_file`
--

DROP TABLE IF EXISTS `sight_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sight_file` (
  `sight_id` int(10) unsigned NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `category` varchar(10) NOT NULL,
  `url` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sight_file`
--

LOCK TABLES `sight_file` WRITE;
/*!40000 ALTER TABLE `sight_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `sight_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `password` char(32) NOT NULL,
  `user_type` tinyint(4) NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ChengTao','e10adc3949ba59abbe56e057f20f883e',0,'2017-10-16 12:53:55','2017-10-17 15:20:52'),(2,'ParadiseHell','e10adc3949ba59abbe56e057f20f883e',0,'2017-10-17 15:13:52','2017-10-17 15:13:52'),(3,'Tom','e10adc3949ba59abbe56e057f20f883e',0,'2017-10-17 15:22:37','2017-10-17 15:22:37'),(4,'Admin','e10adc3949ba59abbe56e057f20f883e',1,'2017-10-18 21:31:24','2017-10-18 21:32:17'),(5,'Anna','e10adc3949ba59abbe56e057f20f883e',0,'2017-10-19 01:18:17','2017-10-19 01:18:17'),(6,'zw','e10adc3949ba59abbe56e057f20f883e',0,'2017-10-20 15:06:27','2017-10-20 15:06:27'),(7,'CT','e10adc3949ba59abbe56e057f20f883e',0,'2017-10-20 15:10:17','2017-10-20 15:10:17');
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

-- Dump completed on 2017-10-21 16:43:54
