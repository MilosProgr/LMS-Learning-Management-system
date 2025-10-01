-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: lms-portal
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `adresa`
--

DROP TABLE IF EXISTS `adresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adresa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `broj` varchar(255) DEFAULT NULL,
  `ulica` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adresa`
--

LOCK TABLES `adresa` WRITE;
/*!40000 ALTER TABLE `adresa` DISABLE KEYS */;
INSERT INTO `adresa` VALUES (1,'7A','Beogradska ulica 24'),(2,'4L','Micurinova');
/*!40000 ALTER TABLE `adresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dekan`
--

DROP TABLE IF EXISTS `dekan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dekan` (
  `id` bigint NOT NULL,
  `period` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKomgmoaa3elccxb3y9k1494b2o` FOREIGN KEY (`id`) REFERENCES `nastavnik` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dekan`
--

LOCK TABLES `dekan` WRITE;
/*!40000 ALTER TABLE `dekan` DISABLE KEYS */;
INSERT INTO `dekan` VALUES (1,'sda');
/*!40000 ALTER TABLE `dekan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluacija_znanja`
--

DROP TABLE IF EXISTS `evaluacija_znanja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluacija_znanja` (
  `bodovi` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `prijavljen_ispit_id` bigint DEFAULT NULL,
  `realizacija_predmeta_id` bigint DEFAULT NULL,
  `vreme_pocetka` datetime(6) DEFAULT NULL,
  `vreme_zavrsetka` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_l8jkc2tjp7m7h5svfm2l4438x` (`prijavljen_ispit_id`),
  KEY `FKmicppx3e9v4gl9wn4i6l46v7p` (`realizacija_predmeta_id`),
  CONSTRAINT `FKmicppx3e9v4gl9wn4i6l46v7p` FOREIGN KEY (`realizacija_predmeta_id`) REFERENCES `realizacija_predmeta` (`id`),
  CONSTRAINT `FKpahetstodolcp66puq8p9nu2v` FOREIGN KEY (`prijavljen_ispit_id`) REFERENCES `prijavljeni_ispit` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluacija_znanja`
--

LOCK TABLES `evaluacija_znanja` WRITE;
/*!40000 ALTER TABLE `evaluacija_znanja` DISABLE KEYS */;
INSERT INTO `evaluacija_znanja` VALUES (30,1,1,1,'2022-04-14 00:00:00.000000','2022-04-15 00:00:00.000000');
/*!40000 ALTER TABLE `evaluacija_znanja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fakultet`
--

DROP TABLE IF EXISTS `fakultet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fakultet` (
  `dekan_id` bigint DEFAULT NULL,
  `fakultet_adresa_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `univerzitet_id` bigint NOT NULL,
  `naziv` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tbfrr0newauh6c2ko3jfiebnr` (`dekan_id`),
  UNIQUE KEY `UK_6cagrc9hfua0rrq03basjenhm` (`fakultet_adresa_id`),
  KEY `FKc9lc5sekwpb19qpobc7eegjpn` (`univerzitet_id`),
  CONSTRAINT `FK54ssqlas0dk7iwf0mpexga9xb` FOREIGN KEY (`dekan_id`) REFERENCES `dekan` (`id`),
  CONSTRAINT `FK5s9wcaq7me95qdl3lq50e3cc4` FOREIGN KEY (`fakultet_adresa_id`) REFERENCES `adresa` (`id`),
  CONSTRAINT `FKc9lc5sekwpb19qpobc7eegjpn` FOREIGN KEY (`univerzitet_id`) REFERENCES `univerzitet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fakultet`
--

LOCK TABLES `fakultet` WRITE;
/*!40000 ALTER TABLE `fakultet` DISABLE KEYS */;
INSERT INTO `fakultet` VALUES (1,2,1,1,'Centar Novi Sad');
/*!40000 ALTER TABLE `fakultet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `godina_studija`
--

DROP TABLE IF EXISTS `godina_studija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `godina_studija` (
  `godina` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `studijski_program_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK365x07l7paf40u1xlbabq84gq` (`studijski_program_id`),
  CONSTRAINT `FK365x07l7paf40u1xlbabq84gq` FOREIGN KEY (`studijski_program_id`) REFERENCES `studijski_program` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `godina_studija`
--

LOCK TABLES `godina_studija` WRITE;
/*!40000 ALTER TABLE `godina_studija` DISABLE KEYS */;
INSERT INTO `godina_studija` VALUES ('2018-04-25 00:00:00.000000',1,1);
/*!40000 ALTER TABLE `godina_studija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kurs`
--

DROP TABLE IF EXISTS `kurs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kurs` (
  `trajanje` int DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) DEFAULT NULL,
  `oznaka` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kurs`
--

LOCK TABLES `kurs` WRITE;
/*!40000 ALTER TABLE `kurs` DISABLE KEYS */;
INSERT INTO `kurs` VALUES (14,1,'Osnove Programiranja','OP');
/*!40000 ALTER TABLE `kurs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nastavnik`
--

DROP TABLE IF EXISTS `nastavnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nastavnik` (
  `fakultet_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL,
  `biografija` varchar(255) DEFAULT NULL,
  `ime` varchar(255) DEFAULT NULL,
  `jmbg` varchar(255) DEFAULT NULL,
  `prezime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1mc9n5u8xb65qqa89dxoym998` (`fakultet_id`),
  CONSTRAINT `FK1mc9n5u8xb65qqa89dxoym998` FOREIGN KEY (`fakultet_id`) REFERENCES `fakultet` (`id`),
  CONSTRAINT `FK4gay61j477khc9m2lex1jjjnx` FOREIGN KEY (`id`) REFERENCES `registrovani_korisnik` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nastavnik`
--

LOCK TABLES `nastavnik` WRITE;
/*!40000 ALTER TABLE `nastavnik` DISABLE KEYS */;
INSERT INTO `nastavnik` VALUES (1,1,'Ovo je nastavnik','Djordje','s4ds4d','Ov');
/*!40000 ALTER TABLE `nastavnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nastavnik_na_realizaciji`
--

DROP TABLE IF EXISTS `nastavnik_na_realizaciji`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nastavnik_na_realizaciji` (
  `broj_casova` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nastavnik_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnevmktxs4agqf99pqqqlaeuhg` (`nastavnik_id`),
  CONSTRAINT `FKnevmktxs4agqf99pqqqlaeuhg` FOREIGN KEY (`nastavnik_id`) REFERENCES `nastavnik` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nastavnik_na_realizaciji`
--

LOCK TABLES `nastavnik_na_realizaciji` WRITE;
/*!40000 ALTER TABLE `nastavnik_na_realizaciji` DISABLE KEYS */;
INSERT INTO `nastavnik_na_realizaciji` VALUES (20,1,1);
/*!40000 ALTER TABLE `nastavnik_na_realizaciji` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'ROLE_NASTAVNIK');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pohadjanje_predmeta`
--

DROP TABLE IF EXISTS `pohadjanje_predmeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pohadjanje_predmeta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `konacna_ocena` bigint DEFAULT NULL,
  `realizacija_predmeta_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ivouw18jq8ya9jgcn86twnqmn` (`realizacija_predmeta_id`),
  CONSTRAINT `FKskigfrejfgiob4xr8ai7w65xu` FOREIGN KEY (`realizacija_predmeta_id`) REFERENCES `realizacija_predmeta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pohadjanje_predmeta`
--

LOCK TABLES `pohadjanje_predmeta` WRITE;
/*!40000 ALTER TABLE `pohadjanje_predmeta` DISABLE KEYS */;
INSERT INTO `pohadjanje_predmeta` VALUES (1,8,1);
/*!40000 ALTER TABLE `pohadjanje_predmeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pohadjanje_predmeta_studenti`
--

DROP TABLE IF EXISTS `pohadjanje_predmeta_studenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pohadjanje_predmeta_studenti` (
  `pohadjanje_predmeta_id` bigint NOT NULL,
  `studenti_id` bigint NOT NULL,
  KEY `FKru2m1b5wvyf9nqpgc9udx2tck` (`studenti_id`),
  KEY `FKivmp1oj6dw92dnubpdexdqts3` (`pohadjanje_predmeta_id`),
  CONSTRAINT `FKivmp1oj6dw92dnubpdexdqts3` FOREIGN KEY (`pohadjanje_predmeta_id`) REFERENCES `pohadjanje_predmeta` (`id`),
  CONSTRAINT `FKru2m1b5wvyf9nqpgc9udx2tck` FOREIGN KEY (`studenti_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pohadjanje_predmeta_studenti`
--

LOCK TABLES `pohadjanje_predmeta_studenti` WRITE;
/*!40000 ALTER TABLE `pohadjanje_predmeta_studenti` DISABLE KEYS */;
/*!40000 ALTER TABLE `pohadjanje_predmeta_studenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `polaganje`
--

DROP TABLE IF EXISTS `polaganje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `polaganje` (
  `bodovi` bigint DEFAULT NULL,
  `evaluacija_znanja_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_na_godini_id` bigint DEFAULT NULL,
  `napomena` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6wk4uvntty7l92l0i4y44ugyg` (`evaluacija_znanja_id`),
  KEY `FKk3is5klk83tliwxm5yy2yvqgg` (`student_na_godini_id`),
  CONSTRAINT `FKk3is5klk83tliwxm5yy2yvqgg` FOREIGN KEY (`student_na_godini_id`) REFERENCES `student_na_godini` (`id`),
  CONSTRAINT `FKovo8yfdimt6l74kvcaih5l9q5` FOREIGN KEY (`evaluacija_znanja_id`) REFERENCES `evaluacija_znanja` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `polaganje`
--

LOCK TABLES `polaganje` WRITE;
/*!40000 ALTER TABLE `polaganje` DISABLE KEYS */;
INSERT INTO `polaganje` VALUES (30,1,1,1,'Nema Prepisivanja');
/*!40000 ALTER TABLE `polaganje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `predmet`
--

DROP TABLE IF EXISTS `predmet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `predmet` (
  `broj_predavanja` int DEFAULT NULL,
  `broj_vezbi` int DEFAULT NULL,
  `drugi_oblici_nastave` int DEFAULT NULL,
  `esbn` int DEFAULT NULL,
  `istrazivacki_rad` int DEFAULT NULL,
  `obavezan` tinyint(1) NOT NULL,
  `ostali_casovi` int DEFAULT NULL,
  `godina_studija_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `naziv` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK37thl4qshgxy3wornrfek2ji3` (`godina_studija_id`),
  CONSTRAINT `FK37thl4qshgxy3wornrfek2ji3` FOREIGN KEY (`godina_studija_id`) REFERENCES `godina_studija` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `predmet`
--

LOCK TABLES `predmet` WRITE;
/*!40000 ALTER TABLE `predmet` DISABLE KEYS */;
INSERT INTO `predmet` VALUES (12,12,4,8,1,1,5,1,1,'Funkcionalno programiranje');
/*!40000 ALTER TABLE `predmet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `predmet_kurs`
--

DROP TABLE IF EXISTS `predmet_kurs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `predmet_kurs` (
  `kurs_id` bigint NOT NULL,
  `predmet_id` bigint NOT NULL,
  PRIMARY KEY (`kurs_id`,`predmet_id`),
  KEY `FK9o8t9be5sxm530eamcf6qf22j` (`predmet_id`),
  CONSTRAINT `FK5aa260775efy19t7bc1syxluw` FOREIGN KEY (`kurs_id`) REFERENCES `kurs` (`id`),
  CONSTRAINT `FK9o8t9be5sxm530eamcf6qf22j` FOREIGN KEY (`predmet_id`) REFERENCES `predmet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `predmet_kurs`
--

LOCK TABLES `predmet_kurs` WRITE;
/*!40000 ALTER TABLE `predmet_kurs` DISABLE KEYS */;
INSERT INTO `predmet_kurs` VALUES (1,1);
/*!40000 ALTER TABLE `predmet_kurs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prijavljeni_ispit`
--

DROP TABLE IF EXISTS `prijavljeni_ispit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prijavljeni_ispit` (
  `prijavljen` bit(1) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKimjffmts8pafb4gdn3hh2vb7m` (`student_id`),
  CONSTRAINT `FKimjffmts8pafb4gdn3hh2vb7m` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prijavljeni_ispit`
--

LOCK TABLES `prijavljeni_ispit` WRITE;
/*!40000 ALTER TABLE `prijavljeni_ispit` DISABLE KEYS */;
INSERT INTO `prijavljeni_ispit` VALUES (_binary '',1,1);
/*!40000 ALTER TABLE `prijavljeni_ispit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `realizacija_predmeta`
--

DROP TABLE IF EXISTS `realizacija_predmeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `realizacija_predmeta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nastavnik_na_realizaciji_id` bigint DEFAULT NULL,
  `predmet_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3cs6pjrmrybtbvc3byap5fwag` (`nastavnik_na_realizaciji_id`),
  UNIQUE KEY `UK_7cwqadmme2i3b1rlatgc23arc` (`predmet_id`),
  CONSTRAINT `FKrw6tx0pbaevbvs89psuqfqijx` FOREIGN KEY (`predmet_id`) REFERENCES `predmet` (`id`),
  CONSTRAINT `FKswpedesc75wxrgn9jms3sxqov` FOREIGN KEY (`nastavnik_na_realizaciji_id`) REFERENCES `nastavnik_na_realizaciji` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `realizacija_predmeta`
--

LOCK TABLES `realizacija_predmeta` WRITE;
/*!40000 ALTER TABLE `realizacija_predmeta` DISABLE KEYS */;
INSERT INTO `realizacija_predmeta` VALUES (1,1,1);
/*!40000 ALTER TABLE `realizacija_predmeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registrovani_korisnik`
--

DROP TABLE IF EXISTS `registrovani_korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registrovani_korisnik` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `korisnicko_ime` varchar(255) DEFAULT NULL,
  `lozinka` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registrovani_korisnik`
--

LOCK TABLES `registrovani_korisnik` WRITE;
/*!40000 ALTER TABLE `registrovani_korisnik` DISABLE KEYS */;
INSERT INTO `registrovani_korisnik` VALUES (1,'NoviKorisnik','{bcrypt}$2a$10$tOzd79W4u9GzmtXF/ziRh.FkjVaJCx74zSS/rvyH.J9mupfF51iMm');
/*!40000 ALTER TABLE `registrovani_korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` bigint NOT NULL,
  `ime` varchar(255) DEFAULT NULL,
  `jmbg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7ncqtgdldfpvpsvsc61yfvmb6` (`jmbg`),
  CONSTRAINT `FK28vj27r54micmnk29pgnpxvrj` FOREIGN KEY (`id`) REFERENCES `registrovani_korisnik` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Milorad','d4s5');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_na_godini`
--

DROP TABLE IF EXISTS `student_na_godini`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_na_godini` (
  `datum_upisa` datetime(6) DEFAULT NULL,
  `godina_studija_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL,
  `student_id` bigint DEFAULT NULL,
  `broj_indeksa` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_c7gt5khnrm1ugj95y12comeeo` (`student_id`),
  KEY `FKa0qj2cperexvshppcmuh1a1s7` (`godina_studija_id`),
  CONSTRAINT `FKa0qj2cperexvshppcmuh1a1s7` FOREIGN KEY (`godina_studija_id`) REFERENCES `godina_studija` (`id`),
  CONSTRAINT `FKmu7rrfp6rrd3ds2i229h46d8l` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_na_godini`
--

LOCK TABLES `student_na_godini` WRITE;
/*!40000 ALTER TABLE `student_na_godini` DISABLE KEYS */;
INSERT INTO `student_na_godini` VALUES ('2017-06-25 00:00:00.000000',1,1,1,'2017896541');
/*!40000 ALTER TABLE `student_na_godini` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_na_godini_seq`
--

DROP TABLE IF EXISTS `student_na_godini_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_na_godini_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_na_godini_seq`
--

LOCK TABLES `student_na_godini_seq` WRITE;
/*!40000 ALTER TABLE `student_na_godini_seq` DISABLE KEYS */;
INSERT INTO `student_na_godini_seq` VALUES (1);
/*!40000 ALTER TABLE `student_na_godini_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studijski_program`
--

DROP TABLE IF EXISTS `studijski_program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studijski_program` (
  `fakultet_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `godina_studija` varchar(255) DEFAULT NULL,
  `naziv` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK303wuewako66hti92rcv4a9mh` (`fakultet_id`),
  CONSTRAINT `FK303wuewako66hti92rcv4a9mh` FOREIGN KEY (`fakultet_id`) REFERENCES `fakultet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studijski_program`
--

LOCK TABLES `studijski_program` WRITE;
/*!40000 ALTER TABLE `studijski_program` DISABLE KEYS */;
INSERT INTO `studijski_program` VALUES (1,1,'2018-04-24','SII');
/*!40000 ALTER TABLE `studijski_program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `termin_nastave`
--

DROP TABLE IF EXISTS `termin_nastave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `termin_nastave` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `realizacija_predmeta_id` bigint DEFAULT NULL,
  `tip_nastave_id` bigint DEFAULT NULL,
  `vreme_pocetka` datetime(6) DEFAULT NULL,
  `vreme_zavrsetka` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gx6i7qeweetm3twjl14tejbg` (`realizacija_predmeta_id`),
  UNIQUE KEY `UK_6fgdiwqup664tlnb00ej4l80u` (`tip_nastave_id`),
  CONSTRAINT `FKasrq9uj8dmfa2dtyycxclwwwu` FOREIGN KEY (`realizacija_predmeta_id`) REFERENCES `realizacija_predmeta` (`id`),
  CONSTRAINT `FKbpd7rs6wsdq5aoxutgqhqm93f` FOREIGN KEY (`tip_nastave_id`) REFERENCES `tip_nastave` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `termin_nastave`
--

LOCK TABLES `termin_nastave` WRITE;
/*!40000 ALTER TABLE `termin_nastave` DISABLE KEYS */;
INSERT INTO `termin_nastave` VALUES (1,1,1,'2022-04-24 00:00:00.000000','2022-04-24 00:00:00.000000');
/*!40000 ALTER TABLE `termin_nastave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tip_evaluacije`
--

DROP TABLE IF EXISTS `tip_evaluacije`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tip_evaluacije` (
  `evaluacija_znanja_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3m2i15ddw6t5qk5rkxbnmh6w` (`evaluacija_znanja_id`),
  CONSTRAINT `FKtc8yfl8f96qi64su0o87a9b6q` FOREIGN KEY (`evaluacija_znanja_id`) REFERENCES `evaluacija_znanja` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tip_evaluacije`
--

LOCK TABLES `tip_evaluacije` WRITE;
/*!40000 ALTER TABLE `tip_evaluacije` DISABLE KEYS */;
INSERT INTO `tip_evaluacije` VALUES (1,1,'Usmeno');
/*!40000 ALTER TABLE `tip_evaluacije` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tip_nastave`
--

DROP TABLE IF EXISTS `tip_nastave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tip_nastave` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nastavnik_na_realizaciji_id` bigint DEFAULT NULL,
  `naziv` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_10usa4ivtv6316csxtcgunfyb` (`nastavnik_na_realizaciji_id`),
  CONSTRAINT `FKs6ulkhp2ya6cxdvqem1vg3t5v` FOREIGN KEY (`nastavnik_na_realizaciji_id`) REFERENCES `nastavnik_na_realizaciji` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tip_nastave`
--

LOCK TABLES `tip_nastave` WRITE;
/*!40000 ALTER TABLE `tip_nastave` DISABLE KEYS */;
INSERT INTO `tip_nastave` VALUES (1,1,'Predavanje');
/*!40000 ALTER TABLE `tip_nastave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `univerzitet`
--

DROP TABLE IF EXISTS `univerzitet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `univerzitet` (
  `datum_osnivanja` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `univerzitet_adresa_id` bigint DEFAULT NULL,
  `naziv` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rugnfsr2et59xu6lj0bnynl03` (`univerzitet_adresa_id`),
  CONSTRAINT `FKdx2nvhcs7c7wigrvlbvaaxj3m` FOREIGN KEY (`univerzitet_adresa_id`) REFERENCES `adresa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `univerzitet`
--

LOCK TABLES `univerzitet` WRITE;
/*!40000 ALTER TABLE `univerzitet` DISABLE KEYS */;
INSERT INTO `univerzitet` VALUES ('1999-04-24 00:00:00.000000',1,1,'Univerzitet Singidunum');
/*!40000 ALTER TABLE `univerzitet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_permission`
--

DROP TABLE IF EXISTS `user_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `korisnik_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3s19aldn6encxel7vgccn2kqu` (`korisnik_id`),
  KEY `FKbklmo9kchans5u3e4va0ouo1s` (`permission_id`),
  CONSTRAINT `FKbklmo9kchans5u3e4va0ouo1s` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`),
  CONSTRAINT `FKggqi1w816i4vt21v4m3ao3inh` FOREIGN KEY (`korisnik_id`) REFERENCES `registrovani_korisnik` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_permission`
--

LOCK TABLES `user_permission` WRITE;
/*!40000 ALTER TABLE `user_permission` DISABLE KEYS */;
INSERT INTO `user_permission` VALUES (1,1,1);
/*!40000 ALTER TABLE `user_permission` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-04 12:07:54
