package com.example.osahaneatMysql.Controller;
import com.example.osahaneatMysql.Service.imp.LoginServiceImp;
import com.example.osahaneatMysql.Service.imp.RoleServiceImp;
import com.example.osahaneatMysql.Utils.JwtUtilsHelper;
import com.example.osahaneatMysql.payload.Request.SignUpRequest;
import com.example.osahaneatMysql.payload.Response.ResponseData;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginServiceImp loginServiceImp;
    @Autowired
    RoleServiceImp roleServiceImp;
    @Autowired
    JwtUtilsHelper jwtUtilsHelper;

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password){
        ResponseData responseData = new ResponseData();

//        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        String enscrypted = Encoders.BASE64.encode(secretKey.getEncoded());
//        System.out.println(enscrypted);

        if(loginServiceImp.checkLogin(username, password)){
            String token = jwtUtilsHelper.generateToken(username);
            responseData.setData(token);
        }else{
            responseData.setData("");
            responseData.setSuccess(false);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/getAllRole")
    public ResponseEntity<?> getAllRole(){
        return new ResponseEntity<>(roleServiceImp.getAllRoles(), HttpStatus.OK);
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(loginServiceImp.addUser(signUpRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
