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
                                                                                             ('Juan', 'Pérez', '12345678', '987654321', 'juan.perez@gmail.com', '1990-01-15'),
                                                                                             ('Ana', 'Gómez', '87654321', '987654322', 'ana.gomez@hotmail.com', '1992-02-20'),
                                                                                             ('Luis', 'Martinez', '56789012', '987654323', 'luis.martinez@yahoo.com', '1985-03-10'),
                                                                                             ('María', 'Rodríguez', '21098765', '987654324', 'maria.rodriguez@gmail.com', '1993-04-05'),
                                                                                             ('Carlos', 'Lopez', '34567890', '987654325', 'carlos.lopez@hotmail.com', '1988-05-25'),
                                                                                             ('Sara', 'Ruiz', '54321098', '987654326', 'sara.ruiz@gmail.com', '1991-06-18'),
                                                                                             ('David', 'Fernandez', '67890123', '987654327', 'david.fernandez@outlook.com', '1987-07-12'),
                                                                                             ('Laura', 'Morales', '43210987', '987654328', 'laura.morales@yahoo.com', '1995-08-22'),
                                                                                             ('José', 'Hernández', '78901234', '987654329', 'jose.hernandez@gmail.com', '1990-09-30'),
                                                                                             ('Lucía', 'Ramirez', '90123456', '987654330', 'lucia.ramirez@outlook.com', '1992-10-17'),
                                                                                             ('Victor Enrique', 'Cabanillas Rojas', '74713885', '968099508', 'kikecabanillas0003@gmail.com', '2001-04-09');

INSERT INTO User (cliente_id, rol_id, username, password) VALUES
    (11, 1, 'Victor', '123456');

INSERT INTO User (cliente_id, rol_id, username, password) VALUES
                                                              (1, 1, 'jperez', 'password123'),       -- administrador
                                                              (2, 2, 'agomez', 'password123'),        -- cajero
                                                              (3, 3, 'lmartinez', 'password123'),     -- cliente
                                                              (4, 3, 'mrodriguez', 'password123'),    -- cliente
                                                              (5, 1, 'clopez', 'password123'),        -- administrador
                                                              (6, 2, 'sruiz', 'password123'),         -- cajero
                                                              (7, 3, 'dfernandez', 'password123'),    -- cliente
                                                              (8, 3, 'lmorales', 'password123'),      -- cliente
                                                              (9, 2, 'jhernandez', 'password123'),    -- cajero
                                                              (10, 3, 'lramirez', 'password123');     -- cliente

INSERT INTO Reserva (user_id, cancha_id, precio_reserva, fecha_reserva, hora_inicio, hora_fin, estado_reserva)  VALUES
                                                                                                                    (3, 1, 50.00, '2024-10-01', '10:00:00', '12:00:00', 'Confirmada'),
                                                                                                                    (4, 2, 55.00, '2024-10-02', '13:00:00', '15:00:00', 'Pendiente'),
                                                                                                                    (7, 3, 60.00, '2024-10-03', '17:00:00', '19:00:00', 'Cancelada'),
                                                                                                                    (8, 4, 65.00, '2024-10-04', '14:00:00', '16:00:00', 'Confirmada'),
                                                                                                                    (10, 1, 50.00, '2024-10-05', '18:00:00', '20:00:00', 'Confirmada'),
                                                                                                                    (3, 2, 55.00, '2024-10-06', '08:00:00', '10:00:00', 'Pendiente'),
                                                                                                                    (4, 3, 60.00, '2024-10-07', '15:00:00', '17:00:00', 'Confirmada'),
                                                                                                                    (7, 4, 65.00, '2024-10-01', '09:00:00', '11:00:00', 'Cancelada'),
                                                                                                                    (8, 1, 50.00, '2024-10-02', '16:00:00', '18:00:00', 'Confirmada'),
                                                                                                                    (10, 3, 55.00, '2024-10-03', '12:00:00', '14:00:00', 'Pendiente');

INSERT INTO Pago (reserva_id, metodo_pago, monto, fecha_pago) VALUES
                                                                  (1, 'Efectivo', 50.00, '2024-10-01'),
                                                                  (2, 'Yape', 55.00, '2024-10-02'),
                                                                  (3, 'Efectivo', 60.00, '2024-10-03'),
                                                                  (4, 'Yape', 65.00, '2024-10-04'),
                                                                  (5, 'Efectivo', 50.00, '2024-10-05'),
                                                                  (6, 'Yape', 55.00, '2024-10-06'),
                                                                  (7, 'Efectivo', 60.00, '2024-10-07'),
                                                                  (8, 'Yape', 65.00, '2024-10-01'),
                                                                  (9, 'Efectivo', 50.00, '2024-10-02'),
                                                                  (10, 'Yape', 55.00, '2024-10-03');