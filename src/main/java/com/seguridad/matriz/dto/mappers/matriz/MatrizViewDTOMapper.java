package com.seguridad.matriz.dto.mappers.matriz;

import com.seguridad.matriz.domain.Matriz;
import com.seguridad.matriz.dto.mappers.DTOMapper;
import com.seguridad.matriz.dto.mappers.evento.EventoViewDTOMapper;
import com.seguridad.matriz.dto.matriz.MatrizViewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class MatrizViewDTOMapper implements DTOMapper<Matriz, MatrizViewDTO> {

    private final EventoViewDTOMapper eventoViewDTOMapper;

    @Override
    public MatrizViewDTO map(Matriz matriz) {
        return new MatrizViewDTO(
                matriz.getId(),
                matriz.getNombre(),
                matriz.getMinima(),
                matriz.getMenor(),
                matriz.getModerada(),
                matriz.getMayor(),
                matriz.getMaxima(),
                matriz.getMuyAlta(),
                matriz.getAlta(),
                matriz.getMedia(),
                matriz.getBaja(),
                matriz.getMuyBaja(),
                matriz.getDeVerde(),
                matriz.getAVerde(),
                matriz.getDeAmarillo(),
                matriz.getAAmarillo(),
                matriz.getDeNaranja(),
                matriz.getANaranja(),
                matriz.getDeRojo(),
                matriz.getARojo(),
                matriz.getEventos() != null
                        ? matriz.getEventos().stream().map(eventoViewDTOMapper::map).toList()
                        : Collections.emptyList(),
                matriz.getIdEmpresa()
        );
    }
}

