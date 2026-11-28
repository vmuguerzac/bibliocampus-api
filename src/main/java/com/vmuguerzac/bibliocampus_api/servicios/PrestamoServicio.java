package com.vmuguerzac.bibliocampus_api.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vmuguerzac.bibliocampus_api.dto.PrestamoRespuesta;
import com.vmuguerzac.bibliocampus_api.modelos.EstadoPrestamo;
import com.vmuguerzac.bibliocampus_api.modelos.Libro;
import com.vmuguerzac.bibliocampus_api.modelos.Prestamo;
import com.vmuguerzac.bibliocampus_api.repositorio.LibroRepositorio;
import com.vmuguerzac.bibliocampus_api.repositorio.PrestamoRepositorio;

@Service 
public class PrestamoServicio {
    private final LibroRepositorio libroRepositorio;
    private final PrestamoRepositorio prestamoRepositorio;

    public PrestamoServicio(LibroRepositorio instanciaLibroRepo, PrestamoRepositorio instanciaPrestamoRepo){
        this.libroRepositorio = instanciaLibroRepo;
        this.prestamoRepositorio = instanciaPrestamoRepo;
    }

    // Flujo Solicitar Prestamo
    // Regla Negocio: el libro debe existir -  caso contrario retornar 404
    // Regla Negocio: el libro debe tener copias - caso contrario retornar 409 (conflicto)
    // Son 2 escrituras 1. Update libro (descontar el prestamo), 2. Insertar en prestamo
    // Usamos syncronized para evitar race condition
    public synchronized PrestamoRespuesta solicitarPrestamo(Long usuarioId, Long libroId){
        // Regla 1:
        Libro libro = libroRepositorio.findById(libroId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "El libro no existe"));
        // Regla 2
        if(libro.getCopiasDisponibles() <= 0){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No hay copias disponibles");
        }
        // descontar el libro
        libro.setCopiasDisponibles(libro.getCopiasDisponibles() - 1);
        // Actualizar
        libroRepositorio.save(libro);
        // Insertar el prestamo
        Prestamo prestamo = new Prestamo(libro, usuarioId, LocalDate.now(), EstadoPrestamo.ACTIVO);
        return PrestamoRespuesta.de(prestamoRepositorio.save(prestamo));
    }

    public List<PrestamoRespuesta> listarPrestamosDeUsuario(Long usuarioId){
        return prestamoRepositorio.obtenerPorUsuario(usuarioId).stream().map(PrestamoRespuesta::de).toList();
    }

    public List<PrestamoRespuesta> listarTodosPrestamos(){
        return prestamoRepositorio.obtenerTodosPrestamos().stream().map(PrestamoRespuesta::de).toList();
    }
}
