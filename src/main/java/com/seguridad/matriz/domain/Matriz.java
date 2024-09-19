package com.seguridad.matriz.domain;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
public class Matriz extends EntitySuperClass implements DomainObject<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer minima;
    private Integer menor;
    private Integer moderada;
    private Integer mayor;
    private Integer maxima;
    private Integer muyAlta;
    private Integer alta;
    private Integer media;
    private Integer baja;
    private Integer muyBaja;
    private Integer deVerde;
    private Integer aVerde;
    private Integer deAmarillo;
    private Integer aAmarillo;
    private Integer deNaranja;
    private Integer aNaranja;
    private Integer deRojo;
    private Integer aRojo;
    private Long idUsuario;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matriz")
    private List<Eventos> eventos;
}
