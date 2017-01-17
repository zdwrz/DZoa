# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.9)
# Database: dz_oa
# Generation Time: 2017-01-17 22:43:48 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table admin_lookup
# ------------------------------------------------------------

DROP TABLE IF EXISTS `admin_lookup`;

CREATE TABLE `admin_lookup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `value` varchar(45) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `abbrv` varchar(45) DEFAULT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

LOCK TABLES `admin_lookup` WRITE;
/*!40000 ALTER TABLE `admin_lookup` DISABLE KEYS */;

INSERT INTO `admin_lookup` (`id`, `type`, `value`, `description`, `abbrv`, `inactive_ind`)
VALUES
	(1,'USER_ROLE','ROLE_SUPER_ADMIN',NULL,NULL,'N'),
	(2,'USER_ROLE','ROLE_ENTERPRISE_ADMIN',NULL,NULL,'N'),
	(3,'USER_ROLE','ROLE_USER',NULL,NULL,'N'),
	(4,'USER_ROLE','ROLE_VIEW_ONLY_USER',NULL,NULL,'N'),
	(5,'CONTACT_TYPE','Email',NULL,NULL,'N'),
	(6,'CONTACT_TYPE','Phone',NULL,NULL,'N'),
	(7,'CONTACT_TYPE','Mail',NULL,NULL,'N'),
	(8,'CONTACT_TYPE','Fax',NULL,NULL,'N'),
	(9,'NOTIFICATION_TYPE','General',NULL,NULL,'N'),
	(10,'NOTIFICATION_TYPE','Project Specific',NULL,NULL,'N'),
	(11,'TS_STATUS','Submitted',NULL,NULL,'N'),
	(12,'TS_STATUS','Approved',NULL,NULL,'N'),
	(13,'TS_STATUS','Denied',NULL,NULL,'N'),
	(14,'TS_STATUS','Removed',NULL,NULL,'N'),
	(17,'USER_DOC_TYPE','Timesheet',NULL,NULL,'N'),
	(18,'TS_STATUS','Resubmitted',NULL,NULL,'N');

/*!40000 ALTER TABLE `admin_lookup` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table admin_um_lookup
# ------------------------------------------------------------

DROP TABLE IF EXISTS `admin_um_lookup`;

CREATE TABLE `admin_um_lookup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `value` varchar(45) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `abbrv` varchar(45) DEFAULT NULL,
  `inactive_ind` varchar(45) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

LOCK TABLES `admin_um_lookup` WRITE;
/*!40000 ALTER TABLE `admin_um_lookup` DISABLE KEYS */;

INSERT INTO `admin_um_lookup` (`id`, `type`, `value`, `description`, `abbrv`, `inactive_ind`)
VALUES
	(1,'MODULE','TIMESHEET',NULL,NULL,'N'),
	(2,'POSITION','CEO',NULL,NULL,'N');

/*!40000 ALTER TABLE `admin_um_lookup` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table enterprise
# ------------------------------------------------------------

DROP TABLE IF EXISTS `enterprise`;

CREATE TABLE `enterprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

LOCK TABLES `enterprise` WRITE;
/*!40000 ALTER TABLE `enterprise` DISABLE KEYS */;

INSERT INTO `enterprise` (`id`, `name`, `inactive_ind`)
VALUES
	(1,'jyhg','N');

