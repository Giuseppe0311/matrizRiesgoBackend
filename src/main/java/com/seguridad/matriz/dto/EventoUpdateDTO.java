package com.seguridad.matriz.dto;

public record EventoUpdateDTO(
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
