package com.seguridad.matriz.dto.mappers.evento;

import com.seguridad.matriz.domain.Eventos;
import com.seguridad.matriz.dto.evento.EventoUpdateDTO;
import com.seguridad.matriz.dto.mappers.DTOMapper;
import org.springframework.stereotype.Component;

@Component
public class EventoUpdateDTOMapper implements DTOMapper<EventoUpdateDTO, Eventos> {
    @Override
    public Eventos map(EventoUpdateDTO eventoUpdateDTO) {
        return Eventos.builder()
                .nombre(eventoUpdateDTO.nombre())
                .nivelRiesgo(eventoUpdateDTO.nivelRiesgo())
                .probabilidad(eventoUpdateDTO.probabilidad())
                .impacto(eventoUpdateDTO.impacto())
                .valor(eventoUpdateDTO.valor())
                .dominio(eventoUpdateDTO.dominio())
                .objetivo(eventoUpdateDTO.objetivo())
                .control(eventoUpdateDTO.control())
                .build();
    }
}
