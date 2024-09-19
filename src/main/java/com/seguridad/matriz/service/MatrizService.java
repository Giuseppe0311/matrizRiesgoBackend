package com.seguridad.matriz.service;

import com.seguridad.matriz.domain.DomainObject;
import com.seguridad.matriz.domain.Matriz;
import com.seguridad.matriz.repository.MatrizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatrizService extends AbstractCrudService<Matriz,Long>{
    private final MatrizRepository repository;


    @Override
    protected void updatePreSave(Matriz fetchedObj, Matriz toSave) {
        toSave.setIdUsuario(fetchedObj.getIdUsuario());
        toSave.setEventos(fetchedObj.getEventos());
        super.updatePreSave(fetchedObj, toSave);
    }

    @Override
    protected JpaRepository<Matriz, Long> getRepository() {
        return repository;
    }
}
