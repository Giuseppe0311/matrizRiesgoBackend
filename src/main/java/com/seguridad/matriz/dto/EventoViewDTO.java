package com.seguridad.matriz.dto;

public record EventoViewDTO(
        Long id,
        String nombre,
        String nivelRiesgo,
        String probabilidad,
        String impacto,
        Integer valor,
        String dominio,
        String objetivo,
        String control
) {
}
