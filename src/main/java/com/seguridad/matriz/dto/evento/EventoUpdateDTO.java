package com.seguridad.matriz.dto.evento;

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