/*!40000 ALTER TABLE `enterprise` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table enterprise_contact
# ------------------------------------------------------------

DROP TABLE IF EXISTS `enterprise_contact`;

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



# Dump of table enterprise_lookup
# ------------------------------------------------------------

DROP TABLE IF EXISTS `enterprise_lookup`;

CREATE TABLE `enterprise_lookup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `value` varchar(45) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table enterprise_module
# ------------------------------------------------------------

DROP TABLE IF EXISTS `enterprise_module`;

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



# Dump of table enterprise_module_detail
# ------------------------------------------------------------

DROP TABLE IF EXISTS `enterprise_module_detail`;

CREATE TABLE `enterprise_module_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `em_id` int(11) NOT NULL,
  `detail_value` varchar(45) DEFAULT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`),
  KEY `entp_module_id_idx` (`em_id`),
  CONSTRAINT `entp_module_id` FOREIGN KEY (`em_id`) REFERENCES `enterprise_module` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table notification
# ------------------------------------------------------------

DROP TABLE IF EXISTS `notification`;

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;

INSERT INTO `notification` (`id`, `type_code_id`, `project_id`, `start_date`, `expiration_date`, `inactive_ind`, `title`, `content`)
VALUES
	(1,10,NULL,'2014-05-02 00:00:00','2017-05-02 00:00:00','N','Test notification is here , please click to see more','sdf sdf asdf asdf asdf asdf asdf asdf sadf sdf sdf sdf'),
	(2,9,NULL,'2014-05-02 00:00:00','2017-05-02 00:00:00','Y','test est set set  set set ','sdfsd fsdf sdf sdf sdf sdf sdf sdf '),
	(3,9,NULL,'2016-08-11 00:00:00','2016-08-13 00:00:00','Y','asdf','asdf'),
	(4,9,NULL,'2016-08-26 00:00:00','2016-08-20 00:00:00','Y','sdfasdf','asdfasdfasdfasdf'),
	(5,9,NULL,'2016-08-24 00:00:00','2016-08-27 00:00:00','Y','sdf','sdf'),
	(6,9,NULL,'2016-08-23 00:00:00','2016-09-03 00:00:00','N','asdf','asdf'),
	(7,9,NULL,'2016-08-10 00:00:00','2016-09-02 00:00:00','Y','asdf','asdf'),
	(8,9,NULL,'2016-08-27 00:00:00','2016-09-03 00:00:00','Y','sdf','sdfasdf'),
	(9,9,NULL,'2016-08-26 00:00:00','2016-09-03 00:00:00','Y','asdf','sadf'),
	(11,9,NULL,'2016-08-03 00:00:00','2016-08-31 00:00:00','Y','sdf','sdf'),
	(12,9,NULL,'2016-09-21 00:00:00','2016-09-29 00:00:00','N','aaaa','aaaa'),
	(13,9,NULL,'2016-09-01 00:00:00',NULL,'N','sdfsdfasdf','sdfasdfasdfs f sdf sf sdf'),
	(14,9,NULL,'2016-08-31 00:00:00','2016-09-16 00:00:00','N','aaa a a a ',' a a a a ');

/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table project
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project`;

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;

INSERT INTO `project` (`id`, `enterprise_id`, `name`, `description`, `inactive_ind`, `start_date`, `complete_date`, `status`)
VALUES
	(10,1,'FY-14 City - Wide Safe Routes to School Construction','hahahahahahahah sdf sd fds fsd f','N','2016-09-09 00:00:00',NULL,1),
	(11,1,'FY-15 Federal Aid Pavement Restoration NHS','hahaha','N','2016-09-16 00:00:00','2016-09-30 00:00:00',1);

/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table project_doc_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project_doc_info`;

CREATE TABLE `project_doc_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proj_id` int(11) NOT NULL,
  `doc_name` varchar(200) NOT NULL,
  `file_type` varchar(250) NOT NULL,
  `file_location` varchar(1000) NOT NULL,
  `uploaded_time` datetime DEFAULT NULL,
  `file_time` datetime DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `proj_doc_proj_id_fk_idx` (`proj_id`),
  KEY `proj_doc_user_id_fk_idx` (`user_id`),
  KEY `proj_doc_category_id_fk_idx` (`category`),
  CONSTRAINT `proj_doc_category_id_fk` FOREIGN KEY (`category`) REFERENCES `enterprise_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proj_doc_proj_id_fk` FOREIGN KEY (`proj_id`) REFERENCES `project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proj_doc_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

LOCK TABLES `project_doc_info` WRITE;
/*!40000 ALTER TABLE `project_doc_info` DISABLE KEYS */;

