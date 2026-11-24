package com.vmuguerzac.bibliocampus_api.controlador;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vmuguerzac.bibliocampus_api.dto.LibroSolictud;
import com.vmuguerzac.bibliocampus_api.modelos.Libro;
import com.vmuguerzac.bibliocampus_api.servicios.LibroServicio;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController 
@RequestMapping("/api/libros")
@CrossOrigin(origins = "http://localhost:4200")
public class LibroControlador {
    // inyeccion de dependencias
    private final LibroServicio libroServicio;

    public LibroControlador(LibroServicio instancia){
        this.libroServicio = instancia; 
    }

    // POST
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@Valid @RequestBody LibroSolictud datos) {
        Libro libroCreado = libroServicio.crearLibro(datos);
        URI ubicacion = URI.create("/api/libros/" + libroCreado.getId());
        return ResponseEntity.created(ubicacion).body(libroCreado); // 201 Created + locacion
    }

    // PUT actualizar
    @PutMapping("/{id}")
    public Libro actualizarLibro(@PathVariable("id") Long id, @Valid @RequestBody LibroSolictud datos ) {
        return libroServicio.actualizarLibro(id, datos);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable("id") Long id){
        libroServicio.eliminarLibro(id);
        return ResponseEntity.noContent().build(); // 204 no content
    }

    // GET listarLibros
    @GetMapping
    public List<Libro> listarLibros(
            @RequestParam(name="q", required = false) String q,
            @RequestParam(name="categoria", required = false) List<String> categoria,
            @RequestParam(name="soloDisponibles", defaultValue = "false") boolean soloDisponibles,
            @RequestParam(name="orden", defaultValue = "titulo") String orden)
    {
        return libroServicio.listarLibros(q, categoria, soloDisponibles, orden);
    }
    
    // GET ListarLibroPorID
    @GetMapping("/{id}")
    public Libro obtenerLibro(@PathVariable("id") Long id) {
        return libroServicio.obtenerLibro(id);
    }

    @GetMapping("/categorias")
    public List<String> obtenerCategorias(){
        return libroServicio.obtenerCategorias();
    }
}
