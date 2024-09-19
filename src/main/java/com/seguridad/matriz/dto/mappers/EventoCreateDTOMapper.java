package com.seguridad.matriz.dto.mappers;

import com.seguridad.matriz.domain.Eventos;
import com.seguridad.matriz.domain.Matriz;
import com.seguridad.matriz.dto.EventoCreateDTO;
import com.seguridad.matriz.repository.MatrizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventoCreateDTOMapper implements DTOMapper<EventoCreateDTO, Eventos> {

    private final MatrizRepository matrizRepository;

    @Override
    public Eventos map(EventoCreateDTO eventoCreateDTO) {
        return Eventos.builder()
                .nombre(eventoCreateDTO.nombre())
                .nivelRiesgo(eventoCreateDTO.nivelRiesgo())
                .probabilidad(eventoCreateDTO.probabilidad())
                .impacto(eventoCreateDTO.impacto())
                .valor(eventoCreateDTO.valor())
                .dominio(eventoCreateDTO.dominio())
                .objetivo(eventoCreateDTO.objetivo())
                .control(eventoCreateDTO.control())
                .matriz(getMatrizById(eventoCreateDTO.idMatriz()))
                .idUsuario(eventoCreateDTO.idUsuario())
                .build();
    }

    private Matriz getMatrizById(Long idMatriz) {
        return matrizRepository.findById(idMatriz)
                .orElseThrow(() -> new RuntimeException("Matriz not found"));
    }
}