INSERT INTO `project_doc_info` (`id`, `proj_id`, `doc_name`, `file_type`, `file_location`, `uploaded_time`, `file_time`, `category`, `inactive_ind`, `user_id`)
VALUES
	(9,10,'Ben Chen.docx','application/vnd.openxmlformats-officedocument.wordprocessingml.document','/Users/daweizhuang/workspace/apache-tomcat-8.0.29/bin/tempFile/tmpFiles/09_21_2016_10_19_55!Ben Chen.docx','2016-09-21 10:19:55','2016-09-09 00:00:00',NULL,'N',1),
	(10,10,'11_03_2016_12_49_54!ts.pdf','application/pdf','/Users/daweizhuang/workspace/apache-tomcat-8.0.29/bin/tempFile/tmpFiles/11_03_2016_13_13_41!11_03_2016_12_49_54!ts.pdf','2016-11-03 13:13:41','2016-09-09 00:00:00',NULL,'N',1),
	(11,10,'Core','application/octet-stream','/Users/daweizhuang/workspace/apache-tomcat-8.0.29/bin/tempFile/tmpFiles/12_07_2016_11_10_10!Core','2016-12-07 11:10:10','2016-09-09 00:00:00',NULL,'N',2);

/*!40000 ALTER TABLE `project_doc_info` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table project_location
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project_location`;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

LOCK TABLES `project_location` WRITE;
/*!40000 ALTER TABLE `project_location` DISABLE KEYS */;

INSERT INTO `project_location` (`id`, `address1`, `address2`, `city`, `state`, `country`, `zip`, `project_id`, `custom_address`, `is_custom_address`, `lat`, `lng`)
VALUES
	(8,'21670','Ashburn Road','Ashburn','VA','United States','20147',10,'21670 Ashburn Road, Ashburn, VA, United States','N','39.0227556','-77.4904515'),
	(9,'20753','Parkside Circle','Sterling','VA','United States','20165',11,'20753 Parkside Circle, Sterling, VA, United States','N','39.043993','-77.38210099999998');

/*!40000 ALTER TABLE `project_location` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table project_lookup
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project_lookup`;

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

LOCK TABLES `project_lookup` WRITE;
/*!40000 ALTER TABLE `project_lookup` DISABLE KEYS */;

INSERT INTO `project_lookup` (`id`, `type`, `value`, `enterprise_id`, `inactive_ind`)
VALUES
	(1,'PROJ_STATUS','In progress',1,'N');

/*!40000 ALTER TABLE `project_lookup` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table project_status_track
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project_status_track`;

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



# Dump of table project_user_assoc
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project_user_assoc`;

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



# Dump of table ts_approval
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ts_approval`;

CREATE TABLE `ts_approval` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status_id` int(11) NOT NULL,
  `submitter_id` int(11) NOT NULL,
  `approver_id` int(11) NOT NULL,
  `start_period_monday` datetime NOT NULL,
  `status_date` datetime NOT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  `total_hrs` int(11) DEFAULT NULL,
  `comment` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status_ts_main_id_fk_idx` (`status_id`),
  KEY `approver_id_ts_approval_fk_idx` (`approver_id`),
  KEY `submitter_id_ts_approval_fk` (`submitter_id`),
  CONSTRAINT `approver_id_ts_approval_fk` FOREIGN KEY (`approver_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `status_ts_main_id_fk` FOREIGN KEY (`status_id`) REFERENCES `admin_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `submitter_id_ts_approval_fk` FOREIGN KEY (`submitter_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

LOCK TABLES `ts_approval` WRITE;
/*!40000 ALTER TABLE `ts_approval` DISABLE KEYS */;

INSERT INTO `ts_approval` (`id`, `status_id`, `submitter_id`, `approver_id`, `start_period_monday`, `status_date`, `inactive_ind`, `total_hrs`, `comment`)
VALUES
	(1,12,1,1,'2016-12-12 00:00:00','2017-01-17 17:07:16','N',40,'approved !');

/*!40000 ALTER TABLE `ts_approval` ENABLE KEYS */;
UNLOCK TABLES;

