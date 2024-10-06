package com.seguridad.matriz.dto.mappers.iso;

import com.seguridad.matriz.domain.Iso;
import com.seguridad.matriz.dto.iso.IsoViewDTO;
import com.seguridad.matriz.dto.mappers.DTOMapper;
import org.springframework.stereotype.Component;

@Component
public class IsoViewDTOMapper implements DTOMapper<Iso, IsoViewDTO> {
    @Override
    public IsoViewDTO map(Iso iso) {
        return new IsoViewDTO(
                iso.getId(),
                iso.getDominio(),
                iso.getObjetivo(),
                iso.getControl()
        );
    }
}
