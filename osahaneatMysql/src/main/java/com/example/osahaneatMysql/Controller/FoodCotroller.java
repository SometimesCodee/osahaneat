package com.example.osahaneatMysql.Controller;

import com.example.osahaneatMysql.Service.imp.FileServiceImp;
import com.example.osahaneatMysql.Service.imp.FoodServiceImp;
import com.example.osahaneatMysql.payload.Response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/food")
public class FoodCotroller {
    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    FoodServiceImp foodServiceImp;

    @PostMapping("/file/save")
    public ResponseEntity<?> createFood(@RequestParam MultipartFile file,
                                        @RequestParam String title,
                                        @RequestParam String timeShip,
                                        @RequestParam boolean isFreeShip,
                                        @RequestParam double price,
                                        @RequestParam int cateId,
                                        @RequestParam String desc) {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = foodServiceImp.insertFood(file, title, isFreeShip, timeShip, price, cateId, desc);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileFood(@PathVariable String filename) {
        Resource resource = fileServiceImp.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }

    @GetMapping("/getAllFood")
    public ResponseEntity<?> getAllFood() {
        ResponseData responseData = new ResponseData();
        responseData.setData(foodServiceImp.getAllFood());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
