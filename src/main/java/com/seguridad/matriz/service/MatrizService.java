package com.seguridad.matriz.service;

import com.seguridad.matriz.domain.DomainObject;
import com.seguridad.matriz.domain.Matriz;
import com.seguridad.matriz.repository.MatrizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatrizService extends AbstractCrudService<Matriz,Long>{
    private final MatrizRepository repository;

    @Override
    public List<Matriz> findAll() {
        return repository.findAllByStatusTrue();
    }

    @Override
    public Optional<Matriz> getById(Long aLong) {
        return repository.findByIdAndStatusTrue(aLong);
    }

    @Override
    public void delete(Long aLong) {
        repository.findById(aLong).ifPresent(matriz -> {
            matriz.setStatus(false);
            repository.save(matriz);
        });
    }

    @Override
    protected void updatePreSave(Matriz fetchedObj, Matriz toSave) {
        toSave.setIdEmpresa(fetchedObj.getIdEmpresa());
        toSave.setEventos(fetchedObj.getEventos());
        super.updatePreSave(fetchedObj, toSave);
    }

    public List<Matriz> findMatrizbyEmpresa(Long idEmpresa){
        return repository.findByIdEmpresaAndStatusTrue(idEmpresa);
    }

    @Override
    protected JpaRepository<Matriz, Long> getRepository() {
        return repository;
    }
}
