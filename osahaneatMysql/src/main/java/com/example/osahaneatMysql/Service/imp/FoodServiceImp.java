package com.example.osahaneatMysql.Service.imp;


import com.example.osahaneatMysql.DTO.FoodDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodServiceImp {
    boolean insertFood(MultipartFile file, String title, boolean isFreeShip, String timeShip, double price, int cateId, String desc);
    List<FoodDTO> getAllFood();
}
