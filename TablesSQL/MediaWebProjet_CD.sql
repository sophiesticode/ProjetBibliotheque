
DROP TABLE IF EXISTS `CD`;

CREATE TABLE `CD` (
  `numDoc` int NOT NULL AUTO_INCREMENT,
  `ageLimite` int DEFAULT NULL,
  `nbMorceaux` int DEFAULT NULL,
  `duree` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`numDoc`),
  CONSTRAINT `FK_DocumentCD` FOREIGN KEY (`numDoc`) REFERENCES `Document` (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `CD` WRITE;

INSERT INTO `CD` VALUES (17,NULL,16,NULL),(39,NULL,12,NULL),(40,NULL,12,NULL),(41,NULL,13,NULL);

UNLOCK TABLES;

