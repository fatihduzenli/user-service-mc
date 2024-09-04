package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO create(UserDTO dto);

    UserDTO readByUserName(String username);
    List<UserDTO> readAllUsers();
    boolean checkByUserName(String username);

    UserDTO update(String username, UserDTO userDTO);
    void delete(String username);

}
