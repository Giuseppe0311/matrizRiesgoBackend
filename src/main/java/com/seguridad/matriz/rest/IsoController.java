package com.seguridad.matriz.rest;

import com.seguridad.matriz.domain.Iso;
import com.seguridad.matriz.dto.IsoCreateDTO;
import com.seguridad.matriz.dto.IsoUpdateDTO;
import com.seguridad.matriz.dto.IsoViewDTO;
import com.seguridad.matriz.dto.mappers.DTOMapper;
import com.seguridad.matriz.dto.mappers.IsoCreateDTOMapper;
import com.seguridad.matriz.dto.mappers.IsoUpdateDTOMapper;
import com.seguridad.matriz.dto.mappers.IsoViewDTOMapper;
import com.seguridad.matriz.service.CrudService;
import com.seguridad.matriz.service.IsoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/isos")
@RequiredArgsConstructor
public class IsoController extends AbstractCrudController<IsoCreateDTO, IsoUpdateDTO, IsoViewDTO, Iso, Long> {

    private final IsoService service;

    private final IsoCreateDTOMapper createDTOMapper;

    private final IsoUpdateDTOMapper updateDTOMapper;

    private final IsoViewDTOMapper viewDTOMapper;

    private static final String ISO_ID = "id";

    @GetMapping("/{" + ISO_ID + "}")
    @Override
    public IsoViewDTO getById(Map<String, String> idMap) {
        return super.getById(idMap);
    }

    @PutMapping("/{" + ISO_ID + "}")
    @Override
    public IsoViewDTO update(IsoUpdateDTO updateDTO, Map<String, String> idMap) {
        return super.update(updateDTO, idMap);
    }

    @DeleteMapping("/{" + ISO_ID + "}")
    @Override
    public void delete(Map<String, String> idMap) {
        super.delete(idMap);
    }

    @Override
    protected CrudService<Iso, Long> getService() {
        return service;
    }

    @Override
    protected DTOMapper<IsoCreateDTO, Iso> getCreateDtoMapper() {
        return createDTOMapper;
    }

    @Override
    protected DTOMapper<IsoUpdateDTO, Iso> getUpdateDtoMapper() {
        return updateDTOMapper;
    }

    @Override
    protected DTOMapper<Iso, IsoViewDTO> getViewDtoMapper() {
        return viewDTOMapper;
    }

    @Override
    protected DTOMapper<Map<String, String>, Long> getIdMapper() {
        return map -> Long.parseLong(map.get("id"));
    }
}
