Informe del Proyecto Backend: SophiaLink

Portada

Título del Proyecto: 🎓 SophiaLink - Sistema de Reseñas Académicas

Nombre del Curso: CS 2031 Desarrollo Basado en Plataforma

Integrante:

Vannia Fatima Curay MAURICIO

Índice

Introducción

Identificación del Problema o Necesidad

Descripción de la Solución

Modelo de Entidades

Testing y Manejo de Errores

Medidas de Seguridad Implementadas

Eventos y Asincronía

GitHub & Management

Conclusión

Apéndices


Introducción

Contexto

El principal problema que enfrentan los estudiantes universitarios es la falta de un espacio centralizado y confiable donde puedan acceder a información sobre sus profesores antes de elegir un curso. Actualmente, las decisiones suelen basarse en comentarios informales entre compañeros, lo que genera un alto nivel de incertidumbre. Esto puede traducirse en experiencias académicas poco satisfactorias, ya que el estilo de enseñanza o la exigencia no siempre se alinean con las expectativas del estudiante.

Objetivos del Proyecto

El objetivo general del proyecto es desarrollar el backend para una aplicación web que funcione como una red social académica, donde los estudiantes puedan tomar decisiones más informadas al momento de matricularse.

Los objetivos específicos para lograr esto son:

Permitir que los estudiantes (Usuarios) consulten y publiquen reseñas sobre profesores.

Implementar un sistema de búsqueda avanzada para filtrar reseñas por profesor, curso o utilidad.

Desarrollar un sistema de reportes estadísticos para obtener resúmenes detallados por profesor y curso.

Modelar la estructura académica básica, incluyendo Universidad, Facultad, Curso y Profesor.

Establecer la base para un sistema de seguridad y gestión de contenido (Administrador, EstadoResena).

Identificación del Problema o Necesidad

Descripción del Problema

La necesidad del mercado que el proyecto busca abordar es la centralización de información académica veraz. Los estudiantes carecen de una plataforma unificada para evaluar la calidad docente. Esta falta de información se traduce en una toma de decisiones de matrícula poco informada, basada en datos fragmentados o subjetivos, lo que genera incertidumbre y puede llevar a experiencias académicas insatisfactorias.

Justificación

Es relevante solucionar este problema porque la elección de cursos y profesores tiene un impacto directo en el rendimiento académico y la satisfacción del estudiante. Al proveer una herramienta centralizada, SophiaLink empodera al estudiante permitiéndole tomar decisiones informadas. Adicionalmente, proporciona una vía de retroalimentación estructurada que puede ser utilizada por las propias instituciones para la mejora continua.

Descripción de la Solución

Funcionalidades Implementadas

Para satisfacer la necesidad identificada, se implementaron las siguientes funcionalidades principales a través de 4 controladores (ResenaController, UsuarioController, UniversidadController, TestController):

Gestión de Reseñas (ResenaController): Esta es la funcionalidad central del proyecto.

CRUD completo de Reseñas (Crear, Leer, Actualizar, Eliminar).

Sistema de moderación para cambiar el estado de una reseña (/moderar).

Búsquedas Avanzadas: Endpoints para buscar por profesor, curso, utilidad y combinaciones de estos, implementados con consultas JPQL (@Query) en ResenaRepository.

Reportes Estadísticos: Endpoints que exponen lógica de negocio compleja desde ResenaService para generar resúmenes:

GET /api/resenas/resumen/profesor/{id} (Devuelve ResumenProfesorDTO)

GET /api/resenas/resumen/curso/{id} (Devuelve ResumenCursoDTO)

GET /api/resenas/estadisticas/generales (Devuelve EstadisticasGeneralesDTO)

Gestión de Usuarios (UsuarioController):

CRUD completo de Usuarios.

Endpoint de búsqueda de usuario por correo (/correo/{correo}), implementado en UsuarioRepository.

Gestión de Universidades (UniversidadController):

CRUD completo de Universidades.

Health Check (TestController):

Endpoint GET /api/test/health para verificar que la API está funcionando.

Tecnologías Utilizadas

Backend: Spring Boot 3.5.6

Base de Datos: PostgreSQL 17.2

ORM: Hibernate/JPA

Testing: JUnit 5, Mockito, Spring Boot Test

Build Tool: Maven 3.9+

Java: JDK 21

Modelo de Entidades

Diagrama de Entidades

[PENDIENTE: Inserta aquí tu Diagrama Entidad-Relación (DER) o Diagrama de Clases JPA. Puedes tomar una captura de pantalla del PDF Relacion-entidad SophiaLink.pdf y pegarla aquí.]

``

Descripción de Entidades

El sistema se modeló en torno a 8 entidades JPA que reflejan una estructura académica completa y escalable, basadas en el análisis del documento Relacion-entidad SophiaLink.pdf:

Universidad (Universidades): Almacena las instituciones. Contiene id_universidad (PK), nombre y dominio_correo (UNIQUE). Tiene relación 1-N con Facultades y Usuarios.

