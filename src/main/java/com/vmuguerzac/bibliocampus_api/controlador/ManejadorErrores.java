package com.vmuguerzac.bibliocampus_api.controlador;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.vmuguerzac.bibliocampus_api.dto.ErrorRespuesta;

@RestControllerAdvice 
public class ManejadorErrores {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorRespuesta> validacion(MethodArgumentNotValidException ex){
        Map<String, String> errores = new LinkedHashMap<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            errores.putIfAbsent(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(new ErrorRespuesta(400, "Hay datos invalidos", errores));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorRespuesta> reglaDeNegocio(ResponseStatusException ex){
        int estado = ex.getStatusCode().value();
        return ResponseEntity.status(estado).body(ErrorRespuesta.de(estado, ex.getReason()));
    }

}
