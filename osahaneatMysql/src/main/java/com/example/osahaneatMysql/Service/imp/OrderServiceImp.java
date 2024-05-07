package com.example.osahaneatMysql.Service.imp;

import com.example.osahaneatMysql.payload.Request.OrderRequest;

public interface OrderServiceImp {
    boolean addOrder(OrderRequest orderRequest);
}
