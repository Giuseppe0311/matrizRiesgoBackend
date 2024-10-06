package com.seguridad.matriz.rest.keycloack;

import com.seguridad.matriz.dto.user.UserDTO;
import com.seguridad.matriz.service.keycloak.KeycloakService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/keycloak/user")
@PreAuthorize("hasRole('superadmin_client')")
@RequiredArgsConstructor
public class KeycloakController {

    private final KeycloakService keycloakService;


    @GetMapping("/search")
    public ResponseEntity<?> findAllUsers(){
        return ResponseEntity.ok(keycloakService.findAllUsers());
    }

    @GetMapping("/search/profile/{profile}")
    @PreAuthorize("hasRole('admin_client')")
    public ResponseEntity<?> searchUserByProfile(@PathVariable String profile){
        return ResponseEntity.ok(keycloakService.searchUserByProfile(profile));
    }


    @GetMapping("/search/{username}")
    public ResponseEntity<?> searchUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(keycloakService.searchUserByUsername(username));
    }


    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO userDTO) throws URISyntaxException {
        String response = keycloakService.createUser(userDTO);
        return ResponseEntity.created(new URI("/keycloak/user/create")).body(
                Map.of("message", response)
        );
    }


    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UserDTO userDTO){
        keycloakService.updateUser(userId, userDTO);
        return ResponseEntity.ok(Map.of(
                "message", "User updated successfully"
        ));
    }


    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
        keycloakService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