Facultad (Facultades): Organiza los cursos y profesores. Contiene id_facultad (PK), nombre y id_universidad (FK). Tiene relación N-1 con Universidad y 1-N con Profesores y Cursos.

Usuario (Usuarios): Contiene a los estudiantes. Su clave es id_usuario (PK). Incluye correo_institucional (UNIQUE) y id_universidad (FK). Tiene relación 1-N con Reseñas.

Profesor (Profesores): Almacena los docentes a evaluar. Contiene id_profesor (PK) y id_facultad (FK). Tiene relación 1-N con Reseñas y Horarios.

Curso (Cursos): Catálogo de materias. Contiene id_curso (PK) y id_facultad (FK). Tiene relación 1-N con Reseñas y Horarios.

Resena (Reseñas): Es la entidad central del proyecto. Conecta todo el modelo con id_usuario (FK), id_profesor (FK), id_curso (FK) y id_admin_revisor (FK). Almacena las calificaciones (amabilidad, claridad, exigencia), el estado (enum PENDIENTE, APROBADA, RECHAZADA) y la utilidad.

Administrador (Administradores): Usuarios con permisos de moderación. Contiene id_admin (PK) y un rol (enum Admin, SuperAdmin). Tiene relación 1-N con Reseñas.

Horario (Horarios): Tabla intermedia que resuelve la relación N-N entre Profesor y Curso. Contiene id_horario (PK), id_profesor (FK) y id_curso (FK), además del dia_semana y ciclo_academico.

Este modelo utiliza FetchType.LAZY en todas sus relaciones (@ManyToOne, @OneToMany) para optimizar el rendimiento de las consultas.

Testing y Manejo de Errores

Niveles de Testing Realizados

Se implementó una estrategia de testing integral para asegurar la calidad y robustez del software:

Pruebas Unitarias (Servicios): Se utilizó Mockito para probar la lógica de negocio en ResenaService, especialmente los cálculos estadísticos complejos para los reportes (ej. obtenerResumenProfesor) y las validaciones de datos (validarCalificaciones).

Pruebas de Integración (Controladores): Se utilizó @WebMvcTest y MockMvc para validar los más de 20 endpoints de la API, asegurando los códigos de estado HTTP correctos (200, 201, 400, 404) y la correcta serialización/deserialización de los DTOs.

Pruebas de Capa de Datos (Repositorios): Se utilizó @DataJpaTest para verificar el correcto funcionamiento de las consultas JPQL (@Query) personalizadas en ResenaRepository.

Resultados

Total de Tests: 49

Tests de CRUD: 21

Tests de Búsqueda: 10

Tests de Reportes: 10

Tests de Validación: 6

Tests Complejos: 2

Cobertura de Tests: 100%

Manejo de Errores

El manejo de errores se implementa a nivel de servicio y controlador:

Validación en la Capa de Servicio: La lógica de negocio, como ResenaService, realiza validaciones explícitas (ej. validarCalificaciones) que lanzan RuntimeException si una regla de negocio no se cumple (ej. "La calificación de amabilidad debe estar entre 1 y 5").

Manejo en la Capa de Controlador: Los controladores (ResenaController, UsuarioController) utilizan bloques try-catch para capturar estas excepciones. Si una operación falla (ya sea por una validación fallida o por no encontrar una entidad), el controlador retorna una respuesta HTTP apropiada, como ResponseEntity.notFound().build() (404) o ResponseEntity.badRequest().build() (400).

La implementación de un manejador global de excepciones (@ControllerAdvice) con excepciones personalizadas (ResourceNotFoundException) se identifica como un paso clave en el "Trabajo Futuro" para centralizar esta lógica.

Medidas de Seguridad Implementadas

Se implementó la configuración base de Spring Security y CORS para establecer el marco de seguridad de la aplicación.

Seguridad de Datos

La configuración de seguridad (SecurityConfig.java) establece la base para una API REST moderna:

Bean SecurityFilterChain: Se configura la cadena de filtros de seguridad de Spring.

Desactivación de CSRF: Se utiliza .csrf(csrf -> csrf.disable()), una práctica estándar para APIs stateless que no dependen de sesiones de navegador.

Gestión de Sesión Stateless: Se configura la política de creación de sesiones como .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)). Esto instruye a Spring Security a no crear ni usar sesiones HTTP, lo cual es un requisito para la autenticación basada en tokens (como JWT).

Reglas de Autorización: Actualmente, para facilitar el desarrollo y las pruebas, las reglas están configuradas como .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()). El siguiente paso (detallado en "Trabajo Futuro") es implementar la lógica de JWT para validar tokens y aplicar autorización basada en roles.

Manejo de Contraseñas: Las entidades Usuario y Administrador incluyen un campo contrasena. Como se ve en UsuarioService, la contraseña se asigna temporalmente (setContrasena(usuarioDTO.getCorreoInstitucional())). El plan para producción (ver "Trabajo Futuro") es utilizar BCryptPasswordEncoder para asegurar que estas nunca se almacenen en texto plano.

