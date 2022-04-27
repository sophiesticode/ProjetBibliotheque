
DROP TABLE IF EXISTS `DVD`;

CREATE TABLE `DVD` (
  `numDoc` int NOT NULL AUTO_INCREMENT,
  `ageLimite` int DEFAULT NULL,
  `duree` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`numDoc`),
  CONSTRAINT `FK_DocumentDVD` FOREIGN KEY (`numDoc`) REFERENCES `Document` (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `DVD` WRITE;

INSERT INTO `DVD` VALUES (22,14,'1h 38min'),(37,0,'1h21m'),(42,16,'1h 48m');

UNLOCK TABLES;