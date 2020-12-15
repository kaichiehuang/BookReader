CREATE DATABASE  IF NOT EXISTS `bookreader` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bookreader`;
-- MySQL dump 10.13  Distrib 8.0.11, for macos10.13 (x86_64)
--
-- Host: rds-mysql-instance.cpdcrxbpepxx.us-east-1.rds.amazonaws.com    Database: bookreader
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `Book`
--

DROP TABLE IF EXISTS `Book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Book` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `author` varchar(200) NOT NULL,
  `page` int unsigned NOT NULL,
  `summary` text,
  `book_identifier` varchar(45) DEFAULT NULL,
  `link` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_author_UNIQUE` (`title`,`author`),
  UNIQUE KEY `identifier_UNIQUE` (`book_identifier`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Book`
--

LOCK TABLES `Book` WRITE;
/*!40000 ALTER TABLE `Book` DISABLE KEYS */;
INSERT INTO `Book` VALUES (47,'Oregon Blue Book','Oregon,Oregon Office of the Secretary of Stat',210,'This work has been selected by scholars as being culturally important and is part of the knowledge base of civilization as we know it. This work is in the public domain in the United States of America, and possibly other nations. Within the United States, you may freely copy and distribute this work, as no entity (individual or corporate) has a copyright on the body of the work. Scholars believe, and we concur, that this work is important enough to be preserved, reproduced, and made generally available to the public. To ensure a quality reading experience, this work has been proofread and republished using a format that seamlessly blends the original graphical elements with text in an easy-to-read typeface. We appreciate your support of the preservation process, and thank you for being an important part of keeping this knowledge alive and relevant.','_8ZwvQEACAAJ','http://books.google.com/books/content?id=_8ZwvQEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api'),(48,'Time Machines','Paul J. Nahin',628,'This book explores the idea of time travel from the first account in English literature to the latest theories of physicists such as Kip Thorne and Igor Novikov. This very readable work covers a variety of topics including: the history of time travel in fiction; the fundamental scientific concepts of time, spacetime, and the fourth dimension; the speculations of Einstein, Richard Feynman, Kurt Goedel, and others; time travel paradoxes, and much more.','39KQY1FnSfkC','http://books.google.com/books/content?id=39KQY1FnSfkC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(49,'The Three-Body Problem','Cixin Liu',400,'\"Wildly imaginative, really interesting.\" —President Barack Obama on The Three-Body Problem trilogy The Three-Body Problem is the first chance for English-speaking readers to experience the Hugo Award-winning phenomenon from China\'s most beloved science fiction author, Liu Cixin. Set against the backdrop of China\'s Cultural Revolution, a secret military project sends signals into space to establish contact with aliens. An alien civilization on the brink of destruction captures the signal and plans to invade Earth. Meanwhile, on Earth, different camps start forming, planning to either welcome the superior beings and help them take over a world seen as corrupt, or to fight against the invasion. The result is a science fiction masterpiece of enormous scope and vision. The Remembrance of Earth\'s Past Trilogy The Three-Body Problem The Dark Forest Death\'s End Other Books Ball Lightning (forthcoming) At the Publisher\'s request, this title is being sold without Digital Rights Management Software (DRM) applied.','ZrNzAwAAQBAJ','http://books.google.com/books/content?id=ZrNzAwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(50,'The Test Book','Mikael Krogerus,Roman Tschäppeler',208,'An essential library of tests for self-knowledge and success, from the strategic thinking experts behind the international bestseller The Decision Book. Are you clever? Can you self-motivate? Are you creative? How do you handle money? Can you lead others well? With their trademark style and wit, best-selling authors Mikael Krogerus and Roman Tschäppeler present sixty-four tests spanning intelligence and personality type; creativity and leadership skills; fitness and lifestyle; and knowledge and belief. From what you see in a Rorschach test to comparing your workout against a Navy SEAL’s, from EQ to IQ and Myers-Briggs in between, The Test Book offers a panoply of ways to assess yourself and decide what you need to succeed. As Krogerus and Tschäppeler highlight, you can only know whether you have the right skills, the right job, or the right partner when you know where you stand right now. Small enough to fit in your pocket but packed with insight and good humor, The Test Book delivers a quick, fun way to evaluate your life and happiness.','lPV1CQAAQBAJ','http://books.google.com/books/content?id=lPV1CQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(51,'Beloved','Toni Morrison',352,'New York Times Bestseller Staring unflinchingly into the abyss of slavery, this spellbinding novel transforms history into a story as powerful as Exodus and as intimate as a lullaby. Sethe, its protagonist, was born a slave and escaped to Ohio, but eighteen years later she is still not free. She has too many memories of Sweet Home, the beautiful farm where so many hideous things happened. And Sethe’s new home is haunted by the ghost of her baby, who died nameless and whose tombstone is engraved with a single word: Beloved. Filled with bitter poetry and suspense as taut as a rope, Beloved is a towering achievement. \"You can\'t go wrong by reading or re-reading the collected works of Toni Morrison. Beloved, Song of Solomon, The Bluest Eye, Sula, everything else — they\'re transcendent, all of them. You’ll be glad you read them.\"--Barack Obama','sfmp6gjZGP8C','http://books.google.com/books/content?id=sfmp6gjZGP8C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(52,'Clearing in the Sky & Other Stories','Jesse Stuart',262,'Here are twenty-one tales from Kentucky\'s inimitable and beloved storyteller, Jesse Stuart. Full of high, rambunctious humor, quick-paced as a mountain square dance, bright as a maple tree against an October hill -- these stories are Stuart in his best form -- the form that has made him one of the most widely read authors in America. Read here about the man who coveted a steam shovel and stole it piece by piece, or about the celebrated eating contest between Sam Whiteapple and the game rooster, or about the hill farmer who wanted to clear and farm one last spot of new ground before he died. Although he has a sharp eye for human foibles and infirmities, Stuart never fails to write of his people with affection or to see that justice is done them. Originally published in 1950, Clearing in the Sky was inadvertently declared out of print after three years and never reprinted. Now for Jesse Stuart\'s many readers it is once more available with an appreciative foreword by Ruel Foster.','8XyWyizIqRgC','http://books.google.com/books/content?id=8XyWyizIqRgC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(53,'3 Tian Tang Street','Wenjun Qin',215,'The story of 3 Tian Tang Street concerns a Shanghai teenager Lang Lang\'s life: the misunderstanding between his parents that threatened to evolve into a divorce, the loving admiration for a girl who was sophisticated enough to take advantage of it, encounters with a school bully and friendship with a despised weakling in his class, the breakup with a buddy because they couldn\'t see eye-to-eye over the bully and the weakling, and a death in the family. Recommended for ages 12 and up','3sFmqioV1lwC','http://books.google.com/books/content?id=3sFmqioV1lwC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(54,'This Is Your Brain on Music','Daniel J. Levitin',336,'In this groundbreaking union of art and science, rocker-turned-neuroscientist Daniel J. Levitin explores the connection between music—its performance, its composition, how we listen to it, why we enjoy it—and the human brain. Taking on prominent thinkers who argue that music is nothing more than an evolutionary accident, Levitin poses that music is fundamental to our species, perhaps even more so than language. Drawing on the latest research and on musical examples ranging from Mozart to Duke Ellington to Van Halen, he reveals: • How composers produce some of the most pleasurable effects of listening to music by exploiting the way our brains make sense of the world • Why we are so emotionally attached to the music we listened to as teenagers, whether it was Fleetwood Mac, U2, or Dr. Dre • That practice, rather than talent, is the driving force behind musical expertise • How those insidious little jingles (called earworms) get stuck in our head A Los Angeles Times Book Award finalist, This Is Your Brain on Music will attract readers of Oliver Sacks and David Byrne, as it is an unprecedented, eye-opening investigation into an obsession at the heart of human nature.','_B3CEBJhhBQC','http://books.google.com/books/content?id=_B3CEBJhhBQC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(55,'Sorry, Wrong Number','Lucille Fletcher',41,'In two plays, an invalid woman tries to stop an unidentified person from killing her after overhearing the plot over the phone, and a driver tries to rid himself of a ghostly hitchhiker that follows him during a cross-country road trip.','6_bKL2anJpIC','http://books.google.com/books/content?id=6_bKL2anJpIC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(56,'Humans of New York: Stories','Brandon Stanton',432,'Now a #1 New York Times Bestseller! In the summer of 2010, photographer Brandon Stanton began an ambitious project -to single-handedly create a photographic census of New York City. The photos he took and the accompanying interviews became the blog Humans of New York. His audience steadily grew from a few hundred followers to, at present count, over eighteen million. In 2013, his book Humans of New York, based on that blog, was published and immediately catapulted to the top of the NY Times Bestseller List where it has appeared for over forty-five weeks. Now, Brandon is back with the Humans of New York book that his loyal followers have been waiting for: Humans of New York: Stories. Ever since Brandon began interviewing people on the streets of New York, the dialogue he\'s had with them has increasingly become as in-depth, intriguing and moving as the photos themselves. Humans of New York: Stories presents a whole new group of people in stunning photographs, with a rich design and, most importantly, longer stories that delve deeper and surprise with greater candor. Let Brandon Stanton and the Humans of New York he\'s photographed astonish you all over again.','eCT_DwAAQBAJ','http://books.google.com/books/content?id=eCT_DwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api'),(57,'Hello Out There','William Saroyan',28,'','gzu1csEwgnUC','http://books.google.com/books/content?id=gzu1csEwgnUC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api');
/*!40000 ALTER TABLE `Book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bookshelf`
--

DROP TABLE IF EXISTS `Bookshelf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Bookshelf` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `user_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_user_id_UNIQUE` (`name`,`user_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `bookshelf_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bookshelf`
--

LOCK TABLES `Bookshelf` WRITE;
/*!40000 ALTER TABLE `Bookshelf` DISABLE KEYS */;
INSERT INTO `Bookshelf` VALUES (100,'favorites',32),(106,'favorites',33),(111,'My Shelf',33),(103,'read',32),(109,'read',33),(102,'reading',32),(108,'reading',33),(101,'recommended',32),(107,'recommended',33),(105,'Science Fiction',32),(104,'want to read',32),(110,'want to read',33);
/*!40000 ALTER TABLE `Bookshelf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Comment`
--

DROP TABLE IF EXISTS `Comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Comment` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `book_id` int unsigned NOT NULL,
  `user_id` int unsigned NOT NULL,
  `rating` int unsigned DEFAULT NULL,
  `content` text,
  `time_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `book_id` (`book_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `Comment_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `Book` (`id`),
  CONSTRAINT `Comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comment`
--

LOCK TABLES `Comment` WRITE;
/*!40000 ALTER TABLE `Comment` DISABLE KEYS */;
INSERT INTO `Comment` VALUES (5,47,32,3,'add','2020-12-14 07:20:14'),(6,47,32,3,'','2020-12-14 07:21:35'),(7,51,33,0,'add review','2020-12-15 00:25:09'),(8,51,33,0,'test comment','2020-12-15 00:28:03');
/*!40000 ALTER TABLE `Comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ExcludedBook`
--

DROP TABLE IF EXISTS `ExcludedBook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ExcludedBook` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `book_id` int unsigned NOT NULL,
  `user_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `all_ids_UNIQUE` (`book_id`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `ExcludedBook_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `Book` (`id`),
  CONSTRAINT `ExcludedBook_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ExcludedBook`
--

LOCK TABLES `ExcludedBook` WRITE;
/*!40000 ALTER TABLE `ExcludedBook` DISABLE KEYS */;
INSERT INTO `ExcludedBook` VALUES (13,48,32),(15,50,32),(14,51,32),(16,52,32),(17,53,32),(18,54,32);
/*!40000 ALTER TABLE `ExcludedBook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FriendRequest`
--

DROP TABLE IF EXISTS `FriendRequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `FriendRequest` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `requestedFriend_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FriendRequest`
--

LOCK TABLES `FriendRequest` WRITE;
/*!40000 ALTER TABLE `FriendRequest` DISABLE KEYS */;
/*!40000 ALTER TABLE `FriendRequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Friendship`
--

DROP TABLE IF EXISTS `Friendship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Friendship` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `friend_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Friendship`
--

LOCK TABLES `Friendship` WRITE;
/*!40000 ALTER TABLE `Friendship` DISABLE KEYS */;
INSERT INTO `Friendship` VALUES (5,32,33),(6,33,32);
/*!40000 ALTER TABLE `Friendship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MyBook`
--

DROP TABLE IF EXISTS `MyBook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `MyBook` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `book_id` int unsigned NOT NULL,
  `user_id` int unsigned NOT NULL,
  `bookshelf_id` int unsigned NOT NULL,
  `progress` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `all_ids_UNIQUE` (`book_id`,`user_id`,`bookshelf_id`),
  KEY `user_id_idx` (`user_id`),
  KEY `bookshelf_id_idx` (`bookshelf_id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MyBook`
--

LOCK TABLES `MyBook` WRITE;
/*!40000 ALTER TABLE `MyBook` DISABLE KEYS */;
INSERT INTO `MyBook` VALUES (57,47,32,103,47),(59,48,32,105,0),(60,47,33,107,0),(67,51,33,108,56),(76,54,32,104,0),(77,54,32,105,0),(78,55,33,110,0),(79,55,32,101,0),(80,56,33,109,100),(81,56,32,101,0),(82,57,33,108,35.714285714285715),(83,57,33,111,35.714285714285715);
/*!40000 ALTER TABLE `MyBook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Timeline`
--

DROP TABLE IF EXISTS `Timeline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Timeline` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `content` text NOT NULL,
  `type` varchar(45) NOT NULL,
  `time_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `Timeline_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Timeline`
--

LOCK TABLES `Timeline` WRITE;
/*!40000 ALTER TABLE `Timeline` DISABLE KEYS */;
INSERT INTO `Timeline` VALUES (3,32,'sherryXiaoyingLi read 47.62% of Oregon Blue Book','progress','2020-12-14 07:17:49'),(4,32,'sherryXiaoyingLi writes on book \"Oregon Blue Book\": add | score:3','review','2020-12-14 07:20:14'),(5,32,'sherryXiaoyingLi writes on book \"Oregon Blue Book\":  | score:3','review','2020-12-14 07:21:35'),(6,32,'sherryXiaoyingLi read 100.0% of Beloved','progress','2020-12-14 23:45:29'),(7,33,'Tom read 56.82% of Beloved','progress','2020-12-15 00:17:07'),(8,33,'Tom writes on book \"Beloved\": add review | score:0','review','2020-12-15 00:25:10'),(9,33,'Tom writes on book \"Beloved\": test comment | score:0','review','2020-12-15 00:28:03'),(10,33,'Tom read 35.71% of Hello Out There','progress','2020-12-15 00:34:23'),(11,33,'Tom read 100.0% of Humans of New York: Stories','progress','2020-12-15 00:34:31');
/*!40000 ALTER TABLE `Timeline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TimelineComment`
--

DROP TABLE IF EXISTS `TimelineComment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `TimelineComment` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `timeline_id` int unsigned NOT NULL,
  `content` text,
  `type` text,
  `time_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `timeline_id` (`timeline_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `TimelineComment_ibfk_1` FOREIGN KEY (`timeline_id`) REFERENCES `Timeline` (`id`),
  CONSTRAINT `TimelineComment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TimelineComment`
--

LOCK TABLES `TimelineComment` WRITE;
/*!40000 ALTER TABLE `TimelineComment` DISABLE KEYS */;
INSERT INTO `TimelineComment` VALUES (11,32,5,'','like','2020-12-14 07:21:45');
/*!40000 ALTER TABLE `TimelineComment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `User` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `default_bookshelf` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (32,'sherryXiaoyingLi','123456','Science Fiction'),(33,'Tom','123456','My Shelf');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-14 18:24:47
