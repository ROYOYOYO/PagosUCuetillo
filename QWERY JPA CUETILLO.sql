CREATE DATABASE DBCuetilloJPA;

USE DBCuetilloJPA;



-- Ejemplos de inserción de datos en la tabla carrera
INSERT INTO carrera (Descripcion, Semestres, CostoCredito) VALUES
/*(1, 'Ingeniería', 10, 150.00),*/
('Historia', 10, 120.00),
('Economía', 10, 140.00),
('Medicina', 12, 200.00),
('Arquitectura', 14, 180.00);

-- Ejemplos de inserción de datos en la tabla semestres
INSERT INTO semestre (descripcion, numero, fechaInicio, fechaFin, creditosMin) VALUES
/*('Primer Semestre', 1, '2022-01-10', '2022-06-30', 20),*/
('Segundo Semestre', 2, '2022-07-10', '2022-12-31', 22),
('Tercer Semestre', 3, '2023-01-10', '2023-06-30', 21),
('Cuarto Semestre', 4, '2023-07-10', '2023-12-31', 23),
('Quinto Semestre', 5, '2024-01-10', '2024-06-30', 22),
('Sexto Semestre', 6, '2024-07-10', '2024-12-31', 21),
('Séptimo Semestre', 7, '2025-01-10', '2025-06-30', 22),
('Octavo Semestre', 8, '2025-07-10', '2025-12-31', 23),
('Noveno Semestre', 9, '2026-01-10', '2026-06-30', 21),
('Décimo Semestre', 10, '2026-07-10', '2026-12-31', 20);

-- Ejemplos de inserción de datos en la tabla tAlumnos
INSERT INTO alumno (nombre, apellidos, edad, dni, genero, carrera, semestre, fechaNacimiento) VALUES
	('Juan', 'González', 20, 12345678, 'M', 1, 1, '2000-01-01'),
    ('María', 'López', 22, 87654321, 'F', 2, 2, '1998-02-15'),
    ('Pedro', 'Martínez', 21, 54321678, 'M', 3, 3, '1999-06-10'),
    ('Ana', 'Rodríguez', 19, 98765432, 'F', 4, 4, '2001-09-20'),
    ('Carlos', 'Fernández', 20, 23456789, 'M', 5, 5, '2000-12-05'),
    ('Laura', 'Gómez', 22, 76543210, 'F', 1, 1, '1998-03-25'),
    ('Luis', 'Pérez', 21, 87654321, 'M', 2, 2, '1999-07-30'),
    ('Sofía', 'Hernández', 19, 98765432, 'F', 3, 3, '2001-10-12'),
    ('Daniel', 'Torres', 20, 12345678, 'M', 4, 4, '2000-04-08'),
    ('Valeria', 'Rojas', 22, 23456789, 'F', 5, 5, '1998-11-18');

-- Ejemplos de inserción de datos en la tabla Asignaturas
INSERT INTO asignatura (Descripcion, Semestre, numeroCreditos, Carrera) VALUES
('Matemáticas', 2, 4, 1),
('Física', 2, 3, 1),
('Historia', 2, 2, 2),
('Biología', 4, 3, 2),
('Economía', '2', 4, 3);

-- Ejemplos de inserción de datos en la tabla Pagos
INSERT INTO pago (alumno, Monto, MetodoPago) VALUES
(1,'500.00', 'Tarjeta'),
(2,'200.00', 'Efectivo'),
(3,'500.00', 'Transferencia'),
(4, '200.00', 'Tarjeta'),
(5, '500.00', 'Efectivo');
