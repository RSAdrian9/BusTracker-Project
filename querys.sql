use bustracker;

# CREACIÓN BASE DE DATOS.
CREATE DATABASE bustracker;

# CREACIÓN TABLA ADMIN.
CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

# CREACIÓN TABLA LINE.
CREATE TABLE `line` (
  `id_bus` tinyint(4) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(100) DEFAULT NULL,
  `place` int(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

# CREACIÓN TABLA STOP.
CREATE TABLE `stop` (
  `id_stop` tinyint(4) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

# CREACIÓN TABLA RELACIÓN LINE_STOP.
CREATE TABLE `line_stop` (
  `id_bus` tinyint(4) NOT NULL,
  `id_stop` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

# Añadir claves a cada una de las columnas.
ALTER TABLE `line_stop`
  ADD KEY `id_bus` (`id_bus`),
  ADD KEY `id_stop` (`id_stop`);
  
# Referenciar que la columna de id_bus de la tabla line_stop tiene que tener los mismos datos que la columna de line, igual con la columna id_stop.
ALTER TABLE `line_stop`
  ADD CONSTRAINT `line_stop_ibfk_1` FOREIGN KEY (`id_bus`) REFERENCES `line` (`id_bus`),
  ADD CONSTRAINT `line_stop_ibfk_2` FOREIGN KEY (`id_stop`) REFERENCES `stop` (`id_stop`);
COMMIT;
  
/*

CREATE TABLE `departuretime` (
  `id_hour` tinyint(4) NOT NULL,
  `id_bus` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

*/

# INSERTAR LOS DATOS EN SUS RESPECTIVAS TABLAS
# Datos tabla admin

INSERT INTO `admin` (`user`, `password`, `email`) VALUES
('Erich', '5678', 'erich@hotmail.es'),
('Adrian', '1234', 'adrian@gmail.com');

# Datos tabla line

INSERT INTO `line` (`name`, `place`) VALUES
('Línea Verde', 60),
('Línea Roja', 60),
('Línea Morada', 60),
('Línea Negra', 60),
('Línea Azul', 60),
('Línea Blanca', 60),
('Línea Naranja', 60),
('Línea Amarilla', 60);

# Datos tabla stop

INSERT INTO `stop` (`name`) VALUES
('Estación de Autobuses de Córdoba'),
('Mezquita-Catedral'),
('Alcázar de los Reyes Cristianos'),
('Templo Romano'),
('Plaza de las Tendillas'),
('Puerta de Almodóvar'),
('Sinagoga'),
('Puerta del Puente'),
('Jardín Botánico'),
('Mercado Victoria'),
('Torre de la Calahorra'),
('Palacio de Viana'),
('Puente Romano'),
('Jardines del Alcázar'),
('Museo Arqueológico de Córdoba');

# Datos tabla line_stop -> relación

INSERT INTO `line_stop` (`id_bus`, `id_stop`) VALUES
(1, 1),
(1, 2),
(1, 3);




