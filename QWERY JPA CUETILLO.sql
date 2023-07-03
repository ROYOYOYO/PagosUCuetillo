CREATE DATABASE DBCuetilloJPA;

USE DBCuetilloJPA;



-- Ejemplos de inserción de datos en la tabla carrera
INSERT INTO carrera (id, Descripcion, Semestres, CostoCredito) VALUES
('C001', 'Ingeniería', 10, 150.00),
('C002', 'Historia', 10, 120.00),
('C003', 'Economía', 10, 140.00),
('C004', 'Medicina', 12, 200.00),
('C005', 'Arquitectura', 9, 180.00);

-- Ejemplos de inserción de datos en la tabla semestres
INSERT INTO semestre (id, Descripcion, Numero, Inicio, Fin, Creditos) VALUES
('S001', 'Primer Semestre', 1, '2022-01-10', '2022-06-30', 20),
('S002', 'Segundo Semestre', 2, '2022-07-10', '2022-12-31', 22),
('S003', 'Tercer Semestre', 3, '2023-01-10', '2023-06-30', 21),
('S004', 'Cuarto Semestre', 4, '2023-07-10', '2023-12-31', 23),
('S005', 'Quinto Semestre', 5, '2024-01-10', '2024-06-30', 22),
('S006', 'Sexto Semestre', 6, '2024-07-10', '2024-12-31', 21),
('S007', 'Séptimo Semestre', 7, '2025-01-10', '2025-06-30', 22),
('S008', 'Octavo Semestre', 8, '2025-07-10', '2025-12-31', 23),
('S009', 'Noveno Semestre', 9, '2026-01-10', '2026-06-30', 21),
('S010', 'Décimo Semestre', 10, '2026-07-10', '2026-12-31', 20);

-- Ejemplos de inserción de datos en la tabla tAlumnos
INSERT INTO alumno (id, Nombre, Apellido, Edad, DNI, Genero, Carrera, Semestre) VALUES
('A001', 'Juan', 'Perez', 20, 12345678, 'M', 'C001', 'S002'),
('A002', 'Maria', 'Lopez', 21, 98765432, 'F', 'C004', 'S004'),
('A003', 'Carlos', 'Gomez', 19, 87654321, 'M', 'C002', 'S002'),
('A004', 'Laura', 'Rodriguez', 22, 56473829, 'F', 'C005', 'S004'),
('A005', 'Pedro', 'Martinez', 23, 38294756, 'M', 'C003', 'S002'),
('A006', 'Ana', 'García', 21, 23847592, 'F', 'C001', 'S002'),
('A007', 'Luis', 'Hernández', 20, 74658392, 'M', 'C002', 'S004'),
('A008', 'Sofía', 'Torres', 22, 92836475, 'F', 'C003', 'S002'),
('A009', 'Diego', 'Fernández', 19, 63548197, 'M', 'C004', 'S004'),
('A010', 'Carolina', 'Sánchez', 23, 29587463, 'F', 'C005', 'S002'),
('A011', 'Jorge', 'Ramírez', 21, 47582936, 'M', 'C001', 'S002'),
('A012', 'Fernanda', 'Gómez', 20, 72643895, 'F', 'C002', 'S004'),
('A013', 'Daniel', 'Martínez', 22, 91827364, 'M', 'C003', 'S002'),
('A014', 'Valeria', 'López', 19, 36482957, 'F', 'C004', 'S004'),
('A015', 'Hugo', 'Pérez', 23, 58273946, 'M', 'C005', 'S002'),
('A016', 'María', 'González', 21, 29475839, 'F', 'C001', 'S002'),
('A017', 'Alejandro', 'Rodríguez', 20, 72638475, 'M', 'C002', 'S004'),
('A018', 'Lucía', 'Vargas', 22, 91827364, 'F', 'C003', 'S002'),
('A019', 'Andrés', 'Soto', 19, 36482957, 'M', 'C004', 'S004'),
('A020', 'Camila', 'Navarro', 23, 58273946, 'F', 'C005', 'S002'),
('A021', 'Pedro', 'Ruiz', 21, 29475839, 'M', 'C001', 'S002'),
('A022', 'Laura', 'Mendoza', 20, 72638475, 'F', 'C002', 'S004'),
('A023', 'Ricardo', 'Gómez', 22, 91827364, 'M', 'C003', 'S002'),
('A024', 'Juliana', 'García', 19, 36482957, 'F', 'C004', 'S004'),
('A025', 'Andrea', 'Torres', 23, 58273946, 'F', 'C005', 'S002');

-- Ejemplos de inserción de datos en la tabla Asignaturas
INSERT INTO asignatura (id, Descripcion, Semestre, numeroCreditos, Carrera) VALUES
('AS001', 'Matemáticas', 'S002', 4, 'C001'),
('AS002', 'Física', 'S002', 3, 'C001'),
('AS003', 'Historia', 'S002', 2, 'C002'),
('AS004', 'Biología', 'S004', 3, 'C002'),
('AS005', 'Economía', 'S002', 4, 'C003');

-- Ejemplos de inserción de datos en la tabla Pagos
INSERT INTO pago (id, idAlumno, Monto, MetodoPago) VALUES
('P001', 'A001', '500.00', 'Tarjeta'),
('P002', 'A002', '200.00', 'Efectivo'),
('P003', 'A003', '500.00', 'Transferencia'),
('P004', 'A004', '200.00', 'Tarjeta'),
('P005', 'A005', '500.00', 'Efectivo');
