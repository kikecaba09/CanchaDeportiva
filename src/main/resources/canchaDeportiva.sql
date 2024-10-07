CREATE DATABASE IF NOT EXISTS CanchaDeportiva;
USE CanchaDeportiva;

-- Tabla Cancha
CREATE TABLE Cancha (
                        cancha_id INT AUTO_INCREMENT PRIMARY KEY,
                        centro_id INT NOT NULL,
                        nro_cancha INT NOT NULL,
                        precio_dia DECIMAL(10,2) NOT NULL,
                        precio_noche DECIMAL(10,2) NOT NULL,
                        hora_abierto TIME NOT NULL,
                        hora_cerrado TIME NOT NULL
);

-- Tabla Rol
CREATE TABLE Rol (
                     rol_id INT AUTO_INCREMENT PRIMARY KEY,
                     rol VARCHAR(50) NOT NULL
);

-- Tabla Cliente
CREATE TABLE Cliente (
                         cliente_id INT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         apellido VARCHAR(100) NOT NULL,
                         nro_identidad VARCHAR(20) NOT NULL,
                         telefono VARCHAR(15),
                         email VARCHAR(100),
                         fecha_nacimiento DATE
);

-- Tabla User
CREATE TABLE User (
                      user_id INT AUTO_INCREMENT PRIMARY KEY,
                      cliente_id INT NOT NULL,
                      rol_id INT NOT NULL,
                      username VARCHAR(50) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      FOREIGN KEY (cliente_id) REFERENCES Cliente(cliente_id)
                          ON DELETE CASCADE
                          ON UPDATE CASCADE,
                      FOREIGN KEY (rol_id) REFERENCES Rol(rol_id)
                          ON DELETE CASCADE
                          ON UPDATE CASCADE
);

-- Tabla Reserva
CREATE TABLE Reserva (
                         reserva_id INT AUTO_INCREMENT PRIMARY KEY,
                         user_id INT NOT NULL,
                         cancha_id INT NOT NULL,
                         precio_reserva DECIMAL(10,2) NOT NULL,
                         fecha_reserva DATE NOT NULL,
                         hora_inicio TIME NOT NULL,
                         hora_fin TIME NOT NULL,
                         estado_reserva VARCHAR(50),
                         FOREIGN KEY (user_id) REFERENCES User(user_id)
                             ON DELETE CASCADE
                             ON UPDATE CASCADE,
                         FOREIGN KEY (cancha_id) REFERENCES Cancha(cancha_id)
                             ON DELETE CASCADE
                             ON UPDATE CASCADE
);

-- Tabla Pago
CREATE TABLE Pago (
                      pago_id INT AUTO_INCREMENT PRIMARY KEY,
                      reserva_id INT NOT NULL,
                      metodo_pago VARCHAR(50) NOT NULL,  -- 'Efectivo' o 'Yape'
                      monto DECIMAL(10,2) NOT NULL,
                      fecha_pago DATE NOT NULL,
                      FOREIGN KEY (reserva_id) REFERENCES Reserva(reserva_id)
                          ON DELETE CASCADE
                          ON UPDATE CASCADE
);
