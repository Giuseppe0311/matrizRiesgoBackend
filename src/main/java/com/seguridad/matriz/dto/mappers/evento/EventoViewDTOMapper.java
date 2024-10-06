package com.seguridad.matriz.dto.mappers.evento;

import com.seguridad.matriz.domain.Empresas;
import com.seguridad.matriz.domain.Eventos;
import com.seguridad.matriz.domain.Usuarios;
import com.seguridad.matriz.dto.evento.EventoViewDTO;
import com.seguridad.matriz.dto.mappers.DTOMapper;
import com.seguridad.matriz.repository.EmpresasRepository;
import com.seguridad.matriz.repository.MatrizRepository;
import com.seguridad.matriz.repository.UsuariosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EventoViewDTOMapper implements DTOMapper<Eventos, EventoViewDTO> {
    private final EmpresasRepository empresasRepository;
    private final UsuariosRepository usuariosRepository;
    @Override
    public EventoViewDTO map(Eventos eventos) {


        String nombreCompleto = null;
        String emailUsuario = null;
        String usernameUsuario = null;


        Empresas empresa = empresasRepository.findById(eventos.getMatriz().getIdEmpresa())
                .orElseThrow(
                        () -> new RuntimeException("Empresa not found")
                );

        if (eventos.getIdUsuarioAsignado() != null) {
            Usuarios usuario = usuariosRepository.findById(eventos.getIdUsuarioAsignado())
                    .orElseThrow(() -> new RuntimeException("Usuario not found"));

            // Asignar los valores del usuario si existe
            nombreCompleto = usuario.getNombre() + " " + usuario.getApellido();
            emailUsuario = usuario.getEmail();
            usernameUsuario = usuario.getUsername();
        }

        return new EventoViewDTO(
                eventos.getId(),
                eventos.getNombre(),
                eventos.getNivelRiesgo(),
                eventos.getProbabilidad(),
                eventos.getImpacto(),
                eventos.getValor(),
                eventos.getDominio(),
                eventos.getObjetivo(),
                eventos.getControl(),
                eventos.getMatriz().getNombre(),
                empresa.getNombre(),
                nombreCompleto,
                emailUsuario,
                usernameUsuario,
                eventos.getIdUsuarioAsignado()
        );
    }
}
