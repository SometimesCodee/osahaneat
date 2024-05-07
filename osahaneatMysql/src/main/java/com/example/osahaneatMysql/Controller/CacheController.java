package com.example.osahaneatMysql.Controller;

import com.example.osahaneatMysql.Service.imp.CacheServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cache")
@CrossOrigin("*")
public class CacheController {
    @Autowired
    CacheServiceImp cacheServiceImp;

    @PostMapping("/clearCache")
    public String clearCache() {
        cacheServiceImp.clearAllCaches();
        return "All caches cleared.";
    }
}
