package com.seguridad.matriz.repository;

import com.seguridad.matriz.domain.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, String> {

}
