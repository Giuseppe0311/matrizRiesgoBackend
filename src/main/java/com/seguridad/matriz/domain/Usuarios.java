package com.seguridad.matriz.domain;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
public class Usuarios  extends EntitySuperClass implements DomainObject<String> {
    @Id
    private String id;
    private String nombre;
    private String apellido;
    private String email;
    private String username;
    private Long idEmpresa;
}
