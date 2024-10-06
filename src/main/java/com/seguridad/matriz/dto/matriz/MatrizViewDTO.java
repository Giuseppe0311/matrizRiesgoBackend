package com.seguridad.matriz.dto.matriz;

import com.seguridad.matriz.dto.evento.EventoViewDTO;

import java.util.List;

public record MatrizViewDTO(
        Long id,
        String nombre,
        Integer minima,
        Integer menor,
        Integer moderada,
        Integer mayor,
        Integer maxima,
        Integer muyAlta,
        Integer alta,
        Integer media,
        Integer baja,
        Integer muyBaja,
        Integer deVerde,
        Integer aVerde,
        Integer deAmarillo,
        Integer aAmarillo,
        Integer deNaranja,
        Integer aNaranja,
        Integer deRojo,
        Integer aRojo,
        List<EventoViewDTO> eventos,
        Long idEmpresa

        ) {
}
