package com.seguridad.matriz.provider;

import jakarta.ws.rs.client.ClientBuilder;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.stereotype.Component;

@Component
public class KeycloakProvider {
    private static final String SERVER_URL = "http://localhost:8080";
    private static final String REALM_NAME = "matriz";
    private static final String REALM_MASTER = "master";
    private static final String ADMIN_CLI = "admin-cli";
    private static final String USER_CONSOLE = "user";
    private static final String PASSWORD_CONSOLE = "secret";
    private static final String CLIENT_SECRET = "GOX8CC3l57hUIS5GoTT9uq2EkC1085Z8";

    private static Keycloak keycloakInstance = null;

    private static synchronized Keycloak getKeycloakInstance() {
        if (keycloakInstance == null) {
            keycloakInstance = KeycloakBuilder.builder()
                    .serverUrl(SERVER_URL)
                    .realm(REALM_MASTER)
                    .clientId(ADMIN_CLI)
                    .username(USER_CONSOLE)
                    .password(PASSWORD_CONSOLE)
                    .clientSecret(CLIENT_SECRET)
                    .resteasyClient(
                            ((ResteasyClientBuilderImpl) ClientBuilder.newBuilder())
                                    .connectionPoolSize(10)
                                    .build()
                    )
                    .build();
        }
        return keycloakInstance;
    }

    public static RealmResource getRealmResource() {
        return getKeycloakInstance().realm(REALM_NAME);
    }

    public static UsersResource getUserResource() {
        return getRealmResource().users();
    }
}
