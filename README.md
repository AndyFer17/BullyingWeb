Create database BullyingWeb3;
USE bullyingWeb;
CREATE TABLE usuarios (
  id_usuario int NOT NULL AUTO_INCREMENT,
  nombre varchar(100) NOT NULL,
  apellido varchar(100) NOT NULL,
  correo varchar(100) NOT NULL,
  contrasena varchar(255) DEFAULT NULL,
  rol varchar(10) DEFAULT NULL,
  auth_code varchar(6) DEFAULT NULL,
  auth_enabled tinyint(1) DEFAULT '0',
  PRIMARY KEY (id_usuario),
  UNIQUE KEY correo (correo),
  KEY idx_usuario_correo (correo)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE clientes (
  id_cliente int NOT NULL AUTO_INCREMENT,
  id_usuario int NOT NULL,
  direccion varchar(255) DEFAULT NULL,
  telefono varchar(20) DEFAULT NULL,
  PRIMARY KEY (id_cliente),
  KEY id_usuario (id_usuario),
  CONSTRAINT clientes_ibfk_1 FOREIGN KEY (id_usuario) REFERENCES usuarios (id_usuario) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE afectados (
  id_afectado int NOT NULL AUTO_INCREMENT,
  id_cliente int NOT NULL,
  nombre varchar(100) NOT NULL,
  fecha_registro date DEFAULT NULL,
  PRIMARY KEY (id_afectado),
  KEY id_cliente (id_cliente),
  CONSTRAINT mascotas_ibfk_1 FOREIGN KEY (id_cliente) REFERENCES clientes (id_cliente) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE reportes (
  id_reporte int NOT NULL AUTO_INCREMENT,
  id_cliente int NOT NULL,
  fecha_hora datetime NOT NULL,
  descripcion text,
  estado enum('pendiente','confirmada','cancelada','completada') NOT NULL DEFAULT 'pendiente',
  servicio varchar(50) DEFAULT NULL,
  id_afectado int DEFAULT NULL,
  PRIMARY KEY (id_reporte),
  KEY id_cliente (id_cliente),
  KEY idx_reporte_estado (estado),
  CONSTRAINT reportes_ibfk_1 FOREIGN KEY (id_cliente) REFERENCES clientes (id_cliente) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE reporteAnonimo (
  id int NOT NULL AUTO_INCREMENT,
  nombre varchar(50) NOT NULL, 
  apellido varchar(50) NOT NULL,
  email varchar(100) NOT NULL, 
  nombreAcusado varchar(20) DEFAULT NULL, 
  comentario text, -
  imagen_ruta varchar(255), 
  PRIMARY KEY (id) 
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
