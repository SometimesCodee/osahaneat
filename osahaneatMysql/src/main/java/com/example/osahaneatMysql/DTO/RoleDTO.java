package com.example.osahaneatMysql.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
public class RoleDTO {
    private int id;
    private String roleName;
    private Date createdDate;
    public RoleDTO() {
    }
}
