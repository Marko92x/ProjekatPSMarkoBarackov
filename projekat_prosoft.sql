/*
SQLyog Community v12.09 (64 bit)
MySQL - 5.6.17 : Database - projekat_prosoft
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`projekat_prosoft` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_croatian_ci */;

USE `projekat_prosoft`;

/*Table structure for table `administrativniradnik` */

DROP TABLE IF EXISTS `administrativniradnik`;

CREATE TABLE `administrativniradnik` (
  `administrativniradnikid` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(100) COLLATE utf8_croatian_ci NOT NULL,
  `prezime` varchar(100) COLLATE utf8_croatian_ci NOT NULL,
  `korisnickoime` varchar(100) COLLATE utf8_croatian_ci NOT NULL,
  `korisnickasifra` varchar(100) COLLATE utf8_croatian_ci NOT NULL,
  PRIMARY KEY (`administrativniradnikid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

/*Data for the table `administrativniradnik` */

insert  into `administrativniradnik`(`administrativniradnikid`,`ime`,`prezime`,`korisnickoime`,`korisnickasifra`) values (1,'Marko','Baračkov','mare','mikimaus'),(2,'Lazar','Blanuša','laki','minimaus'),(3,'Ognjen','Ašković','ogi','somina'),(4,'Ivan','Aracki','raca','glavonja'),(5,'Ana','Adamović','anna','allegra');

/*Table structure for table `dnevnaberba` */

DROP TABLE IF EXISTS `dnevnaberba`;

CREATE TABLE `dnevnaberba` (
  `jmbg` varchar(100) COLLATE utf8_croatian_ci NOT NULL,
  `dnevnaberbaid` int(11) NOT NULL AUTO_INCREMENT,
  `datum` date DEFAULT NULL,
  PRIMARY KEY (`jmbg`,`dnevnaberbaid`),
  KEY `dnevnaberbaid` (`dnevnaberbaid`),
  CONSTRAINT `dnevnaberba_ibfk_1` FOREIGN KEY (`jmbg`) REFERENCES `dobavljac` (`jmbg`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

/*Data for the table `dnevnaberba` */

/*Table structure for table `dobavljac` */

DROP TABLE IF EXISTS `dobavljac`;

CREATE TABLE `dobavljac` (
  `jmbg` varchar(100) COLLATE utf8_croatian_ci NOT NULL,
  `brojgazdinstva` varchar(100) COLLATE utf8_croatian_ci NOT NULL,
  `brojlicnekarte` varchar(100) COLLATE utf8_croatian_ci NOT NULL,
  `ime` varchar(100) COLLATE utf8_croatian_ci NOT NULL,
  `prezime` varchar(100) COLLATE utf8_croatian_ci NOT NULL,
  `tekuciracun` varchar(100) COLLATE utf8_croatian_ci NOT NULL,
  `ulica` varchar(100) COLLATE utf8_croatian_ci NOT NULL,
  `broj` varchar(100) COLLATE utf8_croatian_ci NOT NULL,
  `mesto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`jmbg`),
  KEY `mesto` (`mesto`),
  CONSTRAINT `dobavljac_ibfk_1` FOREIGN KEY (`mesto`) REFERENCES `mesto` (`ptt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

/*Data for the table `dobavljac` */

insert  into `dobavljac`(`jmbg`,`brojgazdinstva`,`brojlicnekarte`,`ime`,`prezime`,`tekuciracun`,`ulica`,`broj`,`mesto`) values ('0236965457852','568462','0949084','Zivojin','Arandjelovic','608 - 36001 - 33','Aradska','345',36313),('0323155984561','8465659519','4901951','Milan','Nedovic','408 - 36001 - 33','Egejska','65',11000),('0804156320008','0515940','09540940','Vukasin','Jerovic','408 - 17001 - 94','Djure Strugara','29',25260),('1010101010101','54952957','098406405','Aleksandar','Cvijetinovic','708 - 15501 - 35','Disova','747',26300),('1111111111111','0595190','0641030','Aleksandar','Toskic','608 - 17001 - 94','Brace Nedic','674',11000),('128503263653214','190498045904','0949840','Marko','Blazenovic','208 - 17001 - 94','Laze Ranica','27',36313),('2145896325632','09482490','09460406','Aleksandar','Blazenovic','888 - 20001 - 18','Aradska','7',11000),('2222222222222','0519510989','0940654','Lazar','Askovic','708 - 17001 - 94','Laze Ranica','264',26330),('2323232323232','29429894029489','0408','Vukasin','Sumadinovic','708 - 28501 - 29','Brace Nedic','652',11000),('243424','2342342','234234','234234','23423','234234','24342234234','234234',NULL),('25169851236','32165498','879804','Marko','Adamovic','708 - 36001 - 33','Laze Ranica','76',11000),('3030303030303','52095090','0984061','Ivan','Radjenovic','908 - 15501 - 35','Djure Strugara','857',36313),('324234','324234','2342342',' marko','barackov','23 - 23423423 - 234','jove','34',NULL),('3333333333333','05295048','04940565','Zivojin','Bogunovic','808 - 17001 - 94','Aradska','358',23207),('4040404040404','0594248904','04065406','Vukasin','Milosev','108 - 28501 - 29','Vojvode Vuka','84',26330),('4444444444444','05192980','0954066','Zivkica','Sumadinovic','908 - 17001 - 94','Egejska','214',25260),('45126520003','511982952','094098409','Marko','Blanusa','608 - 26501 - 15','Djure Strugara','22',11000),('4521658789856','090842480','0984064','Marko','Barackov ','908 - 28501 - 29','Djure Strugara','2',25260),('4523226458521','1654','091502','Marko','Nesic','108 - 36001 - 33','Egejska','22',26330),('4525987654321','0261515925','509459','Milica','Jokic','308 - 36001 - 33','2. Oktobar','321',23207),('4578985123215','91520021','0949540954','Katarina','Tomic','908 - 26501 - 15','Egejska','84',11000),('4585123654562','31945165','541915','Lazar','Askovic','908 - 11501 - 07','Djure Strugara','12',11000),('5050505050505','95240845','09490849','Milica','Askovic','208 - 28501 - 29','Egejska','92',25260),('54652132589','54665949','1951','Aleksandar','Blanusa','908 - 12501 - 14','Vojvode Vuka','11',36313),('5555555555555','50295904985','40951090','Milica','Blazenovic','108 - 15501 - 35','Disova','236',11000),('6060606060606','05284054','04643','Marko','Blazenovic','308 - 28501 - 29','Laze Ranica','72',11000),('6666666666666','059542984','09549049','Marko','Jovanovic','208 - 15501 - 35','Vojvode Vuka','237',26300),('7070707070707','019528949','5940654','Lazar','Milutinovic','408 - 28501 - 29','Brace Nedic','84',26300),('7777777777777','059129410','09446060','Vlastimir','Blanusa','308 - 15501 - 35','Djure Strugara','236',36313),('7865158849856','51565928','0549510','Vukasin','Askovic','508 - 36001 - 33','Djure Strugara','46',26330),('78651589156545','26151984','089429840','Lazar','Leban','908 - 36001 - 33','Egejska','77',26300),('78653211230','05192510','0492409','Aleksandar','Sumadinovic','808 - 26501 - 15','Disova','94',26300),('7865321145214','519128912','409849084','Ivan','Nesic','408 - 26501 - 15','Disova','44',26300),('78962130201','15195487','04904049','Vukasin','Udovicic','308 - 26501 - 15','Egejska','66',25260),('7898325652301','216598','984054','Milica','Aracki','108 - 26501 - 15','Ickova','99',25260),('7898456321235','23154984','5094190','Aleksandar','Barisic','808 - 36001 - 33','Vojvode Vuka','88',36313),('8080808080808','095525949051','09846406','Aleksandar','Blanusa','508 - 28501 - 29','Djure Strugara','96',23207),('8502123695482','190549824950','9409549','Zivojin','Nesic','308 - 17001 - 94','2. Oktobar','28',11000),('85201234563','15192826','094940954','Andrijana','Armenovic','708 - 26501 - 15','Vojvode Vuka','43',23207),('8532123965887','05195281709','04994094','Milica','Adamovic','108 - 17001 - 94','Brace Nedic','74',25260),('8585858585858','048246','098406','Aleksandar','Blanusa','808 - 28501 - 29','Disova','92',36313),('879854621456','0261565592','010594','Ognjen','Adamovic','208 - 36001 - 33','Ickova','33',25260),('8888888888888','0541952095','094065464','Ana','Aracki','508 - 17001 - 94','Egejska','456',26300),('8888888888889','0519248001','0940644','Vukasin','Rendulic','408 - 15501 - 35','2. Oktobar','346',11000),('9090909090909','429840','09940','Ana','Rendulic','608 - 28501 - 29','Egejska','749',26330),('963012458745','259918498','04994094','Ognjen','Askovic','508 - 26501 - 15','Aradska','33',26330),('9999999999999','05928945','0544094','Igor','Ristic','508 - 15501 - 35','Ickova','246',23207),('dfadfa','wfadsf','fsdf','sdfsd','sdfsdaf','asdfa','asdfaasdfas','asdfas',NULL);

/*Table structure for table `mesto` */

DROP TABLE IF EXISTS `mesto`;

CREATE TABLE `mesto` (
  `ptt` bigint(20) NOT NULL,
  `naziv` varchar(100) COLLATE utf8_croatian_ci DEFAULT NULL,
  PRIMARY KEY (`ptt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

/*Data for the table `mesto` */

insert  into `mesto`(`ptt`,`naziv`) values (11000,'Beograd'),(23207,'Aradac'),(25260,'Apatin'),(26300,'Vršac'),(26330,'Uljma'),(36313,'Ugao');

/*Table structure for table `stavkadnevneberbe` */

DROP TABLE IF EXISTS `stavkadnevneberbe`;

CREATE TABLE `stavkadnevneberbe` (
  `jmbg` varchar(100) COLLATE utf8_croatian_ci NOT NULL,
  `dnevnaberbaid` int(11) NOT NULL,
  `stavkaid` int(11) NOT NULL AUTO_INCREMENT,
  `tacne` double DEFAULT NULL,
  `prvaklasa` double DEFAULT NULL,
  `drugaklasa` double DEFAULT NULL,
  `trecaklasa` double DEFAULT NULL,
  `cenatacne` double DEFAULT NULL,
  `cenaprvaklasa` double DEFAULT NULL,
  `cenadrugaklasa` double DEFAULT NULL,
  `cenatrecaklasa` double DEFAULT NULL,
  PRIMARY KEY (`jmbg`,`dnevnaberbaid`,`stavkaid`),
  KEY `stavkaid` (`stavkaid`),
  KEY `stavkadnevneberbe_ibfk_2` (`dnevnaberbaid`),
  CONSTRAINT `stavkadnevneberbe_ibfk_1` FOREIGN KEY (`jmbg`) REFERENCES `dnevnaberba` (`jmbg`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stavkadnevneberbe_ibfk_2` FOREIGN KEY (`dnevnaberbaid`) REFERENCES `dnevnaberba` (`dnevnaberbaid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

/*Data for the table `stavkadnevneberbe` */

/*Table structure for table `zaduzenje` */

DROP TABLE IF EXISTS `zaduzenje`;

CREATE TABLE `zaduzenje` (
  `jmbg` varchar(100) COLLATE utf8_croatian_ci NOT NULL,
  `zaduzenjeid` int(11) NOT NULL AUTO_INCREMENT,
  `datumzaduzenja` date DEFAULT NULL,
  `datumrazduzenja` date DEFAULT NULL,
  `kompost` tinyint(1) DEFAULT NULL,
  `prevoz` tinyint(1) DEFAULT NULL,
  `brojvreca` int(11) DEFAULT NULL,
  `zaduzio` int(11) DEFAULT NULL,
  `razduzio` int(11) DEFAULT NULL,
  PRIMARY KEY (`jmbg`,`zaduzenjeid`),
  KEY `zaduzenjeid` (`zaduzenjeid`),
  KEY `zaduzio` (`zaduzio`),
  KEY `razduzio` (`razduzio`),
  CONSTRAINT `zaduzenje_ibfk_1` FOREIGN KEY (`zaduzio`) REFERENCES `administrativniradnik` (`administrativniradnikid`),
  CONSTRAINT `zaduzenje_ibfk_2` FOREIGN KEY (`razduzio`) REFERENCES `administrativniradnik` (`administrativniradnikid`),
  CONSTRAINT `zaduzenje_ibfk_3` FOREIGN KEY (`jmbg`) REFERENCES `dobavljac` (`jmbg`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

/*Data for the table `zaduzenje` */

insert  into `zaduzenje`(`jmbg`,`zaduzenjeid`,`datumzaduzenja`,`datumrazduzenja`,`kompost`,`prevoz`,`brojvreca`,`zaduzio`,`razduzio`) values ('0236965457852',2,'2015-05-07',NULL,0,1,NULL,5,NULL),('25169851236',3,'2015-05-23',NULL,1,0,96,3,NULL),('3333333333333',1,'2015-05-13',NULL,1,0,56,1,NULL),('4525987654321',4,'2015-05-08','2015-05-28',0,1,NULL,2,4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
