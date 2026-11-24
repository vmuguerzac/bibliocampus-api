package com.vmuguerzac.bibliocampus_api.dto;

public record LibroSolictud(String titulo, String autor, String categoria, int anio,
    String portadaUrl, int copiasDisponibles) {}
