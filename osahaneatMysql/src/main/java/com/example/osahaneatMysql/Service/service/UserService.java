package com.example.osahaneatMysql.Service.service;

import com.example.osahaneatMysql.DTO.RoleDTO;
import com.example.osahaneatMysql.DTO.UserDTO;
import com.example.osahaneatMysql.Entity.Users;
import com.example.osahaneatMysql.Repository.UserRepository;
import com.example.osahaneatMysql.Service.imp.UserServiceImp;
import com.example.osahaneatMysql.payload.Request.UpdateRequest;
import com.example.osahaneatMysql.payload.Response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    UserRepository userRepository;

    @Cacheable("getAllUser")
    @Override
    public List<UserDTO> getAllUser(){
        List<Users> list = userRepository.findAll();
        for (Users user : list) {
            System.out.println(user);
        }
        List<UserDTO> userDTOList = new ArrayList<>();
        for (Users user : list) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUserName(user.getUserName());
            userDTO.setPassword(user.getPassword());
            userDTO.setFullName(user.getFullName());

            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(user.getRoles().getId());
            roleDTO.setRoleName(user.getRoles().getRoleName());
            roleDTO.setCreatedDate(user.getRoles().getCreatedDate());

            userDTO.setRole(roleDTO);

            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public List<UserDTO> getUserByRoleName(String roleName) {
        List<Users> list = userRepository.findByRolesRoleName(roleName);
        List<UserDTO> userDTOList = new ArrayList<>();
        for (Users user : list) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUserName(user.getUserName());
            userDTO.setPassword(user.getPassword());
            userDTO.setFullName(user.getFullName());

            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRoleName(user.getRoles().getRoleName());
            roleDTO.setCreatedDate(user.getRoles().getCreatedDate());

            userDTO.setRole(roleDTO);

            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public boolean deleteUserById(int id) {
        try {
            userRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteUserByUsername(String username) {
        try {
            userRepository.deleteByUserName(username);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateUserById(UpdateRequest updateRequest) {
        Optional<Users> user = userRepository.findById(updateRequest.getId());
        if(user.isPresent()) {
            Users userEntity = user.get();
            try {
                // Update user entity with data from UpdateRequest
                userEntity.setPassword(updateRequest.getPassword());
                userEntity.setFullName(updateRequest.getFullName());
                userEntity.setRoles(updateRequest.getRoles());
                // You may also need to update the role if it's a separate entity

                // Save the updated user entity back to the database
                userRepository.save(userEntity);
                return true; // Return true indicating successful update
            } catch (Exception e) {
                // Handle exception if needed
                return false; // Return false indicating update failure
            }
        } else {
            // Handle case where user with given ID is not found
            return false;
        }

    }

}
