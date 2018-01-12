CREATE DATABASE IF NOT EXISTS `dgss1718_teamC_ligaaas`
DEFAULT CHARACTER SET = utf8
DEFAULT COLLATE utf8_general_ci;

USE `dgss1718_teamC_ligaaas`;

--
-- User creation
--
GRANT ALL PRIVILEGES ON dgss1718_teamC_ligaaas.* TO ligaaas@localhost IDENTIFIED BY 'ligaaaspass';
FLUSH PRIVILEGES;

-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: dgss1718_teamc_ligaaas
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `competition`
--

DROP TABLE IF EXISTS `competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition` (
  `competitionId` bigint(20) NOT NULL,
  `competitionDeleted` bit(1) NOT NULL,
  `competitionDescription` varchar(240) DEFAULT NULL,
  `competitionMaxTeams` int(11) NOT NULL,
  `competitionMinTeams` int(11) NOT NULL,
  `competitionName` varchar(60) NOT NULL,
  `competitionOpen` bit(1) NOT NULL,
  `competitionPublic` bit(1) NOT NULL,
  `competitionShortName` varchar(10) NOT NULL,
  `competitionSportType` varchar(255) NOT NULL,
  `competitionType` varchar(255) NOT NULL,
  `contact_ContactId` bigint(20) DEFAULT NULL,
  `headQuarter_HeadQuarterId` bigint(20) DEFAULT NULL,
  `user_UserId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`competitionId`),
  KEY `FK1rwmtjujq0pydj0fqrdw5s030` (`contact_ContactId`),
  KEY `FK68nipvfeyih59kfjbxjtcxjex` (`headQuarter_HeadQuarterId`),
  KEY `FKglg0mp9plpidhd3xlea1u2bxl` (`user_UserId`),
  CONSTRAINT `FK1rwmtjujq0pydj0fqrdw5s030` FOREIGN KEY (`contact_ContactId`) REFERENCES `contact` (`contactId`),
  CONSTRAINT `FK68nipvfeyih59kfjbxjtcxjex` FOREIGN KEY (`headQuarter_HeadQuarterId`) REFERENCES `headquarter` (`headQuarterId`),
  CONSTRAINT `FKglg0mp9plpidhd3xlea1u2bxl` FOREIGN KEY (`user_UserId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition`
--

LOCK TABLES `competition` WRITE;
/*!40000 ALTER TABLE `competition` DISABLE KEYS */;
/*!40000 ALTER TABLE `competition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `contactId` bigint(20) NOT NULL,
  `contactEmail` varchar(100) DEFAULT NULL,
  `contactFacebook` varchar(100) DEFAULT NULL,
  `contactInstagram` varchar(100) DEFAULT NULL,
  `contactPhone` varchar(255) NOT NULL,
  `contactTwitter` varchar(100) DEFAULT NULL,
  `contactWeb` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`contactId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `headquarter`
--

DROP TABLE IF EXISTS `headquarter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `headquarter` (
  `headQuarterId` bigint(20) NOT NULL,
  `headquarterAddress` varchar(100) NOT NULL,
  `headquarterDescription` varchar(240) NOT NULL,
  `headquarterLocality` varchar(100) NOT NULL,
  `headquarterName` varchar(60) NOT NULL,
  `headquarterProvince` varchar(100) NOT NULL,
  PRIMARY KEY (`headQuarterId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `headquarter`
--

LOCK TABLES `headquarter` WRITE;
/*!40000 ALTER TABLE `headquarter` DISABLE KEYS */;
/*!40000 ALTER TABLE `headquarter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1),(1),(1),(1),(1),(1),(1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match`
--

DROP TABLE IF EXISTS `match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `match` (
  `matchId` bigint(20) NOT NULL,
  `matchDate` datetime NOT NULL,
  `matchDescription` varchar(240) NOT NULL,
  `matchDuration` int(11) NOT NULL,
  `matchLocalPoints` int(11) NOT NULL,
  `matchState` int(11) NOT NULL,
  `matchVisitingPoints` int(11) NOT NULL,
  `team_homeTeamId` bigint(20) NOT NULL,
  `headQuarter_HeadQuarterId` bigint(20) DEFAULT NULL,
  `round_roundId` bigint(20) NOT NULL,
  `team_visitingTeamId` bigint(20) NOT NULL,
  PRIMARY KEY (`matchId`),
  KEY `FKo526r3hnvbaty2ohykw8iumrk` (`team_homeTeamId`),
  KEY `FKtmd9borqqh8stwy8y3xblbyld` (`headQuarter_HeadQuarterId`),
  KEY `FKlmv7hdel5i0jn2ktrgpe6is2v` (`round_roundId`),
  KEY `FKpg7asivk9c0j8437jsdh8lt5n` (`team_visitingTeamId`),
  CONSTRAINT `FKlmv7hdel5i0jn2ktrgpe6is2v` FOREIGN KEY (`round_roundId`) REFERENCES `round` (`roundId`),
  CONSTRAINT `FKo526r3hnvbaty2ohykw8iumrk` FOREIGN KEY (`team_homeTeamId`) REFERENCES `team` (`teamId`),
  CONSTRAINT `FKpg7asivk9c0j8437jsdh8lt5n` FOREIGN KEY (`team_visitingTeamId`) REFERENCES `team` (`teamId`),
  CONSTRAINT `FKtmd9borqqh8stwy8y3xblbyld` FOREIGN KEY (`headQuarter_HeadQuarterId`) REFERENCES `headquarter` (`headQuarterId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match`
--

LOCK TABLES `match` WRITE;
/*!40000 ALTER TABLE `match` DISABLE KEYS */;
/*!40000 ALTER TABLE `match` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_matchcomments`
--

DROP TABLE IF EXISTS `match_matchcomments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `match_matchcomments` (
  `Match_matchId` bigint(20) NOT NULL,
  `matchComments` varchar(255) DEFAULT NULL,
  KEY `FKjqm6igoqu4wcna3lbm7cu21bk` (`Match_matchId`),
  CONSTRAINT `FKjqm6igoqu4wcna3lbm7cu21bk` FOREIGN KEY (`Match_matchId`) REFERENCES `match` (`matchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_matchcomments`
--

LOCK TABLES `match_matchcomments` WRITE;
/*!40000 ALTER TABLE `match_matchcomments` DISABLE KEYS */;
/*!40000 ALTER TABLE `match_matchcomments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player` (
  `playerId` bigint(20) NOT NULL,
  `playerEmail` varchar(40) NOT NULL,
  `playerFavouriteSportsList` varchar(255) DEFAULT NULL,
  `playerFavouriteTeamList` varchar(255) DEFAULT NULL,
  `playerInterests` varchar(1000) DEFAULT NULL,
  `playerLocation` varchar(100) NOT NULL,
  `playerNickName` varchar(30) NOT NULL,
  `playerPicture` tinyblob,
  `playerPrivacity` bit(1) NOT NULL,
  `playerProvince` varchar(50) NOT NULL,
  `playerManagedByUser_userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`playerId`),
  KEY `FKgyfsifln4ef6lklyrmbr6owms` (`playerManagedByUser_userId`),
  CONSTRAINT `FKgyfsifln4ef6lklyrmbr6owms` FOREIGN KEY (`playerManagedByUser_userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `round`
--

DROP TABLE IF EXISTS `round`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `round` (
  `roundId` bigint(20) NOT NULL,
  `roundDate` datetime NOT NULL,
  `roundDescription` varchar(240) NOT NULL,
  `roundNumber` int(11) NOT NULL,
  `roundState` int(11) NOT NULL,
  `competition_CompetitionId` bigint(20) NOT NULL,
  `headQuarter_HeadQuarterId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`roundId`),
  KEY `FKacjq1yq14e9qgiq1mpg9y4yxc` (`competition_CompetitionId`),
  KEY `FKd812j0ck49q1xk7yqpx5yn8up` (`headQuarter_HeadQuarterId`),
  CONSTRAINT `FKacjq1yq14e9qgiq1mpg9y4yxc` FOREIGN KEY (`competition_CompetitionId`) REFERENCES `competition` (`competitionId`),
  CONSTRAINT `FKd812j0ck49q1xk7yqpx5yn8up` FOREIGN KEY (`headQuarter_HeadQuarterId`) REFERENCES `headquarter` (`headQuarterId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `round`
--

LOCK TABLES `round` WRITE;
/*!40000 ALTER TABLE `round` DISABLE KEYS */;
/*!40000 ALTER TABLE `round` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `round_roundcomments`
--

DROP TABLE IF EXISTS `round_roundcomments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `round_roundcomments` (
  `Round_roundId` bigint(20) NOT NULL,
  `roundComments` varchar(255) DEFAULT NULL,
  KEY `FK6usts820dvje4ldd3cedv27y9` (`Round_roundId`),
  CONSTRAINT `FK6usts820dvje4ldd3cedv27y9` FOREIGN KEY (`Round_roundId`) REFERENCES `round` (`roundId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `round_roundcomments`
--

LOCK TABLES `round_roundcomments` WRITE;
/*!40000 ALTER TABLE `round_roundcomments` DISABLE KEYS */;
/*!40000 ALTER TABLE `round_roundcomments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `teamId` bigint(20) NOT NULL,
  `teamDeleted` bit(1) NOT NULL,
  `teamDescription` varchar(240) DEFAULT NULL,
  `teamMaxPlayers` int(11) NOT NULL,
  `teamMinPlayers` int(11) NOT NULL,
  `teamName` varchar(60) NOT NULL,
  `teamOpen` bit(1) NOT NULL,
  `teamPublic` bit(1) NOT NULL,
  `teamShortName` varchar(10) NOT NULL,
  `teamSportType` varchar(255) NOT NULL,
  `contact_ContactId` bigint(20) DEFAULT NULL,
  `headQuarter_HeadQuarterId` bigint(20) DEFAULT NULL,
  `user_UserId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`teamId`),
  KEY `FKifas7j4bqm6iyba6h34qna2d8` (`contact_ContactId`),
  KEY `FK66sta9172t8hxbvu6e08m2nrq` (`headQuarter_HeadQuarterId`),
  KEY `FKe3tgkuij1di4iticg33bb880w` (`user_UserId`),
  CONSTRAINT `FK66sta9172t8hxbvu6e08m2nrq` FOREIGN KEY (`headQuarter_HeadQuarterId`) REFERENCES `headquarter` (`headQuarterId`),
  CONSTRAINT `FKe3tgkuij1di4iticg33bb880w` FOREIGN KEY (`user_UserId`) REFERENCES `user` (`userId`),
  CONSTRAINT `FKifas7j4bqm6iyba6h34qna2d8` FOREIGN KEY (`contact_ContactId`) REFERENCES `contact` (`contactId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_competition`
--

DROP TABLE IF EXISTS `team_competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team_competition` (
  `team_TeamId` bigint(20) NOT NULL,
  `competition_CompetitionId` bigint(20) NOT NULL,
  KEY `FKe5e9wrqkho12rd1ptuoe41jo5` (`competition_CompetitionId`),
  KEY `FKrd2ilfo0s3ss09xpk4vt0ced6` (`team_TeamId`),
  CONSTRAINT `FKe5e9wrqkho12rd1ptuoe41jo5` FOREIGN KEY (`competition_CompetitionId`) REFERENCES `competition` (`competitionId`),
  CONSTRAINT `FKrd2ilfo0s3ss09xpk4vt0ced6` FOREIGN KEY (`team_TeamId`) REFERENCES `team` (`teamId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_competition`
--

LOCK TABLES `team_competition` WRITE;
/*!40000 ALTER TABLE `team_competition` DISABLE KEYS */;
/*!40000 ALTER TABLE `team_competition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_player`
--

DROP TABLE IF EXISTS `team_player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team_player` (
  `team_TeamId` bigint(20) NOT NULL,
  `player_PlayerId` bigint(20) NOT NULL,
  KEY `FK14us2vt2gd42dfo9o1fvpsd3r` (`player_PlayerId`),
  KEY `FKnkijh7sf18hek5dvbb9a0chmv` (`team_TeamId`),
  CONSTRAINT `FK14us2vt2gd42dfo9o1fvpsd3r` FOREIGN KEY (`player_PlayerId`) REFERENCES `player` (`playerId`),
  CONSTRAINT `FKnkijh7sf18hek5dvbb9a0chmv` FOREIGN KEY (`team_TeamId`) REFERENCES `team` (`teamId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_player`
--

LOCK TABLES `team_player` WRITE;
/*!40000 ALTER TABLE `team_player` DISABLE KEYS */;
/*!40000 ALTER TABLE `team_player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` bigint(20) NOT NULL,
  `userBirthdate` datetime DEFAULT NULL,
  `userConfirmed` bit(1) NOT NULL,
  `userCountry` varchar(100) DEFAULT NULL,
  `userEmail` varchar(100) NOT NULL,
  `userFacebook` varchar(100) DEFAULT NULL,
  `userIdentificationDocument` varchar(10) DEFAULT NULL,
  `userInstagram` varchar(100) DEFAULT NULL,
  `userLocality` varchar(100) DEFAULT NULL,
  `userLogin` varchar(15) NOT NULL,
  `userName` varchar(60) NOT NULL,
  `userPassword` varchar(60) NOT NULL,
  `userPhone` varchar(255) DEFAULT NULL,
  `userProvince` varchar(100) DEFAULT NULL,
  `userResidence` varchar(100) DEFAULT NULL,
  `userSurname` varchar(60) NOT NULL,
  `userTwitter` varchar(100) DEFAULT NULL,
  `userPlayer_playerId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `FK6nt1084u364brf8fdr9imr7ta` (`userPlayer_playerId`),
  CONSTRAINT `FK6nt1084u364brf8fdr9imr7ta` FOREIGN KEY (`userPlayer_playerId`) REFERENCES `player` (`playerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'dgss1718_teamc_ligaaas'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-22 20:21:48