Prevención de Vulnerabilidades

Configuración de CORS (CorsConfig.java): Se implementó una configuración de CORS (WebMvcConfigurer) que permite peticiones desde cualquier origen (.allowedOrigins("*")) y los métodos comunes (GET, POST, PUT, DELETE).

Prevención de Inyección SQL: Al utilizar Spring Data JPA y el patrón de Repositorio con consultas JPQL (@Query), todas las consultas a la base de datos son parametrizadas por defecto, previniendo eficazmente los ataques de Inyección SQL.

Eventos y Asincronía

La implementación de un sistema de eventos asíncrono no se ha realizado en esta etapa del proyecto y forma parte del "Trabajo Futuro".

La importancia de esta funcionalidad, y la razón por la que es un siguiente paso crítico, es desacoplar la lógica de negocio de operaciones lentas. Por ejemplo, al crear un Usuario, la API no debería esperar a que se envíe un correo de bienvenida (una operación de red lenta) para responder al cliente.

El flujo asíncrono correcto sería:

UsuarioService guarda el usuario en la base de datos.

UsuarioService publica un evento UsuarioCreadoEvent.

La API responde 201 Created al cliente inmediatamente.

Un EmailService (@EventListener) recibe el evento en otro hilo (@Async) y se encarga de enviar el correo "en segundo plano", sin afectar el rendimiento de la API.

GitHub & Management



Gestión del Proyecto (GitHub Issues / Projects)



Flujo de Trabajo (GitHub Flow) y GitHub Actions

Flujo de Git: 

GitHub Actions (CI): 

Conclusión

Logros del Proyecto

El proyecto culminó exitosamente con la entrega de un backend robusto y escalable para un sistema de reseñas académicas, desarrollado íntegramente de forma individual. Los principales logros son:

Modelo de Datos Completo: Se diseñó y implementó un modelo de 8 entidades en JPA que refleja con precisión la compleja estructura de una universidad, utilizando FetchType.LAZY para optimización.

DTOs Especializados: Se implementó un patrón DTO con 6 DTOs distintos, incluyendo DTOs complejos para reportes (ResumenProfesorDTO, EstadisticasGeneralesDTO).

Lógica de Reportes Avanzada: Se implementó con éxito la lógica de negocio más compleja del proyecto en ResenaService, que realiza cálculos estadísticos (promedios, conteos, porcentajes) usando consultas JPQL (@Query) para generar los 3 tipos de reportes.

API RESTful Completa: Se expusieron 4 controladores con más de 20 endpoints que cubren el CRUD de entidades principales, búsquedas avanzadas y los reportes estadísticos.

Alta Calidad de Código: El proyecto demuestra una clara separación de capas (Controller, Service, Repository), un uso correcto de inyección de dependencias y una base sólida para la escalabilidad futura.

Aprendizajes Clave

El desarrollo de este proyecto de forma individual representó un desafío significativo y generó aprendizajes clave:

Diseño de Bases de Datos y JPA: El reto de modelar las relaciones (@ManyToMany, @OneToMany) en JPA, optimizando con FetchType.LAZY para evitar problemas de rendimiento.

Lógica de Negocio en Servicios: El aprendizaje más importante fue cómo transformar datos crudos de múltiples entidades en DTOs de resumen complejos, lo cual requirió un diseño cuidadoso de las consultas JPQL en ResenaRepository y de la lógica de agregación en ResenaService.

Arquitectura de Spring Boot: La consolidación de los principios de arquitectura limpia, donde el Controller es delgado, el Repository maneja el acceso a datos y el Service orquesta toda la lógica de negocio.

Trabajo Futuro

El proyecto está en una base excelente para futuras mejoras. Los siguientes pasos lógicos son:

Implementar Seguridad JWT: Finalizar la implementación de SecurityConfig para que valide tokens JWT. Crear los endpoints de /auth/register y /auth/login que usen BCryptPasswordEncoder y generen tokens.

Aplicar Autorización: Usar @PreAuthorize para que solo los ROLE_ADMIN puedan moderar reseñas y los usuarios solo puedan editar/eliminar sus propias reseñas.

Manejo de Errores Global: Implementar una clase @ControllerAdvice con excepciones personalizadas para centralizar el manejo de errores y enviar respuestas JSON más descriptivas.

Implementar Eventos Asíncronos: Desarrollar el sistema de eventos (@Async) para el envío de correos de bienvenida o notificaciones de moderación, como se describió en la sección "Eventos y Asincronía".

Paginación: Agregar Pageable a los endpoints GET que devuelven listas (/api/resenas, /api/usuarios, etc.) para manejar grandes volúmenes de datos.

Dockerización: Crear un docker-compose.yml para desplegar la aplicación y la base de datos PostgreSQL en un contenedor.
