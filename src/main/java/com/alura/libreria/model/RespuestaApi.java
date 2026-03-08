package com.alura.libreria.model;

import java.util.List;

public class RespuestaApi {
    private List<DatosLibro> resultado;

    public List<DatosLibro> getResultado() {
        return resultado;
    }

    public void setResultado(List<DatosLibro> resultado) {
        this.resultado = resultado;
    }

}
