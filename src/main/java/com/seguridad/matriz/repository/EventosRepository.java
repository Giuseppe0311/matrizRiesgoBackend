package com.seguridad.matriz.repository;

import com.seguridad.matriz.domain.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventosRepository extends JpaRepository<Eventos,Long> {
}
