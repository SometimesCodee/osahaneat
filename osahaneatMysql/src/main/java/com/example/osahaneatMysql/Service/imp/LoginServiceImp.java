package com.example.osahaneatMysql.Service.imp;

import com.example.osahaneatMysql.DTO.RoleDTO;
import com.example.osahaneatMysql.DTO.UserDTO;
import com.example.osahaneatMysql.payload.Request.SignUpRequest;

import java.util.List;

public interface LoginServiceImp {
    boolean checkLogin(String username, String password);
    boolean addUser(SignUpRequest signUpRequest);

}
