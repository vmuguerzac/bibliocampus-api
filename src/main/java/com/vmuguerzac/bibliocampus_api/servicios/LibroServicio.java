package com.vmuguerzac.bibliocampus_api.servicios;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vmuguerzac.bibliocampus_api.modelos.Libro;
import com.vmuguerzac.bibliocampus_api.repositorio.LibroRepositorio;

@Service 
public class LibroServicio {
    private final LibroRepositorio libroRepositorio;

    public LibroServicio(LibroRepositorio instancia){
        this.libroRepositorio = instancia;
    }

    public List<Libro> listarLibros(){
        return libroRepositorio.findAll(); // SELECT .... FROM LIBROS
    }

    // Regla: pedir un libro que no existe deberia dar error 404
    public Libro obtenerLibro(Long id){
        return libroRepositorio.findById(id).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "El libro no existe")
        );
    }
    

}
