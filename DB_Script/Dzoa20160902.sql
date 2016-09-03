-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: dz_oa
-- ------------------------------------------------------
-- Server version	5.7.9

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
-- Table structure for table `admin_lookup`
--

DROP TABLE IF EXISTS `admin_lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_lookup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `value` varchar(45) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `abbrv` varchar(45) DEFAULT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_lookup`
--

LOCK TABLES `admin_lookup` WRITE;
/*!40000 ALTER TABLE `admin_lookup` DISABLE KEYS */;
INSERT INTO `admin_lookup` VALUES (1,'USER_ROLE','ROLE_SUPER_ADMIN',NULL,NULL,'N'),(2,'USER_ROLE','ROLE_ENTERPRISE_ADMIN',NULL,NULL,'N'),(3,'USER_ROLE','ROLE_USER',NULL,NULL,'N'),(4,'USER_ROLE','ROLE_VIEW_ONLY_USER',NULL,NULL,'N'),(5,'CONTACT_TYPE','Email',NULL,NULL,'N'),(6,'CONTACT_TYPE','Phone',NULL,NULL,'N'),(7,'CONTACT_TYPE','Mail',NULL,NULL,'N'),(8,'CONTACT_TYPE','Fax',NULL,NULL,'N'),(9,'NOTIFICATION_TYPE','General',NULL,NULL,'N'),(10,'NOTIFICATION_TYPE','Project Specific',NULL,NULL,'N');
/*!40000 ALTER TABLE `admin_lookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_um_lookup`
--

DROP TABLE IF EXISTS `admin_um_lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_um_lookup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `value` varchar(45) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `abbrv` varchar(45) DEFAULT NULL,
  `inactive_ind` varchar(45) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_um_lookup`
--

LOCK TABLES `admin_um_lookup` WRITE;
/*!40000 ALTER TABLE `admin_um_lookup` DISABLE KEYS */;
INSERT INTO `admin_um_lookup` VALUES (1,'MODULE','TIMESHEET',NULL,NULL,'N'),(2,'POSITION','CEO',NULL,NULL,'N');
/*!40000 ALTER TABLE `admin_um_lookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enterprise`
--

DROP TABLE IF EXISTS `enterprise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enterprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enterprise`
--

LOCK TABLES `enterprise` WRITE;
/*!40000 ALTER TABLE `enterprise` DISABLE KEYS */;
INSERT INTO `enterprise` VALUES (1,'jyhg','N');
/*!40000 ALTER TABLE `enterprise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enterprise_contact`
--

DROP TABLE IF EXISTS `enterprise_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enterprise_contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) NOT NULL,
  `contact_type` int(11) NOT NULL,
  `value_X` varchar(500) DEFAULT NULL,
  `value_N` int(11) DEFAULT NULL,
  `value_D` datetime DEFAULT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`),
  KEY `entp_id_fk_idx` (`enterprise_id`),
  KEY `contact_id_fk_idx` (`contact_type`),
  CONSTRAINT `contact_id_fk` FOREIGN KEY (`contact_type`) REFERENCES `admin_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `entp_id_fk` FOREIGN KEY (`enterprise_id`) REFERENCES `enterprise` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enterprise_contact`
--

LOCK TABLES `enterprise_contact` WRITE;
/*!40000 ALTER TABLE `enterprise_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `enterprise_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enterprise_lookup`
--

DROP TABLE IF EXISTS `enterprise_lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enterprise_lookup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `value` varchar(45) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enterprise_lookup`
--

LOCK TABLES `enterprise_lookup` WRITE;
/*!40000 ALTER TABLE `enterprise_lookup` DISABLE KEYS */;
/*!40000 ALTER TABLE `enterprise_lookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enterprise_module`
--

DROP TABLE IF EXISTS `enterprise_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enterprise_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) NOT NULL,
  `module_id` int(11) NOT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`),
  KEY `enpt_id_look_fk_idx` (`enterprise_id`),
  KEY `module_fk_idx` (`module_id`),
  CONSTRAINT `enpt_id_look_fk` FOREIGN KEY (`enterprise_id`) REFERENCES `enterprise` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `module_fk` FOREIGN KEY (`module_id`) REFERENCES `admin_um_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enterprise_module`
--

LOCK TABLES `enterprise_module` WRITE;
/*!40000 ALTER TABLE `enterprise_module` DISABLE KEYS */;
/*!40000 ALTER TABLE `enterprise_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enterprise_module_detail`
--

DROP TABLE IF EXISTS `enterprise_module_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enterprise_module_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `em_id` int(11) NOT NULL,
  `detail_value` varchar(45) DEFAULT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`),
  KEY `entp_module_id_idx` (`em_id`),
  CONSTRAINT `entp_module_id` FOREIGN KEY (`em_id`) REFERENCES `enterprise_module` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enterprise_module_detail`
--

LOCK TABLES `enterprise_module_detail` WRITE;
/*!40000 ALTER TABLE `enterprise_module_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `enterprise_module_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_code_id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `expiration_date` datetime DEFAULT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  `title` varchar(400) NOT NULL,
  `content` varchar(2000) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `not_type_code_id_fk_idx` (`type_code_id`),
  KEY `not_project_id_fk_idx` (`project_id`),
  CONSTRAINT `not_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `not_type_code_id_fk` FOREIGN KEY (`type_code_id`) REFERENCES `admin_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,10,NULL,'2014-05-02 00:00:00','2017-05-02 00:00:00','N','Test notification is here , please click to see more','sdf sdf asdf asdf asdf asdf asdf asdf sadf sdf sdf sdf'),(2,9,NULL,'2014-05-02 00:00:00','2017-05-02 00:00:00','Y','test est set set  set set ','sdfsd fsdf sdf sdf sdf sdf sdf sdf '),(3,9,NULL,'2016-08-11 00:00:00','2016-08-13 00:00:00','Y','asdf','asdf'),(4,9,NULL,'2016-08-26 00:00:00','2016-08-20 00:00:00','Y','sdfasdf','asdfasdfasdfasdf'),(5,9,NULL,'2016-08-24 00:00:00','2016-08-27 00:00:00','Y','sdf','sdf'),(6,9,NULL,'2016-08-23 00:00:00','2016-09-03 00:00:00','N','asdf','asdf'),(7,9,NULL,'2016-08-10 00:00:00','2016-09-02 00:00:00','Y','asdf','asdf'),(8,9,NULL,'2016-08-27 00:00:00','2016-09-03 00:00:00','Y','sdf','sdfasdf'),(9,9,NULL,'2016-08-26 00:00:00','2016-09-03 00:00:00','Y','asdf','sadf'),(11,9,NULL,'2016-08-03 00:00:00','2016-08-31 00:00:00','Y','sdf','sdf'),(12,9,NULL,'2016-09-21 00:00:00','2016-09-29 00:00:00','N','aaaa','aaaa');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) NOT NULL,
  `name` varchar(300) NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  `start_date` datetime DEFAULT NULL,
  `complete_date` datetime DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `enterprise_project_id_fk_idx` (`enterprise_id`),
  KEY `status_project_fk_idx` (`status`),
  CONSTRAINT `enterprise_project_id_fk` FOREIGN KEY (`enterprise_id`) REFERENCES `enterprise` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `status_project_fk` FOREIGN KEY (`status`) REFERENCES `project_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (10,1,'project name 1234','hahahahahahahah sdf sd fds fsd f','N','2016-09-09 00:00:00',NULL,1);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_location`
--

DROP TABLE IF EXISTS `project_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address1` varchar(50) DEFAULT NULL,
  `address2` varchar(50) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `custom_address` varchar(500) NOT NULL,
  `is_custom_address` varchar(1) DEFAULT 'N',
  `lat` varchar(20) DEFAULT NULL,
  `lng` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `project_id_proj_location_fk_idx` (`project_id`),
  CONSTRAINT `project_id_proj_location_fk` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_location`
--

LOCK TABLES `project_location` WRITE;
/*!40000 ALTER TABLE `project_location` DISABLE KEYS */;
INSERT INTO `project_location` VALUES (8,'21670','Ashburn Road','Ashburn','VA','United States','20147',10,'21670 Ashburn Road, Ashburn, VA, United States','N','39.0227556','-77.4904515');
/*!40000 ALTER TABLE `project_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_lookup`
--

DROP TABLE IF EXISTS `project_lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_lookup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `value` varchar(45) NOT NULL,
  `enterprise_id` int(11) NOT NULL,
  `inactive_ind` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `enterprise_project_lookup_fk_idx` (`enterprise_id`),
  CONSTRAINT `enterprise_project_lookup_fk` FOREIGN KEY (`enterprise_id`) REFERENCES `enterprise` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_lookup`
--

LOCK TABLES `project_lookup` WRITE;
/*!40000 ALTER TABLE `project_lookup` DISABLE KEYS */;
INSERT INTO `project_lookup` VALUES (1,'PROJ_STATUS','In progress',1,'N');
/*!40000 ALTER TABLE `project_lookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_status_track`
--

DROP TABLE IF EXISTS `project_status_track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_status_track` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `status_date` datetime NOT NULL,
  `status_id` int(11) DEFAULT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`),
  KEY `project_status_hist_pid_fk_idx` (`project_id`),
  KEY `project_status_hist_sid_fk_idx` (`status_id`),
  CONSTRAINT `project_status_hist_pid_fk` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `project_status_hist_sid_fk` FOREIGN KEY (`status_id`) REFERENCES `project_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_status_track`
--

LOCK TABLES `project_status_track` WRITE;
/*!40000 ALTER TABLE `project_status_track` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_status_track` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_user_assoc`
--

DROP TABLE IF EXISTS `project_user_assoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_user_assoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `position_id` int(11) NOT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`),
  KEY `project_user_assoc_id_fk_idx` (`project_id`),
  KEY `user_project_user_assoc_id_fk_idx` (`user_id`),
  CONSTRAINT `position_user_proj_user_assoc_fk` FOREIGN KEY (`id`) REFERENCES `project_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `project_user_assoc_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_project_user_assoc_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_user_assoc`
--

LOCK TABLES `project_user_assoc` WRITE;
/*!40000 ALTER TABLE `project_user_assoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_user_assoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ts_approval`
--

DROP TABLE IF EXISTS `ts_approval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ts_approval` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ts_main_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL,
  `approver_id` int(11) NOT NULL,
  `status_date` datetime NOT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`),
  KEY `ts_main_id_fk_idx` (`ts_main_id`),
  KEY `status_ts_main_id_fk_idx` (`status_id`),
  KEY `approver_id_ts_approval_fk_idx` (`approver_id`),
  CONSTRAINT `approver_id_ts_approval_fk` FOREIGN KEY (`approver_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `status_ts_main_id_fk` FOREIGN KEY (`status_id`) REFERENCES `admin_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ts_main_id_fk` FOREIGN KEY (`ts_main_id`) REFERENCES `ts_main` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ts_approval`
--

LOCK TABLES `ts_approval` WRITE;
/*!40000 ALTER TABLE `ts_approval` DISABLE KEYS */;
/*!40000 ALTER TABLE `ts_approval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ts_bill_code_lookup`
--

DROP TABLE IF EXISTS `ts_bill_code_lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ts_bill_code_lookup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `code_tyoe` varchar(45) NOT NULL,
  `code_value` varchar(45) NOT NULL,
  `code_desc` varchar(300) DEFAULT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`),
  KEY `project_ts_bill_code_fk_idx` (`project_id`),
  CONSTRAINT `project_ts_bill_code_fk` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ts_bill_code_lookup`
--

LOCK TABLES `ts_bill_code_lookup` WRITE;
/*!40000 ALTER TABLE `ts_bill_code_lookup` DISABLE KEYS */;
/*!40000 ALTER TABLE `ts_bill_code_lookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ts_emp_schedule`
--

DROP TABLE IF EXISTS `ts_emp_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ts_emp_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `bill_code_id` int(11) NOT NULL,
  `period` int(11) NOT NULL,
  `max_hour` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id_ts_emp_sche_fk_idx` (`user_id`),
  KEY `bill_code_id_ts_emp_sche_fk_idx` (`bill_code_id`),
  CONSTRAINT `bill_code_id_ts_emp_sche_fk` FOREIGN KEY (`bill_code_id`) REFERENCES `ts_bill_code_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id_ts_emp_sche_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ts_emp_schedule`
--

LOCK TABLES `ts_emp_schedule` WRITE;
/*!40000 ALTER TABLE `ts_emp_schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `ts_emp_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ts_main`
--

DROP TABLE IF EXISTS `ts_main`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ts_main` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `bill_code_id` int(11) NOT NULL,
  `slot_id` int(11) NOT NULL,
  `value` int(11) DEFAULT '0',
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`),
  KEY `user_ts_main_fk_idx` (`user_id`),
  KEY `bill_ts_main_fk_idx` (`bill_code_id`),
  KEY `slot_ts_main_fk_idx` (`slot_id`),
  CONSTRAINT `bill_ts_main_fk` FOREIGN KEY (`bill_code_id`) REFERENCES `ts_bill_code_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `slot_ts_main_fk` FOREIGN KEY (`slot_id`) REFERENCES `ts_slot_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_ts_main_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ts_main`
--

LOCK TABLES `ts_main` WRITE;
/*!40000 ALTER TABLE `ts_main` DISABLE KEYS */;
/*!40000 ALTER TABLE `ts_main` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ts_period_um_lookup`
--

DROP TABLE IF EXISTS `ts_period_um_lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ts_period_um_lookup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ts_period_um_lookup`
--

LOCK TABLES `ts_period_um_lookup` WRITE;
/*!40000 ALTER TABLE `ts_period_um_lookup` DISABLE KEYS */;
/*!40000 ALTER TABLE `ts_period_um_lookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ts_slot_lookup`
--

DROP TABLE IF EXISTS `ts_slot_lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ts_slot_lookup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `is_week_day` varchar(1) DEFAULT 'Y',
  `period_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `period_slot_lookup_fk_idx` (`period_id`),
  CONSTRAINT `period_slot_lookup_fk` FOREIGN KEY (`period_id`) REFERENCES `ts_period_um_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ts_slot_lookup`
--

LOCK TABLES `ts_slot_lookup` WRITE;
/*!40000 ALTER TABLE `ts_slot_lookup` DISABLE KEYS */;
/*!40000 ALTER TABLE `ts_slot_lookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(70) NOT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'zdwrz','$2a$10$2NmsRAHeGV3eBGg5/TkEZOVLzm2n3FALmGR6h0gLiyrn9KI5/R/w2','N');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_detail`
--

DROP TABLE IF EXISTS `user_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `mid_init` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `home_address` varchar(45) DEFAULT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`),
  KEY `user_detail_user_id_fk_idx` (`user_id`),
  CONSTRAINT `user_detail_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_detail`
--

LOCK TABLES `user_detail` WRITE;
/*!40000 ALTER TABLE `user_detail` DISABLE KEYS */;
INSERT INTO `user_detail` VALUES (1,1,'Dawei','Zhuang',NULL,'11','z@gmail.com','address test','N');
/*!40000 ALTER TABLE `user_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_doc_info`
--

DROP TABLE IF EXISTS `user_doc_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_doc_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_type` int(11) DEFAULT NULL,
  `doc_name` varchar(200) NOT NULL,
  `file_type` varchar(45) NOT NULL,
  `file_location` varchar(500) NOT NULL,
  `upload_time` datetime DEFAULT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_doc_info_fk_idx` (`user_id`),
  KEY `user_doc_type_fk_idx` (`doc_type`),
  CONSTRAINT `user_doc_type_fk` FOREIGN KEY (`doc_type`) REFERENCES `enterprise_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id_doc_info_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_doc_info`
--

LOCK TABLES `user_doc_info` WRITE;
/*!40000 ALTER TABLE `user_doc_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_doc_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `enterprise_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  `position_id` int(11) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_fk_idx` (`user_id`),
  KEY `role_id_fk_idx` (`role_id`),
  KEY `enterprise_id_fk_idx` (`enterprise_id`),
  KEY `position_role_id_fk_idx` (`position_id`),
  CONSTRAINT `enterprise_id_fk` FOREIGN KEY (`enterprise_id`) REFERENCES `enterprise` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `position_role_id_fk` FOREIGN KEY (`position_id`) REFERENCES `admin_um_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `admin_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1,3,'N',2,NULL,NULL),(2,1,1,1,'N',2,NULL,NULL);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-02 23:26:08
