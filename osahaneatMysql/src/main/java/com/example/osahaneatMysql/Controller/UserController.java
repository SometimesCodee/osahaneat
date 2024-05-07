package com.example.osahaneatMysql.Controller;

import com.example.osahaneatMysql.Service.service.UserService;
import com.example.osahaneatMysql.payload.Request.UpdateRequest;
import com.example.osahaneatMysql.payload.Response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userServiceImp;

    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUser(){
        return new ResponseEntity<>(userServiceImp.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("/userByRole")
    public ResponseEntity<?> userByRole(@RequestParam String roleName){
        return new ResponseEntity<>(userServiceImp.getUserByRoleName(roleName), HttpStatus.OK);
    }
    @PostMapping("/deleteAnUserById")
    public ResponseEntity<?> deleteAnUserById(@RequestParam int id){
        ResponseData responseData = new ResponseData();
        responseData.setData(userServiceImp.deleteUserById(id));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/deleteAnUserByName")
    public ResponseEntity<?> deleteAnUserByName(@RequestParam String username){
        ResponseData responseData = new ResponseData();
        responseData.setData(userServiceImp.deleteUserByUsername(username));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUserById(@RequestBody UpdateRequest updateRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(userServiceImp.updateUserById(updateRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
