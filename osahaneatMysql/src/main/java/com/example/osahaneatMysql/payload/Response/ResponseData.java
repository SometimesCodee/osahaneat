package com.example.osahaneatMysql.payload.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseData {
    private int status = 200;
    private boolean success = true;
    private String desc;
    private Object data;

    public ResponseData() {
    }
}
