package com.example.osahaneatMysql.Controller;

import com.example.osahaneatMysql.Service.imp.FileServiceImp;
import com.example.osahaneatMysql.Service.imp.RestaurantServiceImp;
import com.example.osahaneatMysql.Service.service.FileService;
import com.example.osahaneatMysql.payload.Response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@CrossOrigin("*")
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    RestaurantServiceImp restaurantServiceImp;

    //upload file len server
    @PostMapping("/file/save")
    public ResponseEntity<?> createRestaurant(@RequestParam MultipartFile file,
                                              @RequestParam String title,
                                              @RequestParam String subtitle,
                                              @RequestParam String description,
                                              @RequestParam boolean isFreeShip,
                                              @RequestParam String address,
                                              @RequestParam String open_date) {

        ResponseData responseData  = new ResponseData();
        boolean isSuccess = restaurantServiceImp.insertRestaurant(file, title, subtitle, description, isFreeShip, address, open_date);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    //tuong tac voi file
    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        Resource resource = fileServiceImp.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }


    @GetMapping("/getAllRestaurant")
    public ResponseEntity<?> getAllRestaurant() {
        ResponseData responseData = new ResponseData();
        responseData.setData(restaurantServiceImp.getAllRestaurants());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getDetailRestaurant(@RequestParam int id) {
        ResponseData responseData = new ResponseData();
        responseData.setData(restaurantServiceImp.getRestaurantById(id));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
