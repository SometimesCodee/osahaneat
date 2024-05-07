package com.example.osahaneatMysql.payload.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderRequest {
    private int resId;
    private int userId;
    private int[] foodIds;

    public OrderRequest() {
    }
}
