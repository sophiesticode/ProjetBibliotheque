
DROP TABLE IF EXISTS `Bibliothecaire`;

CREATE TABLE `Bibliothecaire` (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_UtilisateurBibliothecaire` FOREIGN KEY (`id`) REFERENCES `Utilisateur` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `Bibliothecaire` WRITE;

INSERT INTO `Bibliothecaire` VALUES (2);

UNLOCK TABLES;

