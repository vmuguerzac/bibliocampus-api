package com.vmuguerzac.bibliocampus_api.dto;

import java.time.LocalDate;

import com.vmuguerzac.bibliocampus_api.modelos.EstadoPrestamo;
import com.vmuguerzac.bibliocampus_api.modelos.Libro;
import com.vmuguerzac.bibliocampus_api.modelos.Prestamo;

public record PrestamoRespuesta(Long id, Long libroId, 
    Long usuario, LocalDate fecha, EstadoPrestamo estado, Libro libro){
        public static PrestamoRespuesta de(Prestamo prestamo){
            return new PrestamoRespuesta (prestamo.getId(), 
                                            prestamo.getLibro().getId(), 
                                            prestamo.getUsuarioId(),
                                            prestamo.getFecha(),
                                            prestamo.getEstado(), 
                                            prestamo.getLibro());
        }
    }