package com.seguridad.matriz.rest;

import com.seguridad.matriz.domain.Eventos;
import com.seguridad.matriz.dto.evento.EventoCreateDTO;
import com.seguridad.matriz.dto.evento.EventoUpdateDTO;
import com.seguridad.matriz.dto.evento.EventoViewDTO;
import com.seguridad.matriz.dto.mappers.DTOMapper;
import com.seguridad.matriz.dto.mappers.evento.EventoCreateDTOMapper;
import com.seguridad.matriz.dto.mappers.evento.EventoUpdateDTOMapper;
import com.seguridad.matriz.dto.mappers.evento.EventoViewDTOMapper;
import com.seguridad.matriz.service.CrudService;
import com.seguridad.matriz.service.EventosService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/eventos")
@RequiredArgsConstructor
public class EventosController extends AbstractCrudController<EventoCreateDTO, EventoUpdateDTO, EventoViewDTO, Eventos, Long> {

    private final EventosService service;

    private static final String EVENT_ID = "id";

    private final EventoCreateDTOMapper createDTOMapper;

    private final EventoUpdateDTOMapper updateDTOMapper;

    private final EventoViewDTOMapper viewDTOMapper;

    @GetMapping("/{" + EVENT_ID + "}")
    @Override
    public EventoViewDTO getById(Map<String, String> idMap) {
        return super.getById(idMap);
    }

    @GetMapping("/serach-by-user/{userid}")
    @PreAuthorize("hasAnyRole('superadmin_client','user_client')")
    public List<EventoViewDTO> getByUserId(@PathVariable String userid) {
        return service.getEventosByUserId(userid)
                .stream()
                .map(viewDTOMapper::map)
                .toList();
    }


    @PostMapping("/assign-user/{idEvent}/{userid}")
    @PreAuthorize("hasAnyRole('admin_client','superadmin_client')")
    public void assignUserToEvent(@PathVariable Long idEvent, @PathVariable String userid) {
        service.assingUserToEvent(userid, idEvent);
    }

    @DeleteMapping("/unassign-user/{idEvent}")
    @PreAuthorize("hasAnyRole('admin_client','superadmin_client')")
    public void unassignUserToEvent(@PathVariable Long idEvent) {
        service.unassingUserToEvent(idEvent);
    }

    @PutMapping("/{" + EVENT_ID + "}")
    @Override
    public EventoViewDTO update(EventoUpdateDTO updateDTO, Map<String, String> idMap) {
        return super.update(updateDTO, idMap);
    }

    @DeleteMapping("/{" + EVENT_ID + "}")
    @Override
    public void delete(Map<String, String> idMap) {
        super.delete(idMap);
    }

    @Override
    protected CrudService<Eventos, Long> getService() {
        return service;
    }

    @Override
    protected DTOMapper<EventoCreateDTO, Eventos> getCreateDtoMapper() {
        return createDTOMapper;
    }

    @Override
    protected DTOMapper<EventoUpdateDTO, Eventos> getUpdateDtoMapper() {
        return updateDTOMapper;
    }

    @Override
    protected DTOMapper<Eventos, EventoViewDTO> getViewDtoMapper() {
        return viewDTOMapper;
    }

    @Override
    protected DTOMapper<Map<String, String>, Long> getIdMapper() {
        return map -> Long.parseLong(map.get(EVENT_ID));
    }
}
