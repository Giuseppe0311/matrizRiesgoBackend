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
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/empresas")
@RequiredArgsConstructor
public class EmpresasController extends AbstractCrudController<EmpresaCreateDTO, EmpresaUpdateDTO, EmpresaViewDTO, Empresas,Long>{

    private final EmpresasService service;
    private final EmpresaCreateDTOMapper createDTOMapper;
    private final EmpresaUpdateDTOMapper updateDTOMapper;
    private final EmpresaViewDTOMapper viewDTOMapper;
    private static final String EMPRESA_ID = "id";


    @Override
    @GetMapping("/{" + EMPRESA_ID + "}")
    public EmpresaViewDTO getById(Map<String, String> idMap) {
        return super.getById(idMap);
    }

    @Override
    @PutMapping("/{" + EMPRESA_ID + "}")
    public EmpresaViewDTO update(EmpresaUpdateDTO updateDTO, Map<String, String> idMap) {
        return super.update(updateDTO, idMap);
    }

    @Override
    @DeleteMapping("/{" + EMPRESA_ID + "}")
    public void delete(Map<String, String> idMap) {
        super.delete(idMap);
    }

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
        return id->Long.parseLong(id.get(EMPRESA_ID));
    }
}
