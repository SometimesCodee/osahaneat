package com.example.osahaneatMysql.Service.imp;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileServiceImp {
    boolean save(MultipartFile file);
    Resource load(String filename);
}
