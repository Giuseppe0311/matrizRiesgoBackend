package com.seguridad.matriz.rest;

import com.seguridad.matriz.domain.Empresas;
import com.seguridad.matriz.dto.empresas.EmpresaCreateDTO;
import com.seguridad.matriz.dto.empresas.EmpresaUpdateDTO;
import com.seguridad.matriz.dto.empresas.EmpresaViewDTO;
import com.seguridad.matriz.dto.mappers.DTOMapper;
import com.seguridad.matriz.dto.mappers.empresa.EmpresaCreateDTOMapper;
import com.seguridad.matriz.dto.mappers.empresa.EmpresaUpdateDTOMapper;
import com.seguridad.matriz.dto.mappers.empresa.EmpresaViewDTOMapper;
import com.seguridad.matriz.service.CrudService;
import com.seguridad.matriz.service.EmpresasService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/empresas")
@RequiredArgsConstructor
public class EmpresasController extends AbstractCrudController<EmpresaCreateDTO, EmpresaUpdateDTO, EmpresaViewDTO, Empresas,Long>{

    private final EmpresasService service;
    private final EmpresaCreateDTOMapper createDTOMapper;
    private final EmpresaUpdateDTOMapper updateDTOMapper;
    private final EmpresaViewDTOMapper viewDTOMapper;


    @Override
    protected CrudService<Empresas, Long> getService() {
        return service;
    }

    @Override
    protected DTOMapper<EmpresaCreateDTO, Empresas> getCreateDtoMapper() {
        return createDTOMapper;
    }

    @Override
    protected DTOMapper<EmpresaUpdateDTO, Empresas> getUpdateDtoMapper() {
        return updateDTOMapper;
    }

    @Override
    protected DTOMapper<Empresas, EmpresaViewDTO> getViewDtoMapper() {
        return viewDTOMapper;
    }

    @Override
    protected DTOMapper<Map<String, String>, Long> getIdMapper() {
        return id->Long.parseLong(id.get("id"));
    }
}
