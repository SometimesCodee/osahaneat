package com.example.osahaneatMysql.Service.imp;

import com.example.osahaneatMysql.DTO.RoleDTO;
import com.example.osahaneatMysql.Entity.Roles;

import java.util.List;

public interface RoleServiceImp {
    List<RoleDTO> getAllRoles();
}
