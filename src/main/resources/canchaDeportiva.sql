CREATE DATABASE IF NOT EXISTS Canchas;
USE Canchas;

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
                      username VARCHAR(50),
                      password VARCHAR(255),
                      FOREIGN KEY (cliente_id) REFERENCES Cliente(cliente_id) ON DELETE CASCADE ON UPDATE CASCADE,
                      FOREIGN KEY (rol_id) REFERENCES Rol(rol_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla Reserva
CREATE TABLE Reserva (
                         reserva_id INT AUTO_INCREMENT PRIMARY KEY,
                         cliente_id INT NOT NULL,
                         cancha_id INT NOT NULL,
                         precio_reserva DECIMAL(10,2) NOT NULL,
                         fecha_reserva DATE NOT NULL,
                         hora_inicio TIME NOT NULL,
                         hora_fin TIME NOT NULL,
                         estado_reserva VARCHAR(50),
                         FOREIGN KEY (cliente_id) REFERENCES Cliente(cliente_id) ON DELETE CASCADE ON UPDATE CASCADE,
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
                                                                                                                 (2, 50.00, 70.00, 'cancha2.png', '09:30:00', '20:30:00', 'Disponible'),
                                                                                                                 (3, 55.00, 75.00, 'cancha3.png', '10:30:00', '22:00:00', 'Disponible'),
                                                                                                                 (4, 60.00, 80.00, 'cancha4.png', '09:30:00', '19:00:00', 'Disponible');

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

INSERT INTO Reserva (cliente_id, cancha_id, precio_reserva, fecha_reserva, hora_inicio, hora_fin, estado_reserva) VALUES
                                                                                                                      (3, 1, 48.00, '2024-11-01', '10:00', '12:00', 'Pagado'), -- 2 horas
                                                                                                                      (4, 2, 45.00, '2024-11-01', '13:00', '14:30', 'Pendiente'), -- 1.5 horas
                                                                                                                      (7, 3, 42.50, '2024-11-01', '15:00', '17:00', 'Cancelada'), -- 2 horas
                                                                                                                      (8, 4, 47.50, '2024-11-02', '09:00', '11:30', 'Pagado'), -- 2.5 horas
                                                                                                                      (10, 1, 50.00, '2024-11-02', '12:00', '13:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (3, 2, 35.00, '2024-11-02', '14:00', '15:00', 'Pendiente'), -- 1 hora
                                                                                                                      (4, 3, 49.50, '2024-11-03', '10:00', '12:30', 'Pagado'), -- 2.5 horas
                                                                                                                      (7, 4, 33.50, '2024-11-03', '13:00', '14:30', 'Cancelada'), -- 1.5 horas
                                                                                                                      (8, 1, 46.50, '2024-11-04', '08:00', '09:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (10, 3, 50.00, '2024-11-04', '10:00', '12:00', 'Pendiente'), -- 2 horas
                                                                                                                      (3, 2, 37.00, '2024-11-04', '13:00', '15:30', 'Pagado'), -- 2.5 horas
                                                                                                                      (4, 3, 48.50, '2024-11-05', '16:00', '18:00', 'Pagado'), -- 2 horas
                                                                                                                      (7, 4, 45.00, '2024-11-05', '10:00', '11:00', 'Pendiente'), -- 1 hora
                                                                                                                      (8, 1, 38.00, '2024-11-06', '14:00', '15:30', 'Cancelada'), -- 1.5 horas
                                                                                                                      (10, 2, 44.00, '2024-11-06', '16:00', '18:00', 'Pagado'), -- 2 horas
                                                                                                                      (3, 4, 39.50, '2024-11-06', '11:00', '13:30', 'Pendiente'), -- 2.5 horas
                                                                                                                      (4, 1, 50.00, '2024-11-07', '09:00', '10:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (7, 3, 41.00, '2024-11-07', '11:00', '13:00', 'Cancelada'), -- 2 horas
                                                                                                                      (8, 4, 47.00, '2024-11-08', '13:30', '15:00', 'Pagado'), -- 1.5 horas
                                                                                                                      (3, 1, 43.50, '2024-11-08', '15:30', '17:30', 'Pagado'), -- 2 horas
                                                                                                                      (4, 2, 38.50, '2024-11-08', '08:00', '09:30', 'Pendiente'), -- 1.5 horas
                                                                                                                      (7, 3, 49.00, '2024-11-09', '09:00', '10:30', 'Cancelada'), -- 1.5 horas
                                                                                                                      (8, 4, 40.50, '2024-11-09', '11:00', '13:00', 'Pagado'), -- 2 horas
                                                                                                                      (10, 1, 32.50, '2024-11-10', '13:00', '14:00', 'Pagado'), -- 1 hora
                                                                                                                      (3, 2, 50.00, '2024-11-10', '14:30', '16:30', 'Pendiente'), -- 2 horas
                                                                                                                      (4, 3, 36.50, '2024-11-11', '10:00', '11:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (7, 4, 42.00, '2024-11-11', '12:00', '13:30', 'Pendiente'), -- 1.5 horas
                                                                                                                      (8, 1, 39.50, '2024-11-12', '08:30', '10:30', 'Cancelada'), -- 2 horas
                                                                                                                      (10, 2, 50.00, '2024-11-12', '11:00', '13:00', 'Pagado'), -- 2 horas
                                                                                                                      (3, 4, 31.50, '2024-11-13', '13:30', '15:00', 'Pendiente'); -- 1.5 horas


INSERT INTO Reserva (cliente_id, cancha_id, precio_reserva, fecha_reserva, hora_inicio, hora_fin, estado_reserva) VALUES
                                                                                                                      (3, 1, 45.00, '2024-01-01', '10:00', '12:00', 'Pagado'), -- 2 horas
                                                                                                                      (4, 2, 50.00, '2024-01-02', '13:00', '14:30', 'Pendiente'), -- 1.5 horas
                                                                                                                      (7, 3, 55.00, '2024-01-03', '15:00', '17:00', 'Cancelada'), -- 2 horas
                                                                                                                      (8, 4, 60.00, '2024-02-01', '09:30', '11:00', 'Pagado'), -- 1.5 horas
                                                                                                                      (10, 1, 45.00, '2024-02-02', '12:00', '13:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (3, 2, 50.00, '2024-02-05', '14:00', '15:30', 'Pendiente'), -- 1.5 horas
                                                                                                                      (4, 3, 55.00, '2024-02-08', '10:30', '12:00', 'Pagado'), -- 1.5 horas
                                                                                                                      (7, 4, 60.00, '2024-03-01', '13:30', '15:00', 'Cancelada'), -- 1.5 horas
                                                                                                                      (8, 1, 45.00, '2024-03-03', '08:00', '09:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (10, 3, 55.00, '2024-03-05', '10:30', '12:00', 'Pendiente'), -- 1.5 horas
                                                                                                                      (3, 2, 50.00, '2024-03-08', '14:00', '15:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (4, 3, 55.00, '2024-04-01', '16:00', '17:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (7, 4, 60.00, '2024-04-03', '10:00', '11:30', 'Pendiente'), -- 1.5 horas
                                                                                                                      (8, 1, 45.00, '2024-04-05', '14:00', '15:30', 'Cancelada'), -- 1.5 horas
                                                                                                                      (10, 2, 50.00, '2024-04-06', '16:00', '17:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (3, 4, 60.00, '2024-05-01', '11:00', '12:30', 'Pendiente'), -- 1.5 horas
                                                                                                                      (4, 1, 45.00, '2024-05-04', '09:00', '10:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (7, 2, 50.00, '2024-05-07', '12:00', '13:30', 'Pendiente'), -- 1.5 horas
                                                                                                                      (8, 3, 55.00, '2024-05-08', '13:30', '15:00', 'Pagado'), -- 1.5 horas
                                                                                                                      (10, 4, 60.00, '2024-05-10', '16:00', '17:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (3, 1, 45.00, '2024-06-02', '14:00', '15:30', 'Pendiente'), -- 1.5 horas
                                                                                                                      (4, 3, 55.00, '2024-06-04', '12:00', '13:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (7, 4, 60.00, '2024-06-07', '10:00', '11:30', 'Cancelada'), -- 1.5 horas
                                                                                                                      (8, 1, 45.00, '2024-06-09', '15:30', '17:00', 'Pagado'), -- 1.5 horas
                                                                                                                      (10, 2, 50.00, '2024-07-01', '13:30', '15:00', 'Pendiente'), -- 1.5 horas
                                                                                                                      (3, 3, 55.00, '2024-07-04', '14:00', '15:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (4, 4, 60.00, '2024-07-06', '16:00', '17:30', 'Cancelada'), -- 1.5 horas
                                                                                                                      (7, 1, 45.00, '2024-08-02', '10:00', '11:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (8, 2, 50.00, '2024-08-04', '13:00', '14:30', 'Pendiente'), -- 1.5 horas
                                                                                                                      (10, 3, 55.00, '2024-08-06', '11:00', '12:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (3, 4, 60.00, '2024-08-08', '16:00', '17:30', 'Pendiente'), -- 1.5 horas
                                                                                                                      (4, 1, 45.00, '2024-09-02', '13:30', '15:00', 'Pagado'), -- 1.5 horas
                                                                                                                      (7, 2, 50.00, '2024-09-05', '10:00', '11:30', 'Cancelada'), -- 1.5 horas
                                                                                                                      (8, 3, 55.00, '2024-09-08', '12:00', '13:30', 'Pendiente'), -- 1.5 horas
                                                                                                                      (10, 4, 60.00, '2024-09-10', '14:00', '15:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (3, 1, 45.00, '2024-10-02', '13:00', '14:30', 'Pendiente'), -- 1.5 horas
                                                                                                                      (4, 2, 50.00, '2024-10-04', '10:00', '11:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (7, 3, 55.00, '2024-10-06', '14:00', '15:30', 'Cancelada'), -- 1.5 horas
                                                                                                                      (8, 4, 60.00, '2024-10-08', '11:00', '12:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (10, 1, 45.00, '2024-11-01', '10:00', '11:30', 'Pendiente'), -- 1.5 horas
                                                                                                                      (3, 2, 50.00, '2024-11-03', '15:30', '17:00', 'Pagado'), -- 1.5 horas
                                                                                                                      (4, 3, 55.00, '2024-11-06', '10:00', '11:30', 'Cancelada'), -- 1.5 horas
                                                                                                                      (7, 4, 60.00, '2024-11-08', '14:00', '15:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (8, 1, 45.00, '2024-12-02', '10:00', '11:30', 'Pagado'), -- 1.5 horas
                                                                                                                      (10, 2, 50.00, '2024-12-05', '12:00', '13:30', 'Pendiente'), -- 1.5 horas
                                                                                                                      (3, 3, 55.00, '2024-12-08', '14:00', '15:30', 'Cancelada'), -- 1.5 horas
                                                                                                                      (4, 4, 60.00, '2024-12-10', '14:00', '15:30', 'Pagado'); -- 1.5 horas

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

DELIMITER //

CREATE PROCEDURE RegistrarCajero(
    IN nombre VARCHAR(100),
    IN apellido VARCHAR(100),
    IN nro_identidad VARCHAR(20),
    IN telefono VARCHAR(15),
    IN email VARCHAR(100),
    IN fecha_nacimiento DATE,
    IN username VARCHAR(50),
    IN password_hash VARCHAR(255) -- La contraseña debe llegar ya encriptada
)
BEGIN
    DECLARE cliente_id INT;
    DECLARE rol_id INT DEFAULT 2; -- Asignamos directamente el rol_id de "cajero" (suponiendo que es 2)

    -- Iniciar la transacción
START TRANSACTION;

-- Insertar en la tabla Cliente
INSERT INTO Cliente (nombre, apellido, nro_identidad, telefono, email, fecha_nacimiento)
VALUES (nombre, apellido, nro_identidad, telefono, email, fecha_nacimiento);

-- Obtener el cliente_id generado
SET cliente_id = LAST_INSERT_ID();

    -- Insertar en la tabla User con rol_id ya asignado
INSERT INTO User (cliente_id, rol_id, username, password)
VALUES (cliente_id, rol_id, username, password_hash);

-- Confirmar la transacción
COMMIT;

END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE ListarUsuariosCajeros()
BEGIN
SELECT u.user_id,
       c.nombre,
       c.apellido,
       c.nro_identidad,
       c.telefono,
       c.email,
       c.fecha_nacimiento,
       u.username,
       u.password
FROM User u
         JOIN Cliente c ON u.cliente_id = c.cliente_id
         JOIN Rol r ON u.rol_id = r.rol_id
WHERE r.rol = 'Cajero';
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE ActualizarUsuarioCajero(
    IN p_user_id INT,
    IN p_nombre VARCHAR(100),
    IN p_apellido VARCHAR(100),
    IN p_nro_identidad VARCHAR(20),
    IN p_telefono VARCHAR(15),
    IN p_email VARCHAR(100),
    IN p_fecha_nacimiento DATE,
    IN p_username VARCHAR(50),
    IN p_password VARCHAR(255)
)
BEGIN
    DECLARE v_rol_id INT;

    -- Obtener el rol_id de "Cajero" para verificar el rol del usuario
SELECT rol_id INTO v_rol_id FROM Rol WHERE rol = 'Cajero';

-- Actualizar en la tabla Cliente
UPDATE Cliente c
    JOIN User u ON u.cliente_id = c.cliente_id
    SET c.nombre = p_nombre,
        c.apellido = p_apellido,
        c.nro_identidad = p_nro_identidad,
        c.telefono = p_telefono,
        c.email = p_email,
        c.fecha_nacimiento = p_fecha_nacimiento,
        u.username = p_username,
        u.password = p_password
WHERE u.user_id = p_user_id AND u.rol_id = v_rol_id;
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE EliminarUsuarioCajero(
    IN p_user_id INT
)
BEGIN
    DECLARE v_cliente_id INT;
    DECLARE v_rol_id INT;

    -- Obtener el rol_id de "Cajero"
SELECT rol_id INTO v_rol_id FROM Rol WHERE rol = 'Cajero';

-- Verificar que el usuario existe y es cajero
SELECT cliente_id INTO v_cliente_id
FROM User
WHERE user_id = p_user_id AND rol_id = v_rol_id;

-- Si se encontró el cliente_id, proceder a eliminar
IF v_cliente_id IS NOT NULL THEN
        -- Primero eliminar el usuario
DELETE FROM User WHERE user_id = p_user_id;

-- Luego eliminar el cliente
DELETE FROM Cliente WHERE cliente_id = v_cliente_id;
END IF;
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE CrearCancha(
    IN p_nro_cancha INT,
    IN p_precio_dia DECIMAL(10,2),
    IN p_precio_noche DECIMAL(10,2),
    IN p_imagen_cancha VARCHAR(100),
    IN p_hora_abierto TIME,
    IN p_hora_cerrado TIME,
    IN p_estado VARCHAR(50)
)
BEGIN
INSERT INTO Cancha (nro_cancha, precio_dia, precio_noche, imagen_cancha, hora_abierto, hora_cerrado, estado)
VALUES (p_nro_cancha, p_precio_dia, p_precio_noche, p_imagen_cancha, p_hora_abierto, p_hora_cerrado, p_estado);
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE ActualizarCancha(
    IN p_cancha_id INT,
    IN p_nro_cancha INT,
    IN p_precio_dia DECIMAL(10,2),
    IN p_precio_noche DECIMAL(10,2),
    IN p_imagen_cancha VARCHAR(100),
    IN p_hora_abierto TIME,
    IN p_hora_cerrado TIME,
    IN p_estado VARCHAR(50)
)
BEGIN
UPDATE Cancha
SET nro_cancha = p_nro_cancha,
    precio_dia = p_precio_dia,
    precio_noche = p_precio_noche,
    imagen_cancha = p_imagen_cancha,
    hora_abierto = p_hora_abierto,
    hora_cerrado = p_hora_cerrado,
    estado = p_estado
WHERE cancha_id = p_cancha_id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE EliminarCancha(
    IN p_cancha_id INT
)
BEGIN
DELETE FROM Cancha
WHERE cancha_id = p_cancha_id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE ReservarCancha(
    IN p_cliente_nombre VARCHAR(100),
    IN p_cliente_apellido VARCHAR(100),
    IN p_nro_identidad VARCHAR(20),
    IN p_telefono VARCHAR(15),
    IN p_email VARCHAR(100),
    IN p_fecha_nacimiento DATE,
    IN p_cancha_id INT,
    IN p_hora_inicio TIME,
    IN p_hora_fin TIME,
    IN p_metodo_pago VARCHAR(50),
    IN p_fecha_reserva DATE,  -- Nuevo parámetro para la fecha de la reserva
    OUT p_resultado VARCHAR(255)
)
BEGIN
    DECLARE v_precio DECIMAL(10,2);
    DECLARE v_hora_abierto TIME;
    DECLARE v_hora_cerrado TIME;
    DECLARE v_precio_dia DECIMAL(10,2);
    DECLARE v_precio_noche DECIMAL(10,2);
    DECLARE v_rol_id INT;
    DECLARE v_reserva_id INT;

    -- Obtener la información de la cancha
SELECT hora_abierto, hora_cerrado, precio_dia, precio_noche
INTO v_hora_abierto, v_hora_cerrado, v_precio_dia, v_precio_noche
FROM Cancha
WHERE cancha_id = p_cancha_id;

-- Verificar que las horas de inicio y fin estén dentro del horario de apertura y cierre
IF p_hora_inicio < v_hora_abierto OR p_hora_fin > v_hora_cerrado THEN
        SET p_resultado = 'Error: La hora de la reserva está fuera del horario permitido de la cancha.';
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = p_resultado;
END IF;

    -- Verificar cruce de horarios con otras reservas confirmadas o pagadas
    IF EXISTS (
        SELECT 1
        FROM Reserva
        WHERE cancha_id = p_cancha_id
          AND estado_reserva IN ('Confirmada', 'Pagada')  -- Solo busca reservas confirmadas o pagadas
          AND p_fecha_reserva = fecha_reserva  -- Compara también la fecha de la reserva
          AND ((p_hora_inicio >= hora_inicio AND p_hora_inicio < hora_fin)
            OR (p_hora_fin > hora_inicio AND p_hora_fin <= hora_fin)
            OR (p_hora_inicio <= hora_inicio AND p_hora_fin >= hora_fin))
    ) THEN
        SET p_resultado = 'Error: La reserva se cruza con otra existente.';
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = p_resultado;
END IF;

    -- Determinar si el precio es de día o de noche
    IF HOUR(p_hora_inicio) >= 6 AND HOUR(p_hora_inicio) < 18 THEN
        SET v_precio = v_precio_dia;
       ELSE
       SET v_precio = v_precio_noche;
       END IF;

    -- Insertar los datos del cliente
INSERT INTO Cliente (nombre, apellido, nro_identidad, telefono, email, fecha_nacimiento)
VALUES (p_cliente_nombre, p_cliente_apellido, p_nro_identidad, p_telefono, p_email, p_fecha_nacimiento);

-- Obtener el ID del cliente recién insertado
SET @cliente_id = LAST_INSERT_ID();

    -- Insertar el usuario con rol de cliente
SELECT rol_id INTO v_rol_id FROM Rol WHERE rol = 'cliente';

INSERT INTO User (cliente_id, rol_id, username, password)
VALUES (@cliente_id, v_rol_id, NULL, NULL);

-- Insertar la reserva
INSERT INTO Reserva (cliente_id, cancha_id, precio_reserva, fecha_reserva, hora_inicio, hora_fin, estado_reserva)
VALUES (@cliente_id, p_cancha_id, v_precio, p_fecha_reserva, p_hora_inicio, p_hora_fin, 'Pendiente');

-- Obtener el ID de la reserva recién insertada
SET v_reserva_id = LAST_INSERT_ID();

    -- Registrar el pago en la tabla Pago
INSERT INTO Pago (reserva_id, metodo_pago, monto, fecha_pago)
VALUES (v_reserva_id, p_metodo_pago, v_precio, CURDATE());

SET p_resultado = 'Reserva y pago realizados con éxito.';

END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE ListarClientes()
BEGIN
SELECT
    c.cliente_id,
    c.nombre,
    c.apellido,
    c.nro_identidad,
    c.telefono,
    c.email,
    c.fecha_nacimiento,
    r.rol
FROM
    User u
        JOIN
    Cliente c ON u.cliente_id = c.cliente_id
        JOIN
    Rol r ON u.rol_id = r.rol_id
WHERE
    r.rol = 'Cliente';
END //

DELIMITER ;
