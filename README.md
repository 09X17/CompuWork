
# CompuWork - Sistema de Gestión de Recursos Humanos

Este proyecto fue desarrollado como una aplicación web en Java utilizando **Spring Boot** y ejecutado con **Maven**. CompuWork permite la gestión de empleados, departamentos y reportes de desempeño.

## 🛠️ Tecnologías Utilizadas

- Java 22+
- Spring Boot
- Spring Data JPA
- Thymeleaf
- H2 / PostgreSQL (según configuración)
- Maven
- JUnit 5 (Pruebas unitarias)
- Git / GitHub

## 📁 Módulos del Proyecto

### 1. Gestión de Empleados
- Registro, listado y eliminación de empleados.
- Soporte para distintos tipos: Tiempo Completo, Temporal, Por Horas, Permanente.

### 2. Gestión de Departamentos
- Creación y eliminación de departamentos.
- Asociación de empleados a departamentos.

### 3. Reportes de Desempeño
- Generación de reportes asociados a empleados.
- Visualización de desempeño.

### 4. Pruebas Unitarias
- Cobertura de pruebas con JUnit.
- Verificación de servicios con Mockito (`DepartamentoServiceImplTest`).

## 🚀 Ejecución del Proyecto

1. Clona el repositorio:

```bash
git clone https://github.com/09X17/CompuWork.git
cd CompuWork
```

2. Compila y ejecuta con Maven:

```bash
mvn clean install
mvn spring-boot:run
```

3. Accede a la aplicación en:

```
http://localhost:8080/
```

## 📄 Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── app.recursoshumanos
│   │       ├── controller
│   │       ├── entity
│   │       ├── repository
│   │       ├── service
│   │       └── exception
│   └── resources/
│       ├── templates/ (Thymeleaf)
│       └── application.properties
└── test/
    └── java/
        └── app.recursoshumanos.service
```

## 📝 Documentación del Proceso

- Se documentó la integración de componentes desde el backend hasta las vistas con Thymeleaf.
- Se realizaron pruebas unitarias para garantizar la lógica de negocio.
- El proyecto está versionado en GitHub y sigue buenas prácticas de arquitectura con Spring Boot.

## 📌 Autor

Desarrollado como parte de un ejercicio académico por [09X17](https://github.com/09X17).

---

¡Gracias por revisar el proyecto! ⭐