# Actividad 4: API REST con Spring Boot, Spring Security y JWT

Este proyecto es una API REST construida con SpringBoot y MySQL. Implementa autenticación mediante JSON Web Tokens (JWT) y Spring Security. Incluye validación de datos (Bean Validation), uso de DTOs para ocultar información sensible, y respuestas con códigos de estado HTTP apropiados.

La colección de pruebas de **Bruno** está incluida en la carpeta `/bruno` de este repositorio.

## Endpoints de Autenticación (Públicos - No requieren Token)

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/api/auth/register` | Registra un nuevo usuario en la base de datos. La contraseña se guarda encriptada mediante BCrypt. Valida que el email tenga formato correcto y no esté duplicado. |
| `POST` | `/api/auth/login` | Autentica al usuario usando email y contraseña. Si las credenciales son correctas, devuelve un Token JWT firmado con una vigencia de 24 horas. |

## Endpoints del CRUD - Entidad Materia (Privados - Requieren Token JWT)

Para acceder a estas rutas, es obligatorio incluir el header `Authorization: Bearer <token_jwt>`. Si no se incluye, la API responderá con un error `403 Forbidden`.

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/materias` | Lista todas las materias utilizando paginación. Permite enviar parámetros de URL (`?page=0&size=10`). |
| `GET` | `/api/materias/{id}` | Busca y devuelve una materia específica por su ID. |
| `POST` | `/api/materias` | Crea una nueva materia. Valida mediante un DTO (`MateriaDTO`) que el nombre no esté vacío. Devuelve `201 Created`. |
| `PUT` | `/api/materias/{id}` | Actualiza el nombre de una materia existente. Devuelve `200 OK` si tiene éxito, o `404 Not Found` si el ID no existe. |
| `DELETE` | `/api/materias/{id}` | Elimina una materia de la base de datos. Devuelve `204 No Content` al finalizar exitosamente. |