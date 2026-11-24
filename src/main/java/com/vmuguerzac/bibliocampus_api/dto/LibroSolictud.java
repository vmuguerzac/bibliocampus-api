package com.vmuguerzac.bibliocampus_api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LibroSolictud(
    @NotBlank(message = "El titulo es obligatorio")
    @Size(max=150, message = "El titulo admite como max 150 caracteres") 
    String titulo, 
    @NotBlank(message = "El autor es obligatorio")
    @Size(max=120, message = "El autor admite como max 120 caracteres") 
    String autor,
    @NotBlank(message = "La categoria es obligatoria")
    @Size(max=60, message = "La categoria admite como max 60 caracteres") 
    String categoria, 
    @Min(value = 1450, message = "El año debe ser 1450 o posterior")
    @Max(value = 2100, message = "El año es inválido")
    int anio,
    @Size(max = 500, message = "La URL de la portada admite como maximo 500 car")
    String portadaUrl, 
    @Min(value = 0, message = "Las copias no pueden ser negativas")
    int copiasDisponibles) {}
