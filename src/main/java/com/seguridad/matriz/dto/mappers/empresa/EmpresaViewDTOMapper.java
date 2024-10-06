package com.seguridad.matriz.dto.mappers.empresa;

import com.seguridad.matriz.domain.Empresas;
import com.seguridad.matriz.dto.empresas.EmpresaViewDTO;
import com.seguridad.matriz.dto.mappers.DTOMapper;
import org.springframework.stereotype.Component;

@Component
public class EmpresaViewDTOMapper implements DTOMapper<Empresas, EmpresaViewDTO> {


    @Override
    public EmpresaViewDTO map(Empresas empresas) {
        return new EmpresaViewDTO(
                empresas.getId(),
                empresas.getNombre()
        );
    }
}
