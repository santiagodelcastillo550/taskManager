INSERT INTO users (name, enabled, password, rol) VALUES ('admin', TRUE, '1234', 'ADMIN');

-- Tareas de ejemplo
INSERT INTO tasks (title, description, status, priority, limit_date, create_date, user_name) VALUES
('Tarea 1', 'Descripción 1', 'PENDIENTE', 'ALTA', '2025-09-10', NOW(), 'admin'),
('Tarea 2', 'Descripción 2', 'EN_PROCESO', 'MEDIA', '2025-09-12', NOW(), 'admin'),
('Tarea 3', 'Descripción 3', 'COMPLETADA', 'BAJA', '2025-09-08', NOW(), 'admin'),
('Tarea 4', 'Descripción 4', 'PENDIENTE', 'MEDIA', '2025-09-15', NOW(), 'admin'),
('Tarea 5', 'Descripción 5', 'EN_PROCESO', 'ALTA', '2025-09-18', NOW(), 'admin'),
('Tarea 6', 'Descripción 6', 'COMPLETADA', 'MEDIA', '2025-09-05', NOW(), 'admin');
