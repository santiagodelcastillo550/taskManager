-----------------------------------
------     TASKMANAGER    ------
--------------------------------

------ Descripción           ------

TaskManager es una aplicación web diseñada para organizar y gestionar tareas personales y profesionales
de manera eficiente.  
Permite a los usuarios crear, editar y eliminar tareas, asignarles fechas límite y visualizarlas de forma clara en un **tablero tipo Kanban** o en un **calendario interactivo** gracias a la implementación de FullCalendar. 

El proyecto combina la robustez de **Spring Boot** para el backend con la flexibilidad de **Thymeleaf**, **HTML**, **CSS** y **JavaScript** en el frontend, proporcionando una experiencia de usuario ágil e intuitiva. 
Cada usuario tiene su propio espacio para gestionar sus tareas, 
lo que facilita la organización y planificación diaria.

Entre sus principales características destacan:
- Visualización de tareas en un **tablero dinámico**, donde se pueden organizar por estado (pendiente, en progreso, completada).  
- Visualización de tareas en un **calendario interactivo**, mostrando fechas límite y permitiendo un control temporal claro.  
- **CRUD completo** para tareas: crear, editar, eliminar y marcar como completadas.  
- Autenticación básica de usuarios para proteger la información personal.  
- Estructura de proyecto escalable y modular, que permite añadir nuevas funcionalidades fácilmente.

------ Tecnologías Utilizadas ------

- **Java Spring**: Framework principal para la creación del backend y manejo de la lógica de negocio.
- **Spring Web**: Creación de controladores y gestión de solicitudes HTTP y sus respuestas.
- **H2 Database**: Base de datos en memoria utilizada para el almacenamiento de datos, en este caso en fichero.
- **Thymeleaf**: Motor de plantillas para generar contenido HTML dinámico.
- **FullCalendar**: Implementación usada para añadir el calendario.

------ Acceso a la aplicación ------

Para acceder a la aplicación, después de ejecutarla, tienes q poner este puerto en el navegador:

---> http://localhost:8088


------ Estructura del proyecto -----

El proyecto está estructurado en paquetes diversos:

- **com.example.taskManager**: Paquete base que añade la clase de arranque de la aplicación.
- **com.example.taskManager.bussiness**: Paquete encargado de tener los servicios con la lógica de negocio.
- **com.example.taskManager.common.exception**: Paquete que tiene las excepciones.
- **com.example.taskManager.controller**: Paquete encargado de tener los controladores y manejo entre templates.
- **com.example.taskManager.entities**: Paquete que tiene las entidades del proyecto que luego serán las tablas del proyecto.
- **com.example.taskManager.repositories**: Paquete que tiene los repositorios para interactuar con la base de datos.
- **src/main/resources/static**: Se encarga de guardar imágenes, index y estilos.
- **src/main/resources/templates/fragments**: Se encarga de tener código repetido en fragmentos en común. 
- **src/main/resources/templates**: Se encarga de tener código de las páginas que se muestran en la web.
- **application.properties**: Archivo de configuración principal de Spring Boot.
- **data.sql**: Archivo que tiene dentro datos iniciales que se cargan cuando se ejcuta la aplicación.