package com.seguridad.matriz.service;

import com.seguridad.matriz.domain.DomainObject;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
public abstract class AbstractCrudService<T extends DomainObject<ID>, ID> implements CrudService<T, ID> {

    private static final String NOT_FOUND = "Entity not found in database";
    private static final String LOG_NOT_FOUND_UPDATE = "Tried to update a not existing record";
    private static final String LOG_NOT_FOUND_DELETE = "Tried to delete a not existing record";

    protected abstract JpaRepository<T, ID> getRepository();

    @Override
    public T create(T obj) {
        return getRepository().save(obj);
    }

    @Override
    public List<T> findAll() {
        return (List<T>) getRepository().findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public Optional<T> getById(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public T update(ID id, T obj) {
        Optional<T> fetchedObj = getRepository().findById(id);
        if (fetchedObj.isPresent()) {
            obj.setId(id);
            this.updatePreSave(fetchedObj.get(), obj);
            return getRepository().save(obj);
        } else {
            log.error(LOG_NOT_FOUND_UPDATE);
            throw new NoSuchElementException(NOT_FOUND);
        }
    }

    protected void updatePreSave(T fetchedObj, T toSave) {
        /*
         * The default implementation does not do anything
         */
    }

    @Override
    public void delete(ID id) {
        Optional<T> toDeleteOpt = getRepository().findById(id);
        if (toDeleteOpt.isPresent()) {
            getRepository().deleteById(id);
        } else {
            log.error(LOG_NOT_FOUND_DELETE);
            throw new NoSuchElementException(NOT_FOUND);

        }
    }
}
