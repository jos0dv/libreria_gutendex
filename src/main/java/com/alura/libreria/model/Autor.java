package com.alura.libreria.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer nacimiento;

    private Integer fallecimiento;

    public Autor(){}

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();
        this.nacimiento = datosAutor.anoNacimiento();
        this.fallecimiento = datosAutor.anoFallecimiento();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public Integer getFallecimiento() {
        return fallecimiento;
    }

    @Override
    public String toString() {
        return "\n----- AUTOR -----" +
                "\nNombre: " + nombre +
                "\nNacimiento: " + nacimiento +
                "\nFallecimiento: " + fallecimiento +
                "\n-----------------";
    }
}