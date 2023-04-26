-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.33 - MySQL Community Server - GPL
-- Server OS:                    Linux
-- HeidiSQL Version:             12.4.0.6659
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for MusicShop
CREATE DATABASE IF NOT EXISTS `MusicShop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `MusicShop`;

-- Dumping structure for table MusicShop.album
CREATE TABLE IF NOT EXISTS `album` (
  `albumID` varchar(10) NOT NULL,
  `genreID` varchar(10) DEFAULT NULL,
  `singerID` varchar(10) DEFAULT NULL,
  `thumbnail` blob,
  `numberOfSongs` int DEFAULT NULL,
  `albumDescription` longtext,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`albumID`),
  KEY `album` (`genreID`),
  KEY `singer` (`singerID`),
  CONSTRAINT `album_ibfk_1` FOREIGN KEY (`genreID`) REFERENCES `genre` (`genreID`),
  CONSTRAINT `album_ibfk_2` FOREIGN KEY (`singerID`) REFERENCES `singer` (`singerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table MusicShop.album: ~0 rows (approximately)

-- Dumping structure for table MusicShop.genre
CREATE TABLE IF NOT EXISTS `genre` (
  `genreID` varchar(10) NOT NULL,
  `genreName` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`genreID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table MusicShop.genre: ~0 rows (approximately)

-- Dumping structure for table MusicShop.receipt
CREATE TABLE IF NOT EXISTS `receipt` (
  `receiptID` varchar(10) NOT NULL,
  `userID` varchar(255) DEFAULT NULL,
  `total` float DEFAULT NULL,
  `dateEstablised` datetime DEFAULT CURRENT_TIMESTAMP,
  `state` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'Unpaid',
  PRIMARY KEY (`receiptID`),
  KEY `receipt` (`userID`),
  CONSTRAINT `receipt_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table MusicShop.receipt: ~0 rows (approximately)

-- Dumping structure for table MusicShop.receiptLine
CREATE TABLE IF NOT EXISTS `receiptLine` (
  `receiptID` varchar(10) NOT NULL,
  `albumID` varchar(10) NOT NULL,
  `quantity` int DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`receiptID`,`albumID`),
  KEY `receiptLine` (`albumID`),
  CONSTRAINT `receiptLine_ibfk_1` FOREIGN KEY (`albumID`) REFERENCES `album` (`albumID`),
  CONSTRAINT `receiptLine_ibfk_2` FOREIGN KEY (`receiptID`) REFERENCES `receipt` (`receiptID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table MusicShop.receiptLine: ~0 rows (approximately)

-- Dumping structure for table MusicShop.singer
CREATE TABLE IF NOT EXISTS `singer` (
  `singerID` varchar(10) NOT NULL,
  `singerName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `singerDesciption` longtext,
  PRIMARY KEY (`singerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table MusicShop.singer: ~0 rows (approximately)

-- Dumping structure for table MusicShop.song
CREATE TABLE IF NOT EXISTS `song` (
  `songID` varchar(10) NOT NULL,
  `albumID` varchar(10) DEFAULT NULL,
  `songName` text,
  `duration` int DEFAULT NULL,
  `thumnail` blob,
  `songDescription` longtext,
  PRIMARY KEY (`songID`),
  KEY `song` (`albumID`),
  CONSTRAINT `song_ibfk_1` FOREIGN KEY (`albumID`) REFERENCES `album` (`albumID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table MusicShop.song: ~0 rows (approximately)

-- Dumping structure for table MusicShop.users
CREATE TABLE IF NOT EXISTS `users` (
  `userID` varchar(255) NOT NULL,
  `username` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `ROLE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table MusicShop.users: ~0 rows (approximately)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
