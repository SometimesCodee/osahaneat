package com.example.osahaneatMysql.Controller;

import com.example.osahaneatMysql.Service.imp.OrderServiceImp;
import com.example.osahaneatMysql.payload.Request.OrderRequest;
import com.example.osahaneatMysql.payload.Response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OderController{
    @Autowired
    OrderServiceImp orderServiceImp;

    @PostMapping("addOrder")
    public ResponseEntity<?> addOrder(@RequestBody OrderRequest orderRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(orderServiceImp.addOrder(orderRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
