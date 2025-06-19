package com.freshsip.userservice;



public interface UserManagementService {
    UserDTO saveUser(UserDTO userDTO);

    UserDTO loginUser(UserDTO userDTO);

    UserDTO refreshToken(UserDTO userDTO);

    UserDTO getAllUser();

    UserDTO getUserByEmail(String email);

    UserDTO deleteUser(String email);

    UserDTO updateUser(String email, User updateUser);


}
