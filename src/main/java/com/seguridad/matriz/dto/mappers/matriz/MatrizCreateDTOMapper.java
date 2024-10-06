package com.seguridad.matriz.dto.mappers.matriz;

import com.seguridad.matriz.domain.Matriz;
import com.seguridad.matriz.dto.mappers.DTOMapper;
import com.seguridad.matriz.dto.matriz.MatrizCreateDTO;
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
                .idEmpresa(matrizCreateDTO.idEmpresa())
                .build();
    }
}

