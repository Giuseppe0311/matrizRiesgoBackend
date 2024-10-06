package com.seguridad.matriz.dto.evento;

public record EventoViewDTO(
        Long id,
        String nombre,
        String nivelRiesgo,
        String probabilidad,
        String impacto,
        Integer valor,
        String dominio,
        String objetivo,
        String control,
        String nombreMatriz,
        String nombreEmpresa,
        String nombreUsuarioAsignado,
        String correoUsuarioAsignado,
        String usuarioOfUsuarioAsignado,
        String idUsuarioAsignado

) {
}
