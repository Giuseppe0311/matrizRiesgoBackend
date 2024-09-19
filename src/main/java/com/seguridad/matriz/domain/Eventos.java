package com.seguridad.matriz.domain;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Eventos extends EntitySuperClass  implements DomainObject<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String nivelRiesgo;
    private String probabilidad;
    private String impacto;
    private Integer valor;
    private String dominio;
    private String objetivo;
    private String control;
    private Long idUsuario;
    @ManyToOne
    private Matriz matriz;
}
