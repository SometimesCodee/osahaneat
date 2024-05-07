package com.example.osahaneatMysql.Service.service;

import com.example.osahaneatMysql.DTO.RoleDTO;
import com.example.osahaneatMysql.Entity.Roles;
import com.example.osahaneatMysql.Repository.RoleRepository;
import com.example.osahaneatMysql.Service.imp.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements RoleServiceImp {
    @Autowired
    RoleRepository roleRepository;

    @Cacheable("getAllRoles")
    @Override
    public List<RoleDTO> getAllRoles() {
        List<Roles> roles = roleRepository.findAll();
        List<RoleDTO> list = new ArrayList<>();
        for (Roles role : roles) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(role.getId());
            roleDTO.setRoleName(role.getRoleName());
            roleDTO.setCreatedDate(role.getCreatedDate());
            list.add(roleDTO);
        }
        return list;
    }
}
