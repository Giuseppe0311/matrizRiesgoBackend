package com.seguridad.matriz.dto.user;
import jakarta.validation.constraints.NotNull;

import java.util.Set;


public record UserDTO(
        String username,
        String email,
        String firstName,
        String lastName,
        String password,
        Long idEmpresa,
        Set<String> roles
) {
}
