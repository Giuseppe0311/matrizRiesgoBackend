package com.seguridad.matriz.dto.mappers.empresa;

import com.seguridad.matriz.domain.Empresas;
import com.seguridad.matriz.dto.empresas.EmpresaCreateDTO;
import com.seguridad.matriz.dto.mappers.DTOMapper;
import org.springframework.stereotype.Component;

@Component
public class EmpresaCreateDTOMapper implements DTOMapper<EmpresaCreateDTO, Empresas> {
    @Override
    public Empresas map(EmpresaCreateDTO empresaCreateDTO) {
        return Empresas.builder()
                .nombre(empresaCreateDTO.nombre())
                .build();
    }
}
