package com.seguridad.matriz.dto.mappers.matriz;

import com.seguridad.matriz.domain.Matriz;
import com.seguridad.matriz.dto.mappers.DTOMapper;
import com.seguridad.matriz.dto.matriz.MatrizUpdateDTO;
import org.springframework.stereotype.Component;

@Component
public class MatrizUpdateDTOMapper implements DTOMapper<MatrizUpdateDTO, Matriz> {
    @Override
    public Matriz map(MatrizUpdateDTO matrizUpdateDTO) {
        return Matriz.builder()
                .nombre(matrizUpdateDTO.nombre())
                .minima(matrizUpdateDTO.minima())
                .menor(matrizUpdateDTO.menor())
                .moderada(matrizUpdateDTO.moderada())
                .mayor(matrizUpdateDTO.mayor())
                .maxima(matrizUpdateDTO.maxima())
                .muyAlta(matrizUpdateDTO.muyAlta())
                .alta(matrizUpdateDTO.alta())
                .media(matrizUpdateDTO.media())
                .baja(matrizUpdateDTO.baja())
                .muyBaja(matrizUpdateDTO.muyBaja())
                .deVerde(matrizUpdateDTO.deVerde())
                .aVerde(matrizUpdateDTO.aVerde())
                .deAmarillo(matrizUpdateDTO.deAmarillo())
                .aAmarillo(matrizUpdateDTO.aAmarillo())
                .deNaranja(matrizUpdateDTO.deNaranja())
                .aNaranja(matrizUpdateDTO.aNaranja())
                .deRojo(matrizUpdateDTO.deRojo())
                .aRojo(matrizUpdateDTO.aRojo())
                .build();
    }
}
