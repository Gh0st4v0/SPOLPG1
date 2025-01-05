create database rifateste;
use rifateste;
CREATE TABLE `usuarios` (
   `id` char(36) NOT NULL,
   `nome` varchar(255) NOT NULL,
   `email` varchar(255) NOT NULL,
   `senha` char(60) NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE KEY `email` (`email`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 CREATE TABLE `rifas` (
    `id` char(36) NOT NULL,
    `usuario_id` char(36) NOT NULL,
    `nome` varchar(255) NOT NULL,
    `data_criacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `usuario_id` (`usuario_id`),
    CONSTRAINT `rifas_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

  CREATE TABLE `bilhetes` (
     `id` char(36) NOT NULL,
     `rifa_id` char(36) NOT NULL,
     `usuario_id` char(36) NOT NULL,
     PRIMARY KEY (`id`),
     KEY `rifa_id` (`rifa_id`),
     KEY `usuario_id` (`usuario_id`),
     CONSTRAINT `bilhetes_ibfk_1` FOREIGN KEY (`rifa_id`) REFERENCES `rifas` (`id`) ON DELETE CASCADE,
     CONSTRAINT `bilhetes_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

   CREATE TABLE `usuarios_rifas` (
      `usuario_id` char(36) NOT NULL,
      `rifa_id` char(36) NOT NULL,
      PRIMARY KEY (`usuario_id`,`rifa_id`),
      KEY `rifa_id` (`rifa_id`),
      CONSTRAINT `usuarios_rifas_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
      CONSTRAINT `usuarios_rifas_ibfk_2` FOREIGN KEY (`rifa_id`) REFERENCES `rifas` (`id`) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;