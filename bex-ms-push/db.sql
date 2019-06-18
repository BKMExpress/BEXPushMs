CREATE DATABASE pushtest;

USE pushtest;

CREATE TABLE `ALLOWED_TOKENS` (
  `TOKEN` varchar(191) COLLATE utf8_bin NOT NULL,
  `CREATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`TOKEN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `JOB_EXECUTION_LOG` (
  `ID` varchar(30) COLLATE utf8_bin NOT NULL,
  `NAME` varchar(100) COLLATE utf8_bin NOT NULL,
  `EXECUTION_TIME` int(11) DEFAULT NULL,
  `RESULT_CODE` int(11) DEFAULT NULL,
  `RESULT_DESC` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `PUSH_APP_PARAM` (
  `NAME` varchar(50) COLLATE utf8_bin NOT NULL,
  `VALUE` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `DISPLAYNAME` varchar(250) COLLATE utf8_bin NOT NULL,
  `CREATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

insert into `pushtest`.`PUSH_APP_PARAM`(`NAME`,`VALUE`,`DISPLAYNAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values ('bulk_send_count','1','bulk_send_count',null,null,null,null);
insert into `pushtest`.`PUSH_APP_PARAM`(`NAME`,`VALUE`,`DISPLAYNAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values ('environment','TEST','environment','2018-02-21 09:38:03',null,null,null);
insert into `pushtest`.`PUSH_APP_PARAM`(`NAME`,`VALUE`,`DISPLAYNAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values ('file_base_path','/appdata/FILE/PUSH','base path','2018-02-21 09:38:03',null,null,null);
insert into `pushtest`.`PUSH_APP_PARAM`(`NAME`,`VALUE`,`DISPLAYNAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values ('file_process_path','PROCESSED','process path','2018-02-21 09:38:03',null,null,null);
insert into `pushtest`.`PUSH_APP_PARAM`(`NAME`,`VALUE`,`DISPLAYNAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values ('firebase_app_id','APP_ID','firebase_app_id','2018-04-11 00:00:00',null,null,null);
insert into `pushtest`.`PUSH_APP_PARAM`(`NAME`,`VALUE`,`DISPLAYNAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values ('firebase_get_ios_token_url','http://iid.googleapis.com/iid/v1:batchImport','firebase_get_ios_token_url','2018-02-21 09:38:03',null,null,null);
insert into `pushtest`.`PUSH_APP_PARAM`(`NAME`,`VALUE`,`DISPLAYNAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values ('firebase_is_on','True','firebase_is_on','2018-02-21 09:38:03',null,null,null);
insert into `pushtest`.`PUSH_APP_PARAM`(`NAME`,`VALUE`,`DISPLAYNAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values ('firebase_microservice_url','http://ms-push-devops.cloudapps.bkm.local','firebase_microservice_url','2018-04-11 00:00:00',null,null,null);
insert into `pushtest`.`PUSH_APP_PARAM`(`NAME`,`VALUE`,`DISPLAYNAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values ('firebase_push_url','http://fcm.googleapis.com/fcm/send','firebase_push_url','2018-02-21 09:38:03',null,null,null);
insert into `pushtest`.`PUSH_APP_PARAM`(`NAME`,`VALUE`,`DISPLAYNAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values ('firebase_sandbox_mode','True','firebase_sandbox_mode','2018-02-21 09:38:03',null,null,null);
insert into `pushtest`.`PUSH_APP_PARAM`(`NAME`,`VALUE`,`DISPLAYNAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values ('record_process_size','1500','recordsize','2018-02-21 09:38:03',null,null,null);
insert into `pushtest`.`PUSH_APP_PARAM`(`NAME`,`VALUE`,`DISPLAYNAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values ('thread_sleep_time','80','thread sleep time',null,null,null,null);
insert into `pushtest`.`PUSH_APP_PARAM`(`NAME`,`VALUE`,`DISPLAYNAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values ('worker_machine_count','5','worker_machine_count',null,null,null,null);

CREATE TABLE `PUSH_APP_USER` (
  `ID` int(3) NOT NULL,
  `INDICATOR` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `APP_KEY` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `APP_BUNDLE` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


insert into `pushtest`.`PUSH_APP_USER`(`ID`,`INDICATOR`,`NAME`,`APP_KEY`,`APP_BUNDLE`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values (1,'123456','BKMExpress','API_KEY','API_BUNDLE','2018-02-23 00:00:00',null,null,null);


CREATE TABLE `PUSH_LOG` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `APP_ID` int(3) NOT NULL,
  `TOKEN` varchar(180) COLLATE utf8mb4_turkish_ci NOT NULL,
  `MESSAGE` varchar(2000) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `DEVICETYPE` int(1) DEFAULT NULL,
  `IS_SHOW` varchar(1) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `PUSH_TYPE_ID` int(11) DEFAULT NULL,
  `PUSH_SUBTYPE_ID` int(11) DEFAULT NULL,
  `IS_SEND` varchar(1) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `PUSH_RECORD_ID` varchar(30) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(100) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX_PUSH_LOG_TOKEN` (`TOKEN`),
  KEY `IDX_PUSH_LOG_RECORD_ID` (`PUSH_RECORD_ID`),
  KEY `IDX_PUSH_LOG_CREATED_DATE` (`CREATED_DATE`)
) ENGINE=InnoDB AUTO_INCREMENT=159540 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

CREATE TABLE `PUSH_MASTER` (
  `ID` varchar(30) COLLATE utf8mb4_turkish_ci NOT NULL,
  `NAME` varchar(100) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `DESCRIPTION` varchar(1000) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `RECORD_COUNT` int(11) DEFAULT NULL,
  `VALIDATE_DATE` datetime DEFAULT NULL,
  `PROCESS_DATE` datetime DEFAULT NULL,
  `STARTTIME` datetime DEFAULT NULL,
  `ENDTIME` datetime DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `SUCCESS` int(11) DEFAULT NULL,
  `FAILED` int(11) DEFAULT NULL,
  `INVALID` int(11) DEFAULT NULL,
  `MESSAGE` varchar(1000) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(100) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

CREATE TABLE `PUSH_RECORD` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `PUSH_MASTER_ID` varchar(30) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `LINE_NO` int(11) DEFAULT NULL,
  `LINE` varchar(2000) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `VALIDATED` int(11) DEFAULT NULL,
  `PROCESSED` int(11) DEFAULT NULL,
  `ERROR_DESC` varchar(2000) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `JOB_SEQ` varchar(30) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `PROCESS_TIME` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(100) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX_PUSH_RECORD_JOB_SEQ` (`JOB_SEQ`),
  KEY `IDX_PUSH_RECORD_MASTER_ID` (`PUSH_MASTER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=326572 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

CREATE TABLE `PUSH_SUBTYPE` (
  `ID` int(11) NOT NULL,
  `PUSH_TYPE_ID` int(11) NOT NULL,
  `NAME` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

insert into `pushtest`.`PUSH_SUBTYPE`(`ID`,`PUSH_TYPE_ID`,`NAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values (1,1,'RATING_ADD_CARD','2018-04-26 15:40:18',null,null,null);
insert into `pushtest`.`PUSH_SUBTYPE`(`ID`,`PUSH_TYPE_ID`,`NAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values (2,1,'RATING_QR_PAYMENT','2018-04-26 15:40:18',null,null,null);
insert into `pushtest`.`PUSH_SUBTYPE`(`ID`,`PUSH_TYPE_ID`,`NAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values (3,1,'RATING_DONATION','2018-04-26 15:40:18',null,null,null);
insert into `pushtest`.`PUSH_SUBTYPE`(`ID`,`PUSH_TYPE_ID`,`NAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values (4,1,'RATING_SEND_MONEY','2018-04-26 15:40:18',null,null,null);
insert into `pushtest`.`PUSH_SUBTYPE`(`ID`,`PUSH_TYPE_ID`,`NAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values (5,1,'RATING_CAMPAIGN','2018-04-26 15:40:18',null,null,null);
insert into `pushtest`.`PUSH_SUBTYPE`(`ID`,`PUSH_TYPE_ID`,`NAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values (6,1,'RATING_PAYMENT','2018-04-26 15:40:18',null,null,null);
insert into `pushtest`.`PUSH_SUBTYPE`(`ID`,`PUSH_TYPE_ID`,`NAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values (7,2,'NONE','2018-04-26 15:40:18',null,null,null);
insert into `pushtest`.`PUSH_SUBTYPE`(`ID`,`PUSH_TYPE_ID`,`NAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values (8,3,'REQUEST_MONEY','2018-04-26 15:40:18',null,null,null);
insert into `pushtest`.`PUSH_SUBTYPE`(`ID`,`PUSH_TYPE_ID`,`NAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values (9,0,'CAMPAIGN','2018-04-26 15:40:18',null,null,null);
insert into `pushtest`.`PUSH_SUBTYPE`(`ID`,`PUSH_TYPE_ID`,`NAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values (10,4,'HCE_PAYMENT_SUCCESS','2018-06-06 15:40:18',null,null,null);
insert into `pushtest`.`PUSH_SUBTYPE`(`ID`,`PUSH_TYPE_ID`,`NAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values (11,5,'PURCHASE_SUCCESS','2018-06-19 00:00:00',null,null,null);

CREATE TABLE `PUSH_TYPE` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

insert into `pushtest`.`PUSH_TYPE`(`ID`,`NAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values (0,'Campaign','2018-04-26 15:40:18',null,null,null);
insert into `pushtest`.`PUSH_TYPE`(`ID`,`NAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values (1,'Rate','2018-04-26 15:40:18',null,null,null);
insert into `pushtest`.`PUSH_TYPE`(`ID`,`NAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values (2,'Message','2018-04-26 15:40:18',null,null,null);
insert into `pushtest`.`PUSH_TYPE`(`ID`,`NAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values (3,'RequestMoney','2018-04-26 15:40:18',null,null,null);
insert into `pushtest`.`PUSH_TYPE`(`ID`,`NAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values (4,'HCEPaymentSuccess','2018-06-06 15:40:18',null,null,null);
insert into `pushtest`.`PUSH_TYPE`(`ID`,`NAME`,`CREATED_DATE`,`CREATED_BY`,`UPDATED_DATE`,`UPDATED_BY`) values (5,'Payment','2018-06-19 00:00:00',null,null,null);



