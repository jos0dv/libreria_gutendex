# Catalogo de libros - Challenge Literatura de Alura ONE

Este es un proyecto que pone a prueba las habilidades adquiridas en los cursos de Alura ONE como parte de la formación
La aplicación permite buscar libros utilizando una API y después guardarlos en una base de datos en PostgreSQL para posteriormente consultarla

## Descripción

Aplicación en desarrollo

## Funcionalidades

La aplicación permite:

- Buscar libros por título utilizando la API de Gutendex
- Gusrdar libros en la base de datos PostgreSQL
- Gusrdar autores relacionados con los libros
- Listar los libros registrados en la base de datos
- Listar los libros por idiomas
- Listar los autores registrados
- Listar autores vivos en determinado año
- Mostrar estadísticas de libros por idioma

## Tecnologías

Java (JDK)
IntelliJ Idea
Spring Boot
Spring Data JPA
PostgreSQL
Maven
API Gutendex

## APUIU utulizada

La aplicación consume la API pública Gutendex en :

https://gutendex.com/books/

Esta api proporciona información sobre libros, autores, idiomas y número de descargas

## Base de datos

Se utiliza el gestor de base de datos PostgreSQL para almacenar la información de:
- Libros
- Autores

Las entidadesse están relacionadas mediante el id del autor

## Cómo ejecutar el proyecto

Clonar el repositorio 

```bash
git clone https://github.com/tu-usuario/literalura.git
```

## Autor

Jose Hugo Vera Delgado

