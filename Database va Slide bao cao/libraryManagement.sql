-- MySQL dump 10.13  Distrib 5.6.26, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	5.6.26

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
-- Current Database: `library`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `library` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `library`;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `userName` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `type` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('Admin','000',1),('Librarian','000',2),('loitv','111',3),('M10','000',3),('ABC','abc',3),('CR7','000',3),('Lib2','000',2),('Reader','000',3);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `isbn` int(11) NOT NULL,
  `bookName` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Author` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Category` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Price` double NOT NULL,
  `Total` int(11) NOT NULL,
  `Remain` int(11) NOT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Java ABC','Obama','IT',260000,70,70),(2,'Digital Communication','John Proakis','ET',450000,90,90),(3,'Digital Signal Processing','John Proakis','ET',300000,80,80),(4,'50 Shades of Grey','E.L. James','Literature',120000,90,90),(5,'Mat biec','Nguyen Nhat Anh','Literature',50000,20,20),(6,'Ly thuyet mach','Pham Huu Binh, PTT.Thao','ET',60000,200,200),(101,'Lap trinh C++','Pham Van At','IT',120000,60,60);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pattern_book`
--

DROP TABLE IF EXISTS `pattern_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pattern_book` (
  `patternID` int(11) NOT NULL,
  `isbn` int(11) NOT NULL,
  `title` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pattern_book`
--

LOCK TABLES `pattern_book` WRITE;
/*!40000 ALTER TABLE `pattern_book` DISABLE KEYS */;
INSERT INTO `pattern_book` VALUES (2,4,'50 Shades of Grey'),(2,101,'Lap trinh C++'),(3,2,'Digital Communication'),(3,1,'Java ABC'),(4,3,'Digital Signal Processing');
/*!40000 ALTER TABLE `pattern_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patternborrow`
--

DROP TABLE IF EXISTS `patternborrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patternborrow` (
  `patternID` int(11) NOT NULL,
  `readerID` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `readerName` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `borrowDate` date NOT NULL,
  `returnDate` date NOT NULL,
  `deposit` double NOT NULL,
  PRIMARY KEY (`patternID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patternborrow`
--

LOCK TABLES `patternborrow` WRITE;
/*!40000 ALTER TABLE `patternborrow` DISABLE KEYS */;
INSERT INTO `patternborrow` VALUES (2,'abc','Le Van A','2015-11-29','2016-01-20',60000),(3,'abc','Le Van A','2015-11-25','2015-11-26',177500),(4,'M10','Messi','2012-11-20','2014-11-18',75000);
/*!40000 ALTER TABLE `patternborrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personalinfo`
--

DROP TABLE IF EXISTS `personalinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personalinfo` (
  `personalID` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `address` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` int(10) unsigned NOT NULL,
  PRIMARY KEY (`personalID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personalinfo`
--

LOCK TABLES `personalinfo` WRITE;
/*!40000 ALTER TABLE `personalinfo` DISABLE KEYS */;
INSERT INTO `personalinfo` VALUES ('ABC','Le Van A','Male',0,'','1993-04-17','',3),('Admin','Tran Van Loi','Male',19001900,'Bac Ninh','1996-04-29','loitran1763@gmail.com',1),('CR7','Ronaldo',NULL,0,NULL,NULL,NULL,3),('Lib2','Tom Hank','Male',88888889,'HN, VN','1993-04-17','tomhank@yahoo.com',2),('Librarian','Tom Cruise','Male',99999999,'LA, USA','2015-12-01','tomcruiseVN@gmail.com',2),('loitv','LOITV',NULL,0,NULL,NULL,NULL,3),('M10','Messi',NULL,0,NULL,NULL,NULL,3),('Reader','Tran Van Hoa','Male',12345678,'HN, VN','1993-07-21','hoatran@gmail.com',3);
/*!40000 ALTER TABLE `personalinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tempbook`
--

DROP TABLE IF EXISTS `tempbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tempbook` (
  `isbn` int(11) NOT NULL,
  `title` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tempbook`
--

LOCK TABLES `tempbook` WRITE;
/*!40000 ALTER TABLE `tempbook` DISABLE KEYS */;
INSERT INTO `tempbook` VALUES (5,'Mat biec',50000);
/*!40000 ALTER TABLE `tempbook` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-01  9:35:37
