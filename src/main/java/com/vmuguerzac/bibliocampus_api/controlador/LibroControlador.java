package com.vmuguerzac.bibliocampus_api.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vmuguerzac.bibliocampus_api.modelos.Libro;
import com.vmuguerzac.bibliocampus_api.servicios.LibroServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping("/api/libros")
@CrossOrigin(origins = "http://localhost:4200")
public class LibroControlador {
    // inyeccion de dependencias
    private final LibroServicio libroServicio;

    public LibroControlador(LibroServicio instancia){
        this.libroServicio = instancia; 
    }

    // GET listarLibros
    @GetMapping
    public List<Libro> listarLibros() {
        return libroServicio.listarLibros();
    }
    
    // GET ListarLibroPorID
    @GetMapping("/{id}")
    public Libro obtenerLibro(@PathVariable("id") Long id) {
        return libroServicio.obtenerLibro(id);
    }
    

}