DELIMITER ;;
/*!50003 SET SESSION SQL_MODE="ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION" */;;
/*!50003 CREATE */ /*!50017 DEFINER=`root`@`localhost` */ /*!50003 TRIGGER `ts_approval_update_audit_trigger` BEFORE UPDATE ON `ts_approval` FOR EACH ROW BEGIN
IF (NEW.status_id <> OLD.status_id) then
insert into ts_approval_audit(approval_id,status_id,submitter_id, approver_id, start_period_monday, status_date, total_hrs, `comment`) 
values(OLD.id, OLD.status_id, OLD.submitter_id, OLD.approver_id, OLD.start_period_monday, OLD.status_date, OLD.total_hrs, OLD.`comment`);
END IF;
END */;;
DELIMITER ;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;


# Dump of table ts_approval_audit
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ts_approval_audit`;

CREATE TABLE `ts_approval_audit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status_id` int(11) DEFAULT NULL,
  `submitter_id` int(11) DEFAULT NULL,
  `approver_id` int(11) DEFAULT NULL,
  `start_period_monday` datetime DEFAULT NULL,
  `status_date` datetime DEFAULT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  `total_hrs` int(11) DEFAULT NULL,
  `comment` varchar(1000) DEFAULT NULL,
  `approval_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `status_ts_main_id_fk_idx` (`status_id`),
  KEY `approver_id_ts_approval_fk_idx` (`approver_id`),
  KEY `submitter_id_ts_approval_fk` (`submitter_id`),
  KEY `ts_approval_audit_approval_id_fk` (`approval_id`),
  CONSTRAINT `ts_approval_audit_approval_id_fk` FOREIGN KEY (`approval_id`) REFERENCES `ts_approval` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ts_approval_audit_ibfk_1` FOREIGN KEY (`approver_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ts_approval_audit_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `admin_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ts_approval_audit_ibfk_3` FOREIGN KEY (`submitter_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

LOCK TABLES `ts_approval_audit` WRITE;
/*!40000 ALTER TABLE `ts_approval_audit` DISABLE KEYS */;

INSERT INTO `ts_approval_audit` (`id`, `status_id`, `submitter_id`, `approver_id`, `start_period_monday`, `status_date`, `inactive_ind`, `total_hrs`, `comment`, `approval_id`)
VALUES
	(1,11,1,1,'2016-12-12 00:00:00','2017-01-17 17:06:17','N',40,NULL,1);

/*!40000 ALTER TABLE `ts_approval_audit` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ts_bill_code_lookup
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ts_bill_code_lookup`;

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

LOCK TABLES `ts_bill_code_lookup` WRITE;
/*!40000 ALTER TABLE `ts_bill_code_lookup` DISABLE KEYS */;

INSERT INTO `ts_bill_code_lookup` (`id`, `project_id`, `code_tyoe`, `code_value`, `code_desc`, `inactive_ind`)
VALUES
	(1,10,'BILLABLE','Technician',NULL,'N'),
	(2,10,'BILLABLE','Inspector',NULL,'N'),
	(3,11,'NONE_BILLABLE','Holiday',NULL,'N'),
	(4,10,'BILLABLE','Office Engineer',NULL,'N'),
	(5,10,'BILLABLE','Resident Engineer',NULL,'N'),
	(6,10,'BILLABLE','Secretary',NULL,'N'),
	(7,10,'BILLABLE','Public Relation',NULL,'N'),
	(8,10,'BILLABLE','Estimator',NULL,'N'),
	(9,10,'BILLABLE','Scheduler',NULL,'N'),
	(10,10,'BILLABLE','CAD Drafter',NULL,'N'),
	(11,11,'BILLABLE','CAD Drafter',NULL,'N'),
	(14,11,'BILLABLE','Scheduler',NULL,'N'),
	(15,11,'BILLABLE','Estimator',NULL,'N');

/*!40000 ALTER TABLE `ts_bill_code_lookup` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ts_emp_schedule
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ts_emp_schedule`;

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

LOCK TABLES `ts_emp_schedule` WRITE;
/*!40000 ALTER TABLE `ts_emp_schedule` DISABLE KEYS */;

INSERT INTO `ts_emp_schedule` (`id`, `user_id`, `bill_code_id`, `period`, `max_hour`)
VALUES
	(1,1,1,1,0),
	(2,1,2,1,0),
	(3,1,3,1,0),
	(4,1,4,1,0),
	(5,1,5,1,0),
	(6,1,6,1,0),
	(7,1,7,1,0),
	(8,1,8,1,0),
	(9,1,9,1,0),
	(10,1,10,1,0),
	(11,1,11,1,0),
	(12,1,14,1,0),
	(13,1,15,1,0);

/*!40000 ALTER TABLE `ts_emp_schedule` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ts_main
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ts_main`;

CREATE TABLE `ts_main` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `bill_code_id` int(11) NOT NULL,
  `slot_id` int(11) NOT NULL,
  `value` int(11) DEFAULT '0',
  `inactive_ind` varchar(1) DEFAULT 'N',
  `comment` varchar(1000) NOT NULL DEFAULT 'NONE',
  `approval_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_ts_main_fk_idx` (`user_id`),
  KEY `bill_ts_main_fk_idx` (`bill_code_id`),
  KEY `slot_ts_main_fk_idx` (`slot_id`),
  KEY `approval_status_id_fk` (`approval_id`),
  CONSTRAINT `approval_status_id_fk` FOREIGN KEY (`approval_id`) REFERENCES `ts_approval` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `bill_ts_main_fk` FOREIGN KEY (`bill_code_id`) REFERENCES `ts_bill_code_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `slot_ts_main_fk` FOREIGN KEY (`slot_id`) REFERENCES `ts_slot_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_ts_main_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;

LOCK TABLES `ts_main` WRITE;
/*!40000 ALTER TABLE `ts_main` DISABLE KEYS */;

INSERT INTO `ts_main` (`id`, `user_id`, `bill_code_id`, `slot_id`, `value`, `inactive_ind`, `comment`, `approval_id`)
VALUES
	(39,1,1,27,0,'Y','123123sdf',NULL),
	(40,1,2,27,12,'Y','',NULL),
	(41,1,2,27,12,'N','sdfsdf',NULL),
	(42,1,2,28,12,'N','sdfgdfgsdfsdf',NULL),
	(43,1,1,6,1,'N','sdfsdf',NULL),
	(44,1,2,8,2,'N','sdfsdf',NULL),
	(45,1,3,12,3,'N','fgdfg',NULL),
	(46,1,1,41,8,'N','sdfsdf',1),
	(47,1,2,38,8,'N','do something',1),
	(48,1,4,37,8,'N','haha',1),
	(49,1,5,39,2,'N','gaga',1),
	(50,1,6,40,1,'N','sdfsdf',1),
	(51,1,8,39,6,'N','hrhr',1),
	(52,1,9,40,7,'N','sdfsdf',1);

/*!40000 ALTER TABLE `ts_main` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ts_period_um_lookup
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ts_period_um_lookup`;

CREATE TABLE `ts_period_um_lookup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

LOCK TABLES `ts_period_um_lookup` WRITE;
/*!40000 ALTER TABLE `ts_period_um_lookup` DISABLE KEYS */;

INSERT INTO `ts_period_um_lookup` (`id`, `start_date`, `end_date`, `inactive_ind`)
VALUES
	(1,'2016-08-09 00:00:00','2016-09-09 00:00:00','N');

/*!40000 ALTER TABLE `ts_period_um_lookup` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ts_slot_lookup
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ts_slot_lookup`;

CREATE TABLE `ts_slot_lookup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `is_week_day` varchar(1) DEFAULT 'Y',
  `period_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `period_slot_lookup_fk_idx` (`period_id`),
  CONSTRAINT `period_slot_lookup_fk` FOREIGN KEY (`period_id`) REFERENCES `ts_period_um_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

LOCK TABLES `ts_slot_lookup` WRITE;
/*!40000 ALTER TABLE `ts_slot_lookup` DISABLE KEYS */;

INSERT INTO `ts_slot_lookup` (`id`, `date`, `is_week_day`, `period_id`)
VALUES
	(5,'2016-10-03 00:00:00',NULL,NULL),
	(6,'2016-10-24 00:00:00',NULL,NULL),
	(7,'2016-10-26 00:00:00',NULL,NULL),
	(8,'2016-10-27 00:00:00',NULL,NULL),
	(9,'2016-10-28 00:00:00',NULL,NULL),
	(10,'2016-10-30 00:00:00',NULL,NULL),
	(11,'2016-10-25 00:00:00',NULL,NULL),
	(12,'2016-10-29 00:00:00',NULL,NULL),
	(13,'2016-10-17 00:00:00',NULL,NULL),
	(14,'2016-10-20 00:00:00',NULL,NULL),
	(15,'2016-10-18 00:00:00',NULL,NULL),
	(16,'2016-10-19 00:00:00',NULL,NULL),
	(17,'2016-10-04 00:00:00',NULL,NULL),
	(18,'2016-10-05 00:00:00',NULL,NULL),
	(19,'2016-10-06 00:00:00',NULL,NULL),
	(20,'2016-10-07 00:00:00',NULL,NULL),
	(21,'2016-10-08 00:00:00',NULL,NULL),
	(22,'2016-10-10 00:00:00',NULL,NULL),
	(23,'2016-10-14 00:00:00',NULL,NULL),
	(24,'2016-10-11 00:00:00',NULL,NULL),
	(25,'2016-10-13 00:00:00',NULL,NULL),
	(26,'2016-10-12 00:00:00',NULL,NULL),
	(27,'2016-10-31 00:00:00',NULL,NULL),
	(28,'2016-11-01 00:00:00',NULL,NULL),
	(29,'2016-11-02 00:00:00',NULL,NULL),
	(30,'2016-11-03 00:00:00',NULL,NULL),
	(31,'2016-11-04 00:00:00',NULL,NULL),
	(32,'2016-11-05 00:00:00',NULL,NULL),
	(33,'2016-11-06 00:00:00',NULL,NULL),
	(34,'2016-10-21 00:00:00',NULL,NULL),
	(35,'2016-10-22 00:00:00',NULL,NULL),
	(36,'2016-10-23 00:00:00',NULL,NULL),
	(37,'2016-12-12 00:00:00',NULL,NULL),
	(38,'2016-12-13 00:00:00',NULL,NULL),
	(39,'2016-12-14 00:00:00',NULL,NULL),
	(40,'2016-12-15 00:00:00',NULL,NULL),
	(41,'2016-12-16 00:00:00',NULL,NULL),
	(42,'2016-12-17 00:00:00',NULL,NULL),
	(43,'2016-12-18 00:00:00',NULL,NULL);

/*!40000 ALTER TABLE `ts_slot_lookup` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ts_user_enrollment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ts_user_enrollment`;

CREATE TABLE `ts_user_enrollment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`),
  KEY `ts_user_erlmt_user_id_fk` (`user_id`),
  CONSTRAINT `ts_user_erlmt_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

LOCK TABLES `ts_user_enrollment` WRITE;
/*!40000 ALTER TABLE `ts_user_enrollment` DISABLE KEYS */;

INSERT INTO `ts_user_enrollment` (`id`, `user_id`, `start_date`, `end_date`, `inactive_ind`)
VALUES
	(1,1,'2016-09-26 00:00:00','2016-10-31 00:00:00','N');

/*!40000 ALTER TABLE `ts_user_enrollment` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(70) NOT NULL,
  `inactive_ind` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `user_name`, `password`, `inactive_ind`)
VALUES
	(1,'zdwrz','$2a$10$2NmsRAHeGV3eBGg5/TkEZOVLzm2n3FALmGR6h0gLiyrn9KI5/R/w2','N'),
	(2,'admin','$2a$10$AmxSDpnYU/ejgolimS8ohuc8bT5Glq3/D3Yz/G.Awoa/97ENzVtYq','N');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_detail
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_detail`;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

LOCK TABLES `user_detail` WRITE;
/*!40000 ALTER TABLE `user_detail` DISABLE KEYS */;

INSERT INTO `user_detail` (`id`, `user_id`, `first_name`, `last_name`, `mid_init`, `phone`, `email`, `home_address`, `inactive_ind`)
VALUES
	(1,1,'Dawei','Zhuang',NULL,'11','z@gmail.com','address test','N'),
	(2,2,'Jeff','Tan',NULL,'23123','dsf@sd.com','test address1','N');

/*!40000 ALTER TABLE `user_detail` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_doc_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_doc_info`;

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
  CONSTRAINT `user_doc_type_admin_lookup_fk` FOREIGN KEY (`doc_type`) REFERENCES `admin_lookup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id_doc_info_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

LOCK TABLES `user_doc_info` WRITE;
/*!40000 ALTER TABLE `user_doc_info` DISABLE KEYS */;

INSERT INTO `user_doc_info` (`id`, `doc_type`, `doc_name`, `file_type`, `file_location`, `upload_time`, `inactive_ind`, `user_id`)
VALUES
	(27,17,'11_03_2016_13_23_33!ts.pdf','application/pdf','/Users/daweizhuang/workspace/apache-tomcat-8.0.29/bin/tempFile/ts/1/11_03_2016_13_23_33!ts.pdf','2016-11-03 13:23:34','N',1),
	(28,17,'11_03_2016_15_57_56!ts.pdf','application/pdf','/Users/daweizhuang/workspace/apache-tomcat-8.0.29/bin/tempFile/ts/1/11_03_2016_15_57_56!ts.pdf','2016-11-03 15:57:57','N',1),
	(29,17,'11_04_2016_14_40_33!ts.pdf','application/pdf','/Users/daweizhuang/workspace/apache-tomcat-8.0.29/bin/tempFile/ts/1/11_04_2016_14_40_33!ts.pdf','2016-11-04 14:40:34','N',1),
	(30,17,'11_04_2016_14_43_38!ts.pdf','application/pdf','/Users/daweizhuang/workspace/apache-tomcat-8.0.29/bin/tempFile/ts/1/11_04_2016_14_43_38!ts.pdf','2016-11-04 14:43:39','N',1),
	(31,17,'11_04_2016_14_44_02!ts.pdf','application/pdf','/Users/daweizhuang/workspace/apache-tomcat-8.0.29/bin/tempFile/ts/1/11_04_2016_14_44_02!ts.pdf','2016-11-04 14:44:03','N',1),
	(32,17,'11_04_2016_15_39_56!ts.pdf','application/pdf','/Users/daweizhuang/workspace/apache-tomcat-8.0.29/bin/tempFile/ts/1/11_04_2016_15_39_56!ts.pdf','2016-11-04 15:39:57','N',1),
	(33,17,'11_04_2016_15_48_59!ts.pdf','application/pdf','/Users/daweizhuang/workspace/apache-tomcat-8.0.29/bin/tempFile/ts/1/11_04_2016_15_48_59!ts.pdf','2016-11-04 15:49:00','N',1),
	(34,17,'01_17_2017_17_06_31!ts.pdf','application/pdf','/Users/daweizhuang/workspace/apache-tomcat-8.0.29/bin/tempFile/ts/1/01_17_2017_17_06_30!ts.pdf','2017-01-17 17:06:31','N',1),
	(35,17,'01_17_2017_17_15_54!ts.pdf','application/pdf','/Users/daweizhuang/workspace/apache-tomcat-8.0.29/bin/tempFile/ts/1/01_17_2017_17_15_54!ts.pdf','2017-01-17 17:15:54','N',1);

/*!40000 ALTER TABLE `user_doc_info` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_role`;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;

INSERT INTO `user_role` (`id`, `user_id`, `enterprise_id`, `role_id`, `inactive_ind`, `position_id`, `start_date`, `end_date`)
VALUES
	(1,1,1,3,'N',2,NULL,NULL),
	(2,1,1,1,'N',2,NULL,NULL),
	(3,2,1,3,'N',2,NULL,NULL);

/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
