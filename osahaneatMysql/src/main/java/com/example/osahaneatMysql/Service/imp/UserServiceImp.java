package com.example.osahaneatMysql.Service.imp;

import com.example.osahaneatMysql.DTO.UserDTO;
import com.example.osahaneatMysql.payload.Request.UpdateRequest;

import java.util.List;

public interface UserServiceImp {
    List<UserDTO> getAllUser();
    List<UserDTO> getUserByRoleName(String roleName);
    boolean deleteUserById(int id);
    boolean deleteUserByUsername(String username);
    boolean updateUserById(UpdateRequest updateRequest);
}
