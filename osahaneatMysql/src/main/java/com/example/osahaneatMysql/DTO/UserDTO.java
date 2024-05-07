package com.example.osahaneatMysql.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String userName;
    private String password;
    private String fullName;
    private Date createdDate;
    private RoleDTO role;

    public UserDTO() {
    }
}
