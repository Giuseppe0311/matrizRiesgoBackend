package com.seguridad.matriz.service.keycloak;

import com.seguridad.matriz.domain.Eventos;
import com.seguridad.matriz.domain.Usuarios;
import com.seguridad.matriz.dto.user.UserDTO;
import com.seguridad.matriz.provider.KeycloakProvider;
import com.seguridad.matriz.repository.EventosRepository;
import com.seguridad.matriz.repository.UsuariosRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService{
    private final UsuariosRepository usuariosRepository;
    private final EventosRepository eventosRepository;

    @Override
    public List<UserRepresentation> findAllUsers() {
        List<UserRepresentation> users = KeycloakProvider.getRealmResource()
                .users()
                .list();

        return getUserRepresentations(users);
    }
    @Override
    public List<UserRepresentation> searchUserByProfile(String profile) {
        List<UserRepresentation> allUsers = KeycloakProvider.getRealmResource()
                .users()
                .list();

        List<UserRepresentation> usersWithRoles = getUserRepresentations(allUsers);

        return usersWithRoles.stream()
                .filter(user -> user.getRealmRoles() != null &&
                        user.getRealmRoles().contains(profile))
                .toList();
    }


    @Override
    public List<UserRepresentation> searchUserByUsername(String username) {
        List<UserRepresentation> users = KeycloakProvider.getRealmResource()
                .users()
                .searchByUsername(username, true);

        return getUserRepresentations(users);
    }

    private List<UserRepresentation> getUserRepresentations(List<UserRepresentation> users) {
        return users.stream()
                .filter(user -> {
                    List<RoleRepresentation> realmRoleRepresentations = KeycloakProvider.getRealmResource()
                            .users()
                            .get(user.getId())
                            .roles()
                            .realmLevel()
                            .listAll();

                    boolean isNotSuperAdmin = realmRoleRepresentations.stream()
                            .noneMatch(role -> "superadmin".equals(role.getName()));

                    if (isNotSuperAdmin) {
                        List<String> roleNames = realmRoleRepresentations.stream()
                                .map(RoleRepresentation::getName)
                                .filter(roleName -> !roleName.startsWith("default"))
                                .toList();

                        user.setRealmRoles(roleNames);
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String createUser(UserDTO userDTO) {
        int status = 0;
        UsersResource usersResource = KeycloakProvider.getUserResource();

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(userDTO.firstName());
        userRepresentation.setLastName(userDTO.lastName());
        userRepresentation.setEmail(userDTO.email());
        userRepresentation.setUsername(userDTO.username());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);
        if (userDTO.idEmpresa() != null) {
            Map<String, List<String>> attributes = new HashMap<>();
            attributes.put("idEmpresa", Collections.singletonList(userDTO.idEmpresa().toString()));  // Convierte el ID a String si es necesario
            userRepresentation.setAttributes(attributes);
        }
        Response response = usersResource.create(userRepresentation);

        status = response.getStatus();

        if (status == 201) {
            String path = response.getLocation().getPath();
            String userId = path.substring(path.lastIndexOf("/") + 1);


            Usuarios usuario = new Usuarios();
            usuario.setId(userId);  // Guardar el userId de Keycloak
            usuario.setUsername(userDTO.username());
            usuario.setNombre(userDTO.firstName());
            usuario.setApellido(userDTO.lastName());
            usuario.setEmail(userDTO.email());
            usuario.setIdEmpresa(userDTO.idEmpresa());
            usuariosRepository.save(usuario);
            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setTemporary(false);
            credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
            credentialRepresentation.setValue(userDTO.password());

            usersResource.get(userId).resetPassword(credentialRepresentation);

            RealmResource realmResource = KeycloakProvider.getRealmResource();

            List<RoleRepresentation> rolesRepresentation = null;

            if (userDTO.roles() == null || userDTO.roles().isEmpty()) {
                rolesRepresentation = List.of(realmResource.roles().get("user").toRepresentation());
            } else {
                rolesRepresentation = realmResource.roles()
                        .list()
                        .stream()
                        .filter(role -> userDTO.roles()
                                .stream()
                                .anyMatch(roleName -> roleName.equalsIgnoreCase(role.getName())))
                        .toList();
            }

            realmResource.users().get(userId).roles().realmLevel().add(rolesRepresentation);

            return "User created successfully!!";

        } else if (status == 409) {
            log.error("User exist already!");
            return "User exist already!";
        } else {
            log.error("Error creating user, please contact with the administrator.");
            return "Error creating user, please contact with the administrator.";
        }
    }

    @Override
    @Transactional
    public void deleteUser(String userId) {
        try {
            KeycloakProvider.getUserResource()
                    .get(userId)
                    .remove();
        } catch (Exception e) {
            log.error("Error deleting user with id: {}", userId);
            return;
        }
        usuariosRepository.findById(userId).ifPresent(
                usuario -> {
                    usuario.setStatus(false);
                    log.info("User deleted successfully for user: {}", usuario.getUsername());
                }
        );

    }

    @Override
    public void updateUser(String userId, UserDTO userDTO) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.username());
        user.setFirstName(userDTO.firstName());
        user.setLastName(userDTO.lastName());
        user.setEmail(userDTO.email());
        user.setEnabled(true);
        user.setEmailVerified(true);

        if (userDTO.password() != null && !userDTO.password().isEmpty()) {
            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setTemporary(false);
            credentialRepresentation.setType(OAuth2Constants.PASSWORD);
            credentialRepresentation.setValue(userDTO.password());
            user.setCredentials(Collections.singletonList(credentialRepresentation));
        }

        UserResource usersResource = KeycloakProvider.getUserResource().get(userId);
        usersResource.update(user);

        List<RoleRepresentation> currentRoles = usersResource.roles().realmLevel().listAll();
        List<RoleRepresentation> nonDefaultRoles = currentRoles.stream()
                .filter(role -> !role.getName().startsWith("default"))
                .toList();
        usersResource.roles().realmLevel().remove(nonDefaultRoles);

        RealmResource realmResource = KeycloakProvider.getRealmResource();
        List<RoleRepresentation> rolesRepresentation;
        if (userDTO.roles() == null || userDTO.roles().isEmpty()) {
            rolesRepresentation = List.of(realmResource.roles().get("user").toRepresentation());
        } else {
            rolesRepresentation = realmResource.roles()
                    .list()
                    .stream()
                    .filter(role -> userDTO.roles()
                            .stream()
                            .anyMatch(roleName -> roleName.equalsIgnoreCase(role.getName())))
                    .toList();
        }
        usersResource.roles().realmLevel().add(rolesRepresentation);
        Usuarios usuarios = new Usuarios();
        usuarios.setId(userId);
        usuarios.setUsername(userDTO.username());
        usuarios.setNombre(userDTO.firstName());
        usuarios.setApellido(userDTO.lastName());
        usuarios.setEmail(userDTO.email());
        usuarios.setIdEmpresa(userDTO.idEmpresa());
        usuariosRepository.save(usuarios);
        log.info("User updated successfully for user: {}", userDTO.username());
    }
}
