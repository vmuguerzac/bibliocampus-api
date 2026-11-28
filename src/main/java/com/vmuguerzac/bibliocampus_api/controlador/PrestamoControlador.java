package com.vmuguerzac.bibliocampus_api.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vmuguerzac.bibliocampus_api.dto.PrestamoRespuesta;
import com.vmuguerzac.bibliocampus_api.dto.SolicitudPrestamo;
import com.vmuguerzac.bibliocampus_api.servicios.PrestamoServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping("/api/prestamos")
@CrossOrigin(origins = "http://localhost:4200")
public class PrestamoControlador {
    private static final Long USUARIO_DEMO = 1L;

    // inyección de dependencia
    private final PrestamoServicio prestamoServicio;

    public PrestamoControlador(PrestamoServicio instancia){
        this.prestamoServicio = instancia;
    }
    // GET - MIOS
    @GetMapping("/mios")
    public List<PrestamoRespuesta> mios() {
        return prestamoServicio.listarPrestamosDeUsuario(USUARIO_DEMO);
    }
    
    // GET Todos prestamos
    @GetMapping
    public List<PrestamoRespuesta> obtenerTodosLosPrestamos() {
        return prestamoServicio.listarTodosPrestamos();
    }
    //POST - solicitar prestamo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrestamoRespuesta SolicitarLibro(@RequestBody SolicitudPrestamo cuerpo) {
        return prestamoServicio.solicitarPrestamo(USUARIO_DEMO, cuerpo.libroId());
    }  
}
