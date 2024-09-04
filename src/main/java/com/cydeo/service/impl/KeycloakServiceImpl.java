package com.cydeo.service.impl;

import com.cydeo.config.KeycloakProperties;
import com.cydeo.dto.UserDTO;
import com.cydeo.exception.UserNotFoundException;
import com.cydeo.service.KeycloakService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.keycloak.admin.client.CreatedResponseUtil.getCreatedId;

@Service
public class KeycloakServiceImpl implements KeycloakService {

    private final KeycloakProperties keycloakProperties;

    public KeycloakServiceImpl(KeycloakProperties keycloakProperties) {
        this.keycloakProperties = keycloakProperties;
    }

    @Override
    public void userCreate(UserDTO userDTO) {

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setTemporary(false);
        credential.setValue(userDTO.getPassword());

        UserRepresentation keycloakUser = new UserRepresentation();
        keycloakUser.setUsername(userDTO.getUserName());
        keycloakUser.setFirstName(userDTO.getFirstName());
        keycloakUser.setLastName(userDTO.getLastName());
        keycloakUser.setEmail(userDTO.getUserName());
        keycloakUser.setCredentials(asList(credential));
        keycloakUser.setEmailVerified(true);
        keycloakUser.setEnabled(true);

        try (Keycloak keycloak = getKeycloakInstance()) {

            RealmResource realmResource = keycloak.realm(keycloakProperties.getRealm());
            UsersResource usersResource = realmResource.users();

            Response result = usersResource.create(keycloakUser);

            String userId = getCreatedId(result);
            ClientRepresentation appClient = realmResource.clients()
                    .findByClientId(keycloakProperties.getClientId()).get(0);

            RoleRepresentation userClientRole = realmResource.clients().get(appClient.getId()) //
                    .roles().get(userDTO.getRole().getDescription()).toRepresentation();

            realmResource.users().get(userId).roles().clientLevel(appClient.getId())
                    .add(List.of(userClientRole));
        }

    }

    @Override
    public void userUpdate(UserDTO userDTO) {

        try (Keycloak keycloak = getKeycloakInstance()) {

            RealmResource realmResource = keycloak.realm(keycloakProperties.getRealm());
            UsersResource usersResource = realmResource.users();

            List<UserRepresentation> userRepresentations = usersResource.search(userDTO.getUserName());

            if (userRepresentations.isEmpty()) {
                throw new UserNotFoundException("User does not exist.");
            }

            UserRepresentation keycloakUser = userRepresentations.get(0);

            updateRoles(realmResource, keycloakUser.getId(), userDTO.getRole().getDescription());

            keycloakUser.setFirstName(userDTO.getFirstName());
            keycloakUser.setLastName(userDTO.getLastName());

            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                updatePassword(usersResource, keycloakUser.getId(), userDTO.getPassword());
            }

            usersResource.get(keycloakUser.getId()).update(keycloakUser);

        }

    }

    @Override
    public void delete(String userName) {

        try (Keycloak keycloak = getKeycloakInstance()) {

            RealmResource realmResource = keycloak.realm(keycloakProperties.getRealm());
            UsersResource usersResource = realmResource.users();

            List<UserRepresentation> userRepresentations = usersResource.search(userName);
            String uid = userRepresentations.get(0).getId();

            usersResource.delete(uid);

        }

    }

    @Override
    public String getAccessToken() {
//This class represents the authentication token used by Keycloak
    KeycloakAuthenticationToken keycloakAuthenticationToken=getAuthentication(); //This method (which is defined below) retrieves the current authentication information from the security context.
    return "Bearer "+keycloakAuthenticationToken.getAccount().getKeycloakSecurityContext().getTokenString();
    //The result is a string in the format "Bearer <access_token>", which can be used in HTTP authorization headers.
    }

    private KeycloakAuthenticationToken getAuthentication(){
        //This method essentially retrieves the current authentication token and returns it as a KeycloakAuthenticationToken.
        return (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        //Retrieves the current SecurityContext, which holds the security-related information for the current execution thread.
    }

    private Keycloak getKeycloakInstance() {
        return Keycloak.getInstance(
                keycloakProperties.getAuthServerUrl(),
                keycloakProperties.getMasterRealm(),
                keycloakProperties.getMasterUser(),
                keycloakProperties.getMasterUserPswd(),
                keycloakProperties.getMasterClient());
    }

    private void updateRoles(RealmResource realmResource, String userId, String role) {

        ClientRepresentation appClient = realmResource.clients()
                .findByClientId(keycloakProperties.getClientId()).get(0);

        String clientId = appClient.getId();

        List<RoleRepresentation> existingRoles = realmResource.users().get(userId)
                .roles().clientLevel(clientId).listEffective();
        existingRoles.forEach(existingRole -> realmResource.users().get(userId)
                .roles().clientLevel(clientId).remove(Collections.singletonList(existingRole)));

        RoleRepresentation userClientRole = realmResource.clients().get(clientId)
                .roles().get(role).toRepresentation();

        realmResource.users().get(userId).roles().clientLevel(clientId)
                .add(Collections.singletonList(userClientRole));

    }

    private void updatePassword(UsersResource usersResource, String userId, String newPassword) {

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setTemporary(false);
        credential.setValue(newPassword);

        usersResource.get(userId).resetPassword(credential);

    }

}
