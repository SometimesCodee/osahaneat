package com.example.osahaneatMysql.Service.service;

import com.example.osahaneatMysql.DTO.RoleDTO;
import com.example.osahaneatMysql.DTO.UserDTO;
import com.example.osahaneatMysql.Entity.Roles;
import com.example.osahaneatMysql.Entity.Users;
import com.example.osahaneatMysql.Repository.UserRepository;
import com.example.osahaneatMysql.Service.imp.LoginServiceImp;
import com.example.osahaneatMysql.payload.Request.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements LoginServiceImp {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public boolean checkLogin(String username, String password) {
        Users users = userRepository.findByUserName(username);
        return passwordEncoder.matches(password, users.getPassword());
    }

    @Override
    public boolean addUser(SignUpRequest signUpRequest) {
        Roles role = new Roles();
        role.setId(signUpRequest.getRoleID());

        Users user = new Users();
        user.setFullName(signUpRequest.getFullName());
        user.setUserName(signUpRequest.getUserName());
        user.setPassword(signUpRequest.getPassword());
        user.setRoles(role);
        try {
            userRepository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
