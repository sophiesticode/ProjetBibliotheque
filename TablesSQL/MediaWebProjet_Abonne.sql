DROP TABLE IF EXISTS `Abonne`;

CREATE TABLE `Abonne` (
  `id` int NOT NULL AUTO_INCREMENT,
  `abDesactive` int DEFAULT '0',
  `age` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_UtilisateurAbonne` FOREIGN KEY (`id`) REFERENCES `Utilisateur` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `Abonne` WRITE;

INSERT INTO `Abonne` VALUES (1,0,12);

UNLOCK TABLES;

