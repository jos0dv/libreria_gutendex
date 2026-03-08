package com.alura.libreria.repository;

import com.alura.libreria.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqual(Integer anoNacimiento, Integer anoFallecimiento);

}