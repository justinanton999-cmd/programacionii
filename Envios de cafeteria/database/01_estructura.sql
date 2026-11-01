-- Crear la base de datos (Ejecutar esto primero)
-- CREATE DATABASE cafeteria_db;

-- Tabla de Productos
CREATE TABLE productos (
                           id BIGSERIAL PRIMARY KEY,
                           nombre VARCHAR(255) NOT NULL,
                           precio DOUBLE PRECISION NOT NULL
);

-- Tabla de Pedidos
CREATE TABLE pedidos (
                         id BIGSERIAL PRIMARY KEY,
                         cliente VARCHAR(255) NOT NULL,
                         total DOUBLE PRECISION NOT NULL,
                         estado VARCHAR(50) NOT NULL,
                         fecha TIMESTAMP NOT NULL
);

-- Tabla de Detalles (Relación)
CREATE TABLE detalle_pedidos (
                                 id BIGSERIAL PRIMARY KEY,
                                 pedido_id BIGINT NOT NULL REFERENCES pedidos(id) ON DELETE CASCADE,
                                 producto_id BIGINT NOT NULL REFERENCES productos(id),
                                 cantidad INTEGER NOT NULL
);