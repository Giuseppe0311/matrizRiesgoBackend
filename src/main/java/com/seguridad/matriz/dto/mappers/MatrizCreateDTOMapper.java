package com.seguridad.matriz.dto.mappers;

import com.seguridad.matriz.domain.Matriz;
import com.seguridad.matriz.dto.MatrizCreateDTO;
import org.springframework.stereotype.Component;

@Component
public class MatrizCreateDTOMapper implements DTOMapper<MatrizCreateDTO, Matriz> {
    @Override
    public Matriz map(MatrizCreateDTO matrizCreateDTO) {
        return Matriz.builder()
                .nombre(matrizCreateDTO.nombre())
                .minima(matrizCreateDTO.minima())
                .menor(matrizCreateDTO.menor())
                .moderada(matrizCreateDTO.moderada())
                .mayor(matrizCreateDTO.mayor())
                .maxima(matrizCreateDTO.maxima())
                .muyAlta(matrizCreateDTO.muyAlta())
                .alta(matrizCreateDTO.alta())
                .media(matrizCreateDTO.media())
                .baja(matrizCreateDTO.baja())
                .muyBaja(matrizCreateDTO.muyBaja())
                .deVerde(matrizCreateDTO.deVerde())
                .aVerde(matrizCreateDTO.aVerde())
                .deAmarillo(matrizCreateDTO.deAmarillo())
                .aAmarillo(matrizCreateDTO.aAmarillo())
                .deNaranja(matrizCreateDTO.deNaranja())
                .aNaranja(matrizCreateDTO.aNaranja())
                .deRojo(matrizCreateDTO.deRojo())
                .aRojo(matrizCreateDTO.aRojo())
                .idUsuario(matrizCreateDTO.idUsuario())
                .build();
    }
}

