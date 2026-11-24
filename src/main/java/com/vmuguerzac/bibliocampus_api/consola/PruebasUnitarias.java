package com.vmuguerzac.bibliocampus_api.consola;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vmuguerzac.bibliocampus_api.modelos.Libro;
import com.vmuguerzac.bibliocampus_api.repositorio.LibroRepositorio;
import com.vmuguerzac.bibliocampus_api.servicios.LibroServicio;

@Component 
public class PruebasUnitarias implements CommandLineRunner {

    private final LibroRepositorio libroRepositorio;
    private final LibroServicio libroServicio;

    public PruebasUnitarias(LibroRepositorio instancia, LibroServicio instanciaServicio){
        this.libroRepositorio = instancia; // inyeccion de dependencias
        this.libroServicio = instanciaServicio;
    }

    @Override
    public void run(String... args) throws Exception {
        // Prueba de Repositorio
        // System.out.println("###### Inicia la prueba de guardar");
        // libroRepositorio.save(new Libro(null, "Effective Java", "Joshua Bloch", "Java", 2018, "", 5));
        // libroRepositorio.save(new Libro(null, "Spring in Action", "Craig Walls", "Spring", 2022, "", 0));
        // libroRepositorio.save(new Libro(null, "Design Patterns", "Gamma, Helm, Johnson, Vlissides", "Patrones de diseño", 1994, "", 2));
        // libroRepositorio.save(new Libro(null, "Refactoring", "Martin Fowler", "Buenas prácticas", 2018, "", 0));
        // libroRepositorio.save(new Libro(null, "The Pragmatic Programmer", "Hunt y Thomas", "Buenas prácticas", 2019, "", 4));
        // System.out.println("###### Finaliza la prueba de guardar");
        
        // Prueba de Servicio
        List<Libro> resultado = libroRepositorio.obtenerTodosLosLibros();
        resultado.forEach((l)-> System.out.println(l.getTitulo()));   
    } 
}
