package com.seguridad.matriz.repository;

import com.seguridad.matriz.domain.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventosRepository extends JpaRepository<Eventos,Long> {
    List<Eventos> findByIdUsuarioAsignado(String userId);
    List<Eventos> findAllByStatusTrue();
    Optional<Eventos> findByIdAndStatusTrue(Long id);
}
