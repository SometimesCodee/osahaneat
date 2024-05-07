package com.example.osahaneatMysql.Controller;

import com.example.osahaneatMysql.Service.imp.CategoryServiceImp;
import com.example.osahaneatMysql.Service.imp.FileServiceImp;
import com.example.osahaneatMysql.payload.Response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryServiceImp categoryServiceImp;
    @Autowired
    FileServiceImp fileServiceImp;

    @GetMapping("/getHomeCategory")
    public ResponseEntity<?> getHomeCategory() {
        ResponseData responseData = new ResponseData();
        responseData.setData(categoryServiceImp.getCategotyHomePage());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileCategory(@PathVariable String filename) {
        Resource resource = fileServiceImp.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }

    @CacheEvict(value = "getCategoryHomePage" , allEntries = true)
    @GetMapping("/clear-cache")
    public String clearCache() {
        return "Clear cache successfully!";
    }
}
