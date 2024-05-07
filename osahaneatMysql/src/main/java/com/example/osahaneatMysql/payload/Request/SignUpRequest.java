package com.example.osahaneatMysql.payload.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SignUpRequest {
    private String fullName;
    private String userName;
    private String password;
    private int roleID;
}
