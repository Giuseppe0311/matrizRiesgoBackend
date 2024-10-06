package com.seguridad.matriz.dto.matriz;

import jakarta.validation.constraints.NotNull;

public record MatrizCreateDTO(
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
        @NotNull
        Long idEmpresa
) {
}
