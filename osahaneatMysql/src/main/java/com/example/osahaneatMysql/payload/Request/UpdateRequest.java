package com.example.osahaneatMysql.payload.Request;

import com.example.osahaneatMysql.DTO.RoleDTO;
import com.example.osahaneatMysql.Entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UpdateRequest {
    private int id;
    private String fullName;
    private String password;
    private Roles roles;
}
