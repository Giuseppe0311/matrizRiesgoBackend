package com.seguridad.matriz.dto.mappers;

public interface DTOMapper <Input , Output> {
    Output map(Input input);
}
