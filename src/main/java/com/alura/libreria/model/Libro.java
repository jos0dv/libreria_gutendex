package com.alura.libreria.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String idioma;

    private Double numeroDescargas;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro(){}

    public Libro(DatosLibro datos){
        this.titulo = datos.titulo();
        this.idioma = datos.idiomas().get(0);
        this.numeroDescargas = datos.numeroDescargas();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "\n----- LIBRO -----" +
                "\nTítulo: " + titulo +
                "\nAutor: " + autor.getNombre() +
                "\nIdioma: " + idioma +
                "\nDescargas: " + numeroDescargas +
                "\n-----------------";
    }
}