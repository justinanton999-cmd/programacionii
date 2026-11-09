# Sistema de Pedidos de Cafetería (PF-21)

**Nombre:** Justin Alexander Anton Hernandez
**Carné:** 9941-25-20386
**Curso:** programacion 2

## Descripción del problema
Este sistema permite administrar los pedidos de una cafetería, registrando al cliente, los productos solicitados (con su cantidad) y calculando el total a pagar de forma automática. Además, permite gestionar el estado de cada pedido (recibido, en preparación, listo, entregado).

## Tecnologías utilizadas
* Java 21
* Spring Boot 4.1.1
* Spring Data JPA
* PostgreSQL
* HTML, CSS, JavaScript (Frontend)

## Requisitos para ejecutar el proyecto
1. Tener instalado Java JDK 17 o superior.
2. Tener instalado PostgreSQL y pgAdmin.
3. Tener Maven instalado (o usar el de IntelliJ).

## Configuración de PostgreSQL
1. Abrir pgAdmin y crear una base de datos llamada `cafeteria_db`.
2. Ejecutar los scripts SQL que están en la carpeta `database/` (primero el de estructura y luego el de datos de prueba).
3. Configurar el archivo `src/main/resources/application.properties` con su usuario y contraseña de PostgreSQL local:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/cafeteria_db
   spring.datasource.username=postgres
   spring.datasource.password=TU_CONTRASEÑA_AQUI