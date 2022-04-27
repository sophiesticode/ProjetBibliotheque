
DROP TABLE IF EXISTS `Document`;

CREATE TABLE `Document` (
  `num` int NOT NULL AUTO_INCREMENT,
  `titre` varchar(45) NOT NULL,
  `anneeParution` varchar(5) NOT NULL,
  `idAbonne` int DEFAULT NULL,
  `categorie` varchar(20) DEFAULT NULL,
  `type` int NOT NULL COMMENT 'CD : 1\\nDVD : 2\\nLivre : 3',
  `de` varchar(30) NOT NULL,
  PRIMARY KEY (`num`),
  KEY `FK_DocumentAbonne` (`idAbonne`),
  CONSTRAINT `FK_DocumentAbonne` FOREIGN KEY (`idAbonne`) REFERENCES `Utilisateur` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `Document` WRITE;

INSERT INTO `Document` VALUES (1,'Je sais cuisiner','1932',NULL,'cuisine',3,'Ginette Mathiot'),(17,'Happier Than Ever','2021',NULL,'Pop',1,'Billie Eilish'),(21,'La vie devant soi','1975',NULL,'Fiction',3,'Romain Gary'),(22,'The Father','2021',NULL,'Drame',2,'Florian Zeller'),(37,'L`Âge de glace','2002',NULL,'Aventure',2,'Chris Wedge'),(38,'Vingt-quatre heures de la vie d`une femme','1927',NULL,'Roman',3,'Stefan Zweigh'),(39,'Nonante-Cinq','2021',NULL,'Pop',1,'Angèle'),(40,'30','2021',NULL,'Pop',1,'Adèle'),(41,'Isa','2021',NULL,'Pop',1,'Zaz'),(42,'Titane ','2021',NULL,'Horreur',2,' Julia Ducournau');

UNLOCK TABLES;
