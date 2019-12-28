-- MySQL dump 10.13  Distrib 8.0.17, for macos10.14 (x86_64)
--
-- Host: localhost    Database: lem
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `aid` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `nic` int(11) DEFAULT NULL,
  PRIMARY KEY (`aid`),
  KEY `adminnic_idx` (`nic`),
  CONSTRAINT `adminnic` FOREIGN KEY (`nic`) REFERENCES `person` (`nic`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casefile`
--

DROP TABLE IF EXISTS `casefile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `casefile` (
  `caseid` int(11) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `crid` int(11) DEFAULT NULL,
  PRIMARY KEY (`caseid`),
  KEY `crimecasefile_idx` (`crid`),
  CONSTRAINT `crimecasefile` FOREIGN KEY (`crid`) REFERENCES `crime` (`crid`)
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `casefile`
--

LOCK TABLES `casefile` WRITE;
/*!40000 ALTER TABLE `casefile` DISABLE KEYS */;
/*!40000 ALTER TABLE `casefile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crime`
--

DROP TABLE IF EXISTS `crime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `crime` (
  `crid` int(11) NOT NULL,
  `date` varchar(45) DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  `place` varchar(45) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `vid` int(11) DEFAULT NULL,
  PRIMARY KEY (`crid`),
  KEY `reportedvictimid_idx` (`vid`),
  CONSTRAINT `reportedvictimid` FOREIGN KEY (`vid`) REFERENCES `victim` (`vid`) ON DELETE CASCADE ON UPDATE CASCADE
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crime`
--

LOCK TABLES `crime` WRITE;
/*!40000 ALTER TABLE `crime` DISABLE KEYS */;
/*!40000 ALTER TABLE `crime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `criminal`
--

DROP TABLE IF EXISTS `criminal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `criminal` (
  `cid` int(11) NOT NULL,
  `offense` varchar(45) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `nic` int(11) DEFAULT NULL,
  KEY `arrestedpoliceid_idx` (`pid`),
  KEY `criminalnic_idx` (`nic`),
  CONSTRAINT `arrestedpoliceid` FOREIGN KEY (`pid`) REFERENCES `officer` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `criminalnic` FOREIGN KEY (`nic`) REFERENCES `person` (`nic`) ON DELETE CASCADE ON UPDATE CASCADE
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criminal`
--

LOCK TABLES `criminal` WRITE;
/*!40000 ALTER TABLE `criminal` DISABLE KEYS */;
/*!40000 ALTER TABLE `criminal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evidence`
--

DROP TABLE IF EXISTS `evidence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evidence` (
  `crid` int(11) NOT NULL,
  `eid` int(11) NOT NULL,
  `details` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`crid`,`eid`),
  KEY `logofficerid_idx` (`pid`),
  CONSTRAINT `logofficerid` FOREIGN KEY (`pid`) REFERENCES `officer` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `relatedcrimeid` FOREIGN KEY (`crid`) REFERENCES `crime` (`crid`) ON DELETE CASCADE ON UPDATE CASCADE
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evidence`
--

LOCK TABLES `evidence` WRITE;
/*!40000 ALTER TABLE `evidence` DISABLE KEYS */;
/*!40000 ALTER TABLE `evidence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `officer`
--

DROP TABLE IF EXISTS `officer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `officer` (
  `pid` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `pwd` varchar(45) DEFAULT NULL,
  `nic` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `officernic_idx` (`nic`),
  CONSTRAINT `officernic` FOREIGN KEY (`nic`) REFERENCES `person` (`nic`)
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `officer`
--

LOCK TABLES `officer` WRITE;
/*!40000 ALTER TABLE `officer` DISABLE KEYS */;
/*!40000 ALTER TABLE `officer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `nic` int(11) NOT NULL,
  `fname` varchar(45) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`nic`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `policereport`
--

DROP TABLE IF EXISTS `policereport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `policereport` (
  `repid` int(11) NOT NULL,
  `dateissued` varchar(45) DEFAULT NULL,
  `nic` int(11) DEFAULT NULL,
  PRIMARY KEY (`repid`),
  KEY `polrepnic_idx` (`nic`),
  CONSTRAINT `polrepnic` FOREIGN KEY (`nic`) REFERENCES `person` (`nic`)
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policereport`
--

LOCK TABLES `policereport` WRITE;
/*!40000 ALTER TABLE `policereport` DISABLE KEYS */;
/*!40000 ALTER TABLE `policereport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `victim`
--

DROP TABLE IF EXISTS `victim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `victim` (
  `vid` int(11) NOT NULL,
  `nic` int(11) DEFAULT NULL,
  PRIMARY KEY (`vid`),
  KEY `victimnic_idx` (`nic`),
  CONSTRAINT `victimnic` FOREIGN KEY (`nic`) REFERENCES `person` (`nic`) ON DELETE CASCADE ON UPDATE CASCADE
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `victim`
--

LOCK TABLES `victim` WRITE;
/*!40000 ALTER TABLE `victim` DISABLE KEYS */;
/*!40000 ALTER TABLE `victim` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-05 22:29:42
