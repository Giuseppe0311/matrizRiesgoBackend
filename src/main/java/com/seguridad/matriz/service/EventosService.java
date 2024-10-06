package com.seguridad.matriz.service;

import com.seguridad.matriz.domain.Eventos;
import com.seguridad.matriz.repository.EventosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventosService extends AbstractCrudService<Eventos, Long> {

    private final EventosRepository repository;

    @Override
    public List<Eventos> findAll() {
        return repository.findAllByStatusTrue();
    }

    @Override
    public Optional<Eventos> getById(Long aLong) {
        return repository.findByIdAndStatusTrue(aLong);
    }

    @Override
    public void delete(Long aLong) {
        repository.findById(aLong).ifPresent(eventos -> {
            eventos.setStatus(false);
            repository.save(eventos);
        });
    }

    public List<Eventos> getEventosByUserId(String userId) {
       return repository.findByIdUsuarioAsignado(userId);
    }

    public void assingUserToEvent(String userId, Long eventId) {
        Eventos eventoId = repository.findById(eventId).orElseThrow(
                () -> new RuntimeException("Event not found")
        );
        eventoId.setIdUsuarioAsignado(userId);
        repository.save(eventoId);
    }
    public void unassingUserToEvent(Long eventId) {
        Eventos eventoId = repository.findById(eventId).orElseThrow(
                () -> new RuntimeException("Event not found")
        );
        eventoId.setIdUsuarioAsignado(null);
        repository.save(eventoId);
    }

    @Override
    protected void updatePreSave(Eventos fetchedObj, Eventos toSave) {
        toSave.setIdUsuarioAsignado(fetchedObj.getIdUsuarioAsignado());
        toSave.setMatriz(fetchedObj.getMatriz());
        super.updatePreSave(fetchedObj, toSave);
    }

    @Override
    protected JpaRepository<Eventos, Long> getRepository() {
        return repository;
    }
}
