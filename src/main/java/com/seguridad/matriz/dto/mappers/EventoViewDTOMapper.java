package com.seguridad.matriz.dto.mappers;

import com.seguridad.matriz.domain.Eventos;
import com.seguridad.matriz.dto.EventoViewDTO;
import org.springframework.stereotype.Component;

@Component
public class EventoViewDTOMapper implements DTOMapper<Eventos, EventoViewDTO> {
    @Override
    public EventoViewDTO map(Eventos eventos) {
        return new EventoViewDTO(
                eventos.getId(),
                eventos.getNombre(),
                eventos.getNivelRiesgo(),
                eventos.getProbabilidad(),
                eventos.getImpacto(),
                eventos.getValor(),
                eventos.getDominio(),
                eventos.getObjetivo(),
                eventos.getControl()
        );
    }
}
