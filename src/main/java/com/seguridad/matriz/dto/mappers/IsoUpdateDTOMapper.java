package com.seguridad.matriz.dto.mappers;

import com.seguridad.matriz.domain.Iso;
import com.seguridad.matriz.dto.IsoUpdateDTO;
import org.springframework.stereotype.Component;

@Component
public class IsoUpdateDTOMapper implements DTOMapper<IsoUpdateDTO, Iso>{
    @Override
    public Iso map(IsoUpdateDTO isoUpdateDTO) {
        return Iso.builder()
                .objetivo(isoUpdateDTO.objetivo())
                .control(isoUpdateDTO.control())
                .dominio(isoUpdateDTO.dominio())
                .build();
    }
}
