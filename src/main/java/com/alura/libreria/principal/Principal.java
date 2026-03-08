package com.alura.libreria.principal;

import com.alura.libreria.model.Autor;
import com.alura.libreria.model.Datos;
import com.alura.libreria.model.DatosAutor;
import com.alura.libreria.model.DatosLibro;
import com.alura.libreria.model.Libro;
import com.alura.libreria.repository.AutorRepository;
import com.alura.libreria.repository.LibroRepository;
import com.alura.libreria.service.ConsumoAPI;
import com.alura.libreria.service.ConvertirDatos;

import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvertirDatos conversor = new ConvertirDatos();

    private final String URL_BASE = "https://gutendex.com/books/";

    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio;

    public Principal(LibroRepository libroRepositorio, AutorRepository autorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void muestraMenu(){

        int opcion = -1;

        while(opcion != 0){

            System.out.println("""
                    
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar libros por idioma
                    4 - Listar autores registrados
                    5 - Listar autores vivos en determinado año
                    6 - Cantidad de libros por idioma
                    0 - Salir
                    
                    Seleccione una opción:
                    """);

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){

                case 1:
                    buscarLibroPorTitulo();
                    break;

                case 2:
                    listarLibros();
                    break;

                case 3:
                    listarLibrosPorIdioma();
                    break;

                case 4:
                    listarAutores();
                    break;

                case 5:
                    listarAutoresVivosEnAno();
                    break;

                case 6:
                    estadisticasPorIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando aplicación...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibroPorTitulo(){

        System.out.println("Ingrese el nombre del libro:");
        String titulo = teclado.nextLine();

        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + titulo.replace(" ", "+"));

        Datos datos = conversor.obtenerDatos(json, Datos.class);

        if(datos.resultados().size() > 0){

            DatosLibro datosLibro = datos.resultados().get(0);
            DatosAutor datosAutor = datosLibro.autores().get(0);

            Autor autor = new Autor(datosAutor);
            autorRepositorio.save(autor);

            Libro libro = new Libro(datosLibro);
            libro.setAutor(autor);

            libroRepositorio.save(libro);

            System.out.println(libro);

        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private void listarLibros(){

        List<Libro> libros = libroRepositorio.findAll();

        if(libros.isEmpty()){
            System.out.println("No hay libros registrados.");
        }else{
            libros.forEach(System.out::println);
        }
    }

    private void listarLibrosPorIdioma(){

        System.out.println("""
                Ingrese el idioma para buscar los libros:
                es - español
                en - inglés
                fr - francés
                pt - portugués
                """);

        String idioma = teclado.nextLine();

        List<Libro> libros = libroRepositorio.findByIdioma(idioma);

        if(libros.isEmpty()){
            System.out.println("No se encontraron libros en ese idioma.");
        }else{
            libros.forEach(System.out::println);
        }
    }

    private void listarAutores(){

        List<Autor> autores = autorRepositorio.findAll();

        if(autores.isEmpty()){
            System.out.println("No hay autores registrados.");
        }else{
            autores.forEach(System.out::println);
        }
    }

    private void listarAutoresVivosEnAno(){

        try {

            System.out.println("Ingrese el año para buscar autores vivos:");
            int ano = Integer.parseInt(teclado.nextLine());

            List<Autor> autores =
                    autorRepositorio.findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqual(ano, ano);

            if(autores.isEmpty()){
                System.out.println("No se encontraron autores vivos en ese año.");
            }else{
                System.out.println("\nAutores vivos en el año " + ano + ":\n");
                autores.forEach(System.out::println);
            }

        } catch (NumberFormatException e){

            System.out.println("Debe ingresar un número válido.");
        }
    }

    private void estadisticasPorIdioma(){

        long librosEspanol = libroRepositorio.countByIdioma("es");
        long librosIngles = libroRepositorio.countByIdioma("en");

        System.out.println("""
            
            Cantidad de libros por idioma
            
            Español: """ + librosEspanol + """
            
            Inglés: """ + librosIngles);
    }


}