# LiterAlura

## Descripción

Este proyecto está diseñado para obtener y gestionar datos de libros y autores utilizando la API de [Gutendex](https://gutendex.com/). La aplicación está construida con Java 17, Spring Boot, PostgreSQL, Maven, y utiliza Jackson-Databind para interactuar con la base de datos, realizar peticiones a la API de Gutendex, y mostrar los resultados en una consola interactiva.

## Tecnologías utilizadas

- **Java 17:** Lenguaje de programación utilizado para el desarrollo del proyecto.
- **Spring Boot:** Framework para la creación de aplicaciones web en Java.
- **PostgreSQL:** Base de datos relacional utilizada para almacenar información sobre libros y autores.
- **Maven:** Herramienta de gestión de dependencias y construcción del proyecto.
- **Jackson-Databind:** Librería utilizada para convertir objetos Java a formato JSON y viceversa.
- **IntelliJ IDEA:** IDE utilizado para el desarrollo del proyecto.
- **API de Gutendex:** API utilizada para obtener información sobre libros. Más detalles en [Gutendex](https://gutendex.com/).

## Funciones Disponibles

El sistema ofrece una serie de funcionalidades que permiten interactuar con los datos de libros y autores. A continuación se describen las funciones disponibles:

### 1. Buscar Libro

Permite buscar un libro en la base de datos mediante el título.

#### Descripción:

- **Entrada:** El usuario debe ingresar el título del libro.
- **Proceso:** Se consulta la API de Gutendex para obtener los datos del libro.
- Si el libro no está registrado en la base de datos, se crea un nuevo libro y autor (si no existen) y se guarda.
- **Salida:** Muestra un mensaje de éxito si el libro se guarda correctamente o un mensaje de error si ya está registrado.

### 2. Listar Libros Registrados

Permite listar todos los libros que están registrados en la base de datos.

#### Descripción:

- **Entrada:** No requiere ninguna entrada del usuario.
- **Proceso:** Se obtienen todos los libros registrados en la base de datos.
- **Salida:** Muestra la lista de libros registrados. Si no hay libros, muestra un mensaje indicando que la lista está vacía.

### 3. Listar Autores Registrados

Permite listar todos los autores que están registrados en la base de datos.

#### Descripción:

- **Entrada:** No requiere ninguna entrada del usuario.
- **Proceso:** Se obtienen todos los autores registrados en la base de datos.
- **Salida:** Muestra la lista de autores registrados. Si no hay autores, muestra un mensaje indicando que no hay datos registrados.

### 4. Listar Autores Vivos por Año

Permite listar autores que están vivos en un año específico.

#### Descripción:

- **Entrada:** El usuario debe ingresar un año.
- **Proceso:** Se consultan los autores registrados y se filtran aquellos cuya fecha de muerte es posterior al año ingresado.
- **Salida:** Muestra la lista de autores vivos en el año indicado, junto con los libros que han publicado.

### 5. Listar Autores Vivos por Año

Permite listar libros registrados en un idioma específico.

#### Descripción:

- **Entrada:** El usuario debe seleccionar un idioma (por ejemplo, español, inglés, francés o portugués).
- **Proceso:** Se filtran los libros registrados según el idioma seleccionado.
- **Salida:** Muestra la lista de libros disponibles en el idioma elegido. Si no hay libros en ese idioma, muestra un mensaje indicando que no se encontraron libros.
