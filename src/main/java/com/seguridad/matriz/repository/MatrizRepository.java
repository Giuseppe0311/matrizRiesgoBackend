package com.seguridad.matriz.repository;

import com.seguridad.matriz.domain.Matriz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatrizRepository extends JpaRepository<Matriz,Long> {
    List<Matriz> findByIdEmpresaAndStatusTrue(Long idEmpresa);
    List<Matriz> findAllByStatusTrue();
    Optional<Matriz> findByIdAndStatusTrue(Long aLong);
}
