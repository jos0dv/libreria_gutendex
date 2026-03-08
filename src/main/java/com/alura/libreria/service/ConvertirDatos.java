package com.alura.libreria.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertirDatos {
    private ObjectMapper mapper = new ObjectMapper();

    public <T> T obtenerDatos(String json, Class<T> clase){
        try {
            return mapper.readValue(json, clase);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
