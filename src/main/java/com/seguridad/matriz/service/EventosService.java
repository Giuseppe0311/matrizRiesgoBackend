package com.seguridad.matriz.service;

import com.seguridad.matriz.domain.Eventos;
import com.seguridad.matriz.repository.EventosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventosService extends AbstractCrudService<Eventos, Long> {

    private final EventosRepository repository;


    @Override
    protected void updatePreSave(Eventos fetchedObj, Eventos toSave) {
        toSave.setIdUsuario(fetchedObj.getIdUsuario());
        toSave.setMatriz(fetchedObj.getMatriz());
        super.updatePreSave(fetchedObj, toSave);
    }

    @Override
    protected JpaRepository<Eventos, Long> getRepository() {
        return repository;
    }
}
