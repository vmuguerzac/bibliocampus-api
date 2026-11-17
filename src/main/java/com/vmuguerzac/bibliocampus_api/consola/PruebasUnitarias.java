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
        if(libroRepositorio.count() == 0) {
            libroRepositorio.save(new Libro(null, "Clean Code", "Roberto C. Martin",
             "Buenas prácticas", 2008, "", 3));
        }
        // Prueba de Servicio
        List<Libro> resultado = libroServicio.listarLibros();
        resultado.forEach((l)-> System.out.println(l.getTitulo()));   
    } 
}
