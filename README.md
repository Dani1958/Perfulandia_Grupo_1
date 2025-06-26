# Perfulandia_Grupo_1
Empresa vendedora de perfumes para hombres y mujeres ✨

# ¿Cómo iniciamos?...👩🏻‍💻🥇👨🏻‍💻
1. Crear casos de prueba, los cuales pueden verse con mayor detalle en: [Casos de Prueba](https://docs.google.com/spreadsheets/d/14TDQm57UXrLNfO6yh2W4BZ1GGhcZ_xp6S-X-IP6Si2U/edit?gid=681915443#gid=681915443)
2. Usamos Carta Gantt para llevar un registro de nuestro avance:  [Carta Gantt](https://docs.google.com/spreadsheets/d/1T88xv_SvmG8MqBjmDm0QIB9hgcdLkPwPyVFqAx14S-c/edit?usp=sharing)

Nuestro backend lo haremos en base a el patrón de servicio MVC con servicio adicional, que seria MVCS (Modelo, Vista, Servicio y Controlador).
1. Usar [Spring Boot Initializr](https://start.spring.io/) y agregar dependencias.
2. Usar [Visual Studio Code](https://code.visualstudio.com/download) para implementar el módelo MVCS.
3. Ingresar a [GitHub](https://github.com/login)
4. Crear un repositorio, para poder trabajar con el equipo.

Una vez terminado los métodos, haremos pruebas unitarias en Postman, el cual puedes descargar [Aquí](https://www.postman.com/downloads/)

# Comencemos!!

¿Cómo funciona?

El sistema permite gestionar perfumes: crearlos, actualizarlos, eliminarlos y listarlos.
Puedes buscar perfumes por nombre, disponibilidad o categoría.
Todo se realiza mediante una API REST construida con Spring Boot

Tecnologías principales

1. Spring Boot
2. Visual Studio Code
3. GitHub
4. Postman

Estructura del proyecto

src/

└── main/

└── java/com/perfulandia/

├── controller/

│ └── PerfumeController.java

├── model/

│ └── Perfume.java

├── repository/

│ └── PerfumeRepository.java

├── service/

│ └── PerfumeService.java

└── PerfulandiaApplication.java

¿Cómo ejecutar?

1. Clona el repositorio -> Puedes hacerlo desde la interfaz web o en la terminal de VSC con el comando "git clone + url repositorio" en "git bash" o en "poweshell".
2. Abre el proyecto en Visual Studio Code -> al abrir VSC verás la opción "open folder" y seleccionar la carpeta.
3. Instala las extensiones necesarias (Extension pack for Java)
4. Ejecuta el proyecto con el comando "mvn spring-boot:run" en la terminal "command prompt".
