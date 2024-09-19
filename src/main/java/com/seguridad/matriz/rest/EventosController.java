package com.seguridad.matriz.rest;

import com.seguridad.matriz.domain.Eventos;
import com.seguridad.matriz.dto.EventoCreateDTO;
import com.seguridad.matriz.dto.EventoUpdateDTO;
import com.seguridad.matriz.dto.EventoViewDTO;
import com.seguridad.matriz.dto.mappers.DTOMapper;
import com.seguridad.matriz.dto.mappers.EventoCreateDTOMapper;
import com.seguridad.matriz.dto.mappers.EventoUpdateDTOMapper;
import com.seguridad.matriz.dto.mappers.EventoViewDTOMapper;
import com.seguridad.matriz.service.CrudService;
import com.seguridad.matriz.service.EventosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
