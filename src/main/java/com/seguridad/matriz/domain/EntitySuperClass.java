package com.seguridad.matriz.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
@MappedSuperclass
@Getter
@Setter
public class EntitySuperClass {
    @Column(name = "estado")
    private Boolean status=true;
}
