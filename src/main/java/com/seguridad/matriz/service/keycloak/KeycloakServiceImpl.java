package com.seguridad.matriz.service.keycloak;

import com.seguridad.matriz.dto.UserDTO;
import com.seguridad.matriz.provider.KeycloakProvider;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;;

@Service
@Slf4j
public class KeycloakServiceImpl implements KeycloakService{
    public static final   String CLIENT_ID = "matriz-riesgo";

    @Override
    public List<UserRepresentation> findAllUsers() {
        List<UserRepresentation> users = KeycloakProvider.getRealmResource()
                .users()
                .list();

        return getUserRepresentations(users);
    }

    @Override
    public List<UserRepresentation> searchUserByUsername(String username) {
        List<UserRepresentation> users = KeycloakProvider.getRealmResource()
                .users()
                .searchByUsername(username, true);

        return getUserRepresentations(users);
    }

    private List<UserRepresentation> getUserRepresentations(List<UserRepresentation> users) {
        users.forEach(user -> {
            List<RoleRepresentation> realmRoleRepresentations = KeycloakProvider.getRealmResource()
                    .users()
                    .get(user.getId())
                    .roles()
                    .realmLevel()
                    .listAll();

            List<String> roleNames = realmRoleRepresentations.stream()
                    .map(RoleRepresentation::getName)
                    .filter(roleName -> !roleName.startsWith("default"))  // Filtrar roles que no comienzan con "default"
                    .toList();

            user.setRealmRoles(roleNames);
        });

        return users;
    }

    @Override
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

        Response response = usersResource.create(userRepresentation);

        status = response.getStatus();

        if (status == 201) {
            String path = response.getLocation().getPath();
            String userId = path.substring(path.lastIndexOf("/") + 1);

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
    public void deleteUser(String userId) {
        KeycloakProvider.getUserResource()
                .get(userId)
                .remove();

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

        log.info("User updated successfully for user: {}", userDTO.username());
    }
}
