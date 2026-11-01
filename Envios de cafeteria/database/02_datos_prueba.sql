-- Insertar productos de prueba (mínimo 4)
INSERT INTO productos (nombre, precio) VALUES
                                           ('Café Americano', 15.00),
                                           ('Capuchino', 20.00),
                                           ('Sándwich de Pollo', 25.00),
                                           ('Dona Glaseada', 12.00);

-- (Opcional) Insertar algunos pedidos de prueba
INSERT INTO pedidos (cliente, total, estado, fecha) VALUES
                                                        ('Cliente Prueba 1', 30.00, 'recibido', NOW()),
                                                        ('Cliente Prueba 2', 45.00, 'en preparación', NOW());