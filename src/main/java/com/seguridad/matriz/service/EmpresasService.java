package com.seguridad.matriz.service;

import com.seguridad.matriz.domain.Empresas;
import com.seguridad.matriz.repository.EmpresasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpresasService extends AbstractCrudService<Empresas,Long> {
    private final EmpresasRepository empresasRepository;

    @Override
    public List<Empresas> findAll() {
        return empresasRepository.findAllByStatusTrue();
    }

    @Override
    public Optional<Empresas> getById(Long aLong) {
        return empresasRepository.findByIdAndStatusTrue(aLong);
    }

    @Override
    public void delete(Long aLong) {
        empresasRepository.findById(aLong).ifPresent(empresas -> {
            empresas.setStatus(false);
            empresasRepository.save(empresas);
        });
    }

    @Override
    protected JpaRepository<Empresas, Long> getRepository() {
        return empresasRepository;
    }
}
