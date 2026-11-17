package com.vmuguerzac.bibliocampus_api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vmuguerzac.bibliocampus_api.modelos.Libro;

public interface LibroRepositorio extends JpaRepository<Libro, Long>{
    
}
