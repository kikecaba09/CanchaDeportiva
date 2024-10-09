CREATE DATABASE IF NOT EXISTS CanchaDeportiva;
USE CanchaDeportiva;

-- Tabla Cancha
CREATE TABLE Cancha (
                        cancha_id INT AUTO_INCREMENT PRIMARY KEY,
                        nro_cancha INT NOT NULL,
                        precio_dia DECIMAL(10,2) NOT NULL,
                        precio_noche DECIMAL(10,2) NOT NULL,
                        imagen_cancha VARCHAR(100) NOT NULL,
                        hora_abierto TIME NOT NULL,
                        hora_cerrado TIME NOT NULL,
                        estado VARCHAR(50) NOT NULL
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
                      FOREIGN KEY (cliente_id) REFERENCES Cliente(cliente_id) ON DELETE CASCADE ON UPDATE CASCADE,
                      FOREIGN KEY (rol_id) REFERENCES Rol(rol_id) ON DELETE CASCADE ON UPDATE CASCADE
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
                         FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
                         FOREIGN KEY (cancha_id) REFERENCES Cancha(cancha_id)  ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla Pago
CREATE TABLE Pago (
                      pago_id INT AUTO_INCREMENT PRIMARY KEY,
                      reserva_id INT NOT NULL,
                      metodo_pago VARCHAR(50) NOT NULL,  -- 'Efectivo' o 'Yape'
                      monto DECIMAL(10,2) NOT NULL,
                      fecha_pago DATE NOT NULL,
                      FOREIGN KEY (reserva_id) REFERENCES Reserva(reserva_id) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO Cancha (nro_cancha, precio_dia, precio_noche, imagen_cancha, hora_abierto, hora_cerrado, estado) VALUES
(1, 45.00, 65.00, 'cancha1.png', '08:00:00', '21:00:00', 'Disponible'),
(2, 50.00, 70.00, 'cancha2.png', '09:30:00', '20:30:00', 'No disponible'),
(3, 55.00, 75.00, 'cancha3.png', '10:30:00', '22:00:00', 'Disponible'),
(4, 60.00, 80.00, 'cancha4.png', '09:30:00', '19:00:00', 'No disponible');

INSERT INTO Rol (rol) VALUES ('administrador'), ('cajero'),  ('cliente');

INSERT INTO Cliente (nombre, apellido, nro_identidad, telefono, email, fecha_nacimiento) VALUES
('Victor Enrique', 'Cabanillas Rojas', '74713885', '968099508', 'kikecabanillas0003@gmail.com', '2001-04-09'),
('María', 'González', '87654321', '987654322', 'mariagonzalez@gmail.com', '1992-05-15'),
('Luis', 'Martínez', '11223344', '987654323', 'luismartinez@gmail.com', '1985-10-20');

INSERT INTO User (cliente_id, rol_id, username, password) VALUES
    (1, 1, 'Victor', '123456');