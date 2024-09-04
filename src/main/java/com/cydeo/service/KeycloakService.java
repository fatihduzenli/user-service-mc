package com.cydeo.service;

import com.cydeo.dto.UserDTO;

public interface KeycloakService {

    void userCreate(UserDTO userDTO);

    void userUpdate(UserDTO userDTO);

    void delete(String userName);

    String getAccessToken();

}
