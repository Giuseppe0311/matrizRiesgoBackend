package com.seguridad.matriz.service;

import com.seguridad.matriz.domain.Iso;
import com.seguridad.matriz.repository.IsoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IsoService extends AbstractCrudService<Iso, Long> {

    private final IsoRepository isoRepository;

    @Override
    protected JpaRepository<Iso, Long> getRepository() {
        return isoRepository;
    }
}
