package com.seguridad.matriz.repository;

import com.seguridad.matriz.domain.Empresas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmpresasRepository extends JpaRepository<Empresas,Long> {
    List<Empresas> findAllByStatusTrue();
    Optional<Empresas> findByIdAndStatusTrue(@Param("id") Long id);
}
