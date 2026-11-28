package com.vmuguerzac.bibliocampus_api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vmuguerzac.bibliocampus_api.modelos.Prestamo;

public interface PrestamoRepositorio extends JpaRepository<Prestamo, Long>{
    // Busqueda por usuario
    @Query("SELECT p FROM Prestamo p JOIN FETCH p.libro WHERE p.usuarioId = :usuarioId ORDER BY p.fecha DESC, p.id DESC")
    List<Prestamo> obtenerPorUsuario(@Param("usuarioId") Long usuarioId);

    // busqueda de todos los usuarios
    @Query("SELECT p FROM Prestamo p JOIN FETCH p.libro ORDER BY p.fecha DESC, p.id DESC")
    List<Prestamo> obtenerTodosPrestamos();
    
}
