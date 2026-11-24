package com.vmuguerzac.bibliocampus_api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vmuguerzac.bibliocampus_api.modelos.Libro;

public interface LibroRepositorio extends JpaRepository<Libro, Long>, LibroBusqueda{

    // Buscar por categoria
    List<Libro> findByCategoria(String categoria);

    // Encontrar top 3 que tienen menos copias
    List<Libro> findTop3ByOrderByCopiasDisponiblesAsc();

    // Regla: CRUD no repetir titulo + autor
    boolean existsByTituloIgnoreCaseAndAutorIgnoreCase(String titulo, String autor);

   
    // Named Query
    @Query("SELECT l FROM Libro l")
    List<Libro> obtenerTodosLosLibros();

    // Obtener las categorias unicas de todos los libros
    @Query("SELECT DISTINCT l.categoria FROM Libro l ORDER BY l.categoria")
    List<String> obtenerCategorias();

    
}
