package com.seguridad.matriz.dto.mappers;

import com.seguridad.matriz.domain.Iso;
import com.seguridad.matriz.dto.IsoCreateDTO;
import org.springframework.stereotype.Component;

@Component
public class IsoCreateDTOMapper implements DTOMapper<IsoCreateDTO, Iso> {

    @Override
    public Iso map(IsoCreateDTO isoCreateDTO) {
        return Iso.builder()
                .control(isoCreateDTO.control())
                .dominio(isoCreateDTO.dominio())
                .objetivo(isoCreateDTO.objetivo())
                .build();
    }
}
