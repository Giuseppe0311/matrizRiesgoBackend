package com.seguridad.matriz.dto.mappers.empresa;

import com.seguridad.matriz.domain.Empresas;
import com.seguridad.matriz.dto.empresas.EmpresaUpdateDTO;
import com.seguridad.matriz.dto.mappers.DTOMapper;
import org.springframework.stereotype.Component;

@Component
public class EmpresaUpdateDTOMapper implements DTOMapper<EmpresaUpdateDTO, Empresas> {
    @Override
    public Empresas map(EmpresaUpdateDTO empresaUpdateDTO) {
        return Empresas.builder()
                .nombre(empresaUpdateDTO.nombre())
                .build();
    }
}
