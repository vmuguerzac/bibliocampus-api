package com.vmuguerzac.bibliocampus_api.servicios;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vmuguerzac.bibliocampus_api.dto.LibroSolictud;
import com.vmuguerzac.bibliocampus_api.modelos.Libro;
import com.vmuguerzac.bibliocampus_api.repositorio.LibroRepositorio;

@Service 
public class LibroServicio {
    private final LibroRepositorio libroRepositorio;

    public LibroServicio(LibroRepositorio instancia){
        this.libroRepositorio = instancia;
    } 

    // Regla: no se repite la combinacion titulo mas autor (409)
    public Libro crearLibro(LibroSolictud datosLibro){
        // Validacion
        if(libroRepositorio.existsByTituloIgnoreCaseAndAutorIgnoreCase(datosLibro.titulo().trim(), datosLibro.autor().trim())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un libro con ese titulo y autor");
        }
        Libro libro = new Libro();
        copiarDatos(datosLibro, libro);
        return libroRepositorio.save(libro); // INSERT (el libro no tiene id esta se autogenera)
    }

    private void copiarDatos(LibroSolictud datos, Libro libro){
        libro.setTitulo(datos.titulo().trim());
        libro.setAutor(datos.autor().trim());
        libro.setCategoria(datos.categoria().trim());
        libro.setAnio(datos.anio());
        libro.setPortadaUrl(datos.portadaUrl() == null ? "" : datos.portadaUrl());
        libro.setCopiasDisponibles(datos.copiasDisponibles());
    }

    public List<Libro> listarLibros(String q, List<String> categorias, boolean soloDisponible, String orden){
        return libroRepositorio.buscarLibro(q, categorias, soloDisponible, orden);
    }

    // Regla: pedir un libro que no existe deberia dar error 404
    public Libro obtenerLibro(Long id){
        return libroRepositorio.findById(id).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "El libro no existe")
        );
    }

    public List<String> obtenerCategorias(){
        return libroRepositorio.obtenerCategorias();
    }
    

}
