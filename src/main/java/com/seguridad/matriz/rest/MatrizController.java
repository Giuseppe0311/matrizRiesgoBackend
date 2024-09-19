package com.seguridad.matriz.rest;

import com.seguridad.matriz.domain.Matriz;
import com.seguridad.matriz.dto.MatrizCreateDTO;
import com.seguridad.matriz.dto.MatrizUpdateDTO;
import com.seguridad.matriz.dto.MatrizViewDTO;
import com.seguridad.matriz.dto.mappers.DTOMapper;
import com.seguridad.matriz.dto.mappers.MatrizCreateDTOMapper;
import com.seguridad.matriz.dto.mappers.MatrizUpdateDTOMapper;
import com.seguridad.matriz.dto.mappers.MatrizViewDTOMapper;
import com.seguridad.matriz.service.CrudService;
import com.seguridad.matriz.service.MatrizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/matrices")
@RequiredArgsConstructor
public class MatrizController extends AbstractCrudController<MatrizCreateDTO, MatrizUpdateDTO, MatrizViewDTO, Matriz, Long> {


    private final MatrizService service;

    private final MatrizCreateDTOMapper createDTOMapper;

    private final MatrizUpdateDTOMapper updateDTOMapper;

    private final MatrizViewDTOMapper viewDTOMapper;

    private static final String MATRIZ_ID = "id";

    @GetMapping("/{" + MATRIZ_ID + "}")
    @Override
    public MatrizViewDTO getById(Map<String, String> idMap) {
        return super.getById(idMap);
    }

    @PutMapping("/{" + MATRIZ_ID + "}")
    @Override
    public MatrizViewDTO update(MatrizUpdateDTO updateDTO, Map<String, String> idMap) {
        return super.update(updateDTO, idMap);
    }

    @DeleteMapping("/{" + MATRIZ_ID + "}")
    @Override
    public void delete(Map<String, String> idMap) {
        super.delete(idMap);
    }

    @Override
    protected CrudService<Matriz, Long> getService() {
        return service;
    }

    @Override
    protected DTOMapper<MatrizCreateDTO, Matriz> getCreateDtoMapper() {
        return createDTOMapper;
    }

    @Override
    protected DTOMapper<MatrizUpdateDTO, Matriz> getUpdateDtoMapper() {
        return updateDTOMapper;
    }

    @Override
    protected DTOMapper<Matriz, MatrizViewDTO> getViewDtoMapper() {
        return viewDTOMapper;
    }

    @Override
    protected DTOMapper<Map<String, String>, Long> getIdMapper() {
       return map -> Long.parseLong(map.get(MATRIZ_ID));
    }
}
